package com.kleegroup.formation.ui.controller.formationVenir;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere;
import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.lang.Option;
import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextList;
import io.vertigo.struts2.core.ContextRef;

/**
 * @author prahmoune
 * @version $Id: ProduitListAction.java,v 1.4 2014/06/27 12:21:39 pchretien Exp $
 */
public final class UtilisateurAction extends AbstractKleeFormationActionSupport {
	private static final long serialVersionUID = 1L;

	@Inject
	private UtilisateurServices utilisateurServices;

	@Inject
	private InscriptionServices inscriptionServices;
	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);
	private final ContextForm<Inscription> inscriptionForm = new ContextForm<>("inscription", this);
	private final ContextList<Utilisateur> utilisateurs = new ContextList<>("utilisateurs", this);

	public void initContext(@Named("sesId") final Option<Long> sesId) {
		final UtilisateurCritere utilisateurcritere = new UtilisateurCritere();
		utilisateurs.publish(utilisateurServices.getUtilisateurListByCritere(utilisateurcritere));
		inscriptionForm.publish(new Inscription());

		toModeEdit();

	}

	public String doInscrireMoi() {
		inscriptionServices.inscrireUtilisateurASession(sesIdRef.get());
		return "success_inscription";
	}

	public String doInscrire() {
		inscriptionServices.inscrireUtilisateur(sesIdRef.get(), inscriptionForm.readDto().getUtiId());
		return "success_inscription";
	}

	@Override
	public String getPageName() {
		if (isModeCreate()) {
			return "creation d'une session de formation";
		} else if (isModeEdit()) {
			return "Modification d'une session deformation";
		}
		return "Detail d'une session de formation";
	}

	/** {@inheritDoc} */
	@Override
	public Menu getActiveMenu() {
		return Menu.ADMINISTRATION;
	}

}
