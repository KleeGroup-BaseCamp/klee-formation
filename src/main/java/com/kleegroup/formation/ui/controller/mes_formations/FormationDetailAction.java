package com.kleegroup.formation.ui.controller.mes_formations;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;

import io.vertigo.lang.Option;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextRef;

/**
 */
public final class FormationDetailAction extends AbstractKleeFormationActionSupport {

	private static final long serialVersionUID = -4230604077944183713L;

	@Inject
	private SessionServices sessionServices;

	private final ContextForm<SessionFormation> session = new ContextForm<>("sessionTest", this);

	@Inject
	private InscriptionServices inscriptionServices;

	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);
	private final ContextList<Inscription> inscriptions = new ContextList<>("inscriptions", this);

	/**
	 * @param fodId Id de l'élément a afficher.
	 */
	public void initContext(@Named("sesId") final Option<Long> sesId) {
		if (sesId.isPresent()) {
			session.publish(sessionServices.loadSessionFormation(sesId.get()));
			inscriptions.publish(inscriptionServices.getListInscriptionsBySessionId(sesId.get()));

			if (inscriptionServices.getListInscriptionsBySessionId(sesId.get()).size() > 0) {
				if (inscriptionServices.getListInscriptionsBySessionId(sesId.get()).get(0).getPresence() == null) {
					toModeEdit();
				} else {
					toModeReadOnly();
				}

			}
		}

	}

	public String doSave() {
		for (final Inscription inscription : inscriptions.readDtList()) {
			final SessionFormation session = sessionServices.loadSessionbyId(sesIdRef.get());
			session.setStatus("Réaliser");
			sessionServices.saveSessionFormation(session);
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

}
