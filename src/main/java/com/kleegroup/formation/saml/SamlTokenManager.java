package com.kleegroup.formation.saml;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import io.vertigo.lang.Component;

/**
 * @author xdurand
 */
public class SamlTokenManager implements Component {

	/**
	 *
	 */
	private static final long serialVersionUID = -7236685001008797605L;
	private static final Set<String> VALID_TOKENS = ConcurrentHashMap.newKeySet();
	/**
	 * Générateur sur 130 bits.
	 */
	private static final int RANDOM_BIT_GENERATOR_SIZE = 512;
	/**
	 * Conversion du token en base 32.
	 */
	private static final int ALPHA_NUMERIC_STRING_BASE = 32;
	/**
	 * Générateur random
	 */
	private final SecureRandom random = new SecureRandom();

	/**
	 * @return
	 */
	public String generateAndRegisterToken() {
		final String token = generateToken();
		addToken(token);
		return token;
	}

	/**
	 * @return
	 */
	private String generateToken() {
		String secureToken;
		do {
			secureToken = new BigInteger(RANDOM_BIT_GENERATOR_SIZE, random).toString(ALPHA_NUMERIC_STRING_BASE);
		} while (VALID_TOKENS.contains(secureToken));
		return secureToken;
	}

	/**
	 * @param token
	 */
	private static void addToken(final String token) {
		VALID_TOKENS.add(token);
	}

	/**
	 * @param tokenToCheck
	 * @return
	 */
	public static boolean isTokenValid(final String tokenToCheck) {
		return VALID_TOKENS.contains(tokenToCheck);
	}

	/**
	 * @param tokenToInvalidate
	 */
	public static void invalidateToken(final String tokenToInvalidate) {
		VALID_TOKENS.remove(tokenToInvalidate);
	}
}
