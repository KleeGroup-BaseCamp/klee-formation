package com.kleegroup.formation.ui.controller.formationVenir;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere;
import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.resources.Resources;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.formation.FormationServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.mail.MailServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.dynamo.file.model.VFile;
import io.vertigo.lang.MessageText;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextRef;

/**
 * @author prahmoune
 * @version $Id: ProduitListAction.java,v 1.4 2014/06/27 12:21:39 pchretien Exp $
 */
public final class UtilisateurAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = 1L;

	@Inject
	private UtilisateurServices utilisateurServices;
	@Inject
	private MailServices mailServices;
	@Inject
	private SessionServices sessionServices;
	@Inject
	private FormationServices formationServices;

	@Inject
	private InscriptionServices inscriptionServices;
	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);
	private final ContextForm<Inscription> inscriptionForm = new ContextForm<>("inscription", this);
	private final ContextList<Utilisateur> utilisateurs = new ContextList<>("utilisateurs", this);

	public void initContext(@Named("sesId") final Optional<Long> sesId) {
		final UtilisateurCritere utilisateurcritere = new UtilisateurCritere();
		utilisateurs.publish(utilisateurServices.getUtilisateurListByCritere(utilisateurcritere));
		inscriptionForm.publish(new Inscription());

		toModeEdit();

	}

	public String doInscrireMoi() throws IOException {
		inscriptionServices.inscrireUtilisateurASession(sesIdRef.get());
		final Formation formation = formationServices.loadFormation(sessionServices.loadSessionbyId(sesIdRef.get()).getForId());
		final SessionFormation session = sessionServices.loadSessionbyId(sesIdRef.get());
		final VFile invitCalendar = mailServices.genererFichierIcvs(formation, session);
		new MessageText(Resources.CONFIRMATION_INSCRIPTION);
		mailServices.envoyerInvitationManager(formation, session, utilisateurServices.getCurrentUtilisateur().getMail(), invitCalendar);
		return "success_inscription";
	}

	public String doInscrire() throws IOException {
		inscriptionServices.inscrireUtilisateur(sesIdRef.get(), inscriptionForm.readDto().getUtiId());
		final Utilisateur uti = utilisateurServices.loadUtilisateurWithRoles(inscriptionForm.readDto().getUtiId());
		final Formation formation = formationServices.loadFormation(sessionServices.loadSessionbyId(sesIdRef.get()).getForId());
		final SessionFormation session = sessionServices.loadSessionbyId(sesIdRef.get());
		final VFile invitCalendar = mailServices.genererFichierIcvs(formation, session);
		new MessageText(Resources.CONFIRMATION_INSCRIPTION);
		mailServices.envoyerInvitationManager(formation, session, uti.getMail(), invitCalendar);

		return "success_inscription";
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

	/** {@inheritDoc} */
	@Override
	public Menu getActiveMenu() {
		return Menu.ADMINISTRATION;
	}

}
