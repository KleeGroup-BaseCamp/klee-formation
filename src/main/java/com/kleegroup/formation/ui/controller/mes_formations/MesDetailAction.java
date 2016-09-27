package com.kleegroup.formation.ui.controller.mes_formations;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;

import io.vertigo.lang.Option;
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

	private final ContextForm<Inscription> inscription = new ContextForm<>("inscription", this);
	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);
	private final ContextList<Inscription> inscriptions = new ContextList<>("inscriptions", this);

	public void initContext(@Named("sesId") final Option<Long> sesId) {
		if (sesId.isPresent()) {
			session.publish(sessionServices.loadSessionFormation(sesId.get()));
			inscriptions.publish(inscriptionServices.getListInscriptionsBySessionId(sesId.get()));

			inscription.publish(new Inscription());
		}
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

}
