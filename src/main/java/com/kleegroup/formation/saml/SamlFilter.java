package com.kleegroup.formation.saml;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import io.vertigo.app.Home;
import io.vertigo.core.param.ParamManager;
import io.vertigo.lang.Component;
import io.vertigo.vega.impl.servlet.filter.AbstractFilter;

/**
 * @author xdurand
 */
public class SamlFilter extends AbstractFilter implements Component {

	private static final Logger LOGGER = Logger.getLogger(SamlFilter.class);
	private SamlHelper samlHelper;
	private ParamManager config;

	/** {@inheritDoc} */
	@Override
	public void doMyFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		final boolean samlEnabled = config.getBooleanValue("saml.activer");
		if (!samlEnabled) {
			// Lorsque la vérification de sécurité SAML n'est pas activé, on laisse donc passer la requête et nous
			// chainons avec le filtre suivant.
			chain.doFilter(request, response);
		} else {
			final HttpServletRequest httpRequest = (HttpServletRequest) request;
			final HttpServletResponse httpResponse = (HttpServletResponse) response;
			final Cookie[] cookies = httpRequest.getCookies();
			boolean authnCookieValid = false;
			if (cookies != null) {
				final Optional<Cookie> samlCookie = samlHelper.findSamlCookie(httpRequest);
				if (samlCookie.isPresent()) {
					final Cookie cookie = samlCookie.get();
					if (SamlTokenManager.isTokenValid(cookie.getValue())) {
						authnCookieValid = true;
						chain.doFilter(httpRequest, response);
					} else {
						LOGGER.error("Le cookie n'est pas valide : Réauthentification SAML");
					}
				} else {
					LOGGER.error("Aucun cookie d'authentification trouvé : Réauthentification SAML");
				}
			}
			if (!authnCookieValid) {
				samlHelper.authnRequest(httpRequest, httpResponse);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	protected void doInit() {
		samlHelper = Home.getApp().getComponentSpace().resolve(SamlHelper.class);
		config = Home.getApp().getComponentSpace().resolve(ParamManager.class);
	}
}
