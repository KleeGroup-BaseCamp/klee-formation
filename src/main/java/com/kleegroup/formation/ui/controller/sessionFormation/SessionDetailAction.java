package com.kleegroup.formation.ui.controller.sessionFormation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.DtDefinitions.SessionFormationFields;
import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere;
import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.FormationCritere;
import com.kleegroup.formation.domain.formation.Horaires;
import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.formation.Niveau;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.domain.inscription.InscriptionView;
import com.kleegroup.formation.resources.Resources;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.formation.FormationServices;
import com.kleegroup.formation.services.horaires.HorairesServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.mail.MailServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.lang.MessageText;
import io.vertigo.lang.VUserException;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextListModifiable;
import io.vertigo.struts2.core.ContextMdl;
import io.vertigo.struts2.core.ContextRef;
import io.vertigo.util.DateBuilder;
import io.vertigo.util.DateUtil;
import io.vertigo.vega.webservice.model.UiObject;

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
	@Inject
	private MailServices mailServices;
	@Inject
	private InscriptionServices inscriptionServices;

	private final ContextForm<SessionFormation> session = new ContextForm<>("sessionTest", this);
	private final ContextForm<Formation> formation = new ContextForm<>("formation", this);
	private final ContextForm<Horaires> horaire = new ContextForm<>("horaire", this);
	private final ContextListModifiable<Horaires> horaires = new ContextListModifiable<>("horaires", this);
	private final ContextList<Utilisateur> formatteurs = new ContextList<>("formatteurs", this);
	private final ContextList<Formation> formations = new ContextList<>("formations", this);
	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);
	private final ContextMdl<Niveau> niveaux = new ContextMdl<>("niveaux", this);
	private final ContextMdl<Utilisateur> utilisateurs = new ContextMdl<>("utilisateurs", this);
	private final ContextList<InscriptionView> inscriptions = new ContextList<>("inscriptions", this);

	public void initContext(@Named("sesId") final Optional<Long> sesId) {
		niveaux.publish(Niveau.class, null);
		utilisateurs.publish(Utilisateur.class, null);
		if (sesId.isPresent()) {
			final SessionFormation sessionFormation = sessionServices.loadSessionFormation(sesId.get());
			session.publish(sessionFormation);
			formation.publish(formationServices.loadFormation(sessionFormation.getForId()));
			horaires.publish(horairesServices.getHoraires(sessionFormation));
			inscriptions.publish(inscriptionServices.getListInscriptionsViewBySessionId(sesId.get()));
		} else {
			final UtilisateurCritere utilisateurcritere = new UtilisateurCritere();
			final com.kleegroup.formation.security.Roles role = com.kleegroup.formation.security.Roles.R_FORMATTEUR;
			utilisateurcritere.setRole(role.toString());
			session.publish(new SessionFormation());
			horaires.publish(new DtList<>(Horaires.class));
			loadListsForEdit();
			toModeCreate();
		}
	}

	private void loadListsForEdit() {
		final UtilisateurCritere utilisateurcritere = new UtilisateurCritere();
		final com.kleegroup.formation.security.Roles role = com.kleegroup.formation.security.Roles.R_FORMATTEUR;
		utilisateurcritere.setRole(role.toString());
		formatteurs.publish(utilisateurServices.getUtilisateurListByCritere(utilisateurcritere));
		final FormationCritere formationcritere = new FormationCritere();
		formations.publish(formationServices.getFormationListByCritere(formationcritere));
	}

	public String doEdit() {
		final SessionFormation my_session = sessionServices.loadSessionFormation(sesIdRef.get());
		session.publish(my_session);
		horaires.publish(horairesServices.getHoraires(my_session));
		loadListsForEdit();
		toModeEdit();
		return NONE;
	}

	public String doSave() {
		final SessionFormation sessions = session.readDto();
		if (isModeCreate()) {
			final BigDecimal satisfaction = new BigDecimal(0);
			sessions.setStatus("Brouillon");
			sessions.setEtaCode("Brouillon");
			doSaveSession(satisfaction, sessions);
		} else if (isModeEdit()) {
			final BigDecimal satisfaction = null;
			doSaveSession(satisfaction, sessions);

		}
		return SUCCESS;
	}

	public String doPublish() {
		final SessionFormation sessions = session.readDto();
		if (isModeCreate()) {
			final BigDecimal satisfaction = new BigDecimal(0);
			sessions.setEtaCode("Publier");
			sessions.setStatus("Publier");
			sessions.setEsuCode("Ouverte");
			sessionServices.saveSessionFormation(sessions);
			doSaveSession(satisfaction, sessions);
		} else if (isModeEdit()) {
			final BigDecimal satisfaction = null;
			doSaveSession(satisfaction, sessions);

			final DtList<Inscription> insc = inscriptionServices.getListInscriptionsBySessionId(session.readDto().getSesId());
			if (insc != null) {
				int i = 0;
				while (i < insc.size()) {

					final Utilisateur uti = utilisateurServices.loadUtilisateurWithRoles(insc.get(i).getUtiId());
					mailServices.envoyerModification(formation.readDto(), session.readDto(), uti.getMail());
					i = i + 1;
				}

			}
		}
		return SUCCESS;
	}

	/*
		private void DateIsCorrect(final SessionFormation session) {
			if (DateUtil.newDate().after(session.getDateDebut())) {
				throw new VUserException(new MessageText(Resources.SESSION_DATE_MUST_BE_IN_FUTURE));
			}
			if (session.getDateDebut().compareTo(session.getDateFin()) > 0) {
				throw new VUserException(new MessageText(Resources.SESSION_DATE_FIN_MUST_BE_IN_FUTURE));
			}
		}*/

	private void HoraireIsCorrect(final DtList<Horaires> horairess) {
		int i = 0;

		/*	if (session.getDateDebut().compareTo(session.getDateFin()) > 0) {
		throw new VUserException(new MessageText(Resources.SESSION_DATE_FIN_MUST_BE_IN_FUTURE));
		}*/
		while (i < horairess.size()) {
			if (horairess.get(i).getFin().compareTo(horairess.get(i).getDebut()) < 0) {
				throw new VUserException(new MessageText(Resources.HORAIRES_INCORRECTS));
			}
			if (horairess.get(i).getFinAprem().compareTo(horairess.get(i).getDebutAprem()) < 0) {
				throw new VUserException(new MessageText(Resources.HORAIRES_INCORRECTS));
			}

			if (horairess.get(i).getFin() - horairess.get(i).getDebut() >= 360) {
				throw new VUserException(new MessageText(Resources.HORAIRES_INCORRECTS));
			}

			if (horairess.get(i).getFinAprem() - horairess.get(i).getDebutAprem() >= 360) {
				throw new VUserException(new MessageText(Resources.HORAIRES_INCORRECTS));
			}
			i = i + 1;
		}
	}

	private void doSaveSession(final BigDecimal satisfaction, final SessionFormation sessions) {

		final DtList<Horaires> horairess = horaires.readDtList();
		if (satisfaction != null) {
			sessions.setSatisfaction(satisfaction);
			sessions.setI(satisfaction);
			sessions.setEsuCode("Ouverte");
			sessionServices.saveSessionFormation(sessions);
		}
		if (!horairess.isEmpty()) {
			HoraireIsCorrect(horairess);
			sessions.setHoraire(horairesServices.saveHoraires(horairess, sessions.getSesId()));
			sessions.setDateDebut(horairess.get(0).getJour());
			sessions.setDateFin(horairess.get(horairess.size() - 1).getJour());
			final Long durée = (horairess.get(horairess.size() - 1).getJour().getTime() - horairess.get(0).getJour().getTime()) / 3600000 / 24;
			sessions.setDuree(durée + 1);
			sessionServices.saveSessionFormation(sessions);
		} else {
			final Date dateDebut = sessions.getDateDebut();
			final Date dateFin = sessions.getDateFin();
			final DtList<Horaires> horairesNoModif = new DtList<>(Horaires.class);
			while (dateDebut.compareTo(dateFin) <= 0) {
				horairesNoModif.add(defaultHoraire(new Date(dateDebut.getTime())));
				dateDebut.setDate(dateDebut.getDate() + 1);
			}
			sessions.setDateDebut(horairesNoModif.get(0).getJour());
			sessions.setDateFin(horairesNoModif.get(horairesNoModif.size() - 1).getJour());
			sessions.setHoraire(horairesServices.saveHoraires(horairesNoModif, sessions.getSesId()));
			final Long durée = (horairesNoModif.get(horairesNoModif.size() - 1).getJour().getTime() - horairesNoModif.get(0).getJour().getTime()) / 3600000 / 24;
			sessions.setDuree(durée + 1);
			sessionServices.saveSessionFormation(sessions);
		}

	}

	public String doDelete() {
		final SessionFormation sessions = session.readDto();
		final String status = sessions.getStatus();
		//final String etat = sessions.getEtat().getEtaCode();
		if (status.equals("Brouillon")) {
			sessionServices.deleteSessionCascade(sesIdRef.get());
			horairesServices.deleteHoraires(session.readDto().getSesId());
			sessionServices.deleteSessionFormation(session.readDto().getSesId());
			return "success_delete";
		} else {
			sessions.setStatus("Annulée");
			sessions.setEtaCode("Annuler");
			//sessions.setIsOuvert("Annuler");
			sessions.setEsuCode("Annulée");
			sessionServices.saveSessionFormation(sessions);
			final DtList<Inscription> insc = inscriptionServices.getListInscriptionsBySessionId(session.readDto().getSesId());
			if (insc != null) {
				int i = 0;
				while (i < insc.size()) {

					final Utilisateur uti = utilisateurServices.loadUtilisateurWithRoles(insc.get(i).getUtiId());
					mailServices.envoyersupression(formation.readDto(), session.readDto(), uti.getMail());
					i = i + 1;
				}

			}
			return SUCCESS;
		}
		/*	if (etat.equals("Brouillon")) {
				sessionServices.deleteSessionCascade(sesIdRef.get());
				horairesServices.deleteHoraires(session.readDto().getSesId());
				sessionServices.deleteSessionFormation(session.readDto().getSesId());
				return "success_delete";
			}*/
	}

	@SuppressWarnings("deprecation")
	public String doDatemodif() {
		final SessionFormation session_modif = session.readDto();
		if (DateUtil.newDate().after(session_modif.getDateDebut())) {
			throw new VUserException(new MessageText(Resources.SESSION_DATE_MUST_BE_IN_FUTURE));
		}
		if (session_modif.getDateDebut().compareTo(session_modif.getDateFin()) > 0) {
			throw new VUserException(new MessageText(Resources.SESSION_DATE_FIN_MUST_BE_IN_FUTURE));
		}

		final Date date_début = session_modif.getDateDebut();
		final Date date_fin = session_modif.getDateFin();
		final DtList<Horaires> horairess = session_modif.getHorairesList();
		final Long durée = (date_fin.getTime() - date_début.getTime()) / 3600000 / 24 + 1;
		if (durée >= horairess.size() && date_début.getTime() == horairess.get(0).getJour().getTime()) {
			int i = horairess.size();
			final Date jour = horairess.get(i - 1).getJour();
			jour.setDate(jour.getDate() + 1);
			while (i < durée) {
				horairess.add(defaultHoraire(new Date(jour.getTime())));
				jour.setDate(jour.getDate() + 1);
				i = i + 1;
			}
			horaires.publish(horairess);
			return NONE;
		} else if (durée == horairess.size()) {
			int i = 0;
			while (i < horairess.size()) {
				horairess.get(i).setJour(new Date(date_début.getTime()));
				date_début.setDate(date_début.getDate() + 1);
				i = i + 1;
			}
			horaires.publish(horairess);
			return NONE;
		} else if (durée >= horairess.size() && date_fin.getTime() == horairess.get(horairess.size() - 1).getJour().getTime()) {
			final DtList<Horaires> horaires_new = new DtList<>(Horaires.class);
			int i = horairess.size();
			final Date jour = date_début;
			while (i < durée) {
				horaires_new.add(defaultHoraire(new Date(jour.getTime())));
				jour.setDate(jour.getDate() + 1);
				i = i + 1;
			}
			i = 0;
			while (i < horairess.size()) {
				horaires_new.add(horairess.get(i));
				i = i + 1;
			}
			//horairesServices.deleteHoraires(session.readDto().getSesId());
			horaires.publish(horaires_new);
			return NONE;
		} else {
			final DtList<Horaires> horaires_new = new DtList<>(Horaires.class);
			int i = 0;
			final Date jour = date_début;
			while (i < durée) {
				if (i < horairess.size()) {
					if (horairess.get(i).getJour().compareTo(jour) == 0) {
						horaires_new.add(horairess.get(i));
					}

				} else {
					horaires_new.add(defaultHoraire(new Date(jour.getTime())));
				}
				jour.setDate(jour.getDate() + 1);
				i = i + 1;
			}
			horaires.publish(horaires_new);
			return NONE;
		}
	}

	@SuppressWarnings("deprecation")
	public String doHoraire() {

		final UiObject<SessionFormation> session_date = session.getUiObject();
		if (session_date.getDate(SessionFormationFields.DATE_DEBUT.name()) == null || session_date.getDate(SessionFormationFields.DATE_FIN.name()) == null) {
			throw new VUserException(new MessageText(Resources.DATES_INCORRECTS));
		}
		if (DateUtil.newDate().after(session_date.getDate(SessionFormationFields.DATE_DEBUT.name()))) {
			throw new VUserException(new MessageText(Resources.SESSION_DATE_MUST_BE_IN_FUTURE));
		}
		if (session_date.getDate(SessionFormationFields.DATE_DEBUT.name()).compareTo(session_date.getDate(SessionFormationFields.DATE_FIN.name())) > 0) {
			throw new VUserException(new MessageText(Resources.SESSION_DATE_FIN_MUST_BE_IN_FUTURE));
		}

		final Date dateDebut = session_date.getDate(SessionFormationFields.DATE_DEBUT.name());
		final Date date_fin = session_date.getDate(SessionFormationFields.DATE_FIN.name());
		final DtList<Horaires> horairess = new DtList<>(Horaires.class);
		Date date = new DateBuilder(dateDebut)
				.build();
		while (date.compareTo(date_fin) <= 0) {
			horairess.add(defaultHoraire(new Date(date.getTime())));
			date = new DateBuilder(date)
					.addDays(1)
					.build();
		}
		horaires.publish(horairess);
		return NONE;
	}

	private Horaires defaultHoraire(final Date jour) {
		final Horaires myHoraire = new Horaires();
		myHoraire.setJour(new Date(jour.getTime()));
		myHoraire.setDebut(9 * 60);
		myHoraire.setFin(12 * 60);
		myHoraire.setDebutAprem(14 * 60);
		myHoraire.setFinAprem(18 * 60);
		return myHoraire;
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
		return Menu.GESTIONS;
	}

}
