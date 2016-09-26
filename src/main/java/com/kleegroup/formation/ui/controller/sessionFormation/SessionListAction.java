package com.kleegroup.formation.ui.controller.sessionFormation;

import javax.inject.Inject;

import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;

/**
 * @author cmoutte

 */
public final class SessionListAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = 1L;

	@Inject
	private SessionServices sessionServices;

	private final ContextForm<SessionFormation> session = new ContextForm<>("session", this);
	private final ContextList<SessionFormation> sessions = new ContextList<>("sessions", this);

	/** {@inheritDoc} */
	@Override
	public void initContext() {

		session.publish(new SessionFormation());

		sessions.publish(sessionServices.getSessionListByCritere(session.readDto()));

		toModeEdit();
	}

	public String doRechercher() {
		sessions.publish(sessionServices.getSessionListByCritere(session.readDto()));
		return NONE;
	}

	/** {@inheritDoc} */
	@Override
	public String getPageName() {
		return "Recherche session de formation";
	}
}
