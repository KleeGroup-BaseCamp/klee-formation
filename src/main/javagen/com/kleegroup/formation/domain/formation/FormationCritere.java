package com.kleegroup.formation.domain.formation;

import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.model.DtObject;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
/**
 * Attention cette classe est générée automatiquement !
 * Objet de données FormationCritere
 */
public final class FormationCritere implements DtObject {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String intitule;
	private String nivCode;

	
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

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Code'. 
	 * @return String nivCode 
	 */
	@Field(domain = "DO_CODE", label = "Code")
	public String getNivCode() {
		return nivCode;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Code'.
	 * @param nivCode String 
	 */
	public void setNivCode(final String nivCode) {
		this.nivCode = nivCode;
	}

	//Aucune Association déclarée

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
