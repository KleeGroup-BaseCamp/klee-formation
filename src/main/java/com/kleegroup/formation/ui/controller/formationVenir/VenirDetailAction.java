package com.kleegroup.formation.ui.controller.formationVenir;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.Niveau;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.domain.inscription.InscriptionView;
import com.kleegroup.formation.security.Role;
import com.kleegroup.formation.services.formation.FormationServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.services.util.SecurityUtil;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextMdl;
import io.vertigo.struts2.core.ContextRef;

/**
 */
public final class VenirDetailAction extends AbstractKleeFormationActionSupport {

	private static final long serialVersionUID = -4230604077944183713L;

	@Inject
	private SessionServices sessionServices;
	@Inject
	private FormationServices formationServices;
	@Inject
	private InscriptionServices inscriptionServices;

	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);
	private final ContextForm<Formation> formation = new ContextForm<>("formation", this);
	private final ContextForm<SessionFormation> session = new ContextForm<>("sessionTest", this);
	private final ContextList<InscriptionView> inscriptions = new ContextList<>("inscriptions", this);

	private final ContextMdl<Niveau> niveaux = new ContextMdl<>("niveaux", this);
	private final ContextMdl<Utilisateur> utilisateurs = new ContextMdl<>("utilisateurs", this);

	/**insciptionServices
	 * @param forId Id de l'élément a afficher.
	 */
	public void initContext(@Named("sesId") final Long sesId) {
		sesIdRef.set(sesId);
		niveaux.publish(Niveau.class, null);
		utilisateurs.publish(Utilisateur.class, null);

		final SessionFormation sessionFormation = sessionServices.loadSessionFormation(sesId);
		session.publish(sessionFormation);
		formation.publish(formationServices.loadFormation(sessionFormation.getForId()));
		inscriptions.publish(inscriptionServices.getListInscriptionsViewBySessionId(sesId));
	}

	public String doInscrire() {
		inscriptionServices.inscrireUtilisateurASession(sesIdRef.get());
		return "success_inscription";
	}

	public boolean isManager() {
		return SecurityUtil.hasRole(Role.R_RESPONSSABLE);
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
		return Menu.VENIR;
	}

}
