package com.kleegroup.formation.ui.controller.catalogue;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.Niveau;
import com.kleegroup.formation.services.formation.FormationServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextMdl;

/**
 */
public final class CatalogueDetailAction extends AbstractKleeFormationActionSupport {

	private static final long serialVersionUID = -4230604077944183713L;

	@Inject
	private FormationServices formationServices;

	public Long test;
	private final ContextForm<Formation> formation = new ContextForm<>("formation", this);
	private final ContextMdl<Niveau> niveaux = new ContextMdl<>("niveaux", this);

	/**
	 * @param forId Id de l'élément a afficher.
	 */
	public void initContext(@Named("forId") final Optional<Long> forId) {
		if (forId.isPresent()) {
			formation.publish(formationServices.loadFormation(forId.get()));
		} else {
			formation.publish(new Formation());
			toModeCreate();
		}
		niveaux.publish(Niveau.class, null);
	}

	public String doEdit() {
		toModeEdit();
		return NONE;
	}

	public String doSave() {
		if (isModeCreate() || isModeEdit()) {
			formationServices.saveFormation(formation.readDto());
		}
		return SUCCESS;
	}

	public String doDelete() {
		formationServices.deleteFormation(formation.readDto().getForId());
		return "success_delete";
	}

	@Override
	public String getPageName() {
		if (isModeCreate()) {
			return "creation d'une formation";
		} else if (isModeEdit()) {
			return "Modification d'une formation";
		}
		return "Detail d'une formation";
	}

	@Override
	public Menu getActiveMenu() {
		return Menu.CATALOGUE;
	}
}
