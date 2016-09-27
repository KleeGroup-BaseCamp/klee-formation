package com.kleegroup.formation.ui.controller.administration.utilisateur;

import javax.inject.Inject;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.util.SecurityUtil;

import io.vertigo.struts2.core.AbstractActionSupport;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextRef;

/**
 * @author npiedeloup
 * @version $Id: UtilisateurListAction.java,v 1.3 2014/06/27 12:21:39 pchretien Exp $
 */
public final class UtilisateurListAction extends AbstractActionSupport {
	private static final long serialVersionUID = -77969871290649147L;

	@Inject
	private UtilisateurServices utilisateurServices;

	private final ContextForm<UtilisateurCritere> utilisateurCritereForm = new ContextForm<>("utilisateurCritere", this);
	private final ContextRef<Boolean> allUserRef = new ContextRef<>("allUser", Boolean.class, this);
	private final ContextList<Utilisateur> utilisateurs = new ContextList<>("utilisateurs", this);

	/** {@inheritDoc} */
	@Override
	protected void initContext() {
		SecurityUtil.checkRole(com.kleegroup.formation.security.Role.R_ADMIN);
		utilisateurCritereForm.publish(new UtilisateurCritere());
		allUserRef.set(Boolean.TRUE);
		utilisateurs.publish(utilisateurServices.getUtilisateurListByCritere(utilisateurCritereForm.readDto()));
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
		reloadList();
		return NONE;
	}

	public String doRechercherActif() {
		utilisateurCritereForm.getUiObject().setTypedValue("isActif", true);
		allUserRef.set(Boolean.FALSE);
		reloadList();
		return NONE;
	}

	public String doRechercherInactif() {
		utilisateurCritereForm.getUiObject().setTypedValue("isActif", false);
		allUserRef.set(Boolean.FALSE);
		reloadList();
		return NONE;
	}

	public String doRechercherTous() {
		utilisateurCritereForm.getUiObject().setTypedValue("isActif", false);
		allUserRef.set(Boolean.TRUE);
		reloadList();
		return NONE;
	}
}