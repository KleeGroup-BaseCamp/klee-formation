package com.kleegroup.formation.saml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.opensaml.saml2.core.LogoutResponse;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.parse.XMLParserException;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.Credential;

import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;

import io.vertigo.app.Home;
import io.vertigo.lang.VSystemException;

/**
 * Servlet pour le Single Logout Service.
 * Permet de consommer une demande de déconnexion venant de l'IDP
 */
public class SLSResponseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SLSResponseServlet.class);
	private final SamlHelper samlHelper;
	private final UtilisateurServices utilisateurServices;

	/**
	 * Construct an instance of AcsServlet.
	 */
	public SLSResponseServlet() {
		samlHelper = Home.getApp().getComponentSpace().resolve(SamlHelper.class);
		utilisateurServices = Home.getApp().getComponentSpace().resolve(UtilisateurServices.class);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		final byte samlResponse[] = SamlHelper.getSamlResponseByteArray(request);
		SamlHelper.logHttpRequest(request);
		SamlHelper.logSaml(samlResponse);
		try (InputStream samlIs = new ByteArrayInputStream(samlResponse);) {
			final XMLObject xmlObject = SamlHelper.readSAMLObject(samlIs);
			final LogoutResponse logoutResponse = (LogoutResponse) xmlObject;
			final SamlLogoutResponseChecker samlChecker = SamlHelper.createSamlLogoutResponseChecker();
			samlChecker.checkStatus(logoutResponse.getStatus());
			// Validation de la signature
			final Credential validatingCredential = samlHelper.getIdPCredential();
			//samlChecker.checkSignature(logoutResponse.getSignature(), validatingCredential);
			if (samlChecker.isSamlValid()) {
				samlHelper.handleLogoutSucces(request, response);
				LoginLogoutHelper.invalidateSession(request);
				ServletHelper.sendRelativeRedirect(request, response, LoginLogoutHelper.DECONNEXION_SUCCESSFUL_URL);
			} else {
				LOG.error("La réponse SAML n'est pas valide");
				// Redirection vers une page d'erreur
				throw new VSystemException("La réponse SAML n'est pas valide");
			}
		} catch (XMLParserException | UnmarshallingException e) {
			LOG.error("Erreur lors de la lecture SAML", e);
			throw new VSystemException(e, "Erreur lors du parsing SAML", e);
		} catch (MetadataProviderException | SecurityException e) {
			LOG.error("Erreur lors de la récupération du certificat");
			throw new VSystemException(e, "Erreur lors de la réupération du certificat", e);
		}
	}
}
