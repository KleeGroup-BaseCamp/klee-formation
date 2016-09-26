package com.kleegroup.formation.ui.controller.formations;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.administration.utilisateur.Role;
import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurLogin;
import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.lang.Assertion;
import io.vertigo.lang.Option;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;

/**
 */
public final class FormationsDetailAction extends AbstractKleeFormationActionSupport {

	private static final long serialVersionUID = -4230604077944183713L;

	@Inject
	private SessionServices sessionServices;

	private final ContextForm<SessionFormation> session = new ContextForm<>("sessionTest", this);

	@Inject
	private InscriptionServices inscriptionServices;

	@Inject
	private UtilisateurServices utilisateurServices;

	private final ContextForm<UtilisateurLogin> utilisateurLogin = new ContextForm<>("utilisateurLogin", this);
	private final ContextForm<Utilisateur> utilisateur = new ContextForm<>("utilisateur", this);
	private final ContextList<Role> roles = new ContextList<>("roles", this);
	private final ContextList<Inscription> inscriptions = new ContextList<>("inscriptions", this);

	/**
	 * @param fodId Id de l'élément a afficher.
	 */
	public void initContext(@Named("sesId") final Option<Long> sesId, @Named("forId") final Option<Long> forId, @Named("Intitule") final Option<String> intitule, @Named("utiId") final Option<Long> utiId) {
		if (sesId.isPresent()) {
			Assertion.checkArgument(!forId.isPresent(), "L'id de formation ne doit pas être passé pour le chargement d'une session");
			session.publish(sessionServices.loadSessionFormation(sesId.get()));
			inscriptions.publish(inscriptionServices.getListInscriptionsBySessionId(sesId.get()));

		} else {
			Assertion.checkArgument(forId.isPresent(), "L'id de formation est obligatoire");

			final SessionFormation sessions = new SessionFormation();
			sessions.setForId(forId.get());
			sessions.setFormationName(intitule.get());

			session.publish(sessions);

			inscriptions.publish(inscriptionServices.getListInscriptionsBySessionId(sesId.get()));

			if (utiId.isPresent()) {
				final Utilisateur dtoUtilisateur = utilisateurServices.loadUtilisateurWithRoles(utiId.get());
				utilisateur.publish(dtoUtilisateur);
				roles.publish(dtoUtilisateur.getRoleList());
			} else {
				utilisateur.publish(new Utilisateur());
				roles.publish(new DtList<>(Role.class));
				toModeCreate();
			}
			utilisateurLogin.publish(new UtilisateurLogin());
			toModeCreate();

		}
	}

	public String doEdit() {
		toModeEdit();
		return NONE;
	}

	public String doSave() {
		final SessionFormation sessions = session.readDto();
		sessions.setStatus("Brouillon");
		sessionServices.saveSessionFormation(sessions);
		return SUCCESS;
	}

	public String doPublish() {
		final SessionFormation sessions = session.readDto();
		sessions.setStatus("Publier");
		sessionServices.saveSessionFormation(sessions);
		return SUCCESS;
	}

	public String doDelete() {
		sessionServices.deleteSessionFormation(session.readDto().getSesId());
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
