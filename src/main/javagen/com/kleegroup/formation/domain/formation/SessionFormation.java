package com.kleegroup.formation.domain.formation;

import io.vertigo.dynamo.domain.stereotype.DtDefinition;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.model.DtObject;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
/**
 * Attention cette classe est générée automatiquement !
 * Objet de données SessionFormation
 */
@DtDefinition
public final class SessionFormation implements DtObject {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Long sesId;
	private String horaire;
	private Long nbPersonne;
	private String formationName;
	private String status;
	private String commentaire;
	private String niveau;
	private String isOuvert;
	private String formateur;
	private java.math.BigDecimal satisfaction;
	private java.math.BigDecimal i;
	private Long duree;
	private java.util.Date dateFin;
	private java.util.Date dateDebut;
	private Long forId;
	private Long utiId;
	private io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Inscription> inscription;
	private com.kleegroup.formation.domain.formation.Formation formation;
	private io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Etat> etat;
	private io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Horaires> horaires;
	private com.kleegroup.formation.domain.administration.utilisateur.Utilisateur utilisateur;
	private io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.EtatSessionUtilisateur> etatSessionUtilisateur;

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
	 * Récupère la valeur de la propriété 'FormationName'. 
	 * @return String formationName 
	 */
	@Field(domain = "DO_LIBELLE", label = "FormationName")
	public String getFormationName() {
		return formationName;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'FormationName'.
	 * @param formationName String 
	 */
	public void setFormationName(final String formationName) {
		this.formationName = formationName;
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
	 * Récupère la valeur de la propriété 'Commentaire'. 
	 * @return String commentaire 
	 */
	@Field(domain = "DO_LIBELLE_LONG", label = "Commentaire")
	public String getCommentaire() {
		return commentaire;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Commentaire'.
	 * @param commentaire String 
	 */
	public void setCommentaire(final String commentaire) {
		this.commentaire = commentaire;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Niveau'. 
	 * @return String niveau 
	 */
	@Field(domain = "DO_LIBELLE_COURT", label = "Niveau")
	public String getNiveau() {
		return niveau;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Niveau'.
	 * @param niveau String 
	 */
	public void setNiveau(final String niveau) {
		this.niveau = niveau;
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
	 * Récupère la valeur de la propriété 'Formateur'. 
	 * @return String formateur 
	 */
	@Field(domain = "DO_LIBELLE_LONG", label = "Formateur")
	public String getFormateur() {
		return formateur;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Formateur'.
	 * @param formateur String 
	 */
	public void setFormateur(final String formateur) {
		this.formateur = formateur;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Satisfaction'. 
	 * @return java.math.BigDecimal satisfaction 
	 */
	@Field(domain = "DO_STATIQUE", label = "Satisfaction")
	public java.math.BigDecimal getSatisfaction() {
		return satisfaction;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Satisfaction'.
	 * @param satisfaction java.math.BigDecimal 
	 */
	public void setSatisfaction(final java.math.BigDecimal satisfaction) {
		this.satisfaction = satisfaction;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'i'. 
	 * @return java.math.BigDecimal i 
	 */
	@Field(domain = "DO_STATIQUE", label = "i")
	public java.math.BigDecimal getI() {
		return i;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'i'.
	 * @param i java.math.BigDecimal 
	 */
	public void setI(final java.math.BigDecimal i) {
		this.i = i;
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
	@Field(domain = "DO_DATE_MINUTE", label = "DateFin")
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
	@Field(domain = "DO_DATE_MINUTE", label = "DateDebut")
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
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Formation'. 
	 * @return Long forId 
	 */
	@Field(domain = "DO_IDENTIFIANT", type = "FOREIGN_KEY", label = "Formation")
	public Long getForId() {
		return forId;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Formation'.
	 * @param forId Long 
	 */
	public void setForId(final Long forId) {
		this.forId = forId;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Utilisateur'. 
	 * @return Long utiId 
	 */
	@Field(domain = "DO_IDENTIFIANT", type = "FOREIGN_KEY", label = "Utilisateur")
	public Long getUtiId() {
		return utiId;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Utilisateur'.
	 * @param utiId Long 
	 */
	public void setUtiId(final Long utiId) {
		this.utiId = utiId;
	}

	/**
	 * Association : Inscription.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Inscription>
	 */
    @io.vertigo.dynamo.domain.stereotype.Association (
    	name = "A_LCO_CMD",
    	fkFieldName = "SES_ID",
    	primaryDtDefinitionName = "DT_SESSION_FORMATION",
    	primaryIsNavigable = true,
    	primaryRole = "SessionFormation",
    	primaryLabel = "Session formation",
    	primaryMultiplicity = "0..1",
    	foreignDtDefinitionName = "DT_INSCRIPTION",
    	foreignIsNavigable = true,
    	foreignRole = "Inscription",
    	foreignLabel = "Inscription",
    	foreignMultiplicity = "0..*"
    )
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
    	primaryMultiplicity = "0..1",
    	foreignDtDefinitionName = "DT_INSCRIPTION",
    	foreignIsNavigable = true,
    	foreignRole = "Inscription",
    	foreignLabel = "Inscription",
    	foreignMultiplicity = "0..*"
    )
	public io.vertigo.dynamo.domain.metamodel.association.DtListURIForSimpleAssociation getInscriptionDtListURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createDtListURIForSimpleAssociation(this, "A_LCO_CMD", "Inscription");
	}
	/**
	 * Association : Formation.
	 * @return com.kleegroup.formation.domain.formation.Formation
	 */
    @io.vertigo.dynamo.domain.stereotype.Association (
    	name = "A_FOR_SES",
    	fkFieldName = "FOR_ID",
    	primaryDtDefinitionName = "DT_FORMATION",
    	primaryIsNavigable = true,
    	primaryRole = "Formation",
    	primaryLabel = "Formation",
    	primaryMultiplicity = "0..1",
    	foreignDtDefinitionName = "DT_SESSION_FORMATION",
    	foreignIsNavigable = true,
    	foreignRole = "SessionFormation",
    	foreignLabel = "Session formation",
    	foreignMultiplicity = "0..*"
    )
	public com.kleegroup.formation.domain.formation.Formation getFormation() {
		final io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.Formation> fkURI = getFormationURI();
		if (fkURI == null) {
			return null;
		}
		//On est toujours dans un mode lazy. On s'assure cependant que l'objet associé n'a pas changé
		if (formation != null) {
			// On s'assure que l'objet correspond à la bonne clé
			final io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.Formation> uri;
			uri = new io.vertigo.dynamo.domain.model.URI<>(io.vertigo.dynamo.domain.util.DtObjectUtil.findDtDefinition(formation), io.vertigo.dynamo.domain.util.DtObjectUtil.getId(formation));
			if (!fkURI.urn().equals(uri.urn())) {
				formation = null;
			}
		}		
		if (formation == null) {
			formation = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().read(fkURI);
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
    	primaryMultiplicity = "0..1",
    	foreignDtDefinitionName = "DT_SESSION_FORMATION",
    	foreignIsNavigable = true,
    	foreignRole = "SessionFormation",
    	foreignLabel = "Session formation",
    	foreignMultiplicity = "0..*"
    )
	public io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.Formation> getFormationURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createURI(this, "A_FOR_SES", com.kleegroup.formation.domain.formation.Formation.class);
	}
	/**
	 * Association : Etat.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Etat>
	 */
    @io.vertigo.dynamo.domain.stereotype.Association (
    	name = "A_ASSOCIATION5",
    	fkFieldName = "SES_ID",
    	primaryDtDefinitionName = "DT_SESSION_FORMATION",
    	primaryIsNavigable = false,
    	primaryRole = "SessionFormation",
    	primaryLabel = "Session formation",
    	primaryMultiplicity = "1..1",
    	foreignDtDefinitionName = "DT_ETAT",
    	foreignIsNavigable = true,
    	foreignRole = "Etat",
    	foreignLabel = "Etat",
    	foreignMultiplicity = "0..*"
    )
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Etat> getEtatList() {
//		return this.<com.kleegroup.formation.domain.formation.Etat> getList(getEtatListURI());
		// On doit avoir une clé primaire renseignée. Si ce n'est pas le cas, on renvoie une liste vide
		if (io.vertigo.dynamo.domain.util.DtObjectUtil.getId(this) == null) {
			return new io.vertigo.dynamo.domain.model.DtList<>(com.kleegroup.formation.domain.formation.Etat.class);
		}
		final io.vertigo.dynamo.domain.model.DtListURI fkDtListURI = getEtatDtListURI();
		io.vertigo.lang.Assertion.checkNotNull(fkDtListURI);
		//---------------------------------------------------------------------
		//On est toujours dans un mode lazy.
		if (etat == null) {
			etat = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().findAll(fkDtListURI);
		}
		return etat;
	}

	/**
	 * Association URI: Etat.
	 * @return URI de l'association
	 */
    @io.vertigo.dynamo.domain.stereotype.Association (
    	name = "A_ASSOCIATION5",
    	fkFieldName = "SES_ID",
    	primaryDtDefinitionName = "DT_SESSION_FORMATION",
    	primaryIsNavigable = false,
    	primaryRole = "SessionFormation",
    	primaryLabel = "Session formation",
    	primaryMultiplicity = "1..1",
    	foreignDtDefinitionName = "DT_ETAT",
    	foreignIsNavigable = true,
    	foreignRole = "Etat",
    	foreignLabel = "Etat",
    	foreignMultiplicity = "0..*"
    )
	public io.vertigo.dynamo.domain.metamodel.association.DtListURIForSimpleAssociation getEtatDtListURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createDtListURIForSimpleAssociation(this, "A_ASSOCIATION5", "Etat");
	}
	/**
	 * Association : Horaires.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Horaires>
	 */
    @io.vertigo.dynamo.domain.stereotype.Association (
    	name = "A_ASSOCIATION7",
    	fkFieldName = "SES_ID",
    	primaryDtDefinitionName = "DT_SESSION_FORMATION",
    	primaryIsNavigable = false,
    	primaryRole = "SessionFormation",
    	primaryLabel = "Session formation",
    	primaryMultiplicity = "0..1",
    	foreignDtDefinitionName = "DT_HORAIRES",
    	foreignIsNavigable = true,
    	foreignRole = "Horaires",
    	foreignLabel = "Horaires",
    	foreignMultiplicity = "0..*"
    )
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
    	primaryMultiplicity = "0..1",
    	foreignDtDefinitionName = "DT_HORAIRES",
    	foreignIsNavigable = true,
    	foreignRole = "Horaires",
    	foreignLabel = "Horaires",
    	foreignMultiplicity = "0..*"
    )
	public io.vertigo.dynamo.domain.metamodel.association.DtListURIForSimpleAssociation getHorairesDtListURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createDtListURIForSimpleAssociation(this, "A_ASSOCIATION7", "Horaires");
	}
	/**
	 * Association : Utilisateur.
	 * @return com.kleegroup.formation.domain.administration.utilisateur.Utilisateur
	 */
    @io.vertigo.dynamo.domain.stereotype.Association (
    	name = "A_UTI_SES",
    	fkFieldName = "UTI_ID",
    	primaryDtDefinitionName = "DT_UTILISATEUR",
    	primaryIsNavigable = true,
    	primaryRole = "Utilisateur",
    	primaryLabel = "Utilisateur",
    	primaryMultiplicity = "0..1",
    	foreignDtDefinitionName = "DT_SESSION_FORMATION",
    	foreignIsNavigable = false,
    	foreignRole = "SessionFormation",
    	foreignLabel = "Session formation",
    	foreignMultiplicity = "0..*"
    )
	public com.kleegroup.formation.domain.administration.utilisateur.Utilisateur getUtilisateur() {
		final io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> fkURI = getUtilisateurURI();
		if (fkURI == null) {
			return null;
		}
		//On est toujours dans un mode lazy. On s'assure cependant que l'objet associé n'a pas changé
		if (utilisateur != null) {
			// On s'assure que l'objet correspond à la bonne clé
			final io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> uri;
			uri = new io.vertigo.dynamo.domain.model.URI<>(io.vertigo.dynamo.domain.util.DtObjectUtil.findDtDefinition(utilisateur), io.vertigo.dynamo.domain.util.DtObjectUtil.getId(utilisateur));
			if (!fkURI.urn().equals(uri.urn())) {
				utilisateur = null;
			}
		}		
		if (utilisateur == null) {
			utilisateur = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().read(fkURI);
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
    	primaryMultiplicity = "0..1",
    	foreignDtDefinitionName = "DT_SESSION_FORMATION",
    	foreignIsNavigable = false,
    	foreignRole = "SessionFormation",
    	foreignLabel = "Session formation",
    	foreignMultiplicity = "0..*"
    )
	public io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> getUtilisateurURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createURI(this, "A_UTI_SES", com.kleegroup.formation.domain.administration.utilisateur.Utilisateur.class);
	}
	/**
	 * Association : Etat session utilisateur.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.EtatSessionUtilisateur>
	 */
    @io.vertigo.dynamo.domain.stereotype.Association (
    	name = "A_ESUSES",
    	fkFieldName = "SES_ID",
    	primaryDtDefinitionName = "DT_SESSION_FORMATION",
    	primaryIsNavigable = false,
    	primaryRole = "SessionFormation",
    	primaryLabel = "Session formation",
    	primaryMultiplicity = "1..1",
    	foreignDtDefinitionName = "DT_ETAT_SESSION_UTILISATEUR",
    	foreignIsNavigable = true,
    	foreignRole = "EtatSessionUtilisateur",
    	foreignLabel = "Etat session utilisateur",
    	foreignMultiplicity = "0..*"
    )
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.EtatSessionUtilisateur> getEtatSessionUtilisateurList() {
//		return this.<com.kleegroup.formation.domain.formation.EtatSessionUtilisateur> getList(getEtatSessionUtilisateurListURI());
		// On doit avoir une clé primaire renseignée. Si ce n'est pas le cas, on renvoie une liste vide
		if (io.vertigo.dynamo.domain.util.DtObjectUtil.getId(this) == null) {
			return new io.vertigo.dynamo.domain.model.DtList<>(com.kleegroup.formation.domain.formation.EtatSessionUtilisateur.class);
		}
		final io.vertigo.dynamo.domain.model.DtListURI fkDtListURI = getEtatSessionUtilisateurDtListURI();
		io.vertigo.lang.Assertion.checkNotNull(fkDtListURI);
		//---------------------------------------------------------------------
		//On est toujours dans un mode lazy.
		if (etatSessionUtilisateur == null) {
			etatSessionUtilisateur = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().findAll(fkDtListURI);
		}
		return etatSessionUtilisateur;
	}

	/**
	 * Association URI: Etat session utilisateur.
	 * @return URI de l'association
	 */
    @io.vertigo.dynamo.domain.stereotype.Association (
    	name = "A_ESUSES",
    	fkFieldName = "SES_ID",
    	primaryDtDefinitionName = "DT_SESSION_FORMATION",
    	primaryIsNavigable = false,
    	primaryRole = "SessionFormation",
    	primaryLabel = "Session formation",
    	primaryMultiplicity = "1..1",
    	foreignDtDefinitionName = "DT_ETAT_SESSION_UTILISATEUR",
    	foreignIsNavigable = true,
    	foreignRole = "EtatSessionUtilisateur",
    	foreignLabel = "Etat session utilisateur",
    	foreignMultiplicity = "0..*"
    )
	public io.vertigo.dynamo.domain.metamodel.association.DtListURIForSimpleAssociation getEtatSessionUtilisateurDtListURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createDtListURIForSimpleAssociation(this, "A_ESUSES", "EtatSessionUtilisateur");
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
