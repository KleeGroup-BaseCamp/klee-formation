package com.kleegroup.formation.domain.administration.utilisateur;

import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;

/**
 * Attention cette classe est générée automatiquement !
 * Objet de données Utilisateur
 */
public final class Utilisateur implements Entity {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Long utiId;
	private String nom;
	private String prenom;
	private String mail;
	private Boolean admin;
	private Boolean responsable;
	private Boolean formateur;
	private io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.administration.utilisateur.Login> login;
	private io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.administration.utilisateur.Role> role;

	/** {@inheritDoc} */
	@Override
	public URI<Utilisateur> getURI() {
		return DtObjectUtil.createURI(this);
	}

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'UTI_ID'.
	 * @return Long utiId <b>Obligatoire</b>
	 */
	@Field(domain = "DO_IDENTIFIANT", type = "ID", required = true, label = "UTI_ID")
	public Long getUtiId() {
		return utiId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'UTI_ID'.
	 * @param utiId Long <b>Obligatoire</b>
	 */
	public void setUtiId(final Long utiId) {
		this.utiId = utiId;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Nom'.
	 * @return String nom
	 */
	@Field(domain = "DO_LIBELLE", label = "Nom")
	public String getNom() {
		return nom;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Nom'.
	 * @param nom String
	 */
	public void setNom(final String nom) {
		this.nom = nom;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Prénom'.
	 * @return String prenom
	 */
	@Field(domain = "DO_LIBELLE", label = "Prénom")
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Prénom'.
	 * @param prenom String
	 */
	public void setPrenom(final String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Courriel'.
	 * @return String mail <b>Obligatoire</b>
	 */
	@Field(domain = "DO_EMAIL", required = true, label = "Courriel")
	public String getMail() {
		return mail;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Courriel'.
	 * @param mail String <b>Obligatoire</b>
	 */
	public void setMail(final String mail) {
		this.mail = mail;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'admin'.
	 * @return Boolean admin
	 */
	@Field(domain = "DO_OUI_NON", label = "admin")
	public Boolean getAdmin() {
		return admin;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'admin'.
	 * @param admin Boolean
	 */
	public void setAdmin(final Boolean admin) {
		this.admin = admin;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'responsable'.
	 * @return Boolean responsable
	 */
	@Field(domain = "DO_OUI_NON", label = "responsable")
	public Boolean getResponsable() {
		return responsable;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'responsable'.
	 * @param responsable Boolean
	 */
	public void setResponsable(final Boolean responsable) {
		this.responsable = responsable;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'formateur'.
	 * @return Boolean formateur
	 */
	@Field(domain = "DO_OUI_NON", label = "formateur")
	public Boolean getFormateur() {
		return formateur;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'formateur'.
	 * @param formateur Boolean
	 */
	public void setFormateur(final Boolean formateur) {
		this.formateur = formateur;
	}

	/**
	 * Champ : COMPUTED.
	 * Récupère la valeur de la propriété calculée 'Prénom Nom'.
	 * @return String prenomNom
	 */
	@Field(domain = "DO_LIBELLE_LONG", type = "COMPUTED", persistent = false, label = "Prénom Nom")
	public String getPrenomNom() {
		final StringBuilder builder = new StringBuilder();
		if (getPrenom() != null) {
			builder.append(getPrenom());
			builder.append(' ');
		}
		builder.append(getNom());
		return builder.toString();
	}

	/**
	 * Champ : COMPUTED.
	 * Récupère la valeur de la propriété calculée 'Nom complet'.
	 * @return String nomComplet
	 */
	@Field(domain = "DO_LIBELLE", type = "COMPUTED", persistent = false, label = "Nom complet")
	public String getNomComplet() {
		final StringBuilder builder = new StringBuilder();
                        builder.append(getPrenom());
                        builder.append(" ");
                        builder.append(getNom());
                        return builder.toString();
	}

	/**
	 * Association : Login.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.administration.utilisateur.Login>
	 */
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.administration.utilisateur.Login> getLoginList() {
//		return this.<com.kleegroup.formation.domain.administration.utilisateur.Login> getList(getLoginListURI());
		// On doit avoir une clé primaire renseignée. Si ce n'est pas le cas, on renvoie une liste vide
		if (io.vertigo.dynamo.domain.util.DtObjectUtil.getId(this) == null) {
			return new io.vertigo.dynamo.domain.model.DtList<>(com.kleegroup.formation.domain.administration.utilisateur.Login.class);
		}
		final io.vertigo.dynamo.domain.model.DtListURI fkDtListURI = getLoginDtListURI();
		io.vertigo.lang.Assertion.checkNotNull(fkDtListURI);
		//---------------------------------------------------------------------
		//On est toujours dans un mode lazy.
		if (login == null) {
			login = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().findAll(fkDtListURI);
		}
		return login;
	}

	/**
	 * Association URI: Login.
	 * @return URI de l'association
	 */
	@io.vertigo.dynamo.domain.stereotype.Association (
			name = "A_UTI_LOG",
			fkFieldName = "UTI_ID",
			primaryDtDefinitionName = "DT_UTILISATEUR",
			primaryIsNavigable = false,
			primaryRole = "Utilisateur",
			primaryLabel = "Utilisateur",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DT_LOGIN",
			foreignIsNavigable = true,
			foreignRole = "Login",
			foreignLabel = "Login",
			foreignMultiplicity = "0..*")
	public io.vertigo.dynamo.domain.metamodel.association.DtListURIForSimpleAssociation getLoginDtListURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createDtListURIForSimpleAssociation(this, "A_UTI_LOG", "Login");
	}

	// Association : Inscription non navigable

	// Association : Session formation non navigable
	/**
	 * Association : Rôle.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.administration.utilisateur.Role>
	 */
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.administration.utilisateur.Role> getRoleList() {
//		return this.<com.kleegroup.formation.domain.administration.utilisateur.Role> getList(getRoleListURI());
		// On doit avoir une clé primaire renseignée. Si ce n'est pas le cas, on renvoie une liste vide
		if (io.vertigo.dynamo.domain.util.DtObjectUtil.getId(this) == null) {
			return new io.vertigo.dynamo.domain.model.DtList<>(com.kleegroup.formation.domain.administration.utilisateur.Role.class);
		}
		final io.vertigo.dynamo.domain.model.DtListURI fkDtListURI = getRoleDtListURI();
		io.vertigo.lang.Assertion.checkNotNull(fkDtListURI);
		//---------------------------------------------------------------------
		//On est toujours dans un mode lazy.
		if (role == null) {
			role = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().findAll(fkDtListURI);
		}
		return role;
	}

	/**
	 * Association URI: Rôle.
	 * @return URI de l'association
	 */
	@io.vertigo.dynamo.domain.stereotype.AssociationNN (
			name = "ANN_UTI_ROL",
			tableName = "UTI_ROL",
			dtDefinitionA = "DT_UTILISATEUR",
			dtDefinitionB = "DT_ROLE",
			navigabilityA = false,
			navigabilityB = true,
			roleA = "Utilisateur",
			roleB = "Role",
			labelA = "Utilisateur",
			labelB = "Rôle"
	)
	public io.vertigo.dynamo.domain.metamodel.association.DtListURIForNNAssociation getRoleDtListURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createDtListURIForNNAssociation(this, "ANN_UTI_ROL", "Role");
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
