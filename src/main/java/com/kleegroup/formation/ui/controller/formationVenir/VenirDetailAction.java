package com.kleegroup.formation.ui.controller.formationVenir;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.security.Role;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.services.util.SecurityUtil;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;

import io.vertigo.lang.Assertion;
import io.vertigo.lang.Option;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextRef;

/**
 */
public final class VenirDetailAction extends AbstractKleeFormationActionSupport {

	private static final long serialVersionUID = -4230604077944183713L;

	@Inject
	private SessionServices sessionServices;

	@Inject
	private InscriptionServices inscriptionServices;

	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);
	private final ContextForm<SessionFormation> session = new ContextForm<>("sessionTest", this);
	private final ContextList<Inscription> inscriptions = new ContextList<>("inscriptions", this);

	Long id;

	/**insciptionServices
	 * @param forId Id de l'élément a afficher.
	 */
	public void initContext(@Named("sesId") final Option<Long> sesId, @Named("forId") final Option<Long> forId, @Named("Intitule") final Option<String> intitule) {
		if (sesId.isPresent()) {
			sesIdRef.set(sesId.get());
			Assertion.checkArgument(!forId.isPresent(), "L'id de formation ne doit pas être passé pour le chargement d'une session");
			session.publish(sessionServices.loadSessionFormation(sesId.get()));
			inscriptions.publish(inscriptionServices.getListInscriptionsBySessionId(sesId.get()));

		} else {
			Assertion.checkArgument(forId.isPresent(), "L'id de formation est obligatoire");

			final SessionFormation sessions = new SessionFormation();
			sessions.setForId(forId.get());
			sessions.setFormationName(intitule.get());

			session.publish(sessions);
			toModeCreate();

		}
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

}
