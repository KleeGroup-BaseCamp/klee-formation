package com.kleegroup.formation.services.formation;

//import com.kleegroup.formation.domain.client.ClientCritere;
import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.FormationCritere;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.lang.Component;

/**
 * Décrit les services associ�s � la gestion des formations.
 *
 * @author camille
 */
public interface FormationServices extends Component {

	/**
	 * Charge une formation par son id.
	 *
	 * @param fodId identifiant
	 * @return Formation
	 */
	Formation loadFormation(Long forId);

	/**
	 * Retourne la liste des formations répondant aux critères.
	 *
	 * @param criteres critère de recherche des formations
	 * @return liste de formations r�pondant aux crit�res donn�s en entr�e
	 */
	DtList<Formation> getFormationListByCritere(FormationCritere criteres);

	/**
	 * Sauvegarde en base une formation.
	 *
	 * @param formation formation à persister en base
	 */
	void saveFormation(Formation formation);

	/**
	 * Supprime un une formation en base de donn�es.
	 *
	 * @param forId Id produit à supprimer en base
	 */
	void deleteFormation(Long ForId);

	void deleteFormationCascade(final Long forId);
}
