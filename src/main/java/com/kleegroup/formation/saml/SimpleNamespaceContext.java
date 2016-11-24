package com.kleegroup.formation.saml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;

/**
 * @author xdurand
 */
public class SimpleNamespaceContext implements NamespaceContext {

	private final Map<String, String> PREF_MAP = new HashMap<>();

	/**
	 * Construct an instance of SimpleNamespaceContext.
	 *
	 * @param prefMap
	 */
	public SimpleNamespaceContext(final Map<String, String> prefMap) {
		PREF_MAP.putAll(prefMap);
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public String getNamespaceURI(final String prefix) {
		return PREF_MAP.get(prefix);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrefix(final String uri) {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator getPrefixes(final String uri) {
		throw new UnsupportedOperationException();
	}
}
