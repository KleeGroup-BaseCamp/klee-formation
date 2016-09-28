package com.kleegroup.formation.ui.controller.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kleegroup.formation.services.util.SecurityUtil;

import io.vertigo.lang.Assertion;
import io.vertigo.util.StringUtil;

/**
 * Items de la navigation.
 *
 * @author jmosset
 * @version $Id: codetemplates.xml,v 1.2 2011/06/21 14:33:16 npiedeloup Exp $
 */
public class NavigationItem implements Serializable {

	private static final long serialVersionUID = 321710149087020644L;

	private static final String CODE_REQUIRED = "The navigation item {0} must have a code.";

	// Parent
	private NavigationItem parent;

	// Sous menus
	private final List<NavigationItem> subItems = new ArrayList<>();

	// code
	private final String code;

	// Label
	private final String label;

	// Adresse
	private final String address;

	/**
	 * Constructeur.
	 *
	 * @param code unique
	 * @param label affiché à l'utilisateur
	 * @param address Adresse
	 */
	public NavigationItem(final String code, final String label, final String address) {
		Assertion.checkNotNull(code, CODE_REQUIRED, label);
		this.code = code;
		this.label = label;
		this.address = address;
	}

	/**
	 * Construction du menu arborescent en indiquant le parent.
	 *
	 * @param parent parent
	 */
	public void setParent(final NavigationItem parent) {
		this.parent = parent;
	}

	/**
	 * getter of the subItems.
	 *
	 * @return the subItems non modifiables
	 */
	public List<NavigationItem> getSubItems() {
		return new ArrayList<>(subItems);
	}

	/**
	 * Permet de retouver le parent.
	 *
	 * @return le parent
	 */
	public NavigationItem getParent() {
		return parent;
	}

	/**
	 * Permet de retrouver la racine de l'arborescence du menu.
	 *
	 * @return le parent racine
	 */
	public List<NavigationItem> getParents() {
		final List<NavigationItem> list;
		if (parent != null) {
			list = parent.getParents();
		} else {
			list = new ArrayList<>();
		}
		list.add(this);
		return list;
	}

	/**
	 * Ajout un enfant dans l'arborescence du menu. execute : nav.setParent(this).
	 *
	 * @param nav l'enfant
	 */
	public void addSubItem(final NavigationItem nav) {
		nav.setParent(this);
		subItems.add(nav);
	}

	/**
	 * Ajout une liste d'enfants dans l'arborescence du menu.
	 *
	 * @param navSubItemList la liste
	 */
	public void addAllSubItems(final List<NavigationItem> navSubItemList) {
		for (final NavigationItem navigationItem : navSubItemList) {
			addSubItem(navigationItem);
		}

	}

	/**
	 * Retourne vrai si l'item possède des sous items.
	 *
	 * @return Booléen
	 */
	public boolean hasChildren() {
		return subItems != null && !subItems.isEmpty();
	}

	/**
	 * Donne la valeur de label.
	 *
	 * @return label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Donne la valeur de code.
	 *
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Donne la valeur d'adresse.
	 *
	 * @return adresse
	 */
	public String getAddress() {
		return viewMenu() ? address : null;
	}

	public String getRawAddress() {
		return address == null ? null : address.split("\\?")[0];
	}

	/**
	 * Vérifie si l'utilisateur connecté a les droits d'accès.
	 *
	 * @return boolean
	 */
	public boolean hasAccess() {
		return address == null || SecurityUtil.hasAccess(this, "OP_READ");
	}

	/**
	 * Retourne vrai si l'utilisateur a les droits d'accès au menu et si l'adresse doit être affichée.
	 *
	 * @return Booléen
	 */
	public boolean viewMenu() {
		// Droits insuffisants
		if (!hasAccess()) {
			return false;
		}
		// Pour un menu, on vérifie qu'au moins un sous menu est actif.
		if (getParent() != null && address == null && Menu.ROOT_MENU.getNavItem().getCode().equals(getParent().getCode())) {
			boolean hasSubMenus = false;
			for (final NavigationItem subMenu : getSubItems()) {
				if (subMenu.hasAccess()) {
					hasSubMenus = true;
					break;
				}
			}
			if (!hasSubMenus) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Retourne vrai si l'adresse est vide.
	 *
	 * @return Booléen
	 */
	public boolean isAddressEmpty() {
		return StringUtil.isEmpty(getAddress());
	}

}
