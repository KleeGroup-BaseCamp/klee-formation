package io.vertigo.persona.ldap;

import java.util.List;

import io.vertigo.lang.Manager;

public interface LdapManager extends Manager {

	List<LdapUser> loadAllUsers();

}
