package com.kleegroup.formation.ui.controller.mes_formations;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.DtDefinitions.InscriptionViewFields;
import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.Horaires;
import com.kleegroup.formation.domain.formation.Niveau;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.domain.inscription.InscriptionView;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.formation.FormationServices;
import com.kleegroup.formation.services.horaires.HorairesServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextListModifiable;
import io.vertigo.struts2.core.ContextMdl;
import io.vertigo.struts2.core.ContextRef;

/**
 */
public final class MesDetailAction extends AbstractKleeFormationActionSupport {

	private static final long serialVersionUID = -4230604077944183713L;

	@Inject
	private SessionServices sessionServices;
	private final ContextForm<SessionFormation> session = new ContextForm<>("sessionTest", this);
	@Inject
	private InscriptionServices inscriptionServices;
	@Inject
	private UtilisateurServices utilisateurServices;
	@Inject
	private FormationServices formationServices;
	@Inject
	private HorairesServices horairesServices;

	private final ContextForm<Formation> formation = new ContextForm<>("formation", this);
	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);
	private final ContextList<InscriptionView> inscriptions = new ContextList<>("inscriptions", InscriptionViewFields.SES_ID, this);
	private final ContextMdl<Utilisateur> utilisateursList = new ContextMdl<>("utilisateurs", this);
	private final ContextMdl<Niveau> niveaux = new ContextMdl<>("niveaux", this);
	private final ContextListModifiable<Horaires> horaires = new ContextListModifiable<>("horaires", this);

	public void initContext(@Named("sesId") final Long sesId) {
		niveaux.publish(Niveau.class, null);
		utilisateursList.publish(Utilisateur.class, null);
		final SessionFormation sessionFormation = sessionServices.loadSessionFormation(sesId);
		formation.publish(formationServices.loadFormation(sessionFormation.getForId()));
		session.publish(sessionFormation);
		inscriptions.publish(inscriptionServices.getListInscriptionsViewBySessionId(sesId));
		horaires.publish(horairesServices.getHoraires(sessionFormation));
	}

	public String delete() {
		inscriptionServices.deleteInscription(inscriptionServices.InscriptionByUtiSesId(utilisateurServices.getCurrentUtilisateur().getUtiId(), sesIdRef.get()).getInsId());
		final SessionFormation session_modif = sessionServices.loadSessionbyId(session.readDto().getSesId());
		if (session_modif.getEsuCode() == "Complete") {
			session_modif.setEsuCode("Ouverte");
			sessionServices.saveSessionFormation(session_modif);
		}
		return "success_delete";
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
