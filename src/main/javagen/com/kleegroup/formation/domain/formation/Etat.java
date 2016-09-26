package com.kleegroup.formation.domain.formation;

import io.vertigo.dynamo.domain.stereotype.DtDefinition;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.model.DtObject;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
/**
 * Attention cette classe est générée automatiquement !
 * Objet de données Etat
 */
@DtDefinition
public final class Etat implements DtObject {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String etaCode;
	private String libelle;
	private Long sesId;

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'Code'. 
	 * @return String etaCode <b>Obligatoire</b>
	 */
	@Field(domain = "DO_CODE", type = "ID", required = true, label = "Code")
	public String getEtaCode() {
		return etaCode;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'Code'.
	 * @param etaCode String <b>Obligatoire</b>
	 */
	public void setEtaCode(final String etaCode) {
		this.etaCode = etaCode;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Libelle'. 
	 * @return String libelle 
	 */
	@Field(domain = "DO_LIBELLE_COURT", label = "Libelle")
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Libelle'.
	 * @param libelle String 
	 */
	public void setLibelle(final String libelle) {
		this.libelle = libelle;
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


	// Association : Session formation non navigable

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
