package com.kleegroup.formation.ui.controller.formations;

import javax.inject.Inject;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.services.util.SecurityUtil;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextRef;

/**
 * @author prahmoune
 * @version $Id: ProduitListAction.java,v 1.4 2014/06/27 12:21:39 pchretien Exp $
 */
public final class FormationsListAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = 1L;

	@Inject
	private SessionServices sessionServices;

	private final ContextForm<SessionFormation> session = new ContextForm<>("session", this);
	private final ContextList<SessionFormation> sessions = new ContextList<>("sessions", this);

	@Inject
	private UtilisateurServices utilisateurServices;

	private final ContextForm<UtilisateurCritere> utilisateurCritereForm = new ContextForm<>("utilisateurCritere", this);
	private final ContextRef<Boolean> allUserRef = new ContextRef<>("allUser", Boolean.class, this);
	private final ContextList<Utilisateur> utilisateurs = new ContextList<>("utilisateurs", this);

	/** {@inheritDoc} */
	@Override
	public void initContext() {
		SecurityUtil.checkRole(com.kleegroup.formation.security.Role.R_RESPONSSABLE);
		session.publish(new SessionFormation());

		sessions.publish(sessionServices.listSessionByEtat());

		utilisateurCritereForm.publish(new UtilisateurCritere());
		allUserRef.set(true);
		utilisateurs.publish(new DtList<Utilisateur>(DtObjectUtil.findDtDefinition(Utilisateur.class)));

		toModeEdit();
	}

	protected void reloadList() {
		final UtilisateurCritere utilisateurCritere = utilisateurCritereForm.readDto();
		if (allUserRef.get()) {
			utilisateurCritere.setIsActif(null); //pr�f�rer un autre boolean dans le crit�re : ici c'est pour test
		}
		utilisateurs.publish(utilisateurServices.getUtilisateurListByCritere(utilisateurCritere));
	}

	public String doRechercher() {
		sessions.publish(sessionServices.getSessionListByCritere(session.readDto()));
		return NONE;
	}

	/** {@inheritDoc} */
	@Override
	public String getPageName() {
		return "Recherche session de formation";
	}

	public String doUtilisateurRechercher() {
		reloadList();
		return NONE;
	}

	public String doRechercherActif() {
		utilisateurCritereForm.getUiObject().setTypedValue("isActif", true);
		allUserRef.set(false);
		reloadList();
		return NONE;
	}

	public String doRechercherInactif() {
		utilisateurCritereForm.getUiObject().setTypedValue("isActif", false);
		allUserRef.set(false);
		reloadList();
		return NONE;
	}

	public String doRechercherTous() {
		utilisateurCritereForm.getUiObject().setTypedValue("isActif", false);
		allUserRef.set(true);
		reloadList();
		return NONE;
	}
}
