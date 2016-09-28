package com.kleegroup.formation.ui.controller.sessionFormation;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere;
import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.FormationCritere;
import com.kleegroup.formation.domain.formation.Horaires;
import com.kleegroup.formation.domain.formation.Niveau;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.formation.FormationServices;
import com.kleegroup.formation.services.horaires.HorairesServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.lang.Option;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextListModifiable;
import io.vertigo.struts2.core.ContextMdl;
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
	private final ContextForm<Formation> formation = new ContextForm<>("formation", this);
	private final ContextForm<Horaires> horaire = new ContextForm<>("horaire", this);
	private final ContextListModifiable<Horaires> horaires = new ContextListModifiable<>("horaires", this);
	private final ContextList<Utilisateur> formatteurs = new ContextList<>("formatteurs", this);
	private final ContextList<Formation> formations = new ContextList<>("formations", this);
	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);

	private final ContextMdl<Niveau> niveaux = new ContextMdl<>("niveaux", this);
	private final ContextMdl<Utilisateur> utilisateurs = new ContextMdl<>("utilisateurs", this);

	public void initContext(@Named("sesId") final Option<Long> sesId) {
		niveaux.publish(Niveau.class, null);
		utilisateurs.publish(Utilisateur.class, null);
		if (sesId.isPresent()) {
			final SessionFormation sessionFormation = sessionServices.loadSessionFormation(sesId.get());
			session.publish(sessionFormation);
			formation.publish(formationServices.loadFormation(sessionFormation.getForId()));
		} else {
			final UtilisateurCritere utilisateurcritere = new UtilisateurCritere();
			final com.kleegroup.formation.security.Role role = com.kleegroup.formation.security.Role.R_FORMATTEUR;
			utilisateurcritere.setRole(role.toString());
			session.publish(new SessionFormation());
			loadListsForEdit();
			toModeCreate();

		}
	}

	private void loadListsForEdit() {

		final UtilisateurCritere utilisateurcritere = new UtilisateurCritere();
		final com.kleegroup.formation.security.Role role = com.kleegroup.formation.security.Role.R_FORMATTEUR;
		utilisateurcritere.setRole(role.toString());
		formatteurs.publish(utilisateurServices.getUtilisateurListByCritere(utilisateurcritere));
		horaire.publish(new Horaires());
		horaires.publish(new DtList<>(Horaires.class));
		final FormationCritere formationcritere = new FormationCritere();
		formations.publish(formationServices.getFormationListByCritere(formationcritere));

	}

	public String doEdit() {
		session.publish(sessionServices.loadSessionFormation(sesIdRef.get()));
		loadListsForEdit();
		toModeEdit();
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
		sessions.setEtaCode("Publier");
		sessions.setEsuCode("Ouvert");
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
			//			sessions.setEsuCode();
			sessions.setIsOuvert("Ouvert");
			//			sessionServices.saveEtatSessionUtilisateur(etatsessionutilisateur);
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

		}
		sessions.setStatus("Annuler");
		sessions.setIsOuvert("Annuler");
		sessionServices.saveSessionFormation(sessions);
		return SUCCESS;
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
		}
		horaires.publish(new DtList<>(Horaires.class));
		horaire.publish(new Horaires());

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

	@Override
	public Menu getActiveMenu() {
		return Menu.MES_FORMATION;
	}

}
