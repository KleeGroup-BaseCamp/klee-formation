package com.kleegroup.formation.ui.controller.menu;

import io.vertigo.lang.Assertion;

/**
 * Enumeration décrivant le menu de l'application.
 *
 * @author jtiano
 * @version $Id: codetemplates.xml,v 1.2 2011/06/21 14:33:16 npiedeloup Exp $
 */
public enum Menu {

	/**
	 * Menu racine.
	 */
	ROOT_MENU(),
	/************************************** Menu Accueil. **************************************************/
	ACCUEIL(ROOT_MENU, " Accueil", "VenirList.do", "fa fa-home"), //
	MES_FORMATION(ROOT_MENU, " Mes formations", "MesList.do", "fa fa-user"), //
	//VENIR(ROOT_MENU, "Formations à venir", "VenirList.do"), //
	GESTIONS(ROOT_MENU, " Création de session", "SessionList.do", "fa fa-file-text"), //
	CATALOGUE(ROOT_MENU, " Création de formation", "CatalogueList.do", "fa fa-book"), //
	ADMINISTRATION(ROOT_MENU, " Administration", "UtilisateurList.do", "fa fa-users");

	/**
	 * Associated navigation item.
	 */
	private final NavigationItem navItem;

	/**
	 * Sous menus.
	 */
	private NavigationItem[] children;

	/**
	 * Raccourcis.
	 */
	private Menu() {
		navItem = new NavigationItem(name(), "Accueil", "VenirList.do", null);
		//navItem = new NavigationItem(name(), "Accueil", "Accueil.do");
	}

	/**
	 * Raccourcis.
	 *
	 * @param parent
	 * @param address
	 */
	private Menu(final Menu parent, final String label, final String adresse, final String icon) {
		Assertion.checkNotNull(parent);
		// ---------------------------------------------------------------------
		navItem = new NavigationItem(name(), label, adresse, icon);
		parent.getNavItem().addSubItem(navItem);
	}

	/**
	 * Retourne un tableau contenant les menus enfants.
	 *
	 * @return NavigationItem[]
	 */
	public NavigationItem[] getChildren() {
		if (children != null) {
			return children;
		}
		children = new NavigationItem[navItem.getSubItems().size()];
		for (int i = 0; i < navItem.getSubItems().size(); i++) {
			children[i] = navItem.getSubItems().get(i);
		}
		return children;
	}

	/**
	 * getter of the navItem.
	 *
	 * @return the navItem
	 */
	public NavigationItem getNavItem() {
		return navItem;
	}

	/**
	 * Accesseur pour simuler la propriété 'Name' du Menu pour l'accès depuis les JSP.
	 *
	 * @return name
	 */
	public String getName() {
		return name();
	}
}
