package com.kleegroup.formation.services.horaires;

//import com.kleegroup.formation.domain.client.ClientCritere;
import com.kleegroup.formation.domain.formation.Horaires;
import com.kleegroup.formation.domain.formation.SessionFormation;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.lang.Component;

/**
 * D�crit les services associ�s � la gestion des formations.
 *
 * @author camille
 * @version $Id: ClientServices.java,v 1.2 2014/02/07 16:48:27 npiedeloup Exp $
 */
public interface HorairesServices extends Component {

	/**
	 * Sauvegarde en base une horaire.
	 *
	 */

	String saveHoraires(DtList<Horaires> horairess, Long sesId);

	/**
	 * Renvoie une liste d'horaire à partir d'une session de formation
	 * @param sessionFormation
	 * @return une liste d'horaires
	 */
	DtList<Horaires> getHoraires(SessionFormation sessionFormation);

	/**
	 * Supprime en base une horaire.
	 * @param id session
	 */
	void deleteHoraires(final Long sesId);

	public int horaireDebut(final Long sesId);

	public int horaireFin(final Long sesId);

}
