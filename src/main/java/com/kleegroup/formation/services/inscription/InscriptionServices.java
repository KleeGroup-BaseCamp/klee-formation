package com.kleegroup.formation.services.inscription;

import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.inscription.InscriptionView;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.lang.Component;

/**
 * D�crit les services associ�s � la gestion des formations.
 *
 * @author cmoutte
 * @version $Id: ClientServices.java,v 1.2 2014/02/07 16:48:27 npiedeloup Exp $
 */
public interface InscriptionServices extends Component {

	Inscription loadInscription(Long insId);

	/**
	 * Sauvegarde en base d'une session de formation.
	 *
	 * @param formation formation à persister en base
	 */
	void saveInscription(Inscription inscription);

	/**
	 * Supprime une session de formation en base de donn�es.
	 *
	 * @param date de  début de la session de formation à supprimer en base
	 */
	void deleteInscription(Long insId);

	/**
	 * inscrire l'utilisateur courant à une session.
	 *
	 * @param id session
	 */

	void inscrireUtilisateurASession(Long sesId);

	/**
	 * inscrire un autre utilisateur à une session.
	 *
	 * @param id session
	 */

	void inscrireUtilisateur(final Long sesId, long utiId);

	//DtList<Inscription> getInscriptionListByCritere(final Inscription inscription);

	/**
	 * liste des inscription à la session
	 * @param id session
	 * @return liste de sessionView
	 */

	DtList<InscriptionView> getListInscriptionsViewBySessionId(Long sessionId);

	/**
	 * liste des inscription d'un utilisateur
	 * @param id utilisateur
	 * @return liste de inscriptionView
	 */
	DtList<InscriptionView> getListInscriptionByUtiId(final Long utiId);

	/**
	 * liste des inscription à la session par rapport à un utilisateur
	 * @param id session, id utilisateur
	 * @return liste de inscriptionView
	 */

	Inscription InscriptionByUtiSesId(final Long utilisateurId, final Long sessionFormationId);

	/**
	 * liste des inscription par utlisateur qui sont à venir ( supérieur ou égal à la date courante)
	 * @param id utilisateur
	 * @return liste des utilisateur
	 */

	DtList<InscriptionView> InscriptionVenirFormation(final Long utilisateurId);

	/**
	 * liste des inscription par utlisateur qui sont passée (inférieur à la date courante)
	 * @param id utilisateur
	 * @return liste des utilisateur
	 */

	DtList<InscriptionView> InscriptionPasserFormation(final Long utilisateurId);

	/**
	 * liste des inscription à la session
	 * @param id session
	 * @return liste de session
	 */

	DtList<Inscription> getListInscriptionsBySessionId(Long sessionId);
}
