package com.kleegroup.formation.ui.controller.formationVenir;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.Horaires;
import com.kleegroup.formation.domain.formation.Niveau;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.domain.inscription.InscriptionView;
import com.kleegroup.formation.security.Roles;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.formation.FormationServices;
import com.kleegroup.formation.services.horaires.HorairesServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.mail.MailServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.services.util.SecurityUtil;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextListModifiable;
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
	@Inject
	private UtilisateurServices utilisateurServices;
	@Inject
	private HorairesServices horairesServices;

	@Inject
	private MailServices mailServices;

	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);
	private final ContextForm<Formation> formation = new ContextForm<>("formation", this);
	private final ContextForm<SessionFormation> session = new ContextForm<>("sessionTest", this);
	private final ContextList<InscriptionView> inscriptions = new ContextList<>("inscriptions", this);
	private final ContextListModifiable<Horaires> horaires = new ContextListModifiable<>("horaires", this);
	private final ContextMdl<Niveau> niveaux = new ContextMdl<>("niveaux", this);
	private final ContextMdl<Utilisateur> utilisateurs = new ContextMdl<>("utilisateurs", this);

	/**insciptionServices
	 * @param sesId Id de l'élément a afficher.
	 */
	public void initContext(@Named("sesId") final Long sesId) {
		sesIdRef.set(sesId);
		niveaux.publish(Niveau.class, null);
		utilisateurs.publish(Utilisateur.class, null);
		final SessionFormation sessionFormation = sessionServices.loadSessionFormation(sesId);
		session.publish(sessionFormation);
		formation.publish(formationServices.loadFormation(sessionFormation.getForId()));
		inscriptions.publish(inscriptionServices.getListInscriptionsViewBySessionId(sesId));
		horaires.publish(horairesServices.getHoraires(sessionFormation));
	}

	public String doInscrire() throws IOException {
		inscriptionServices.inscrireUtilisateurASession(sesIdRef.get());
		mailServices.genererFichierIcvs(formation.readDto(), session.readDto());
		//mailServices.envoyerInvitation(, utilisateurServices.getCurrentUtilisateur().getMail());

		mailServices.envoyerInvitation(formation.readDto(), session.readDto(), utilisateurServices.getCurrentUtilisateur().getMail());
		//TODO : virer new MessageText(Resources.CONFIRMATION_INSCRIPTION);
		return "success_inscription";
	}

	public boolean isManager() {
		return SecurityUtil.hasRole(Roles.R_RESPONSSABLE);
	}

	public boolean isComplet() {
		if (session.readDto().getEsuCode().equals("Complete")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isInscrit() {
		final Long uti_id_courant = utilisateurServices.getCurrentUtilisateur().getUtiId();
		final DtList<InscriptionView> inscriptions_uti = inscriptionServices.getListInscriptionByUtiId(uti_id_courant);
		int i = 0;
		while (i < inscriptions_uti.size()) {
			if (inscriptions_uti.get(i).getSesId().equals(session.readDto().getSesId())) {
				return false;
			}
			i++;
		}
		return true;
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
		return Menu.ACCUEIL;
	}

}
