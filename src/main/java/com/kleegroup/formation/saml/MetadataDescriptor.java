package com.kleegroup.formation.saml;

import org.opensaml.xml.security.credential.Credential;

import io.vertigo.lang.Assertion;

/**
 * @author xdurand
 */
public class MetadataDescriptor {

	private final Credential idpCredential;
	private final String singleSignOnRedirectUrl;
	private final String singleLogOutRequestUrl;
	private final String singleLogOutReponseUrl;
	private final String entityIdIDP;
	private final String recipientSP;
	private final String audienceSP;

	/**
	 * Construct an instance of MetadataDescriptor.
	 *
	 * @param idpCredential
	 * @param singleSignOnRedirectUrl
	 * @param singleLogOutRequestUrl
	 * @param singleLogOutReponseUrl
	 * @param entityIdIDP
	 * @param recipientSP
	 * @param audienceSP
	 */
	public MetadataDescriptor(final Credential idpCredential, final String singleSignOnRedirectUrl,
			final String singleLogOutRequestUrl, final String singleLogOutReponseUrl, final String entityIdIDP,
			final String recipientSP, final String audienceSP) {
		super();
		Assertion.checkNotNull(idpCredential, "Le certificat de l'idp n'est pas valide");
		Assertion.checkNotNull(singleSignOnRedirectUrl, "L'url de redirection au service de SSO n'est pas valide");
		Assertion.checkNotNull(singleLogOutRequestUrl, "L'url de requete au service de SLO n'est pas valide");
		Assertion.checkNotNull(singleLogOutReponseUrl, "L'url de reponse au service de SLO n'est pas valide");
		Assertion.checkNotNull(entityIdIDP, "L'entityId de l'idp n'est pas valide");
		Assertion.checkNotNull(recipientSP, "Le recipient du SP n'est pas valide");
		Assertion.checkNotNull(audienceSP, "L'audience du SP n'est pas valide");
		this.idpCredential = idpCredential;
		this.singleSignOnRedirectUrl = singleSignOnRedirectUrl;
		this.singleLogOutRequestUrl = singleLogOutRequestUrl;
		this.singleLogOutReponseUrl = singleLogOutReponseUrl;
		this.entityIdIDP = entityIdIDP;
		this.recipientSP = recipientSP;
		this.audienceSP = audienceSP;
	}

	/**
	 * Give the value of idpCredential.
	 *
	 * @return the value of idpCredential.
	 */
	public Credential getIdpCredential() {
		return idpCredential;
	}

	/**
	 * Give the value of singleSignOnRedirectUrl.
	 *
	 * @return the value of singleSignOnRedirectUrl.
	 */
	public String getSingleSignOnRedirectUrl() {
		return singleSignOnRedirectUrl;
	}

	/**
	 * Give the value of singleLogOutRequestUrl.
	 *
	 * @return the value of singleLogOutRequestUrl.
	 */
	public String getSingleLogOutRequestUrl() {
		return singleLogOutRequestUrl;
	}

	/**
	 * Give the value of singleLogOutReponseUrl.
	 *
	 * @return the value of singleLogOutReponseUrl.
	 */
	public String getSingleLogOutReponseUrl() {
		return singleLogOutReponseUrl;
	}

	/**
	 * Give the value of entityIdIDP.
	 *
	 * @return the value of entityIdIDP.
	 */
	public String getEntityIdIDP() {
		return entityIdIDP;
	}

	/**
	 * Give the value of recipientSP.
	 *
	 * @return the value of recipientSP.
	 */
	public String getRecipientSP() {
		return recipientSP;
	}

	/**
	 * Give the value of audienceSP.
	 *
	 * @return the value of audienceSP.
	 */
	public String getAudienceSP() {
		return audienceSP;
	}
}
