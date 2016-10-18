package com.kleegroup.formation.domain.formation;

import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
/**
 * Attention cette classe est générée automatiquement !
 * Objet de données Inscription
 */
public final class Inscription implements Entity {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Long insId;
	private Boolean presence;
	private java.math.BigDecimal satisfaction;
	private java.math.BigDecimal duree;
	private java.math.BigDecimal theme;
	private java.math.BigDecimal contenu;
	private java.math.BigDecimal progression;
	private java.math.BigDecimal attentes;
	private java.math.BigDecimal benefices;
	private java.math.BigDecimal approfondir;
	private java.math.BigDecimal contact;
	private java.math.BigDecimal explication;
	private String etat;
	private Long sesId;
	private Long utiId;
	private com.kleegroup.formation.domain.formation.SessionFormation sessionFormation;
	private com.kleegroup.formation.domain.administration.utilisateur.Utilisateur utilisateur;

	/** {@inheritDoc} */
	@Override
	public URI<Inscription> getURI() {
		return DtObjectUtil.createURI(this);
	}
	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'INS_ID'. 
	 * @return Long insId <b>Obligatoire</b>
	 */
	@Field(domain = "DO_IDENTIFIANT", type = "ID", required = true, label = "INS_ID")
	public Long getInsId() {
		return insId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'INS_ID'.
	 * @param insId Long <b>Obligatoire</b>
	 */
	public void setInsId(final Long insId) {
		this.insId = insId;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'PRESENCE'. 
	 * @return Boolean presence 
	 */
	@Field(domain = "DO_OUI_NON", label = "PRESENCE")
	public Boolean getPresence() {
		return presence;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'PRESENCE'.
	 * @param presence Boolean 
	 */
	public void setPresence(final Boolean presence) {
		this.presence = presence;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'SATISFACTION'. 
	 * @return java.math.BigDecimal satisfaction 
	 */
	@Field(domain = "DO_STATIQUE", label = "SATISFACTION")
	public java.math.BigDecimal getSatisfaction() {
		return satisfaction;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'SATISFACTION'.
	 * @param satisfaction java.math.BigDecimal 
	 */
	public void setSatisfaction(final java.math.BigDecimal satisfaction) {
		this.satisfaction = satisfaction;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Durée'. 
	 * @return java.math.BigDecimal duree 
	 */
	@Field(domain = "DO_STATIQUE", label = "Durée")
	public java.math.BigDecimal getDuree() {
		return duree;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Durée'.
	 * @param duree java.math.BigDecimal 
	 */
	public void setDuree(final java.math.BigDecimal duree) {
		this.duree = duree;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Theme'. 
	 * @return java.math.BigDecimal theme 
	 */
	@Field(domain = "DO_STATIQUE", label = "Theme")
	public java.math.BigDecimal getTheme() {
		return theme;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Theme'.
	 * @param theme java.math.BigDecimal 
	 */
	public void setTheme(final java.math.BigDecimal theme) {
		this.theme = theme;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Contenu'. 
	 * @return java.math.BigDecimal contenu 
	 */
	@Field(domain = "DO_STATIQUE", label = "Contenu")
	public java.math.BigDecimal getContenu() {
		return contenu;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Contenu'.
	 * @param contenu java.math.BigDecimal 
	 */
	public void setContenu(final java.math.BigDecimal contenu) {
		this.contenu = contenu;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Progression'. 
	 * @return java.math.BigDecimal progression 
	 */
	@Field(domain = "DO_STATIQUE", label = "Progression")
	public java.math.BigDecimal getProgression() {
		return progression;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Progression'.
	 * @param progression java.math.BigDecimal 
	 */
	public void setProgression(final java.math.BigDecimal progression) {
		this.progression = progression;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Attentes'. 
	 * @return java.math.BigDecimal attentes 
	 */
	@Field(domain = "DO_STATIQUE", label = "Attentes")
	public java.math.BigDecimal getAttentes() {
		return attentes;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Attentes'.
	 * @param attentes java.math.BigDecimal 
	 */
	public void setAttentes(final java.math.BigDecimal attentes) {
		this.attentes = attentes;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Bénéfices'. 
	 * @return java.math.BigDecimal benefices 
	 */
	@Field(domain = "DO_STATIQUE", label = "Bénéfices")
	public java.math.BigDecimal getBenefices() {
		return benefices;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Bénéfices'.
	 * @param benefices java.math.BigDecimal 
	 */
	public void setBenefices(final java.math.BigDecimal benefices) {
		this.benefices = benefices;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Approfondir'. 
	 * @return java.math.BigDecimal approfondir 
	 */
	@Field(domain = "DO_STATIQUE", label = "Approfondir")
	public java.math.BigDecimal getApprofondir() {
		return approfondir;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Approfondir'.
	 * @param approfondir java.math.BigDecimal 
	 */
	public void setApprofondir(final java.math.BigDecimal approfondir) {
		this.approfondir = approfondir;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Contact'. 
	 * @return java.math.BigDecimal contact 
	 */
	@Field(domain = "DO_STATIQUE", label = "Contact")
	public java.math.BigDecimal getContact() {
		return contact;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Contact'.
	 * @param contact java.math.BigDecimal 
	 */
	public void setContact(final java.math.BigDecimal contact) {
		this.contact = contact;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Explication'. 
	 * @return java.math.BigDecimal explication 
	 */
	@Field(domain = "DO_STATIQUE", label = "Explication")
	public java.math.BigDecimal getExplication() {
		return explication;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Explication'.
	 * @param explication java.math.BigDecimal 
	 */
	public void setExplication(final java.math.BigDecimal explication) {
		this.explication = explication;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'etat'. 
	 * @return String etat 
	 */
	@Field(domain = "DO_LIBELLE", label = "etat")
	public String getEtat() {
		return etat;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'etat'.
	 * @param etat String 
	 */
	public void setEtat(final String etat) {
		this.etat = etat;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Session formation'. 
	 * @return Long sesId <b>Obligatoire</b>
	 */
	@Field(domain = "DO_IDENTIFIANT", type = "FOREIGN_KEY", required = true, label = "Session formation")
	public Long getSesId() {
		return sesId;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Session formation'.
	 * @param sesId Long <b>Obligatoire</b>
	 */
	public void setSesId(final Long sesId) {
		this.sesId = sesId;
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
	 * Association : Session formation.
	 * @return com.kleegroup.formation.domain.formation.SessionFormation
	 */
	public com.kleegroup.formation.domain.formation.SessionFormation getSessionFormation() {
		final io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.SessionFormation> fkURI = getSessionFormationURI();
		if (fkURI == null) {
			return null;
		}
		//On est toujours dans un mode lazy. On s'assure cependant que l'objet associé n'a pas changé
		if (sessionFormation != null) {
			// On s'assure que l'objet correspond à la bonne clé
			if (!fkURI.equals(sessionFormation.getURI())) {
				sessionFormation = null;
			}
		}		
		if (sessionFormation == null) {
			sessionFormation = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().read(fkURI);
		}
		return sessionFormation;
	}

	/**
	 * Retourne l'URI: Session formation.
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
    	foreignMultiplicity = "0..*"
    )
	public io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.SessionFormation> getSessionFormationURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createURI(this, "A_LCO_CMD", com.kleegroup.formation.domain.formation.SessionFormation.class);
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
		if (utilisateur != null) {
			// On s'assure que l'objet correspond à la bonne clé
			if (!fkURI.equals(utilisateur.getURI())) {
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
    	name = "A_ASSOCIATION3",
    	fkFieldName = "UTI_ID",
    	primaryDtDefinitionName = "DT_UTILISATEUR",
    	primaryIsNavigable = true,
    	primaryRole = "Utilisateur",
    	primaryLabel = "Utilisateur",
    	primaryMultiplicity = "1..1",
    	foreignDtDefinitionName = "DT_INSCRIPTION",
    	foreignIsNavigable = false,
    	foreignRole = "Inscription",
    	foreignLabel = "Inscription",
    	foreignMultiplicity = "0..*"
    )
	public io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> getUtilisateurURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createURI(this, "A_ASSOCIATION3", com.kleegroup.formation.domain.administration.utilisateur.Utilisateur.class);
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
