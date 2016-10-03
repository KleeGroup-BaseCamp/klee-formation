package com.kleegroup.formation.services.inscription;

import java.math.BigDecimal;

import javax.inject.Inject;

import com.kleegroup.formation.dao.formation.InscriptionDAO;
import com.kleegroup.formation.dao.services.inscription.InscriptionPAO;
import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.domain.inscription.InscriptionView;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.session.SessionServices;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.domain.model.DtListURIForCriteria;
import io.vertigo.dynamo.transaction.Transactional;
import io.vertigo.lang.Assertion;

@Transactional
public class InscriptionServicesImpl implements InscriptionServices {

	@Inject
	private InscriptionDAO inscriptionDAO;
	@Inject
	private InscriptionPAO inscriptionPAO;

	@Inject
	private UtilisateurServices utilisateurServices;

	@Inject
	private SessionServices sessionServices;

	/** {@inheritDoc} */
	@Override
	public Inscription loadInscription(final Long insId) {
		final Inscription inscription = inscriptionDAO.get(insId);
		return inscription;
	}

	/** {@inheritDoc} */
	@Override
	public DtList<Inscription> getInscriptionListByCritere(final Inscription inscription) {
		return inscriptionDAO.getList(DtListURIForCriteria.createCriteria(inscription), 100);
	}

	/** {@inheritDoc} */
	@Override
	public void saveInscription(final Inscription inscription) {
		inscriptionDAO.save(inscription);
	}

	/** {@inheritDoc} */
	@Override
	public void deleteInscription(final Long insId) {
		inscriptionDAO.delete(insId);

	}

	@Override
	public DtList<InscriptionView> getListInscriptionsViewBySessionId(final Long sessionId) {
		Assertion.checkNotNull(sessionId);
		// ---
		return inscriptionPAO.getListInscriptionsViewBySessionId(sessionId);
	}

	@Override
	public DtList<Inscription> getListInscriptionsBySessionId(final Long sessionId) {
		Assertion.checkNotNull(sessionId);
		// ---
		return inscriptionDAO.getListInscriptionsBySessionId(sessionId);
	}

	@Override
	public void inscrireUtilisateurASession(final Long sesId) {
		final Utilisateur utilisateurCourant = utilisateurServices.getCurrentUtilisateur();
		inscrireUtilisateur(sesId, utilisateurCourant.getUtiId());
	}

	@Override
	public void inscrireUtilisateur(final Long sesId, final long utiId) {
		final Inscription inscription = new Inscription();
		inscription.setUtiId(utiId);
		inscription.setSesId(sesId);
		enregistrerInscription(inscription);
	}

	private void enregistrerInscription(final Inscription inscription) {
		//		final SessionFormation session = sessionServices.loadSessionbyId(inscription.getSesId());
		final SessionFormation session = sessionServices.loadSessionbyId(inscription.getSesId());
		final BigDecimal zero = new BigDecimal(0);
		inscription.setSatisfaction(zero);

		if (session.getInscriptionList().size() + 1 < session.getNbPersonne()) {
			inscriptionDAO.save(inscription);

		} else {
			session.setEsuCode("Complet");
			sessionServices.saveSessionFormation(session);
		}
	}

	@Override
	public DtList<InscriptionView> getListInscriptionByUtiId(final Long utiId) {
		return inscriptionPAO.getListInscriptionViewByUtiId(utiId);
	}

	@Override
	public Inscription InscriptionByUtiSesId(final Long utilisateurId, final Long sessionFormationId) {
		return inscriptionDAO.getInscriptionByUtiSesId(utilisateurId, sessionFormationId);
	}

	@Override
	public DtList<InscriptionView> InscriptionVenirFormation(final Long utilisateurId) {
		return inscriptionPAO.getInscriptionViewVenirFormation(utilisateurId);
	}

	@Override
	public DtList<InscriptionView> InscriptionPasserFormation(final Long utilisateurId) {
		return inscriptionPAO.getInscriptionViewPasserFormation(utilisateurId);
	}

}
