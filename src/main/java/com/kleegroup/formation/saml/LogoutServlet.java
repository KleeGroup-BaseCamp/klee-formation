package com.kleegroup.formation.saml;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.Signature;

import com.kleegroup.formation.services.KleeFormationUserSession;

import io.vertigo.app.Home;
import io.vertigo.core.param.ParamManager;
import io.vertigo.persona.security.VSecurityManager;

/**
 * Servlet pour l'authentification.
 *
 * @author xdurand
 */
public class LogoutServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LogoutServlet.class);

	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		final LoginLogoutHelper loginHelper = LoginLogoutHelper.getLoginHelper(request);
		try {
			//loginHelper.bindSession();
			final VSecurityManager securityManager = Home.getApp().getComponentSpace().resolve(VSecurityManager.class);
			final KleeFormationUserSession session = (KleeFormationUserSession) securityManager.getCurrentUserSession().get();
			final ParamManager config = Home.getApp().getComponentSpace().resolve(ParamManager.class);
			final boolean samlEnabled = config.getBooleanValue("saml.activer");
			if (!samlEnabled) {
				LoginLogoutHelper.invalidateSession(request);
				ServletHelper.sendRelativeRedirect(request, response, LoginLogoutHelper.DECONNEXION_SUCCESSFUL_URL);
			} else {
				LOG.info("Création de la requête SAML de deconnexion");
				final SamlHelper samlHelper = Home.getApp().getComponentSpace().resolve(SamlHelper.class);
				// Emission de la requete SAML de déconnexion.
				final LogoutRequest logoutRequest = samlHelper
						.createSAMLLogoutRequest(session.getUtilisateur().getMail());
				final BasicSAMLMessageContext messageContext = new BasicSAMLMessageContext();
				final HttpServletResponseAdapter outTransport = new HttpServletResponseAdapter(response, true);
				messageContext.setOutboundMessageTransport(outTransport);
				messageContext.setOutboundSAMLMessage(logoutRequest);
				messageContext.setPeerEntityEndpoint(samlHelper.createSingleLogoutRequestEndpoint());
				LOG.info("messageContext.getOutboundSAMLMessage() = " + messageContext.getOutboundSAMLMessage());
				// SP private key
				final Credential credential = SamlHelper
						.createCredentialFromPkcs8(config.getStringValue("saml.pathKeyPKCS8"));
				final Signature sig = SamlHelper.createSignature(credential);
				logoutRequest.setSignature(sig);
				samlHelper.signAndSendHttpPostBinding(messageContext, sig, credential);
			}
		} catch (final Exception e) {
			LOG.error("Erreur lors de la déconnexion.", e);
		} finally {
			loginHelper.unbindSession();
		}
	}
}
