package com.kleegroup.formation.services.session;

import com.kleegroup.formation.domain.formation.EtatSessionUtilisateur;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.domain.session.CritereSession;
import com.kleegroup.formation.domain.session.SessionView;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.lang.Component;

/**
 * D�crit les services associ�s � la gestion des formations.
 *
 * @author cgodard
 * @version $Id: ClientServices.java,v 1.2 2014/02/07 16:48:27 npiedeloup Exp $
 */
public interface SessionServices extends Component {

	/**
	 * Charge une session de formation par sa date de début.
	 *
	 * @param fodId identifiant
	 * @return Formation
	 */
	SessionFormation loadSessionFormation(Long sesId);

	SessionFormation loadSessionbyId(final Long sesId);

	/**
	 * Retourne la liste des sessions formations répondant aux critères.
	 *
	 * @param critereSession critère de recherche des formations
	 * @return liste de formations r�pondant aux crit�res donn�s en entr�e
	 */
	DtList<SessionView> getSessionListByCritere(CritereSession critereSession);

	DtList<SessionView> listSessionByDate();

	DtList<SessionFormation> listSessionByEtat();

	//DtList<SessionFormation> listSessionByUtiId(final Long utiId);

	/**
	 * Sauvegarde en base d'une session de formation.
	 *
	 * @param formation formation à persister en base
	 */
	void saveSessionFormation(SessionFormation session);

	void saveEtatSessionUtilisateur(final EtatSessionUtilisateur etat);

	/**
	 * Supprime une session de formation en base de donn�es.
	 *
	 * @param date de  début de la session de formation à supprimer en base
	 */
	DtList<SessionFormation> listSessionByFormateurId(final Long formateurId);

	DtList<SessionView> ListSessionFormateurVenir(final Long formateurId);

	DtList<SessionView> ListSessionFormateurPasser(final Long formateurId);

	void deleteSessionFormation(Long sesId);

	DtList<SessionView> ListSessionByForId(final Long forId);

	void deleteSessionCascade(final Long sesId);

}
