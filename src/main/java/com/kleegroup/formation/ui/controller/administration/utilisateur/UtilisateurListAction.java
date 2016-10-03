package com.kleegroup.formation.ui.controller.administration.utilisateur;

import javax.inject.Inject;

import com.kleegroup.formation.domain.administration.utilisateur.Role;
import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.util.SecurityUtil;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextMdl;

/**
 * @author npiedeloup
 * @version $Id: UtilisateurListAction.java,v 1.3 2014/06/27 12:21:39 pchretien Exp $
 */
public final class UtilisateurListAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = -77969871290649147L;

	@Inject
	private UtilisateurServices utilisateurServices;
	private final ContextMdl<Role> roles = new ContextMdl<>("roles", this);
	private final ContextForm<UtilisateurCritere> utilisateurCritereForm = new ContextForm<>("utilisateurCritere", this);
	private final ContextList<Utilisateur> utilisateurs = new ContextList<>("utilisateurs", this);

	/** {@inheritDoc} */
	@Override
	protected void initContext() {
		SecurityUtil.checkRole(com.kleegroup.formation.security.Role.R_ADMIN, com.kleegroup.formation.security.Role.R_RESPONSSABLE);
		roles.publish(Role.class, null);
		utilisateurCritereForm.publish(new UtilisateurCritere());
		utilisateurs.publish(utilisateurServices.getUtilisateurListByCritere(utilisateurCritereForm.readDto()));
		toModeEdit();
	}

	protected void reloadList() {
		final UtilisateurCritere utilisateurCritere = utilisateurCritereForm.readDto();
		utilisateurs.publish(utilisateurServices.getUtilisateurListByCritere(utilisateurCritere));
	}

	public String doRechercher() {
		reloadList();
		return NONE;
	}

	/** {@inheritDoc} */
	//@Override
	@Override
	public Menu getActiveMenu() {
		return Menu.ADMINISTRATION;
	}

	public boolean isAdministrateur() {
		return SecurityUtil.hasRole(com.kleegroup.formation.security.Role.R_ADMIN);
	}

	@Override
	public String getPageName() {
		return "Administration";
	}
}
