package com.kleegroup.formation.security;

import io.vertigo.app.Home;
import io.vertigo.persona.security.metamodel.Role;


/**
 * Warning. This class is generated automatically !
 *
 * Enum of the roles known by the vertigo application.
 */
public enum Roles {

	/**
	 * Administrateur.
	 */
	R_ADMIN,
	/**
	 * Formatteur.
	 */
	R_FORMATTEUR,
	/**
	 * Responssable.
	 */
	R_RESPONSSABLE,
	/**
	 * Anonyme.
	 */
	R_ANONYMOUS;


	/**
	 * Get the associated role for Vertigo.
	 *
	 * @param code
	 *            role code
	 * @return role
	 */
	public static Role getSecurityRole(final String code) {
		return Home.getApp().getDefinitionSpace().resolve(code, Role.class);
	}

	/**
	 * Get the associated role for Vertigo.
	 *
	 * @return role
	 */
	public Role getSecurityRole() {
		return getSecurityRole(name());
	}

}
