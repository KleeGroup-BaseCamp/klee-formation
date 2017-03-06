package com.kleegroup.formation.ui.controller.mes_formations;

import java.math.BigDecimal;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;
import com.kleegroup.formation.ui.controller.menu.Menu;

import io.vertigo.struts2.core.ContextForm;
import io.vertigo.struts2.core.ContextRef;

/**
 */
public final class SatisfactionAction extends AbstractKleeFormationActionSupport {

	private static final long serialVersionUID = -4230604077944183713L;

	@Inject
	private InscriptionServices inscriptionServices;

	@Inject
	private UtilisateurServices utilisateurServices;
	@Inject
	private SessionServices sessionServices;

	private final ContextForm<Inscription> statistique = new ContextForm<>("statistique", this);
	private final ContextRef<Long> sesIdRef = new ContextRef<>("sesId", Long.class, this);

	public void initContext(@Named("sesId") final Optional<Long> sesId) {
		final BigDecimal zero = new BigDecimal(0);
		if (inscriptionServices.InscriptionByUtiSesId(utilisateurServices.getCurrentUtilisateur().getUtiId(), sesId.get()).getSatisfaction().compareTo(zero) == 0) {
			statistique.publish(inscriptionServices.InscriptionByUtiSesId(utilisateurServices.getCurrentUtilisateur().getUtiId(), sesId.get()));
			toModeCreate();
		}
		statistique.publish(inscriptionServices.InscriptionByUtiSesId(utilisateurServices.getCurrentUtilisateur().getUtiId(), sesId.get()));

	}

	public String save() {

		final Inscription inscription = statistique.readDto();
		final BigDecimal neuf = new BigDecimal(9);
		final BigDecimal satisfactionMoyenne = inscription.getContact().add(inscription.getDuree()).add(inscription.getApprofondir())
				.add(inscription.getBenefices()).add(inscription.getAttentes()).add(inscription.getExplication()).add(inscription.getProgression())
				.add(inscription.getContenu()).add(inscription.getTheme()).divide(neuf, 10, BigDecimal.ROUND_HALF_DOWN);
		inscription.setSatisfaction(satisfactionMoyenne);
		final SessionFormation session = sessionServices.loadSessionbyId(sesIdRef.get());

		sessionServices.saveSessionFormation(session);
		inscriptionServices.saveInscription(statistique.readDto());

		toModeReadOnly();
		return "success";
	}

	@Override
	public String getPageName() {
		if (isModeCreate()) {
			return "Création d'une session de formation";
		} else if (isModeEdit()) {
			return "Modification d'une session deformation";
		}
		return "Detail d'une session de formation";
	}

	@Override
	public Menu getActiveMenu() {
		return Menu.MES_FORMATION;
	}

}
