package com.kleegroup.formation.domain.formation;

import io.vertigo.dynamo.domain.stereotype.DtDefinition;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.model.DtObject;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
/**
 * Attention cette classe est générée automatiquement !
 * Objet de données Formation
 */
@DtDefinition
public final class Formation implements DtObject {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Long forId;
	private String intitule;
	private String commentaire;
	private String nivFormation;
	private String nivCode;
	private io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.SessionFormation> sessionFormation;
	private com.kleegroup.formation.domain.formation.Niveau niveau;

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'Id'. 
	 * @return Long forId <b>Obligatoire</b>
	 */
	@Field(domain = "DO_IDENTIFIANT", type = "ID", required = true, label = "Id")
	public Long getForId() {
		return forId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'Id'.
	 * @param forId Long <b>Obligatoire</b>
	 */
	public void setForId(final Long forId) {
		this.forId = forId;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'intitule'. 
	 * @return String intitule 
	 */
	@Field(domain = "DO_LIBELLE_COURT", label = "intitule")
	public String getIntitule() {
		return intitule;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'intitule'.
	 * @param intitule String 
	 */
	public void setIntitule(final String intitule) {
		this.intitule = intitule;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'commentaire'. 
	 * @return String commentaire 
	 */
	@Field(domain = "DO_LIBELLE_LONG", label = "commentaire")
	public String getCommentaire() {
		return commentaire;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'commentaire'.
	 * @param commentaire String 
	 */
	public void setCommentaire(final String commentaire) {
		this.commentaire = commentaire;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'niveau'. 
	 * @return String nivFormation 
	 */
	@Field(domain = "DO_LIBELLE_COURT", label = "niveau")
	public String getNivFormation() {
		return nivFormation;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'niveau'.
	 * @param nivFormation String 
	 */
	public void setNivFormation(final String nivFormation) {
		this.nivFormation = nivFormation;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Niveau'. 
	 * @return String nivCode 
	 */
	@Field(domain = "DO_CODE", type = "FOREIGN_KEY", label = "Niveau")
	public String getNivCode() {
		return nivCode;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Niveau'.
	 * @param nivCode String 
	 */
	public void setNivCode(final String nivCode) {
		this.nivCode = nivCode;
	}

	/**
	 * Association : Session formation.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.SessionFormation>
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
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.SessionFormation> getSessionFormationList() {
//		return this.<com.kleegroup.formation.domain.formation.SessionFormation> getList(getSessionFormationListURI());
		// On doit avoir une clé primaire renseignée. Si ce n'est pas le cas, on renvoie une liste vide
		if (io.vertigo.dynamo.domain.util.DtObjectUtil.getId(this) == null) {
			return new io.vertigo.dynamo.domain.model.DtList<>(com.kleegroup.formation.domain.formation.SessionFormation.class);
		}
		final io.vertigo.dynamo.domain.model.DtListURI fkDtListURI = getSessionFormationDtListURI();
		io.vertigo.lang.Assertion.checkNotNull(fkDtListURI);
		//---------------------------------------------------------------------
		//On est toujours dans un mode lazy.
		if (sessionFormation == null) {
			sessionFormation = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().findAll(fkDtListURI);
		}
		return sessionFormation;
	}

	/**
	 * Association URI: Session formation.
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
	public io.vertigo.dynamo.domain.metamodel.association.DtListURIForSimpleAssociation getSessionFormationDtListURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createDtListURIForSimpleAssociation(this, "A_FOR_SES", "SessionFormation");
	}
	/**
	 * Association : Niveau.
	 * @return com.kleegroup.formation.domain.formation.Niveau
	 */
    @io.vertigo.dynamo.domain.stereotype.Association (
    	name = "A_ASSOCIATION6",
    	fkFieldName = "NIV_CODE",
    	primaryDtDefinitionName = "DT_NIVEAU",
    	primaryIsNavigable = true,
    	primaryRole = "Niveau",
    	primaryLabel = "Niveau",
    	primaryMultiplicity = "0..1",
    	foreignDtDefinitionName = "DT_FORMATION",
    	foreignIsNavigable = false,
    	foreignRole = "Formation",
    	foreignLabel = "Formation",
    	foreignMultiplicity = "0..*"
    )
	public com.kleegroup.formation.domain.formation.Niveau getNiveau() {
		final io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.Niveau> fkURI = getNiveauURI();
		if (fkURI == null) {
			return null;
		}
		//On est toujours dans un mode lazy. On s'assure cependant que l'objet associé n'a pas changé
		if (niveau != null) {
			// On s'assure que l'objet correspond à la bonne clé
			final io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.Niveau> uri;
			uri = new io.vertigo.dynamo.domain.model.URI<>(io.vertigo.dynamo.domain.util.DtObjectUtil.findDtDefinition(niveau), io.vertigo.dynamo.domain.util.DtObjectUtil.getId(niveau));
			if (!fkURI.urn().equals(uri.urn())) {
				niveau = null;
			}
		}		
		if (niveau == null) {
			niveau = io.vertigo.app.Home.getApp().getComponentSpace().resolve(io.vertigo.dynamo.store.StoreManager.class).getDataStore().read(fkURI);
		}
		return niveau;
	}

	/**
	 * Retourne l'URI: Niveau.
	 * @return URI de l'association
	 */
    @io.vertigo.dynamo.domain.stereotype.Association (
    	name = "A_ASSOCIATION6",
    	fkFieldName = "NIV_CODE",
    	primaryDtDefinitionName = "DT_NIVEAU",
    	primaryIsNavigable = true,
    	primaryRole = "Niveau",
    	primaryLabel = "Niveau",
    	primaryMultiplicity = "0..1",
    	foreignDtDefinitionName = "DT_FORMATION",
    	foreignIsNavigable = false,
    	foreignRole = "Formation",
    	foreignLabel = "Formation",
    	foreignMultiplicity = "0..*"
    )
	public io.vertigo.dynamo.domain.model.URI<com.kleegroup.formation.domain.formation.Niveau> getNiveauURI() {
		return io.vertigo.dynamo.domain.util.DtObjectUtil.createURI(this, "A_ASSOCIATION6", com.kleegroup.formation.domain.formation.Niveau.class);
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
