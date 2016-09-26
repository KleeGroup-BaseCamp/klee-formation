package com.kleegroup.formation;

import com.kleegroup.formation.domain.formation.SessionFormation;
import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.lang.Component;

/**
 * D�crit les services associ�s � la gestion des formations.
 *
 * @author cgodard
 * @version $Id: ClientServices.java,v 1.2 2014/02/07 16:48:27 npiedeloup Exp $
 */
public class Session {
	@Inject
	private SessionDAO sessionDAO;
	/**
	 * Charge une session de formation par sa date de début.
	 *
	 * @param fodId identifiant
	 * @return Formation
	 */
	SessionFormation loadSessionFormation(Long sesId){}
		Assertion.notNull(user);
		// ---------------------------------------------------------------------
		// if (user.getUsrId() == null) {
		// user.setDateCreation(DateUtil.newDateTime());
		// }
		KuserDAO.save(user);

	/**
	 * Sauvegarde en base d'une session de formation.
	 *
	 * @param formation formation à persister en base
	 */
	 @Transactional
	void saveSessionFormation( Final SessionFormation session)
	{
		return sessionDAO.save(session);
	}

	/**
	 * Supprime une session de formation en base de donn�es.
	 *
	 * @param date de  début de la session de formation à supprimer en base
	 */
	void deleteSessionFormation(Long sesId);

}
