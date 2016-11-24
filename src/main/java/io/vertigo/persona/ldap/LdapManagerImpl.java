package io.vertigo.persona.ldap;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapInvalidAttributeValueException;
import org.apache.directory.api.ldap.model.message.BindRequest;
import org.apache.directory.api.ldap.model.message.BindRequestImpl;
import org.apache.directory.api.ldap.model.message.BindResponse;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;

import io.vertigo.lang.Assertion;
import io.vertigo.util.BeanUtil;
import io.vertigo.util.ListBuilder;

/**
 * Created by sbernard on 19/03/2015.
 */
public final class LdapManagerImpl implements LdapManager {
	private final Map<String, String> mappingConf = new HashMap<>();

	private final String host;
	private final int port;
	private final String username;
	private final String password;
	private final String myDn;

	@Inject
	public LdapManagerImpl(@Named("host") final String host, @Named("port") final int port, @Named("username") final String username, @Named("password") final String password, @Named("dn") final String dn) {
		Assertion.checkArgNotEmpty(host);
		Assertion.checkNotNull(port);
		Assertion.checkArgNotEmpty(username);
		Assertion.checkNotNull(password);
		Assertion.checkNotNull(dn);
		//-------------
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		myDn = dn;
		initConf();
	}

	private void initConf() {
		mappingConf.put("displayName", "name");
		mappingConf.put("sAMAccountName", "accountName");
		mappingConf.put("+mail", "email");
		mappingConf.put("company", "company");
		mappingConf.put("department", "department");
		mappingConf.put("givenName", "firstName");
		mappingConf.put("physicalDeliveryOfficeName", "office");
		mappingConf.put("telephoneNumber", "phone");
		mappingConf.put("title", "title");
		mappingConf.put("manager", "manager");
		mappingConf.put("image$thumbnailPhoto", "thumbnail");
	}

	@Override
	public List<LdapUser> loadAllUsers() {
		final ListBuilder<LdapUser> listBuilder = new ListBuilder<>();
		try (final LdapConnection connection = new LdapNetworkConnection(host, port)) {
			final BindRequest bindRequest = new BindRequestImpl()
					.setSimple(true)
					.setDn(new Dn(myDn))
					.setName(username)
					.setCredentials(password);
			final BindResponse bindResponse = connection.bind(bindRequest);
			bindResponse.getLdapResult();

			final Dn dn = new Dn("ou=Utilisateurs,dc=klee,dc=lan,dc=net");
			final EntryCursor cursor = connection.search(dn, "(&(objectclass=person)(objectclass=user))", SearchScope.SUBTREE);
			for (final Entry entry : cursor) {
				final Optional<LdapUser> ldapUser = convertLdapEntryToLdapUser(entry);
				if (ldapUser.isPresent()) {
					listBuilder.add(ldapUser.get());
				}
			}
			return listBuilder.build();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	private final Optional<LdapUser> convertLdapEntryToLdapUser(final Entry ldapEntry) throws LdapInvalidAttributeValueException {
		final LdapUser result = new LdapUser();
		for (final Map.Entry<String, String> confEntry : mappingConf.entrySet()) {
			String entryKey = confEntry.getKey();
			boolean valueMandatory = false;
			final String value;
			if (entryKey.startsWith("+")) {
				//value is mandatory
				valueMandatory = true;
				entryKey = entryKey.substring(1);
			}
			if (entryKey.startsWith("image$")) {
				value = getEncodedImage(ldapEntry, entryKey.substring("image$".length()));
			} else {
				value = getAttribute(ldapEntry, entryKey);
			}
			if (valueMandatory && (value == null || value.isEmpty())) {
				return Optional.empty(); //si un champ obligatoire est absent on passe cette entrée
			}
			BeanUtil.setValue(result, confEntry.getValue(), value);
		}
		//gros hack pas top à remplacer par displayName - givenName => name
		result.setName(result.getName().replaceAll(result.getFirstName(), "").trim());
		return Optional.of(result);
	}

	private static final String getAttribute(final Entry entry, final String attribute) throws LdapInvalidAttributeValueException {
		if (entry.containsAttribute(attribute)) {
			return entry.get(attribute).getString();
		}
		return "";
	}

	private static final String getEncodedImage(final Entry entry, final String attribute) throws LdapInvalidAttributeValueException {
		if (entry.containsAttribute(attribute)) {
			return Base64.getUrlEncoder().encodeToString(entry.get(attribute).getBytes());
		}
		return "";
	}

}
