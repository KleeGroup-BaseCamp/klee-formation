package com.kleegroup.formation.services.inscription;

import java.math.BigDecimal;

import javax.inject.Inject;

import com.kleegroup.formation.dao.formation.InscriptionDAO;
import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.formation.SessionFormation;
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
	public DtList<Inscription> getListInscriptionsBySessionId(final Long sessionId) {
		Assertion.checkNotNull(sessionId);
		// ---
		return inscriptionDAO.getListInscriptionsBySessionId(sessionId);
	}

	@Override
	public void inscrireUtilisateurASession(final Long sesId) {
		final Utilisateur utilisateurCourant = utilisateurServices.getCurrentUtilisateur();
		final Inscription inscription = new Inscription();
		inscription.setMail(utilisateurCourant.getMail());
		inscription.setNom(utilisateurCourant.getNom());
		inscription.setPrenom(utilisateurCourant.getPrenom());
		inscription.setUtiId(utilisateurCourant.getUtiId());
		inscription(sesId);

	}

	@Override
	public void inscrireUtilisateurAutre(final Long sesId, final Inscription inscriptions) {
		final Inscription inscription = new Inscription();
		inscription.setUtiId(inscriptions.getUtiId());
		final Utilisateur utilisateur = utilisateurServices.loadUtilisateurWithRoles(inscription.getUtiId());
		inscription.setNom(utilisateur.getNom());
		inscription.setPrenom(utilisateur.getPrenom());
		inscription.setMail(utilisateur.getMail());
		inscription(sesId);

	}

	private void inscription(final Long sesId) {
		final Inscription inscription = new Inscription();
		final SessionFormation session = sessionServices.loadSessionbyId(sesId);
		inscription.setSessionName(session.getFormationName());
		inscription.setCommentaire(session.getCommentaire());
		inscription.setNiveau(session.getNiveau());
		inscription.setDatedebut(session.getDateDebut());
		inscription.setDatefin(session.getDateFin());
		inscription.setDureejour(session.getDuree());
		inscription.setSesId(sesId);
		inscription.setHoraire(session.getHoraire());
		final BigDecimal zero = new BigDecimal(0);
		inscription.setSatisfaction(zero);
		//+1
		if (session.getInscriptionList().size() < session.getNbPersonne()) {
			inscriptionDAO.save(inscription);

		} else {
			session.setIsOuvert("Complet");
			sessionServices.saveSessionFormation(session);
		}
	}

	@Override
	public DtList<Inscription> getListInscriptionByUtiId(final Long utiId) {
		return inscriptionDAO.getListInscriptionByUtiId(utiId);
	}

	@Override
	public Inscription InscriptionByUtiSesId(final Long utilisateurId, final Long sessionFormationId) {
		return inscriptionDAO.getInscriptionByUtiSesId(utilisateurId, sessionFormationId);
	}

	@Override
	public DtList<Inscription> InscriptionVenirFormation(final Long utilisateurId) {
		return inscriptionDAO.getInscriptionVenirFormation(utilisateurId);
	}

	@Override
	public DtList<Inscription> InscriptionPasserFormation(final Long utilisateurId) {
		return inscriptionDAO.getInscriptionPasserFormation(utilisateurId);
	}

}
