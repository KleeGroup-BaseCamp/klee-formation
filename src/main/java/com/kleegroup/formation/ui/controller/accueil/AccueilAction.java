package com.kleegroup.formation.ui.controller.accueil;

import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

/**
 * @author npiedeloup
 * @version $Id: AccueilAction.java,v 1.3 2014/06/27 12:21:39 pchretien Exp $
 */
public final class AccueilAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = -7166081954858375515L;
	private static final String PAGE_TITLE = "Accueil";

	@Override
	protected void initContext() {
		//SecurityUtil.checkRole(com.kleegroup.formation.security.Roles.R_ANONYMOUS);
		//rien
	}

	/** {@inheritDoc} */
	@Override
	public String getPageName() {
		return PAGE_TITLE;
	}

	/** {@inheritDoc} */
	@Override
	public Menu getActiveMenu() {
		return Menu.ACCUEIL;
	}

}
