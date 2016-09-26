package com.kleegroup.formation.ui.controller.formation;

import javax.inject.Inject;

import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.FormationCritere;
import com.kleegroup.formation.domain.formation.Niveau;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.formation.FormationServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.services.util.SecurityUtil;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextMdl;

/**
 * @author prahmoune
 * @version $Id: ProduitListAction.java,v 1.4 2014/06/27 12:21:39 pchretien Exp $
 */
public final class FormationListAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = 1L;

	@Inject
	private FormationServices formationServices;

	private final ContextForm<FormationCritere> formationCritere = new ContextForm<>("formationCritere", this);
	private final ContextList<Formation> formations = new ContextList<>("formations", this);
	private final ContextMdl<Niveau> niveaux = new ContextMdl<>("niveaux", this);
	@Inject
	private SessionServices sessionServices;

	private final ContextForm<SessionFormation> session = new ContextForm<>("session", this);
	private final ContextList<SessionFormation> sessions = new ContextList<>("sessions", this);

	/** {@inheritDoc} */
	@Override
	public void initContext() {
		SecurityUtil.checkRole(com.kleegroup.formation.security.Role.R_ADMIN, com.kleegroup.formation.security.Role.R_FORMATTEUR);
		niveaux.publish(Niveau.class, null);
		formationCritere.publish(new FormationCritere());

		formations.publish(formationServices.getFormationListByCritere(formationCritere.readDto()));
		session.publish(new SessionFormation());
		final SessionFormation test1 = new SessionFormation();
		test1.setStatus("Brouillon");
		sessions.publish(sessionServices.getSessionListByCritere(test1));
		toModeEdit();

	}

	public String doRechercher() {
		formations.publish(formationServices.getFormationListByCritere(formationCritere.readDto()));
		return NONE;

	}

	/** {@inheritDoc} */
	@Override
	public String getPageName() {
		return "Recherche de formation";
	}

}
