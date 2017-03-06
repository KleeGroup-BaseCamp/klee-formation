package com.kleegroup.formation.ui.controller.catalogue;

import javax.inject.Inject;

import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.FormationCritere;
import com.kleegroup.formation.domain.formation.Niveau;
import com.kleegroup.formation.services.formation.FormationServices;
import com.kleegroup.formation.services.util.SecurityUtil;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextMdl;

/**
 * @author prahmoune
 * @version $Id: ProduitListAction.java,v 1.4 2014/06/27 12:21:39 pchretien Exp $
 */
public final class CatalogueListAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = 1L;

	@Inject
	private FormationServices formationServices;

	private final ContextForm<FormationCritere> formationCritere = new ContextForm<>("formationCritere", this);
	private final ContextList<Formation> formations = new ContextList<>("formations", this);
	private final ContextMdl<Niveau> niveaux = new ContextMdl<>("niveaux", this);

	/** {@inheritDoc} */
	@Override
	public void initContext() {
		SecurityUtil.checkRole(com.kleegroup.formation.security.Roles.R_ADMIN, com.kleegroup.formation.security.Roles.R_FORMATTEUR);
		niveaux.publish(Niveau.class, null);
		formationCritere.publish(new FormationCritere());
		formations.publish(formationServices.getFormationListByCritere(formationCritere.readDto()));
		toModeEdit();

	}

	public String rechercher() {
		formations.publish(formationServices.getFormationListByCritere(formationCritere.readDto()));
		return NONE;

	}

	/** {@inheritDoc} */
	@Override
	public String getPageName() {
		return "Recherche de formation";
	}

	/** {@inheritDoc} */
	@Override
	public Menu getActiveMenu() {
		return Menu.CATALOGUE;
	}
}
