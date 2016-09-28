package com.kleegroup.formation.services.session;

import javax.inject.Inject;

import com.kleegroup.formation.dao.formation.EtatSessionUtilisateurDAO;
import com.kleegroup.formation.dao.formation.SessionFormationDAO;
import com.kleegroup.formation.dao.services.session.SessionPAO;
import com.kleegroup.formation.domain.formation.EtatSessionUtilisateur;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.domain.session.CritereSession;
import com.kleegroup.formation.domain.session.SessionView;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.transaction.Transactional;

@Transactional
public class SessionServicesImpl implements SessionServices {

	@Inject
	private SessionFormationDAO sessionformationDAO;

	@Inject
	EtatSessionUtilisateurDAO etatSessionUtilisateurDAO;

	@Inject
	private SessionPAO sessionPAO;

	//	@Inject
	//private FormationServices formationServices;
	/** {@inheritDoc} */
	@Override
	public SessionFormation loadSessionFormation(final Long sesId) {
		final SessionFormation sessionformation = sessionformationDAO.get(sesId);
		return sessionformation;
	}

	/** {@inheritDoc} */
	@Override
	public SessionFormation loadSessionbyId(final Long sesId) {
		final SessionFormation session = sessionformationDAO.get(sesId);

		return session;

	}

	/** {@inheritDoc} */
	@Override
	public DtList<SessionView> getSessionListByCritere(final CritereSession critereSession) {
		return sessionPAO.listSessionByCritere(critereSession);
	}

	/** {@inheritDoc} */
	@Override
	public DtList<SessionView> listSessionByDate() {
		return sessionPAO.listSessionByDate();
	}

	/** {@inheritDoc} */
	@Override
	public DtList<SessionFormation> listSessionByEtat() {
		return sessionformationDAO.listSessionByEtat();
	}

	/** {@inheritDoc} */
	@Override
	public void saveSessionFormation(final SessionFormation session) {
		sessionformationDAO.save(session);

	}

	/** {@inheritDoc} */
	@Override
	public void saveEtatSessionUtilisateur(final EtatSessionUtilisateur etat) {
		etatSessionUtilisateurDAO.save(etat);
	}

	/** {@inheritDoc} */
	@Override
	public void deleteSessionFormation(final Long sesId) {
		sessionformationDAO.delete(sesId);

	}

	@Override
	public DtList<SessionFormation> listSessionByFormateurId(final Long formateurId) {
		return sessionformationDAO.getListSessionByFormateurId(formateurId);
	}

	@Override
	public DtList<SessionFormation> ListSessionFormateurVenir(final Long formateurId) {
		return sessionformationDAO.getListSessionFormateurVenir(formateurId);
	}

	@Override
	public DtList<SessionFormation> ListSessionFormateurPasser(final Long formateurId) {
		return sessionformationDAO.getListSessionFormateurPasser(formateurId);
	}

	@Override
	public DtList<SessionView> ListSessionByForId(final Long forId) {
		return sessionPAO.getListSessionByForId(forId);
	}

	@Override
	public void deleteSessionCascade(final Long sesId) {
		sessionPAO.equals(sesId);
	}
}
