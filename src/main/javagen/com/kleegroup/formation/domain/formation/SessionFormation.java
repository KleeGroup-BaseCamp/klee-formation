package com.kleegroup.formation.domain.formation;

import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;

/**
 * Attention cette classe est générée automatiquement !
 * Objet de données SessionFormation
 */
public final class SessionFormation implements Entity {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Long sesId;
	private String horaire;
	private Long nbPersonne;
	private String status;
	private String isOuvert;
	private Long duree;
	private java.util.Date dateFin;
	private java.util.Date dateDebut;
	private String lieux;
	private Long forId;
	private String etaCode;
	private Long utiId;
	private String esuCode;
	private io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Inscription> inscription;
	private com.kleegroup.formation.domain.formation.Formation formation;
	private com.kleegroup.formation.domain.formation.Etat etat;
	private io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Horaires> horaires;
	private com.kleegroup.formation.domain.administration.utilisateur.Utilisateur utilisateur;
	private com.kleegroup.formation.domain.formation.EtatSessionUtilisateur etatSessionUtilisateur;

	/** {@inheritDoc} */
	@Override
	public URI<SessionFormation> getURI() {
		return DtObjectUtil.createURI(this);
	}

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'Id'.
	 * @return Long sesId <b>Obligatoire</b>
	 */
	@Field(domain = "DO_IDENTIFIANT", type = "ID", required = true, label = "Id")
	public Long getSesId() {
		return sesId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'Id'.
	 * @param sesId Long <b>Obligatoire</b>
	 */
	public void setSesId(final Long sesId) {
		this.sesId = sesId;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Horaire'.
	 * @return String horaire
	 */
	@Field(domain = "DO_LIBELLE_LONG", label = "Horaire")
	public String getHoraire() {
		return horaire;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Horaire'.
	 * @param horaire String
	 */
	public void setHoraire(final String horaire) {
		this.horaire = horaire;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'NbPersonne'.
	 * @return Long nbPersonne
	 */
	@Field(domain = "DO_QUANTITE", label = "NbPersonne")
	public Long getNbPersonne() {
		return nbPersonne;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'NbPersonne'.
	 * @param nbPersonne Long
	 */
	public void setNbPersonne(final Long nbPersonne) {
		this.nbPersonne = nbPersonne;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Status'.
	 * @return String status
	 */
	@Field(domain = "DO_LIBELLE", label = "Status")
	public String getStatus() {
		return status;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Status'.
	 * @param status String
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Is_ouvert'.
	 * @return String isOuvert
	 */
	@Field(domain = "DO_LIBELLE", label = "Is_ouvert")
	public String getIsOuvert() {
		return isOuvert;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Is_ouvert'.
	 * @param isOuvert String
	 */
	public void setIsOuvert(final String isOuvert) {
		this.isOuvert = isOuvert;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Duree'.
	 * @return Long duree
	 */
	@Field(domain = "DO_IDENTIFIANT", label = "Duree")
	public Long getDuree() {
		return duree;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Duree'.
	 * @param duree Long
	 */
	public void setDuree(final Long duree) {
		this.duree = duree;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'DateFin'.
	 * @return java.util.Date dateFin
	 */
	@Field(domain = "DO_DATE", label = "DateFin")
	public java.util.Date getDateFin() {
		return dateFin;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'DateFin'.
	 * @param dateFin java.util.Date
	 */
	public void setDateFin(final java.util.Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'DateDebut'.
	 * @return java.util.Date dateDebut
	 */
	@Field(domain = "DO_DATE", label = "DateDebut")
	public java.util.Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'DateDebut'.
	 * @param dateDebut java.util.Date
	 */
	public void setDateDebut(final java.util.Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Lieux'.
	 * @return String lieux
	 */
	@Field(domain = "DO_LIBELLE_LONG", label = "Lieux")
	public String getLieux() {
		return lieux;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Lieux'.
	 * @param lieux String
	 */
	public void setLieux(final String lieux) {
		this.lieux = lieux;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Formation'.
	 * @return Long forId <b>Obligatoire</b>
	 */
	@Field(domain = "DO_IDENTIFIANT", type = "FOREIGN_KEY", required = true, label = "Formation")
	public Long getForId() {
		return forId;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Formation'.
	 * @param forId Long <b>Obligatoire</b>
	 */
	public void setForId(final Long forId) {
		this.forId = forId;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Etat'.
	 * @return String etaCode <b>Obligatoire</b>
	 */
	@Field(domain = "DO_CODE", type = "FOREIGN_KEY", required = true, label = "Etat")
	public String getEtaCode() {
		return etaCode;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Etat'.
	 * @param etaCode String <b>Obligatoire</b>
	 */
	public void setEtaCode(final String etaCode) {
		this.etaCode = etaCode;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Utilisateur'.
	 * @return Long utiId <b>Obligatoire</b>
	 */
	@Field(domain = "DO_IDENTIFIANT", type = "FOREIGN_KEY", required = true, label = "Utilisateur")
	public Long getUtiId() {
		return utiId;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Utilisateur'.
	 * @param utiId Long <b>Obligatoire</b>
	 */
	public void setUtiId(final Long utiId) {
		this.utiId = utiId;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Etat session utilisateur'.
	 * @return String esuCode <b>Obligatoire</b>
	 */
	@Field(domain = "DO_CODE", type = "FOREIGN_KEY", required = true, label = "Etat session utilisateur")
	public String getEsuCode() {
		return esuCode;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Etat session utilisateur'.
	 * @param esuCode String <b>Obligatoire</b>
	 */
	public void setEsuCode(final String esuCode) {
		this.esuCode = esuCode;
	}

	/**
	 * Association : Inscription.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Inscription>
	 */
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Inscription> getInscriptionList() {
//		return this.<com.kleegroup.formation.domain.formation.Inscription> getList(getInscriptionListURI());
		// On doit avoir une clé primaire renseignée. Si ce n'est pas le cas, on renvoie une liste vide
		if (io.vertigo.dynamo.domain.util.DtObjectUtil.getId(this) == null) {
			return new io.vertigo.dynamo.domain.model.DtList<>(com.kleegroup.formation.domain.formation.Inscription.class);
		}
		final io.vertigo.dynamo.domain.model.DtListURI fkDtListURI = getInscriptionDtListURI();
		io.vertigo.lang.Assertion.checkNotNull(fkDtListURI);
		//---------------------------------------------------------------------
		//On est toujours dans un mode lazy.
		if (inscription == null) {
			inscription = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().findAll(fkDtListURI);
		}
		return inscription;
	}

	/**
	 * Association URI: Inscription.
	 * @return URI de l'association
	 */
	@io.vertigo.dynamo.domain.stereotype.Association (
			name = "A_LCO_CMD",
			fkFieldName = "SES_ID",
			primaryDtDefinitionName = "DT_SESSION_FORMATION",
			primaryIsNavigable = true,
			primaryRole = "SessionFormation",
			primaryLabel = "Session formation",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DT_INSCRIPTION",
			foreignIsNavigable = true,
			foreignRole = "Inscription",
			foreignLabel = "Inscription",
			foreignMultiplicity = "0..*")
	public io.vertigo.dynamo.domain.metamodel.association.DtListURIForSimpleAssociation getInscriptionDtListURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createDtListURIForSimpleAssociation(this, "A_LCO_CMD", "Inscription");
	}
	/**
	 * Association : Formation.
	 * @return com.kleegroup.formation.domain.formation.Formation
	 */
	public com.kleegroup.formation.domain.formation.Formation getFormation() {
		final io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.Formation> fkURI = getFormationURI();
		if (fkURI == null) {
			return null;
		}
		//On est toujours dans un mode lazy. On s'assure cependant que l'objet associé n'a pas changé
		if (formation == null || !fkURI.equals(formation.getURI())) {
			formation = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().readOne(fkURI);
		}
		return formation;
	}

	/**
	 * Retourne l'URI: Formation.
	 * @return URI de l'association
	 */
	@io.vertigo.dynamo.domain.stereotype.Association (
			name = "A_FOR_SES",
			fkFieldName = "FOR_ID",
			primaryDtDefinitionName = "DT_FORMATION",
			primaryIsNavigable = true,
			primaryRole = "Formation",
			primaryLabel = "Formation",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DT_SESSION_FORMATION",
			foreignIsNavigable = true,
			foreignRole = "SessionFormation",
			foreignLabel = "Session formation",
			foreignMultiplicity = "0..*")
	public io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.Formation> getFormationURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createURI(this, "A_FOR_SES", com.kleegroup.formation.domain.formation.Formation.class);
	}
	/**
	 * Association : Etat.
	 * @return com.kleegroup.formation.domain.formation.Etat
	 */
	public com.kleegroup.formation.domain.formation.Etat getEtat() {
		final io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.Etat> fkURI = getEtatURI();
		if (fkURI == null) {
			return null;
		}
		//On est toujours dans un mode lazy. On s'assure cependant que l'objet associé n'a pas changé
		if (etat == null || !fkURI.equals(etat.getURI())) {
			etat = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().readOne(fkURI);
		}
		return etat;
	}

	/**
	 * Retourne l'URI: Etat.
	 * @return URI de l'association
	 */
	@io.vertigo.dynamo.domain.stereotype.Association (
			name = "A_ASSOCIATION5",
			fkFieldName = "ETA_CODE",
			primaryDtDefinitionName = "DT_ETAT",
			primaryIsNavigable = true,
			primaryRole = "Etat",
			primaryLabel = "Etat",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DT_SESSION_FORMATION",
			foreignIsNavigable = false,
			foreignRole = "SessionFormation",
			foreignLabel = "Session formation",
			foreignMultiplicity = "0..*")
	public io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.Etat> getEtatURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createURI(this, "A_ASSOCIATION5", com.kleegroup.formation.domain.formation.Etat.class);
	}
	/**
	 * Association : Horaires.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Horaires>
	 */
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Horaires> getHorairesList() {
//		return this.<com.kleegroup.formation.domain.formation.Horaires> getList(getHorairesListURI());
		// On doit avoir une clé primaire renseignée. Si ce n'est pas le cas, on renvoie une liste vide
		if (io.vertigo.dynamo.domain.util.DtObjectUtil.getId(this) == null) {
			return new io.vertigo.dynamo.domain.model.DtList<>(com.kleegroup.formation.domain.formation.Horaires.class);
		}
		final io.vertigo.dynamo.domain.model.DtListURI fkDtListURI = getHorairesDtListURI();
		io.vertigo.lang.Assertion.checkNotNull(fkDtListURI);
		//---------------------------------------------------------------------
		//On est toujours dans un mode lazy.
		if (horaires == null) {
			horaires = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().findAll(fkDtListURI);
		}
		return horaires;
	}

	/**
	 * Association URI: Horaires.
	 * @return URI de l'association
	 */
	@io.vertigo.dynamo.domain.stereotype.Association (
			name = "A_ASSOCIATION7",
			fkFieldName = "SES_ID",
			primaryDtDefinitionName = "DT_SESSION_FORMATION",
			primaryIsNavigable = false,
			primaryRole = "SessionFormation",
			primaryLabel = "Session formation",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DT_HORAIRES",
			foreignIsNavigable = true,
			foreignRole = "Horaires",
			foreignLabel = "Horaires",
			foreignMultiplicity = "0..*")
	public io.vertigo.dynamo.domain.metamodel.association.DtListURIForSimpleAssociation getHorairesDtListURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createDtListURIForSimpleAssociation(this, "A_ASSOCIATION7", "Horaires");
	}
	/**
	 * Association : Utilisateur.
	 * @return com.kleegroup.formation.domain.administration.utilisateur.Utilisateur
	 */
	public com.kleegroup.formation.domain.administration.utilisateur.Utilisateur getUtilisateur() {
		final io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> fkURI = getUtilisateurURI();
		if (fkURI == null) {
			return null;
		}
		//On est toujours dans un mode lazy. On s'assure cependant que l'objet associé n'a pas changé
		if (utilisateur == null || !fkURI.equals(utilisateur.getURI())) {
			utilisateur = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().readOne(fkURI);
		}
		return utilisateur;
	}

	/**
	 * Retourne l'URI: Utilisateur.
	 * @return URI de l'association
	 */
	@io.vertigo.dynamo.domain.stereotype.Association (
			name = "A_UTI_SES",
			fkFieldName = "UTI_ID",
			primaryDtDefinitionName = "DT_UTILISATEUR",
			primaryIsNavigable = true,
			primaryRole = "Utilisateur",
			primaryLabel = "Utilisateur",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DT_SESSION_FORMATION",
			foreignIsNavigable = false,
			foreignRole = "SessionFormation",
			foreignLabel = "Session formation",
			foreignMultiplicity = "0..*")
	public io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> getUtilisateurURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createURI(this, "A_UTI_SES", com.kleegroup.formation.domain.administration.utilisateur.Utilisateur.class);
	}
	/**
	 * Association : Etat session utilisateur.
	 * @return com.kleegroup.formation.domain.formation.EtatSessionUtilisateur
	 */
	public com.kleegroup.formation.domain.formation.EtatSessionUtilisateur getEtatSessionUtilisateur() {
		final io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.EtatSessionUtilisateur> fkURI = getEtatSessionUtilisateurURI();
		if (fkURI == null) {
			return null;
		}
		//On est toujours dans un mode lazy. On s'assure cependant que l'objet associé n'a pas changé
		if (etatSessionUtilisateur == null || !fkURI.equals(etatSessionUtilisateur.getURI())) {
			etatSessionUtilisateur = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().readOne(fkURI);
		}
		return etatSessionUtilisateur;
	}

	/**
	 * Retourne l'URI: Etat session utilisateur.
	 * @return URI de l'association
	 */
	@io.vertigo.dynamo.domain.stereotype.Association (
			name = "A_ESUSES",
			fkFieldName = "ESU_CODE",
			primaryDtDefinitionName = "DT_ETAT_SESSION_UTILISATEUR",
			primaryIsNavigable = true,
			primaryRole = "EtatSessionUtilisateur",
			primaryLabel = "Etat session utilisateur",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DT_SESSION_FORMATION",
			foreignIsNavigable = false,
			foreignRole = "SessionFormation",
			foreignLabel = "Session formation",
			foreignMultiplicity = "0..*")
	public io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.EtatSessionUtilisateur> getEtatSessionUtilisateurURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createURI(this, "A_ESUSES", com.kleegroup.formation.domain.formation.EtatSessionUtilisateur.class);
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
