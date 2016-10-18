package com.kleegroup.formation.ui.controller.administration.utilisateur;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurLogin;
import com.kleegroup.formation.domain.inscription.InscriptionView;
import com.kleegroup.formation.security.Role;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.util.SecurityUtil;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import java.util.Optional;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextRef;

/**
 * @author npiedeloup
 * @version $Id: UtilisateurDetailAction.java,v 1.3 2014/06/27 12:21:39 pchretien Exp $
 */
public final class UtilisateurDetailAction extends AbstractKleeFormationActionSupport {

	private static final long serialVersionUID = 5176368775307768464L;

	@Inject
	private UtilisateurServices utilisateurServices;

	@Inject
	private InscriptionServices inscriptionServices;

	private final ContextList<InscriptionView> inscriptions = new ContextList<>("inscriptions", this);
	private final ContextForm<UtilisateurLogin> utilisateurLogin = new ContextForm<>("utilisateurLogin", this);
	private final ContextForm<Utilisateur> utilisateur = new ContextForm<>("utilisateur", this);
	private final ContextRef<Boolean> roleAdmin = new ContextRef<>("roleAdmin", Boolean.class, this);
	private final ContextRef<Boolean> roleFormatteur = new ContextRef<>("roleFormatteur", Boolean.class, this);
	private final ContextRef<Boolean> roleResponssable = new ContextRef<>("roleResponssable", Boolean.class, this);

	/**
	 * @param utiId Id de l'élément a afficher.
	 */
	public void initContext(@Named("utiId") final Optional<Long> utiId) {
		SecurityUtil.checkRole(com.kleegroup.formation.security.Role.R_ADMIN, com.kleegroup.formation.security.Role.R_RESPONSSABLE);
		//-----
		if (utiId.isPresent()) {
			final Utilisateur dtoUtilisateur = utilisateurServices.loadUtilisateurWithRoles(utiId.get());
			utilisateur.publish(dtoUtilisateur);
			roleAdmin.set(Boolean.FALSE);
			roleFormatteur.set(Boolean.FALSE);
			roleResponssable.set(Boolean.FALSE);
			final DtList<com.kleegroup.formation.domain.administration.utilisateur.Role> roles = dtoUtilisateur.getRoleList();
			for (final com.kleegroup.formation.domain.administration.utilisateur.Role role : roles) {
				if (com.kleegroup.formation.security.Role.R_ADMIN.equals(role.getRolCode())) {
					roleAdmin.set(Boolean.TRUE);
				}
				if (com.kleegroup.formation.security.Role.R_FORMATTEUR.equals(role.getRolCode())) {
					roleFormatteur.set(Boolean.TRUE);
				}
				if (com.kleegroup.formation.security.Role.R_RESPONSSABLE.equals(role.getRolCode())) {
					roleResponssable.set(Boolean.TRUE);
				}
			}
			inscriptions.publish(inscriptionServices.getListInscriptionByUtiId(utiId.get()));
		} else {
			utilisateur.publish(new Utilisateur());
			roleAdmin.set(Boolean.FALSE);
			roleFormatteur.set(Boolean.FALSE);
			roleResponssable.set(Boolean.FALSE);
			toModeCreate();
		}
		utilisateurLogin.publish(new UtilisateurLogin());
	}

	/**
	 * @return Outcome d'enregistrement
	 */
	public String doSave() {
		utilisateur.checkErrors();
		final Utilisateur uti = utilisateur.readDto();
		uti.setAdmin(false);
		uti.setFormateur(false);
		uti.setResponsable(false);
		final List<URI> roles = new ArrayList<>();
		if (roleAdmin.get()) {
			roles.add(DtObjectUtil.createURI(com.kleegroup.formation.domain.administration.utilisateur.Role.class, com.kleegroup.formation.security.Role.R_ADMIN.name()));
			uti.setAdmin(true);
		}
		if (roleFormatteur.get()) {
			roles.add(DtObjectUtil.createURI(com.kleegroup.formation.domain.administration.utilisateur.Role.class, com.kleegroup.formation.security.Role.R_FORMATTEUR.name()));
			uti.setFormateur(true);
		}
		if (roleResponssable.get()) {
			roles.add(DtObjectUtil.createURI(com.kleegroup.formation.domain.administration.utilisateur.Role.class, com.kleegroup.formation.security.Role.R_RESPONSSABLE.name()));
			uti.setResponsable(true);
		}

		roles.add(DtObjectUtil.createURI(com.kleegroup.formation.domain.administration.utilisateur.Role.class, com.kleegroup.formation.security.Role.R_ANONYMOUS.name()));
		if (isModeCreate()) {
			utilisateurServices.saveUtilisateur(utilisateurLogin.readDto(), uti, roles);
		} else {
			utilisateurServices.saveUtilisateur(utilisateur.readDto(), roles);
		}
		return SUCCESS;
	}

	/**
	 * @return Outcome de suppression
	 */
	public String doDelete() {
		utilisateurServices.deleteUtilisateur(utilisateur.readDto().getUtiId());
		return "success_delete";
	}

	/**
	 * @return Outcome du passage en edition
	 */
	public String doEdit() {
		toModeEdit();
		return NONE;
	}

	/** {@inheritDoc} */
	@Override
	public String getPageName() {
		if (isModeCreate()) {
			return "Creation d'un utilisateur";
		} else if (isModeEdit()) {
			return "Modification d'un utilisateur";
		}
		return "Detail d'un utilisateur";
	}

	public boolean isAdministrateur() {
		return SecurityUtil.hasRole(Role.R_ADMIN);
	}

	@Override
	public Menu getActiveMenu() {
		return Menu.ADMINISTRATION;
	}
}
