package com.kleegroup.formation.ui.controller.sessionFormation;

import javax.inject.Inject;

import com.kleegroup.formation.domain.session.CritereSession;
import com.kleegroup.formation.domain.session.SessionView;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;

/**
 * @author cmoutte

 */
public final class SessionListAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = 1L;

	@Inject
	private SessionServices sessionServices;

	private final ContextForm<CritereSession> critere = new ContextForm<>("critere", this);
	private final ContextList<SessionView> sessions = new ContextList<>("sessions", this);

	/** {@inheritDoc} */
	@Override
	public void initContext() {

		critere.publish(new CritereSession());

		sessions.publish(sessionServices.getSessionListByCritere(critere.readDto()));

		toModeEdit();
	}

	public String doRechercher() {
		sessions.publish(sessionServices.getSessionListByCritere(critere.readDto()));
		return NONE;
	}

	/** {@inheritDoc} */
	@Override
	public String getPageName() {
		return "Recherche session de formation";
	}

	/** {@inheritDoc} */
	@Override
	public Menu getActiveMenu() {
		return Menu.GESTIONS;
	}
}
