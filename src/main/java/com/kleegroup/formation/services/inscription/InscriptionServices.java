package com.kleegroup.formation.services.inscription;

import com.kleegroup.formation.domain.formation.Inscription;

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

	void inscrireUtilisateurASession(Long sesId);

	void inscrireUtilisateurAutre(final Long sesId, Inscription inscriptions);

	DtList<Inscription> getInscriptionListByCritere(final Inscription inscription);

	DtList<Inscription> getListInscriptionsBySessionId(Long sessionId);

	DtList<Inscription> getListInscriptionByUtiId(final Long utiId);

	Inscription InscriptionByUtiSesId(final Long utilisateurId, final Long sessionFormationId);

	DtList<Inscription> InscriptionVenirFormation(final Long utilisateurId);

	DtList<Inscription> InscriptionPasserFormation(final Long utilisateurId);
}
