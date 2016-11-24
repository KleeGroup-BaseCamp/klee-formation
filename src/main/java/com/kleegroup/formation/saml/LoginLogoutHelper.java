package com.kleegroup.formation.saml;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import io.vertigo.app.Home;
import io.vertigo.persona.security.UserSession;
import io.vertigo.persona.security.VSecurityManager;

/**
 * Helper pour la connexion/déconnexion.
 *
 * @author xdurand
 */
public final class LoginLogoutHelper {

	private static final Logger LOG = Logger.getLogger(LoginLogoutHelper.class);
	private static final String USER_SESSION = "vertigo.webservice.Session";
	private final UserSession user;
	/**
	 * URL pour une deconnexion OK.
	 */
	public static final String DECONNEXION_SUCCESSFUL_URL = "logoutSuccess";

	private LoginLogoutHelper(final HttpServletRequest request) {
		user = obtainUserSession(request);
	}

	private static UserSession obtainUserSession(final HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession().getAttribute(USER_SESSION);
		// Create the user session is needed
		if (userSession == null) {
			LOG.debug("Nouvelle session utilisateur");
			userSession = Home.getApp().getComponentSpace().resolve(VSecurityManager.class).createUserSession();
			request.getSession().setAttribute(USER_SESSION, userSession);
		}
		return userSession;
	}

	/**
	 * Information to know if a user is logged or not.
	 *
	 * @return boolean
	 */
	public boolean isUserAuthenticated() {
		return user.isAuthenticated();
	}

	/**
	 * Get a loginHelper.
	 *
	 * @param request - request
	 * @return LoginHelper
	 */
	public static LoginLogoutHelper getLoginHelper(final HttpServletRequest request) {
		return new LoginLogoutHelper(request);
	}

	/**
	 * @param request
	 */
	public static void invalidateSession(final HttpServletRequest request) {
		final HttpSession httpSession = request.getSession();
		if (httpSession != null) {
			httpSession.invalidate();
		}
	}

	/**
	 * Start session.
	 */
	public void bindSession() {
		Home.getApp().getComponentSpace().resolve(VSecurityManager.class).startCurrentUserSession(user);
	}

	/**
	 * Stop session.
	 */
	public void unbindSession() {
		Home.getApp().getComponentSpace().resolve(VSecurityManager.class).stopCurrentUserSession();
	}
}
