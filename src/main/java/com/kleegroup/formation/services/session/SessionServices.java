package com.kleegroup.formation.services.session;

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
	 * Charge une session de formation par son id
	 *
	 * @param sesId identifiant
	 * @return SessionFormation
	 */
	SessionFormation loadSessionFormation(Long sesId);

	/**
	 * Charge une session de formation par son id
	 *
	 * @param sesId identifiant
	 * @return SessionFormation
	 */
	SessionFormation loadSessionbyId(final Long sesId);

	/**
	 * Retourne la liste des sessions formations répondant aux critères.
	 *
	 * @param critereSession critère de recherche des sessionformations
	 * @return liste de sessionViews r�pondant aux crit�res donn�s en entr�e
	 */
	DtList<SessionView> getSessionListByCritere(CritereSession critereSession);

	/**
	 * Retourne la liste des sessions formations où la date est >= à la date courante.
	 *
	 * @return liste de sessionViews
	 */
	DtList<SessionView> listSessionByDate();

	/**
	 * Retourne la liste des sessions formations où le status est égale à Réaliser ou Publier ou Annuler.
	 *
	 * @return liste de sessionFormations
	 */
	DtList<SessionFormation> listSessionByEtat();

	/**
	 * Sauvegarde en base d'une session de formation.
	 *
	 * @param formation formation à persister en base
	 */
	void saveSessionFormation(SessionFormation session);

	//void saveEtatSessionUtilisateur(final EtatSessionUtilisateur etat);

	/**
	 * Renvoie une liste de sessions de formation en base de donn�es avec comme critère l'id du formateur.
	 *
	 * @param id du formateur
	 * @return liste de sessionformation
	 */

	DtList<SessionFormation> listSessionByFormateurId(final Long formateurId);

	/**
	 * Renvoie une liste de sessionView en base de donn�es avec comme critère être supérieur ou égale à la date du jour.
	 *
	 * @param id du formateur
	 * @return liste de sessionformation
	 */

	DtList<SessionView> ListSessionFormateurVenir(final Long formateurId);

	/**
	 * Renvoie une liste de sessionView en base de donn�es avec comme critère être inférieur à la date du jour.
	 *
	 * @param id du formateur
	 * @return liste de sessionformation
	 */

	DtList<SessionView> ListSessionFormateurPasser(final Long formateurId);

	/**
	 * Supprime une session de formation en base de donn�es si elle n'a pas d'horaire.
	 *
	 * @param date de  début de la session de formation à supprimer en base
	 */
	void deleteSessionFormation(Long sesId);

	/**
	 * Renvoie une liste de sessionView en base de donn�es avec comme critère l'id de la formation.
	 *
	 * @param id de la formation
	 * @return liste de sessionView
	 */

	DtList<SessionView> ListSessionByForId(final Long forId);

	/**
	 * Supprime d'abord les horaires puis la session de formation en base de donn�es.
	 *
	 * @param date de  début de la session de formation à supprimer en base
	 */
	void deleteSessionCascade(final Long sesId);

}
