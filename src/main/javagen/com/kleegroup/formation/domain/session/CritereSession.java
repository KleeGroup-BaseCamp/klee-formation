package com.kleegroup.formation.domain.session;

import io.vertigo.dynamo.domain.model.DtObject;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;

/**
 * Attention cette classe est générée automatiquement !
 * Objet de données CritereSession
 */
public final class CritereSession implements DtObject {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String intitule;

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Libellé court'.
	 * @return String intitule
	 */
	@Field(domain = "DO_LIBELLE_COURT", label = "Libellé court")
	public String getIntitule() {
		return intitule;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Libellé court'.
	 * @param intitule String
	 */
	public void setIntitule(final String intitule) {
		this.intitule = intitule;
	}

	//Aucune Association déclarée

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
