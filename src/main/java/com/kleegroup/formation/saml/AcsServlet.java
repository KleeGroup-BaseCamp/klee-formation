package com.kleegroup.formation.saml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.parse.XMLParserException;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.Credential;

import com.kleegroup.formation.services.KleeFormationUserSession;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;

import io.vertigo.app.Home;
import io.vertigo.lang.VSystemException;
import io.vertigo.persona.security.VSecurityManager;

/**
 * Servlet pour l'Assertion Consumer Service.
 * Permet de consommer une assertion SAML (provenant du BIG-IP F5)
 */
public class AcsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AcsServlet.class);
	private final SamlHelper samlHelper;
	private final UtilisateurServices utilisateurServices;

	/**
	 * Construct an instance of AcsServlet.
	 */
	public AcsServlet() {
		samlHelper = Home.getApp().getComponentSpace().resolve(SamlHelper.class);
		utilisateurServices = Home.getApp().getComponentSpace().resolve(UtilisateurServices.class);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		final byte samlResponse[] = SamlHelper.getSamlResponseByteArray(request);
		SamlHelper.logHttpRequest(request);
		SamlHelper.logSaml(samlResponse);
		try (InputStream samlIs = new ByteArrayInputStream(samlResponse);) {
			final XMLObject xmlObject = SamlHelper.readSAMLObject(samlIs);
			final Response reponse = (Response) xmlObject;
			final SamlAssertionChecker samlChecker = samlHelper.createSamlAssertionChecker();
			samlChecker.checkStatus(reponse.getStatus());
			// Validation de la signature du profile
			if (reponse.getAssertions().isEmpty() || reponse.getAssertions().size() > 1) {
				LOG.error("La réponse d'authentification SAML doit contenir une et une seule assertion SAML");
				throw new VSystemException("Erreur lors de la lecture SAML");
			}
			final Assertion assertion = reponse.getAssertions().iterator().next();
			samlChecker.checkAssertionIssuer(assertion);
			// Validation de la signature
			final Credential validatingCredential = samlHelper.getIdPCredential();
			samlChecker.checkSignature(assertion.getSignature(), validatingCredential);
			samlChecker.checkSubject(assertion.getSubject());
			samlChecker.checkConditions(assertion.getConditions());
			samlChecker.checkAuthnStatement(assertion.getAuthnStatements());
			samlChecker.checkAttributeStatements(assertion.getAttributeStatements());
			if (samlChecker.isSamlValid()) {
				LOG.info("La réponse SAML est valide");
				samlHelper.handleAssertionSucces(request, response);
				final LoginLogoutHelper loginHelper = LoginLogoutHelper.getLoginHelper(request);
				try {
					// Bind userSession to SecurityManager
					LOG.debug("Binding de la session");
					//loginHelper.bindSession();
					final VSecurityManager vSecurityManager = Home.getApp().getComponentSpace()
							.resolve(VSecurityManager.class);
					LOG.debug("Récupération de la session utilisateur");
					final Optional<KleeFormationUserSession> opt = vSecurityManager.getCurrentUserSession();
					if (LOG.isDebugEnabled()) {
						if (opt.isPresent()) {
							LOG.debug("Récupération de la session utilisateur : OK ");
						} else {
							LOG.debug("Récupération de la session utilisateur : KO ");
						}
					}
					final KleeFormationUserSession session = opt.get();
					session.authenticate();

					// Authentification du user dans l'application à partir de son nivol
					final String name = SamlHelper.getNameFromPrincipal(assertion);

					if (LOG.isDebugEnabled()) {
						LOG.debug("name: " + name);
					}
					/*
					final UtilisateurLogin ul = new UtilisateurLogin();
					ul.setLogin(name);
					utilisateurServices.connecterUtilisateur(ul);
					*/
					utilisateurServices.connecterUtilisateur(name, true);

					if (LOG.isDebugEnabled()) {
						LOG.debug("Authentification de l'utilisateur " + name + " effectué.");
					}
					final Optional<String> urltoRedirect = SamlHelper.extractRequestedUrl(request);
					if (LOG.isDebugEnabled()) {
						LOG.debug("Redirection de l'utilisateur sur " + urltoRedirect.orElse("/"));
					}
					response.sendRedirect(urltoRedirect.orElse("/"));
				} catch (final Exception e) {
					LOG.error("Erreur lors de la configuration de la session utilisateur.", e);
					throw e;
				} finally {
					loginHelper.unbindSession();
					LOG.debug("Unbind de la session OK");
				}
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
