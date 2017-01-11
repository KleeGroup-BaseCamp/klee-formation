package com.kleegroup.formation.saml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.parse.XMLParserException;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.Signature;

import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;

import io.vertigo.app.Home;
import io.vertigo.core.param.ParamManager;
import io.vertigo.lang.VSystemException;

/**
 * Servlet pour le Single Logout Service.
 * Permet de consommer une demande de déconnexion venant de l'IDP
 */
public class SLSRequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SLSResponseServlet.class);
	private final SamlHelper samlHelper;

	private final UtilisateurServices utilisateurServices;
	private final ParamManager config;

	/**
	 * Construct an instance of AcsServlet.
	 */
	public SLSRequestServlet() {
		samlHelper = Home.getApp().getComponentSpace().resolve(SamlHelper.class);
		utilisateurServices = Home.getApp().getComponentSpace().resolve(UtilisateurServices.class);
		config = Home.getApp().getComponentSpace().resolve(ParamManager.class);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		final byte samlResponse[] = SamlHelper.getSamlRequestByteArray(request);
		SamlHelper.logHttpRequest(request);
		SamlHelper.logSaml(samlResponse);
		try (InputStream samlIs = new ByteArrayInputStream(samlResponse);) {
			final XMLObject xmlObject = SamlHelper.readSAMLObject(samlIs);
			final LogoutRequest logoutRequest = (LogoutRequest) xmlObject;
			final SamlLogoutRequestChecker samlChecker = SamlHelper.createSamlLogoutRequestChecker();
			samlChecker.checkDestination(logoutRequest);
			samlChecker.checkIssuer(logoutRequest.getIssuer());
			// Validation de la signature
			final Credential validatingCredential = samlHelper.getIdPCredential();
			samlChecker.checkSignature(logoutRequest.getSignature(), validatingCredential);
			if (samlChecker.isSamlValid()) {
				final BasicSAMLMessageContext messageContext = new BasicSAMLMessageContext();
				final HttpServletResponseAdapter outTransport = new HttpServletResponseAdapter(response, true);
				messageContext.setOutboundMessageTransport(outTransport);
				messageContext.setOutboundSAMLMessage(logoutRequest);
				messageContext.setPeerEntityEndpoint(samlHelper.createSingleLogoutResponseEndpoint());
				LOG.info("messageContext.getOutboundSAMLMessage() = " + messageContext.getOutboundSAMLMessage());
				// SP private key
				final Credential credential = SamlHelper
						.createCredentialFromPkcs8(config.getParam("saml.pathKeyPKCS8").getValueAsString());
				final Signature sig = SamlHelper.createSignature(credential);
				logoutRequest.setSignature(sig);
				samlHelper.signAndSendHttpPostBinding(messageContext, sig, credential);
			} else {
				LOG.error("La réponse SAML n'est pas valide");
				// Redirection vers une page d'erreur
				throw new VSystemException("La réponse SAML n'est pas valide");
			}
		} catch (XMLParserException | UnmarshallingException e) {
			LOG.error("Erreur lors de la lecture SAML", e);
			throw new VSystemException("Erreur lors du parsing SAML", e);
		} catch (MetadataProviderException | SecurityException e) {
			LOG.error("Erreur lors de la récupération du certificat");
			throw new VSystemException("Erreur lors de la réupération du certificat", e);
		}
	}
}
