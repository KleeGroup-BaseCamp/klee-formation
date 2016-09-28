package com.kleegroup.formation.ui.controller.mes_formations;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
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

	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);
	private final ContextList<Inscription> inscriptions = new ContextList<>("inscriptions", this);

	public void initContext(@Named("sesId") final Long sesId) {
		session.publish(sessionServices.loadSessionFormation(sesId));
		inscriptions.publish(inscriptionServices.getListInscriptionsBySessionId(sesId));
	}

	public String doDelete() {
		inscriptionServices.deleteInscription(inscriptionServices.InscriptionByUtiSesId(utilisateurServices.getCurrentUtilisateur().getUtiId(), sesIdRef.get()).getInsId());
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
