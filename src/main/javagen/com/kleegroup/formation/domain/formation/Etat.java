package com.kleegroup.formation.domain.formation;

import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;

/**
 * Attention cette classe est générée automatiquement !
 * Objet de données Etat
 */
public final class Etat implements Entity {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String etaCode;
	private String libelle;

	/** {@inheritDoc} */
	@Override
	public URI<Etat> getURI() {
		return DtObjectUtil.createURI(this);
	}

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

	// Association : Session formation non navigable

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
