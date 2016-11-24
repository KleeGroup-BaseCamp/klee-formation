package com.kleegroup.formation.ui.controller.formations;

import javax.inject.Inject;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.util.SecurityUtil;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextRef;

/**
 * @author prahmoune
 * @version $Id: ProduitListAction.java,v 1.4 2014/06/27 12:21:39 pchretien Exp $
 */
public final class FormationsListAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = 1L;

	@Inject
	private UtilisateurServices utilisateurServices;

	private final ContextForm<UtilisateurCritere> utilisateurCritereForm = new ContextForm<>("utilisateurCritere", this);
	private final ContextRef<Boolean> allUserRef = new ContextRef<>("allUser", Boolean.class, this);
	private final ContextList<Utilisateur> utilisateurs = new ContextList<>("utilisateurs", this);

	/** {@inheritDoc} */
	@Override
	public void initContext() {
		SecurityUtil.checkRole(com.kleegroup.formation.security.Role.R_RESPONSSABLE);
		utilisateurCritereForm.publish(new UtilisateurCritere());
		allUserRef.set(true);
		utilisateurs.publish(new DtList<Utilisateur>(DtObjectUtil.findDtDefinition(Utilisateur.class)));
		toModeEdit();
	}

	protected void reloadList() {
		final UtilisateurCritere utilisateurCritere = utilisateurCritereForm.readDto();
		if (allUserRef.get()) {
			utilisateurCritere.setIsActif(null); //pr�f�rer un autre boolean dans le crit�re : ici c'est pour test
		}
		utilisateurs.publish(utilisateurServices.getUtilisateurListByCritere(utilisateurCritere));
	}

	public String doUtilisateurRechercher() {
		reloadList();
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
		return Menu.ADMINISTRATION;
	}
}
