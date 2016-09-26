package com.kleegroup.formation.services.util;

import java.util.HashSet;
import java.util.Set;

import com.kleegroup.formation.resources.Resources;
import com.kleegroup.formation.security.Role;

import io.vertigo.app.Home;
import io.vertigo.lang.MessageText;
import io.vertigo.persona.security.VSecurityManager;
import io.vertigo.struts2.core.ComponentRef;
import io.vertigo.struts2.exception.VSecurityException;

/**
 * Utilitaire de vérification des accés sécurisés.
 * @author npiedeloup
 * @version $Id: SecurityUtil.java,v 1.2 2014/07/07 16:40:26 pchretien Exp $
 */
public final class SecurityUtil {

	private static ComponentRef<VSecurityManager> securityManager = ComponentRef.makeLazyRef(VSecurityManager.class);

	/**
	 * Vérifie l'accés à une ressource sécurisé.
	 * La class de l'objet détermine la SecurityResourceFactory à utiliser.
	 * @param value Object à tester
	 * @param operation Type d'opération à tester
	 * @return Si l'opération est autorisée sur cet Objet
	 */
	public static boolean hasAccess(final Object value, final String operation) {
		return securityManager.get().isAuthorized(value.getClass().getSimpleName(), value, operation);
	}

	/**
	 * Vérifie l'accés à une ressource sécurisé.
	 * La class de l'objet détermine la SecurityResourceFactory à utiliser.
	 * @param value Object à tester
	 * @param operation Type d'opération à tester
	 * @throws VSecurityException Si l'opération n'est pas autorisée sur cet Objet
	 */
	public static void checkAccess(final Object value, final String operation) throws VSecurityException {
		if (!hasAccess(value, operation)) {
			throw new VSecurityException("Vous ne posséder pas les droits suffisant pour réaliser cette opération. (" + operation + " sur " + value.getClass().getSimpleName() + ")");
		}
	}

	public static void checkRole(final Role... authorizedRoles) {
		final VSecurityManager securityManager = Home.getApp().getComponentSpace().resolve(VSecurityManager.class);
		final Set<io.vertigo.persona.security.metamodel.Role> roles = new HashSet<>();
		for (final Role authorizedRole : authorizedRoles) {
			roles.add(Home.getApp().getDefinitionSpace().resolve(authorizedRole.name(), io.vertigo.persona.security.metamodel.Role.class));
		}
		if (!securityManager.hasRole(securityManager.getCurrentUserSession().get(), roles)) {
			throw new VSecurityException(new MessageText(Resources.DROIT_PAS_SUFFISANT).getDisplay());

			//throw new VSecurityException("Vous n'avez pas les droits suffisants");
			//throw new VUserException(new MessageText(Resources.DROIT_PAS_SUFFISANT);
			//throw new VSecurityException();

		}
	}

	public static boolean hasRole(final Role... authorizedRoles) {
		final VSecurityManager securityManager = Home.getApp().getComponentSpace().resolve(VSecurityManager.class);
		final Set<io.vertigo.persona.security.metamodel.Role> roles = new HashSet<>();
		for (final Role authorizedRole : authorizedRoles) {
			roles.add(Home.getApp().getDefinitionSpace().resolve(authorizedRole.name(), io.vertigo.persona.security.metamodel.Role.class));
		}
		if (securityManager.hasRole(securityManager.getCurrentUserSession().get(), roles)) {
			return true;

		}
		return false;
	}

}
