package com.kleegroup.formation.saml;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;

import io.vertigo.app.Home;

/**
 * Servlet récuperant les données postées pour l'authentification bouchon.
 *
 * @author xdurand
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		final LoginLogoutHelper loginHelper = LoginLogoutHelper.getLoginHelper(request);
		try {
			final UtilisateurServices utilisateurServices = Home.getApp().getComponentSpace()
					.resolve(UtilisateurServices.class);
			// Bind userSession to SecurityManager
			loginHelper.bindSession();
			final String login = request.getParameter("login");

			//utilisateurServices.connecterUtilisateur(login);
			if (loginHelper.isUserAuthenticated()) {
				response.sendRedirect("");
			}
		} finally {
			// Unbind userSession to SecurityManager
			loginHelper.unbindSession();
		}
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		response.sendRedirect("login");
	}
}
