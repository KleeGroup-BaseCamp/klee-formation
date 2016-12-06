package com.kleegroup.formation.saml;

import org.apache.log4j.Logger;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.security.SAMLSignatureProfileValidator;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureValidator;
import org.opensaml.xml.validation.ValidationException;

/**
 * @author xdurand
 */
public class SamlLogoutRequestChecker {

	private static final Logger LOG = Logger.getLogger(SamlLogoutRequestChecker.class);
	private boolean destinationValid;
	private boolean issuerValid;
	private boolean signatureValid;

	/**
	 * @param signature
	 * @param validatingCredential
	 */
	public void checkSignature(final Signature signature, final Credential validatingCredential) {
		final SAMLSignatureProfileValidator profileValidator = new SAMLSignatureProfileValidator();
		try {
			profileValidator.validate(signature);
		} catch (final ValidationException e) {
			LOG.error("Erreur de validation de la signature", e);
		}
		final SignatureValidator validator = new SignatureValidator(validatingCredential);
		try {
			validator.validate(signature);
			signatureValid = true;
		} catch (final ValidationException e) {
			LOG.error("Erreur de validation de la signature", e);
			signatureValid = false;
		}
	}

	/**
	 * @param logoutRequest
	 */
	public void checkDestination(final LogoutRequest logoutRequest) {
		// logoutRequest.getDestination()
		destinationValid = true;
	}

	/**
	 * @param issuer
	 */
	public void checkIssuer(final Issuer issuer) {
		issuerValid = true;
	}

	public boolean isSamlValid() {
		return destinationValid && issuerValid && signatureValid;
	}
}
