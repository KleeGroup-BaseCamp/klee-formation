package com.kleegroup.formation.ui.controller.mes_formations;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.formation.Niveau;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.formation.FormationServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextListModifiable;
import io.vertigo.struts2.core.ContextMdl;
import io.vertigo.struts2.core.ContextRef;

/**
 */
public final class FormationDetailAction extends AbstractKleeFormationActionSupport {

	private static final long serialVersionUID = -4230604077944183713L;

	@Inject
	private SessionServices sessionServices;

	@Inject
	private FormationServices formationServices;

	private final ContextForm<SessionFormation> session = new ContextForm<>("sessionTest", this);

	@Inject
	private InscriptionServices inscriptionServices;

	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);
	private final ContextListModifiable<Inscription> inscriptions = new ContextListModifiable<>("inscriptions", this);
	private final ContextForm<Formation> formation = new ContextForm<>("formation", this);
	private final ContextMdl<Utilisateur> utilisateursList = new ContextMdl<>("utilisateurs", this);
	private final ContextMdl<Niveau> niveaux = new ContextMdl<>("niveaux", this);

	public void initContext(@Named("sesId") final Long sesId) {
		niveaux.publish(Niveau.class, null);
		utilisateursList.publish(Utilisateur.class, null);
		final SessionFormation sessionFormation = sessionServices.loadSessionFormation(sesId);
		session.publish(sessionFormation);
		formation.publish(formationServices.loadFormation(sessionFormation.getForId()));
		final DtList<Inscription> myInscriptions = inscriptionServices.getListInscriptionsBySessionId(sesId);

		inscriptions.publish(myInscriptions);

		if (myInscriptions.size() > 0) {
			if (myInscriptions.get(0).getPresence() == null) {
				toModeEdit();
			} else {
				toModeReadOnly();
			}

		}

	}

	public String doSave() {
		final SessionFormation sessionFormation = sessionServices.loadSessionbyId(sesIdRef.get());
		sessionFormation.setEtaCode("Realiser");
		sessionServices.saveSessionFormation(sessionFormation);
		for (final Inscription inscription : inscriptions.readDtList()) {
			inscriptionServices.saveInscription(inscription);
		}
		toModeReadOnly();
		return "success";
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
