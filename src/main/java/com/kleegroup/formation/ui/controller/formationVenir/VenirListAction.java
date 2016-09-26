package com.kleegroup.formation.ui.controller.formationVenir;

import javax.inject.Inject;

import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;

/**
 * @author prahmoune
 * @version $Id: ProduitListAction.java,v 1.4 2014/06/27 12:21:39 pchretien Exp $
 */
public final class VenirListAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = 1L;

	@Inject
	private SessionServices sessionServices;

	private final ContextForm<SessionFormation> session = new ContextForm<>("session", this);
	private final ContextList<SessionFormation> sessions = new ContextList<>("sessions", this);

	/** {@inheritDoc}*/
	@Override
	public void initContext() {
		session.publish(new SessionFormation());
		sessions.publish(sessionServices.listSessionByDate());
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
