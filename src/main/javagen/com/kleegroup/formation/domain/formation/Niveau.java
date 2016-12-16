package com.kleegroup.formation.domain.formation;

import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;

/**
 * Attention cette classe est générée automatiquement !
 * Objet de données Niveau
 */
public final class Niveau implements Entity {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String nivCode;
	private String libelle;

	/** {@inheritDoc} */
	@Override
	public URI<Niveau> getURI() {
		return DtObjectUtil.createURI(this);
	}

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'Niv_Code'.
	 * @return String nivCode <b>Obligatoire</b>
	 */
	@Field(domain = "DO_CODE", type = "ID", required = true, label = "Niv_Code")
	public String getNivCode() {
		return nivCode;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'Niv_Code'.
	 * @param nivCode String <b>Obligatoire</b>
	 */
	public void setNivCode(final String nivCode) {
		this.nivCode = nivCode;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Libellé'.
	 * @return String libelle
	 */
	@Field(domain = "DO_LIBELLE_COURT", label = "Libellé")
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Libellé'.
	 * @param libelle String
	 */
	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}


	// Association : Formation non navigable

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
