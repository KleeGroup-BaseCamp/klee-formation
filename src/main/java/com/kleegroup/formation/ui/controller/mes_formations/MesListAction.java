package com.kleegroup.formation.ui.controller.mes_formations;

import javax.inject.Inject;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.domain.inscription.InscriptionView;
import com.kleegroup.formation.domain.session.SessionView;
import com.kleegroup.formation.security.Roles;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.services.util.SecurityUtil;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;

/**
 * @author camille
 * @version $Id: ProduitListAction.java,v 1.4 2014/06/27 12:21:39 pchretien Exp $
 */
public final class MesListAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = 1L;

	@Inject
	private SessionServices sessionServices;

	private final ContextForm<SessionFormation> session = new ContextForm<>("session", this);
	private final ContextList<InscriptionView> inscriptions = new ContextList<>("inscriptions", this);
	private final ContextList<SessionView> sessions = new ContextList<>("sessions", this);
	private final ContextList<InscriptionView> inscriptions2 = new ContextList<>("inscriptions2", this);
	private final ContextList<SessionView> sessions2 = new ContextList<>("sessions2", this);

	@Inject
	private UtilisateurServices utilisateurServices;

	@Inject
	private InscriptionServices inscriptionServices;

	/** {@inheritDoc} */
	@Override
	public void initContext() {
		session.publish(new SessionFormation());
		final Utilisateur utilisateurCourant = utilisateurServices.getCurrentUtilisateur();
		inscriptions.publish(inscriptionServices.InscriptionPasserFormation(utilisateurCourant.getUtiId()));
		inscriptions2.publish(inscriptionServices.InscriptionVenirFormation(utilisateurCourant.getUtiId()));
		sessions.publish(sessionServices.ListSessionFormateurPasser(utilisateurCourant.getUtiId()));
		sessions2.publish(sessionServices.ListSessionFormateurVenir(utilisateurCourant.getUtiId()));
		toModeEdit();

	}

	public boolean isformateur() {
		return SecurityUtil.hasRole(Roles.R_FORMATTEUR);
	}

	/** {@inheritDoc} */
	@Override
	public String getPageName() {
		return "Recherche session de formation";
	}

	/** {@inheritDoc} */
	@Override
	public Menu getActiveMenu() {
		return Menu.MES_FORMATION;
	}

}
