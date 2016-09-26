package com.kleegroup.formation.ui.controller.mes_formations;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.inject.Named;

import com.kleegroup.formation.domain.formation.Inscription;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.administration.utilisateur.UtilisateurServices;
import com.kleegroup.formation.services.inscription.InscriptionServices;
import com.kleegroup.formation.services.session.SessionServices;
import com.kleegroup.formation.ui.controller.AbstractKleeFormationActionSupport;

import io.vertigo.lang.Option;
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

	/**
	 * @param fodId Id de l'élément a afficher.
	 */
	public void initContext(@Named("sesId") final Option<Long> sesId, @Named("utiId") final Option<Long> utiId) {
		if (utiId.isPresent()) {
			statistique.publish(inscriptionServices.InscriptionByUtiSesId(utiId.get(), sesId.get()));

		} else {
			final BigDecimal zero = new BigDecimal(0);
			if (inscriptionServices.InscriptionByUtiSesId(utilisateurServices.getCurrentUtilisateur().getUtiId(), sesId.get()).getSatisfaction().compareTo(zero) == 0) {
				statistique.publish(inscriptionServices.InscriptionByUtiSesId(utilisateurServices.getCurrentUtilisateur().getUtiId(), sesId.get()));
				toModeCreate();
			}
			statistique.publish(inscriptionServices.InscriptionByUtiSesId(utilisateurServices.getCurrentUtilisateur().getUtiId(), sesId.get()));
		}

	}

	public String doSave() {

		final Inscription ins = statistique.readDto();
		final BigDecimal neuf = new BigDecimal(9);
		final BigDecimal test = ins.getContact().add(ins.getDuree()).add(ins.getApprofondir()).add(ins.getBenefices()).add(ins.getAttentes()).add(ins.getExplication()).add(ins.getProgression()).add(ins.getContenu()).add(ins.getTheme()).divide(neuf, 10, BigDecimal.ROUND_HALF_DOWN);
		ins.setSatisfaction(test);
		final SessionFormation session = sessionServices.loadSessionbyId(sesIdRef.get());

		final BigDecimal un = new BigDecimal(1);
		session.setI(session.getI().add(un));
		session.setSatisfaction(session.getSatisfaction().add(test));
		session.setSatisfaction(session.getSatisfaction().divideToIntegralValue(session.getI()));
		sessionServices.saveSessionFormation(session);
		inscriptionServices.saveInscription(statistique.readDto());

		toModeReadOnly();
		return "success";
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

}
