package com.kleegroup.formation.saml;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import io.vertigo.app.Home;
import io.vertigo.core.param.ParamManager;

/**
 * Servlet home déclenché lors de l'accès à '/'. C'est la servlet "point d'entrée" dans l'application qui permet
 * d'effectuer le dispatch entre la version SAML et la version bouchonné de l'authentification.
 *
 * @author xdurand
 */
public class HomeServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -3885297702081883988L;
	/**
	 *
	 */
	private static final Logger LOG = Logger.getLogger(HomeServlet.class);
	private static final String DESKTOP_HOME = "/VenirList.do";
	private final SamlHelper samlHelper;

	/**
	 * Construct an instance of HomeServlet.
	 */
	public HomeServlet() {
		samlHelper = Home.getApp().getComponentSpace().resolve(SamlHelper.class);
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		final LoginLogoutHelper loginHelper = LoginLogoutHelper.getLoginHelper(request);
		final ParamManager config = Home.getApp().getComponentSpace().resolve(ParamManager.class);
		final boolean samlEnabled = config.getBooleanValue("saml.activer");
		if (!samlEnabled) {
			// Mode Bouchon pour l'authentification
			if (!loginHelper.isUserAuthenticated()) {
				response.sendRedirect("Login.do");
			} else {
				forwardDesktopHome(request, response);
			}
		} else {
			// Mode SAML pour l'authentification
			if (loginHelper.isUserAuthenticated()) {
				forwardDesktopHome(request, response);
			} else {
				// Ce cas arrive uniquement lorsque le JETON SAML est valide mais que la session applicative a expirée.
				// On ré authentifie donc l'utilisateur.
				LOG.error(
						"L'utilisateur a passé la verification de sécurité SAML mais n'est pas authentifié par l'application (pas de session). La session a expiré, réauthentification SAML");
				samlHelper.authnRequest(request, response);
			}
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void forwardDesktopHome(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(DESKTOP_HOME);
		dispatcher.forward(request, response);
	}
}
