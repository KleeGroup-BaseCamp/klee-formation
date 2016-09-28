package com.kleegroup.formation.ui.controller.accueil;

import javax.inject.Inject;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurLogin;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.UiRequestUtil;

/**
 * @author npiedeloup
 * @version $Id: LoginAction.java,v 1.6 2014/08/04 16:57:50 npiedeloup Exp $
 */
public final class LoginAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = 3517185648660870776L;

	@Inject
	private UtilisateurServices utilisateurServices;

	private final ContextForm<UtilisateurLogin> utilisateurLoginRef = new ContextForm<>("utilisateur", this);

	/** {@inheritDoc} */
	@Override
	public void initContext() {
		final UtilisateurLogin utilisateurLogin = new UtilisateurLogin();
		utilisateurLogin.setLogin("admin");
		utilisateurLogin.setPassword("adminadmin");
		utilisateurLoginRef.publish(utilisateurLogin);
		toModeEdit();
	}

	/**
	 * Connexion.
	 * @return outcome du login
	 */
	public String doLogin() {
		final UtilisateurLogin utilisateurLogin = utilisateurLoginRef.readDto();
		final Utilisateur utilisateur = utilisateurServices.connecterUtilisateur(utilisateurLogin);
		return SUCCESS; //success va sur accueil
	}

	/**
	 * Déconnexion.
	 * @return outcome du logout
	 */
	public String doLogout() {
		UiRequestUtil.invalidateHttpSession();
		return "reload"; // reload la page
	}

	/** {@inheritDoc} */
	@Override
	public String getPageName() {
		return "Page de connexion";
	}

	/** {@inheritDoc} */
	@Override
	public Menu getActiveMenu() {
		return Menu.ROOT_MENU;
	}
}
