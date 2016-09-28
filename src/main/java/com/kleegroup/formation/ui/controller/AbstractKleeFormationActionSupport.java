package com.kleegroup.formation.ui.controller;

import org.apache.struts2.ServletActionContext;

import com.kleegroup.formation.ui.controller.menu.Menu;
import com.kleegroup.formation.ui.controller.menu.NavigationItem;

import io.vertigo.struts2.core.AbstractActionSupport;
import io.vertigo.struts2.impl.MethodUtil;
import io.vertigo.struts2.impl.servlet.RequestContainerWrapper;

/**
 * Super class des Actions struts.
 *
 * @author npiedeloup
 * @version $Id: AbstractKleeFormationActionSupport.java,v 1.2 2013/11/18 10:26:13 npiedeloup Exp $
 */
public abstract class AbstractKleeFormationActionSupport extends AbstractActionSupport {

	private static final long serialVersionUID = 374760712087148984L;

	/**
	* Constructeur.
	*/
	protected AbstractKleeFormationActionSupport() {
		//rien
	}

	/** {@inheritDoc} */
	@Override
	protected void initContext() {
		final RequestContainerWrapper container = new RequestContainerWrapper(ServletActionContext.getRequest());
		MethodUtil.invoke(this, "initContext", container);
	}

	/**
	* Initialisation du context.
	*/
	@Override
	protected final void preInitContext() {
		super.preInitContext();
		getModel().put("menuItems", Menu.ROOT_MENU.getChildren());
		getModel().put("activeMenu", getActiveMenu());
		// Enregistrement des informations des sous-menus si besoin.
		if (getActiveSubMenu() != null) {
			getModel().put("subMenuItems", getSubMenuItems());
			getModel().put("activeSubMenu", getActiveSubMenu());
		}
	}

	/**
		* Retourne le menu actif.
		*
		* @return Menu
		*/
	public abstract Menu getActiveMenu();

	/**
	* Retourne le sous-menu actif si besoin.
	*
	* @return sous menu actif
	*/
	protected Menu getActiveSubMenu() {
		// A surcharger si besoin
		return null;
	}

	/**
		* Retourne la liste des sous menu d'un menu .
		*
		* @return liste des enfant d'un menu
		*/
	public final NavigationItem[] getSubMenuItems() {
		return getActiveMenu().getChildren();
	}

	/**
	 * Retourne le nom de la page.
	 * @return Nom de la page.
	 */
	public abstract String getPageName();

}
