package com.kleegroup.formation.saml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.joda.time.DateTime;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.IdentifierGenerator;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.impl.SecureRandomIdentifierGenerator;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.encoding.HTTPPostEncoder;
import org.opensaml.saml2.binding.encoding.HTTPRedirectDeflateEncoder;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.SingleLogoutService;
import org.opensaml.saml2.metadata.impl.SingleSignOnServiceImpl;
import org.opensaml.saml2.metadata.provider.DOMMetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.security.MetadataCredentialResolver;
import org.opensaml.security.MetadataCredentialResolverFactory;
import org.opensaml.security.MetadataCriteria;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.io.UnmarshallerFactory;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.parse.BasicParserPool;
import org.opensaml.xml.parse.XMLParserException;
import org.opensaml.xml.security.CriteriaSet;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.criteria.EntityIDCriteria;
import org.opensaml.xml.security.x509.BasicX509Credential;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureConstants;
import org.opensaml.xml.signature.SignatureException;
import org.opensaml.xml.signature.Signer;
import org.opensaml.xml.util.XMLHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import io.vertigo.core.param.ParamManager;
import io.vertigo.lang.Activeable;
import io.vertigo.lang.Component;
import io.vertigo.lang.MessageText;
import io.vertigo.lang.VSystemException;
import io.vertigo.vega.webservice.exception.VSecurityException;

/**
 * @author xdurand
 */
public class SamlHelper implements Component, Activeable {

	private static final Logger LOG = Logger.getLogger(SamlHelper.class);
	private static final BasicParserPool FACTORY = new BasicParserPool();
	private static final String NOM_ATTRIBUT_PRINCIPAL = "nameIdentifier";
	private static final String NOM_ATTRIBUT_MAIL = "mail";
	private static final String XPATH_NODE_DESCRIPTOR = "//md:EntityDescriptor/md:IDPSSODescriptor/";
	private static final String XPATH_BINDING_HTTP_POST = "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST";
	private static final String XPATH_BINDING_HTTP_REDIRECT = "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect";
	private static final String HTTP_POST_BINDING_VELOCITY_TEMPLATE = "/templates/saml2-post-binding.vm";
	/**
	 *
	 */
	private static final XMLObjectBuilderFactory BUILDER_FACTORY = Configuration.getBuilderFactory();
	/**
	 * Nom du cookie SAML utilisé pour GAIA.
	 */
	public static final String SAML_COOKIE = "SAML";
	private static final String RELAY_STATE = "RelayState";
	private static final String SAML_REQUEST = "SAMLRequest";
	private static final String SAML_RESPONSE = "SAMLResponse";
	private Path pathMetadata;
	private MetadataDescriptor metadataDescriptor;
	private VelocityEngine velocityEngine;
	private IdentifierGenerator samlIdGenerator;
	@Inject
	private SamlTokenManager samlTokenManager;
	@Inject
	private ParamManager config;

	/** {@inheritDoc} */
	@Override
	public void start() {
		final boolean samlEnabled = config.getParam("saml.activer").getValueAsBoolean();
		if (!samlEnabled) {
			LOG.info("OpenSAML désactivé. Authentification/Déconnexion en mode bouchon");
		} else {
			try {
				DefaultBootstrap.bootstrap();
				samlIdGenerator = new SecureRandomIdentifierGenerator();
				pathMetadata = Paths.get(config.getParam("saml.pathIDPMetadata").getValueAsString());
				metadataDescriptor = parseIdpMetadata();
				velocityEngine = new VelocityEngine();
				velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
				velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
				velocityEngine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
						"org.apache.velocity.runtime.log.Log4JLogChute");
				velocityEngine.setProperty("runtime.log.logsystem.log4j.logger", SamlHelper.class.getName());
				velocityEngine.init();
			} catch (final ConfigurationException | IOException | XMLParserException | MetadataProviderException
					| SecurityException | XPathExpressionException | NoSuchAlgorithmException e) {
				LOG.error("Initialization impossible d'OpenSAML:", e);
				throw new VSystemException(e, "Initialization impossible d'OpenSAML");
			}
		}
	}

	/**
	 * Méthode générique permettant de builder un Object SAML.
	 *
	 * @param cls le type d'objet à builder.
	 * @param qname le QName associé à lo'bjet à créer.
	 * @return l'objet
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createXMLObject(final Class<T> cls, final QName qname) {
		return (T) BUILDER_FACTORY.getBuilder(qname).buildObject(qname);
	}

	/**
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws XMLParserException
	 * @throws MetadataProviderException
	 * @throws SecurityException
	 * @throws XPathExpressionException
	 */
	private MetadataDescriptor parseIdpMetadata() throws FileNotFoundException, IOException, XMLParserException,
			MetadataProviderException, SecurityException, XPathExpressionException {
		final String entityIdIDP = config.getParam("saml.entityIdIDP").getValueAsString();
		final String recipientSP = config.getParam("saml.recipentSP").getValueAsString();
		final String audienceSP = config.getParam("saml.audienceSP").getValueAsString();
		Element elmDoc;
		try (InputStream is = new FileInputStream(pathMetadata.toFile())) {
			elmDoc = getDocumentElement(is);
		}
		final DOMMetadataProvider idpMetadataProvider = new DOMMetadataProvider(elmDoc);
		idpMetadataProvider.setRequireValidMetadata(true);
		idpMetadataProvider.setParserPool(FACTORY);
		idpMetadataProvider.initialize();
		final Credential idpCredential = getCredential(idpMetadataProvider, entityIdIDP);
		final XPathFactory xpf = XPathFactory.newInstance();
		final XPath xp = xpf.newXPath();
		final HashMap<String, String> prefixesSAML = new HashMap<>();
		prefixesSAML.put("md", "urn:oasis:names:tc:SAML:2.0:metadata");
		prefixesSAML.put("saml", "urn:oasis:names:tc:SAML:2.0:assertion");
		final SimpleNamespaceContext namespaces = new SimpleNamespaceContext(prefixesSAML);
		xp.setNamespaceContext(namespaces);
		final String singleSignOnRedirectUrl = xp.evaluate(XPATH_NODE_DESCRIPTOR + "md:SingleSignOnService[@Binding='"
				+ XPATH_BINDING_HTTP_REDIRECT + "']/@Location", elmDoc);
		final String singleLogOutRequestUrl = xp.evaluate(XPATH_NODE_DESCRIPTOR + "md:SingleLogoutService[@Binding=\""
				+ XPATH_BINDING_HTTP_POST + "\"]/@Location", elmDoc);
		final String singleLogOutReponseUrl = xp.evaluate(XPATH_NODE_DESCRIPTOR + "md:SingleLogoutService[@Binding=\""
				+ XPATH_BINDING_HTTP_POST + "\"]/@ResponseLocation", elmDoc);
		return new MetadataDescriptor(idpCredential, singleSignOnRedirectUrl, singleLogOutRequestUrl,
				singleLogOutReponseUrl, entityIdIDP, recipientSP, audienceSP);
	}

	/**
	 * @param idpMetadataProvider
	 * @param entityIdIDP
	 * @throws SecurityException
	 */
	private static Credential getCredential(final DOMMetadataProvider idpMetadataProvider, final String entityIdIDP)
			throws SecurityException {
		final MetadataCredentialResolverFactory credentialResolverFactory = MetadataCredentialResolverFactory
				.getFactory();
		final MetadataCredentialResolver credentialResolver = credentialResolverFactory
				.getInstance(idpMetadataProvider);
		final CriteriaSet criteriaSet = new CriteriaSet();
		criteriaSet.add(new MetadataCriteria(IDPSSODescriptor.DEFAULT_ELEMENT_NAME, SAMLConstants.SAML20P_NS));
		criteriaSet.add(new EntityIDCriteria(entityIdIDP));
		return credentialResolver.resolveSingle(criteriaSet);
	}

	/** {@inheritDoc} */
	@Override
	public void stop() {
		// Rien a faire
	}

	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MetadataProviderException
	 * @throws SecurityException
	 * @throws XMLParserException
	 */
	public Credential getIdPCredential() throws FileNotFoundException, IOException, MetadataProviderException,
			SecurityException, XMLParserException {
		Element elmDoc;
		try (InputStream is = new FileInputStream(pathMetadata.toFile())) {
			elmDoc = getDocumentElement(is);
		}
		final DOMMetadataProvider idpMetadataProvider = new DOMMetadataProvider(elmDoc);
		idpMetadataProvider.setRequireValidMetadata(true);
		idpMetadataProvider.setParserPool(FACTORY);
		idpMetadataProvider.initialize();
		final MetadataCredentialResolverFactory credentialResolverFactory = MetadataCredentialResolverFactory
				.getFactory();
		final MetadataCredentialResolver credentialResolver = credentialResolverFactory
				.getInstance(idpMetadataProvider);
		final CriteriaSet criteriaSet = new CriteriaSet();
		criteriaSet.add(new MetadataCriteria(IDPSSODescriptor.DEFAULT_ELEMENT_NAME, SAMLConstants.SAML20P_NS));
		criteriaSet.add(new EntityIDCriteria(metadataDescriptor.getEntityIdIDP()));
		return credentialResolver.resolveSingle(criteriaSet);
	}

	/**
	 * @param filePath
	 * @throws XMLParserException
	 * @throws UnmarshallingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static XMLObject readSAML(final Path filePath)
			throws XMLParserException, UnmarshallingException, FileNotFoundException, IOException {
		try (InputStream is = new FileInputStream(filePath.toFile())) {
			return readSAMLObject(is);
		}
	}

	/**
	 * @param is
	 * @return XmlObject
	 * @throws UnmarshallingException
	 * @throws org.opensaml.xml.io.UnmarshallingException
	 * @throws org.opensaml.xml.parse.XMLParserException
	 */
	public static XMLObject readSAMLObject(final InputStream is) throws XMLParserException, UnmarshallingException {
		final Element elmDoc = getDocumentElement(is);
		final UnmarshallerFactory unmarshallerFacto = org.opensaml.xml.Configuration.getUnmarshallerFactory();
		final Unmarshaller unmarshaller = unmarshallerFacto.getUnmarshaller(elmDoc);
		return unmarshaller.unmarshall(elmDoc);
	}

	public AuthnRequest createSAMLAuthnRequest(final String requestedUrl) {
		final AuthnRequest authnRequest = createXMLObject(AuthnRequest.class, AuthnRequest.DEFAULT_ELEMENT_NAME);
		authnRequest.setAssertionConsumerServiceURL(metadataDescriptor.getRecipientSP());
		authnRequest.setID(samlIdGenerator.generateIdentifier());
		authnRequest.setIssueInstant(DateTime.now());
		authnRequest.setDestination(requestedUrl);
		authnRequest.setIssuer(generateIssuer());
		return authnRequest;
	}

	public LogoutRequest createSAMLLogoutRequest(final String userId) {
		final LogoutRequest logoutRequest = createXMLObject(LogoutRequest.class, LogoutRequest.DEFAULT_ELEMENT_NAME);
		final DateTime dateTime = DateTime.now();
		logoutRequest.setIssueInstant(dateTime);
		logoutRequest.setNotOnOrAfter(dateTime.plusMinutes(5));
		logoutRequest.setIssuer(generateIssuer());
		logoutRequest.setID(UUID.randomUUID().toString());
		final NameID nameID = createXMLObject(NameID.class, NameID.DEFAULT_ELEMENT_NAME);
		nameID.setValue(userId);
		final Signature signature = createXMLObject(Signature.class, Signature.DEFAULT_ELEMENT_NAME);
		logoutRequest.setNameID(nameID);
		logoutRequest.setSignature(signature);
		return logoutRequest;
	}

	public static Signature createSignature(final Credential credential) {
		final Signature sig = SamlHelper.createXMLObject(Signature.class, Signature.DEFAULT_ELEMENT_NAME);
		sig.setSigningCredential(credential);
		sig.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA1);
		sig.setCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
		return sig;
	}

	/**
	 * @param messageContext
	 * @param sig
	 * @param credential
	 */
	public void signAndSendHttpPostBinding(final BasicSAMLMessageContext messageContext, final Signature sig,
			final Credential credential) {
		messageContext.setOutboundSAMLMessageSigningCredential(credential);
		final MarshallerFactory factory = org.opensaml.xml.Configuration.getMarshallerFactory();
		final Marshaller marshaller = factory.getMarshaller(messageContext.getOutboundSAMLMessage());
		Node node = null;
		try {
			node = marshaller.marshall(messageContext.getOutboundSAMLMessage());
			Signer.signObject(sig);
		} catch (final MarshallingException e) {
			LOG.error("Impossible de signer la requete de deconnexion.", e);
			throw new VSystemException(e, "Impossible de signer la requete de deconnexion.");
		} catch (final SignatureException e) {
			LOG.error("Impossible de signer la requete de deconnexion.", e);
			throw new VSystemException(e, "Impossible de signer la requete de deconnexion.");
		}
		LOG.info(XMLHelper.prettyPrintXML(node));
		final HTTPPostEncoder encoder = new HTTPPostEncoder(getVelocityEngine(), HTTP_POST_BINDING_VELOCITY_TEMPLATE);
		try {
			encoder.encode(messageContext);
		} catch (final MessageEncodingException e) {
			LOG.error("Impossible de signer la requete de deconnexion.", e);
			throw new VSystemException(e, "Impossible de signer la requete de deconnexion.");
		}
	}

	public static Credential createCredentialFromPkcs8(final String path) throws IOException {
		final Path keyFile = Paths.get(path);
		final byte[] keyData = Files.readAllBytes(keyFile);
		final KeySpec ks = new PKCS8EncodedKeySpec(keyData);
		KeyFactory kf;
		PrivateKey privateKey;
		try {
			kf = KeyFactory.getInstance("RSA");
			privateKey = kf.generatePrivate(ks);
		} catch (final NoSuchAlgorithmException e) {
			LOG.error("Impossible de récuperer le KeyFactory", e);
			throw new VSystemException(e, "Impossible de récuperer le KeyFactory");
		} catch (final InvalidKeySpecException e) {
			LOG.error("Clé privée invalide", e);
			throw new VSystemException(e, "Clé privée invalide");
		}
		final BasicX509Credential credential = new BasicX509Credential();
		credential.setPrivateKey(privateKey);
		return credential;
	}

	private Issuer generateIssuer() {
		final Issuer issuer = createXMLObject(Issuer.class, Issuer.DEFAULT_ELEMENT_NAME);
		issuer.setValue(metadataDescriptor.getAudienceSP());
		return issuer;
	}

	public SingleSignOnServiceImpl createSingleSignOnEndpoint() {
		final QName qName = new QName(SAMLConstants.SAML20MD_NS, "SingleSignOnService", SAMLConstants.SAML20MD_PREFIX);
		final SingleSignOnServiceImpl endpoint = SamlHelper.createXMLObject(SingleSignOnServiceImpl.class, qName);
		endpoint.setLocation(metadataDescriptor.getSingleSignOnRedirectUrl());
		return endpoint;
	}

	public SingleLogoutService createSingleLogoutRequestEndpoint() {
		final QName qName = new QName(SAMLConstants.SAML20MD_NS, "SingleLogoutService", SAMLConstants.SAML20MD_PREFIX);
		final SingleLogoutService endpoint = SamlHelper.createXMLObject(SingleLogoutService.class, qName);
		endpoint.setLocation(metadataDescriptor.getSingleLogOutRequestUrl());
		return endpoint;
	}

	public SingleLogoutService createSingleLogoutResponseEndpoint() {
		final QName qName = new QName(SAMLConstants.SAML20MD_NS, "SingleLogoutService", SAMLConstants.SAML20MD_PREFIX);
		final SingleLogoutService endpoint = SamlHelper.createXMLObject(SingleLogoutService.class, qName);
		endpoint.setLocation(metadataDescriptor.getSingleLogOutReponseUrl());
		return endpoint;
	}

	/**
	 * @param is
	 * @return
	 * @throws XMLParserException
	 */
	private static Element getDocumentElement(final InputStream is) throws XMLParserException {
		final Document document = FACTORY.parse(is);
		return document.getDocumentElement();
	}

	public static String getNameFromPrincipal(final Assertion assertion) {
		final AttributeStatement attributeStatement = assertion.getAttributeStatements().get(0);

		final Optional<String> nivol = attributeStatement.getAttributes().stream()
				.filter(att -> NOM_ATTRIBUT_MAIL.equals(att.getName())).map(att -> att.getAttributeValues())
				.flatMap(List::stream).map(av -> av.getDOM().getTextContent()).findFirst();

		return nivol.orElseThrow(() -> new VSecurityException(new MessageText("NameID", null)));
	}

	public SamlAssertionChecker createSamlAssertionChecker() {
		return new SamlAssertionChecker(metadataDescriptor.getEntityIdIDP(), metadataDescriptor.getAudienceSP(),
				metadataDescriptor.getRecipientSP());
	}

	public static SamlLogoutResponseChecker createSamlLogoutResponseChecker() {
		return new SamlLogoutResponseChecker();
	}

	public static SamlLogoutRequestChecker createSamlLogoutRequestChecker() {
		return new SamlLogoutRequestChecker();
	}

	/**
	 * @param samlResponse
	 */
	public static void logSaml(final byte[] samlResponse) {
		if (LOG.isDebugEnabled()) {
			final String response = new String(samlResponse, StandardCharsets.UTF_8);
			LOG.debug(response);
		}
	}

	/**
	 * @param request
	 */
	public static void logHttpRequest(final HttpServletRequest request) {
		if (LOG.isDebugEnabled()) {
			final Cookie cookies[] = request.getCookies();
			final Enumeration<String> headers = request.getHeaderNames();
			LOG.debug("Headers:");
			while (headers.hasMoreElements()) {
				final String headerName = headers.nextElement();
				final String headerValue = request.getHeader(headerName);
				LOG.debug("Name:" + headerName + " Value:" + headerValue);
			}
			LOG.debug("Cookies:");
			for (final Cookie cookie : cookies) {
				LOG.debug("Name:" + cookie.getName() + " Value:" + cookie.getValue());
			}
		}
	}

	/**
	 * @param req
	 */
	public Optional<Cookie> findSamlCookie(final HttpServletRequest req) {
		final Cookie cookies[] = req.getCookies();
		Cookie samlCookie = null;
		for (final Cookie cookie : cookies) {
			if (SAML_COOKIE.equals(cookie.getName())) {
				samlCookie = cookie;
			}
		}
		return Optional.ofNullable(samlCookie);
	}

	/**
	 * @param req
	 */
	public static String getSamlRequestBase64(final HttpServletRequest req) {
		final String samlRequest = req.getParameter(SAML_REQUEST);
		LOG.info("[Base64] SAMLRequest=" + samlRequest);
		return samlRequest;
	}

	public static byte[] getSamlRequestByteArray(final HttpServletRequest req) {
		final String samlRequestBase64 = getSamlRequestBase64(req);
		return Base64.getDecoder().decode(samlRequestBase64);
	}

	public static String getSamlResponseBase64(final HttpServletRequest req) {
		final String samlResponse = req.getParameter(SAML_RESPONSE);
		if (LOG.isDebugEnabled()) {
			LOG.debug("[Base64] SAMLResponse=" + samlResponse);
		}
		return samlResponse;
	}

	public static byte[] getSamlResponseByteArray(final HttpServletRequest req) {
		final String samlResponseBase64 = getSamlResponseBase64(req);
		return Base64.getDecoder().decode(samlResponseBase64);
	}

	/**
	 * Emission d'un requete d'authentification SAML.
	 * Profile utilisé : HTTP-Redirect.
	 *
	 * @param response
	 * @param httpRequest
	 * @throws IOException
	 */
	public void authnRequest(final HttpServletRequest httpRequest, final HttpServletResponse response)
			throws IOException {
		final AuthnRequest authnRequest = createSAMLAuthnRequest(httpRequest.getRequestURL().toString());
		final HTTPRedirectDeflateEncoder encoder = new HTTPRedirectDeflateEncoder();
		final HttpServletResponseAdapter responseAdapter = new HttpServletResponseAdapter(response, true);
		final BasicSAMLMessageContext<SAMLObject, AuthnRequest, SAMLObject> context = new BasicSAMLMessageContext<>();
		context.setPeerEntityEndpoint(createSingleSignOnEndpoint());
		context.setOutboundSAMLMessage(authnRequest);
		// Pas de signature de la requête d'authentification
		// context.setOutboundSAMLMessageSigningCredential(getSigningCredential());
		context.setOutboundMessageTransport(responseAdapter);
		context.setRelayState(httpRequest.getRequestURL().toString());
		try {
			// La redirection est effectuée par l'encoder:
			encoder.encode(context);
		} catch (final MessageEncodingException e) {
			LOG.error(e.getMessage(), e);
			throw new VSystemException(e, "Erreur lors de la redirection pour la requete SAML");
		}
	}

	/**
	 * @param req
	 * @param res
	 */
	public void handleAssertionSucces(final HttpServletRequest req, final HttpServletResponse res) {
		final String samlCookie = samlTokenManager.generateAndRegisterToken();
		final Cookie cookie = new Cookie(SAML_COOKIE, samlCookie);
		cookie.setHttpOnly(true);
		cookie.setPath(req.getContextPath());
		//cookie.setPath("/");
		//cookie.setSecure(true);
		res.addCookie(cookie);
	}

	/**
	 * Gère la réponse de LogoutReponse en succes envoyé par l'IDP.
	 * Le token SAML est invalidé de la liste des cookie SAML autorisés.
	 * Le cookie SAML HTTP est invalidé.
	 *
	 * @param req
	 * @param res
	 */
	public void handleLogoutSucces(final HttpServletRequest req, final HttpServletResponse res) {
		final Optional<Cookie> samlCookie = findSamlCookie(req);
		if (samlCookie.isPresent()) {
			final Cookie cookie = samlCookie.get();
			SamlTokenManager.invalidateToken(cookie.getValue());
			cookie.setMaxAge(0);
		} else {
			LOG.error("Impossible de trouver le cookie SAML");
		}
	}

	/**
	 * @param request
	 */
	public static Optional<String> extractRequestedUrl(final HttpServletRequest request) {
		//return Optional.<String> ofNullable((String) request.getAttribute(RELAY_STATE));
		return Optional.<String> ofNullable(request.getParameter(RELAY_STATE));
	}

	/**
	 * Give the value of metadataDescriptor.
	 *
	 * @return the value of metadataDescriptor.
	 */
	public MetadataDescriptor getMetadataDescriptor() {
		return metadataDescriptor;
	}

	/**
	 * Give the value of velocityEngine.
	 *
	 * @return the value of velocityEngine.
	 */
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}
}
