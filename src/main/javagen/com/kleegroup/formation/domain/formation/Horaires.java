package com.kleegroup.formation.domain.formation;

import io.vertigo.dynamo.domain.stereotype.DtDefinition;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.model.DtObject;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
/**
 * Attention cette classe est générée automatiquement !
 * Objet de données Horaires
 */
@DtDefinition
public final class Horaires implements DtObject {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Long datId;
	private java.util.Date debut;
	private java.util.Date fin;
	private Long sesId;

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'DatId'. 
	 * @return Long datId <b>Obligatoire</b>
	 */
	@Field(domain = "DO_IDENTIFIANT", type = "ID", required = true, label = "DatId")
	public Long getDatId() {
		return datId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'DatId'.
	 * @param datId Long <b>Obligatoire</b>
	 */
	public void setDatId(final Long datId) {
		this.datId = datId;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'debut'. 
	 * @return java.util.Date debut 
	 */
	@Field(domain = "DO_DATE_MINUTE", label = "debut")
	public java.util.Date getDebut() {
		return debut;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'debut'.
	 * @param debut java.util.Date 
	 */
	public void setDebut(final java.util.Date debut) {
		this.debut = debut;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'fin'. 
	 * @return java.util.Date fin 
	 */
	@Field(domain = "DO_DATE_MINUTE", label = "fin")
	public java.util.Date getFin() {
		return fin;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'fin'.
	 * @param fin java.util.Date 
	 */
	public void setFin(final java.util.Date fin) {
		this.fin = fin;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Session formation'. 
	 * @return Long sesId 
	 */
	@Field(domain = "DO_IDENTIFIANT", type = "FOREIGN_KEY", label = "Session formation")
	public Long getSesId() {
		return sesId;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Session formation'.
	 * @param sesId Long 
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
