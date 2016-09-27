package com.kleegroup.formation.ui.controller.formation;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.Niveau;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.formation.FormationServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.lang.Option;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextMdl;

/**
 */
public final class FormationDetailAction extends AbstractKleeFormationActionSupport {

	private static final long serialVersionUID = -4230604077944183713L;

	@Inject
	private FormationServices formationServices;

	@Inject
	private SessionServices sessionServices;

	public Long test;

	private final ContextForm<Formation> formation = new ContextForm<>("formation", this);
	private final ContextList<SessionFormation> sessions = new ContextList<>("sessions", this);
	private final ContextMdl<Niveau> niveaux = new ContextMdl<>("niveaux", this);

	/**
	 * @param forId Id de l'élément a afficher.
	 */
	public void initContext(@Named("forId") final Option<Long> forId) {
		if (forId.isPresent()) {
			formation.publish(formationServices.loadFormation(forId.get()));
			sessions.publish(sessionServices.ListSessionByForId(forId.get()));
		} else {
			formation.publish(new Formation());
			toModeCreate();
		}
		//niveaux.publish(Niveau.class, PersistenceManagerInitializer.ALL_DATA_CODE);
		niveaux.publish(Niveau.class, null);
	}

	public String doEdit() {
		toModeEdit();
		return NONE;
	}

	public String doSave() {
		if (isModeCreate()) {
			formationServices.saveFormation(formation.readDto());
		} else if (isModeEdit()) {
			final Formation formation_modifier = formation.readDto();
			final DtList<SessionFormation> session_modif = sessionServices.ListSessionByForId(formation.readDto().getForId());
			int i = 0;
			while (i < session_modif.size()) {
				session_modif.get(i).setFormationName(formation_modifier.getIntitule());
				session_modif.get(i).setCommentaire(formation_modifier.getCommentaire());
				session_modif.get(i).setNiveau(formation_modifier.getNiveau().getLibelle());
				sessionServices.saveSessionFormation(session_modif.get(i));
				i = i + 1;
			}
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
}
