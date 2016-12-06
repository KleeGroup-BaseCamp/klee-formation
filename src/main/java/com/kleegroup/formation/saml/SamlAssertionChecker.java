package com.kleegroup.formation.saml;

import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.SubjectConfirmation;
import org.opensaml.saml2.core.SubjectConfirmationData;
import org.opensaml.security.SAMLSignatureProfileValidator;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureValidator;
import org.opensaml.xml.validation.ValidationException;

import io.vertigo.util.StringUtil;

/**
 * @author xdurand
 */
public class SamlAssertionChecker {

	private static final Logger LOG = Logger.getLogger(SamlAssertionChecker.class);
	private final String allowedIssuer;
	private final String allowedAudience;
	private final String allowedRecipient;
	private boolean statusValid;
	private boolean assertionIssuerValid;
	private boolean signatureValid;
	private boolean subjectValid;
	private boolean conditionsValid;
	private boolean authnStatementValid;
	private boolean atributeStatementValid;

	/**
	 * Construct an instance of SamlChecker.
	 *
	 * @param allowedIssuer
	 * @param allowedAudience
	 * @param allowedRecipient
	 */
	public SamlAssertionChecker(final String allowedIssuer, final String allowedAudience,
			final String allowedRecipient) {
		super();
		this.allowedIssuer = allowedIssuer;
		this.allowedAudience = allowedAudience;
		this.allowedRecipient = allowedRecipient;
	}

	/**
	 * @param assertion
	 */
	public void checkAssertionIssuer(final Assertion assertion) {
		final String issuerValue = assertion.getIssuer().getValue();
		LOG.info("Assertion issued by:" + issuerValue);
		assertionIssuerValid = allowedIssuer.equals(issuerValue);
	}

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
	 * @param subject
	 */
	public void checkSubject(final Subject subject) {
		final NameID nameID = subject.getNameID();
		final List<SubjectConfirmation> subjectConfirmation = subject.getSubjectConfirmations();
		LOG.info("Subject name: " + nameID.getValue() + "(Format " + nameID.getFormat() + ")");
		boolean allSubjectConfirmationValide = subjectConfirmation.isEmpty() ? false : true;
		for (final SubjectConfirmation sc : subjectConfirmation) {
			final SubjectConfirmationData scd = sc.getSubjectConfirmationData();
			final DateTime notBefore = scd.getNotBefore();
			final DateTime notOnOrAfter = scd.getNotOnOrAfter();
			final DateTime now = DateTime.now();
			if (notBefore != null) {
				if (!notBefore.isBefore(now)) {
					LOG.info("Le sujet n'est pas encore valide");
					allSubjectConfirmationValide = false;
				}
			}
			if (notOnOrAfter != null) {
				if (!notOnOrAfter.isAfter(now)) {
					LOG.info("Le sujet n'est plus valide");
					allSubjectConfirmationValide = false;
				}
			}
			if (!allowedRecipient.equals(scd.getRecipient())) {
				LOG.info("Le recipient du sujet n'est pas valide");
				allSubjectConfirmationValide = false;
			}
			if (!allSubjectConfirmationValide) {
				break;
			}
		}
		subjectValid = allSubjectConfirmationValide;
	}

	/**
	 * @param list
	 */
	public void checkAuthnStatement(final List<AuthnStatement> list) {
		LOG.info("Authentification context classes found:");
		for (final AuthnStatement authnStatement : list) {
			LOG.info(authnStatement.getAuthnContext().getAuthnContextClassRef().getAuthnContextClassRef());
		}
		authnStatementValid = !list.isEmpty();
	}

	/**
	 * @param conditions
	 */
	public void checkConditions(final Conditions conditions) {
		final DateTime notBefore = conditions.getNotBefore();
		final DateTime notOnOrAfter = conditions.getNotOnOrAfter();
		final DateTime now = DateTime.now();
		boolean conditionsValidity = true;
		if (notBefore != null) {
			conditionsValidity = notBefore.isBefore(now);
		}
		if (notOnOrAfter != null) {
			conditionsValidity = conditionsValidity && notOnOrAfter.isAfter(now);
		}
		if (!conditionsValidity) {
			LOG.info("Les conditions de restriction ne sont plus valide");
		}
		boolean audienceValid = conditions.getAudienceRestrictions().isEmpty() ? false : true;
		for (final AudienceRestriction audienceRestriction : conditions.getAudienceRestrictions()) {
			for (final Audience audience : audienceRestriction.getAudiences()) {
				LOG.info("Audience:" + audience.getAudienceURI());
				if (!allowedAudience.equals(audience.getAudienceURI())) {
					audienceValid = false;
					break;
				}
			}
		}
		if (!audienceValid) {
			LOG.info("L'audience n'est pas valide");
		}
		conditionsValid = conditionsValidity && audienceValid;
	}

	/**
	 * @param attributeStatements
	 */
	public void checkAttributeStatements(final List<AttributeStatement> attributeStatements) {
		boolean allAttributeValid = true;
		boolean hasAttribute = false;
		for (final AttributeStatement attributeStatement : attributeStatements) {
			for (final Attribute attribute : attributeStatement.getAttributes()) {
				allAttributeValid = allAttributeValid && !attribute.getAttributeValues().isEmpty();
				for (final XMLObject attributeValue : attribute.getAttributeValues()) {
					hasAttribute = true;
					if (StringUtil.isEmpty(attributeValue.getDOM().getTextContent())) {
						allAttributeValid = false;
						break;
					}
				}
			}
		}
		if (!hasAttribute) {
			LOG.info("Pas d'attribut SAML trouvé");
		}
		if (!allAttributeValid) {
			LOG.info("Un ou plusieurs attributs ne sont pas valide");
		}
		atributeStatementValid = true;
	}

	/**
	 * @return true is Saml is Valid.
	 */
	public boolean isSamlValid() {
		return statusValid && signatureValid && subjectValid && conditionsValid && authnStatementValid
				&& assertionIssuerValid && atributeStatementValid;
	}
}
