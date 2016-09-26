package com.kleegroup.formation.ui.controller.sessionFormation;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere;
import com.kleegroup.formation.domain.formation.EtatSessionUtilisateur;
import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.FormationCritere;
import com.kleegroup.formation.domain.formation.Horaires;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.formation.FormationServices;
import com.kleegroup.formation.services.horaires.HorairesServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.lang.Option;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextListModifiable;
import io.vertigo.struts2.core.ContextRef;

/**
 */
public final class SessionDetailAction extends AbstractKleeFormationActionSupport {

	private static final long serialVersionUID = -4230604077944183713L;
	@Inject
	private FormationServices formationServices;

	@Inject
	private SessionServices sessionServices;

	@Inject
	private HorairesServices horairesServices;

	@Inject
	private UtilisateurServices utilisateurServices;

	private final ContextForm<SessionFormation> session = new ContextForm<>("sessionTest", this);
	private final ContextForm<Horaires> horaire = new ContextForm<>("horaire", this);
	private final ContextListModifiable<Horaires> horaires = new ContextListModifiable<>("horaires", this);
	private final ContextList<Utilisateur> formatteurs = new ContextList<>("formatteurs", this);
	private final ContextList<Formation> formations = new ContextList<>("formations", this);
	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);

	/**
	 * @param fodId Id de l'élément a afficher.
	 */
	public void initContext(@Named("sesId") final Option<Long> sesId) {
		if (sesId.isPresent()) {
			session.publish(sessionServices.loadSessionFormation(sesId.get()));
		} else {
			final UtilisateurCritere utilisateurcritere = new UtilisateurCritere();
			final com.kleegroup.formation.security.Role role = com.kleegroup.formation.security.Role.R_FORMATTEUR;
			utilisateurcritere.setRole(role.toString());

			formatteurs.publish(utilisateurServices.getUtilisateurListByCritere(utilisateurcritere));

			horaire.publish(new Horaires());
			horaires.publish(new DtList<>(Horaires.class));
			final FormationCritere formationcritere = new FormationCritere();
			formations.publish(formationServices.getFormationListByCritere(formationcritere));
			session.publish(new SessionFormation());
			toModeCreate();

		}
	}

	public String doEdit() {
		toModeEdit();

		final UtilisateurCritere utilisateurcritere = new UtilisateurCritere();
		final com.kleegroup.formation.security.Role role = com.kleegroup.formation.security.Role.R_FORMATTEUR;
		utilisateurcritere.setRole(role.toString());
		formatteurs.publish(utilisateurServices.getUtilisateurListByCritere(utilisateurcritere));
		horaire.publish(new Horaires());
		horaires.publish(new DtList<>(Horaires.class));
		final FormationCritere formationcritere = new FormationCritere();
		formations.publish(formationServices.getFormationListByCritere(formationcritere));
		session.publish(sessionServices.loadSessionFormation(sesIdRef.get()));
		return NONE;
	}

	public String doSave() {
		final SessionFormation sessions = session.readDto();
		sessions.setStatus("Brouillon");
		if (isModeCreate()) {
			final BigDecimal satisfaction = new BigDecimal(0);
			doSaveSession(satisfaction, sessions);

		} else if (isModeEdit()) {
			final BigDecimal satisfaction = null;
			doSaveSession(satisfaction, sessions);

		}
		return SUCCESS;
	}

	public String doPublish() {
		final SessionFormation sessions = session.readDto();
		sessions.setStatus("Publier");
		if (isModeCreate()) {
			final BigDecimal satisfaction = new BigDecimal(0);
			doSaveSession(satisfaction, sessions);

		} else if (isModeEdit()) {
			final BigDecimal satisfaction = null;
			doSaveSession(satisfaction, sessions);
		}
		return SUCCESS;
	}

	private void doSaveSession(final BigDecimal satisfaction, final SessionFormation sessions) {

		final Utilisateur uti = utilisateurServices.loadUtilisateurWithRoles(sessions.getUtiId());
		sessions.setFormateur(uti.getNom().concat(" ").concat(uti.getPrenom()));
		final Formation formation = formationServices.loadFormation(sessions.getForId());
		sessions.setFormationName(formation.getIntitule());
		sessions.setCommentaire(formation.getCommentaire());
		sessions.setNiveau(formation.getNiveau().getLibelle());
		sessionServices.saveSessionFormation(sessions);
		final DtList<Horaires> horairess = horaires.readDtList();
		if (!horairess.isEmpty()) {
			sessions.setHoraire(horairesServices.saveHoraires(horairess, sessions.getSesId()));
			final Long durée = (horairess.get(horairess.size() - 1).getFin().getTime() - horairess.get(0).getDebut().getTime()) / 3600000 / 24;
			sessions.setDateDebut(horairess.get(0).getDebut());
			sessions.setDateFin(horairess.get(horairess.size() - 1).getFin());
			sessions.setDuree(durée + 1);
			sessionServices.saveSessionFormation(sessions);

		}
		if (satisfaction != null) {
			sessions.setSatisfaction(satisfaction);
			sessions.setI(satisfaction);
			final EtatSessionUtilisateur etatsessionutilisateur = new EtatSessionUtilisateur();
			etatsessionutilisateur.setLibelle("Ouvert");
			etatsessionutilisateur.setSesId(sessions.getSesId());
			sessions.setIsOuvert("Ouvert");
			sessionServices.saveEtatSessionUtilisateur(etatsessionutilisateur);
			sessionServices.saveSessionFormation(sessions);
		}

	}

	public String doDelete() {
		final SessionFormation sessions = session.readDto();
		final String status = sessions.getStatus();
		if (status.equals("Brouillon")) {
			sessionServices.deleteSessionCascade(sesIdRef.get());
			horairesServices.deleteHoraires(session.readDto().getSesId());
			sessionServices.deleteSessionFormation(session.readDto().getSesId());
			return "success_delete";

		} else {
			sessions.setStatus("Annuler");
			sessions.setIsOuvert("Annuler");
			sessionServices.saveSessionFormation(sessions);
			return SUCCESS;
		}
	}

	public String doAdd() {
		horaires.getUiListModifiable().add(horaire.readDto());
		horaire.publish(new Horaires());

		return NONE;
	}

	public String doRemove() {
		if (isModeEdit()) {
			final SessionFormation sessions = session.readDto();
			horairesServices.deleteHoraires(sessions.getSesId());
			sessions.setHoraire("");
			sessions.setDateDebut(null);
			sessions.setDateFin(null);
			sessionServices.saveSessionFormation(sessions);
			horaires.publish(new DtList<>(Horaires.class));
			horaire.publish(new Horaires());
		} else {
			horaires.publish(new DtList<>(Horaires.class));
			horaire.publish(new Horaires());
		}

		return NONE;
	}

	@Override
	public String getPageName() {
		if (isModeCreate()) {
			return "creation d'une session de formation";
		} else if (isModeEdit()) {
			return "Modification d'une session deformation";
		}
		return "Detail d'une session de formation";
	}

}
