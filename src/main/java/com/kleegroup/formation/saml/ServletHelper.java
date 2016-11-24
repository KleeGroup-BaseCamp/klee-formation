/**
 * Helper d'envoi de mail
 */
package com.kleegroup.formation.saml;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author xdurand
 */
public final class ServletHelper {

	private final static Logger LOGGER = Logger.getLogger(ServletHelper.class);

	/**
	 * Construct an instance of ServletHelper.
	 */
	private ServletHelper() {
		super();
	}

	/**
	 * @param request
	 * @param response
	 * @param url
	 * @throws IOException
	 */
	public static void sendRelativeRedirect(final HttpServletRequest request, final HttpServletResponse response,
			final String url) throws IOException {
		final String location = request.getContextPath() + '/' + url;
		LOGGER.info("Redirect to :" + location);
		response.sendRedirect(location);
	}
}
