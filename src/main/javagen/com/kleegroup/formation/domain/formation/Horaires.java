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
	private Integer debut;
	private Integer fin;
	private java.util.Date jour;
	private Integer debutAprem;
	private Integer finAprem;
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
	 * @return Integer debut 
	 */
	@Field(domain = "DO_HEURE", label = "debut")
	public Integer getDebut() {
		return debut;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'debut'.
	 * @param debut Integer 
	 */
	public void setDebut(final Integer debut) {
		this.debut = debut;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'fin'. 
	 * @return Integer fin 
	 */
	@Field(domain = "DO_HEURE", label = "fin")
	public Integer getFin() {
		return fin;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'fin'.
	 * @param fin Integer 
	 */
	public void setFin(final Integer fin) {
		this.fin = fin;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'jour'. 
	 * @return java.util.Date jour 
	 */
	@Field(domain = "DO_DATE", label = "jour")
	public java.util.Date getJour() {
		return jour;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'jour'.
	 * @param jour java.util.Date 
	 */
	public void setJour(final java.util.Date jour) {
		this.jour = jour;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'debutAprem'. 
	 * @return Integer debutAprem 
	 */
	@Field(domain = "DO_HEURE", label = "debutAprem")
	public Integer getDebutAprem() {
		return debutAprem;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'debutAprem'.
	 * @param debutAprem Integer 
	 */
	public void setDebutAprem(final Integer debutAprem) {
		this.debutAprem = debutAprem;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'finAprem'. 
	 * @return Integer finAprem 
	 */
	@Field(domain = "DO_HEURE", label = "finAprem")
	public Integer getFinAprem() {
		return finAprem;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'finAprem'.
	 * @param finAprem Integer 
	 */
	public void setFinAprem(final Integer finAprem) {
		this.finAprem = finAprem;
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
