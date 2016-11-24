package com.kleegroup.formation.saml;

import org.apache.log4j.Logger;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.security.SAMLSignatureProfileValidator;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureValidator;
import org.opensaml.xml.validation.ValidationException;

/**
 * @author xdurand
 */
public class SamlLogoutResponseChecker {

	private static final Logger LOG = Logger.getLogger(SamlLogoutResponseChecker.class);
	private boolean statusValid;
	private boolean signatureValid;

	/**
	 * @param status
	 */
	public void checkStatus(final Status status) {
		LOG.info("Response status:" + status.getStatusCode().getValue());
		statusValid = StatusCode.SUCCESS_URI.equals(status.getStatusCode().getValue());
	}

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
	 * @return
	 */
	public boolean isSamlValid() {
		return statusValid;//&& signatureValid;
	}
}
