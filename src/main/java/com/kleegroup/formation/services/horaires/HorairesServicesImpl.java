package com.kleegroup.formation.services.horaires;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import com.kleegroup.formation.dao.formation.HorairesDAO;
import com.kleegroup.formation.dao.services.session.SessionPAO;
import com.kleegroup.formation.domain.formation.Horaires;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.session.SessionServices;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.transaction.Transactional;
import io.vertigo.lang.Assertion;

/**
 * Implémentation des services associés à la gestion des produits.
 *
 * @author cgodard
 * @version $Id: ProduitServicesImpl.java,v 1.5 2014/02/07 16:48:27 npiedeloup Exp $
 */
@Transactional
public class HorairesServicesImpl implements HorairesServices {

	@Inject
	private SessionServices sessionServices;

	@Inject
	private HorairesDAO horairesDAO;
	@Inject
	private SessionPAO horairesPAO;

	@Override
	public String saveHoraires(final DtList<Horaires> horairess, final Long sesId) {
		final SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
		final SessionFormation session = sessionServices.loadSessionbyId(sesId);
		for (final Horaires horaires : horairess) {
			horaires.setSesId(sesId);
			horairesDAO.save(horaires);
		}
		final int objValue = horairess.get(0).getDebut();
		String str = new String();
		float i = (float) objValue / (float) 60;
		str = (int) i + ":" + String.valueOf(i).substring(String.valueOf(i).length() - 1, String.valueOf(i).length()) + "0";
		final int objValue2 = horairess.get(horairess.size() - 1).getFinAprem();
		i = (float) objValue2 / (float) 60;
		String str_fin = new String();
		str_fin = (int) i + ":" + String.valueOf(i).substring(String.valueOf(i).length() - 1, String.valueOf(i).length()) + "0";

		final String line = formater.format(horairess.get(0).getJour()).concat(" à ").concat(str).concat(" au ").concat(formater.format(horairess.get(horairess.size() - 1).getJour())).concat(" à ").concat(str_fin);
		session.setHoraire(line);
		sessionServices.saveSessionFormation(session);
		return line;

	}

	@Override
	public DtList<Horaires> getHoraires(final SessionFormation sessionFormation) {
		Assertion.checkNotNull(sessionFormation);
		// ---
		return sessionFormation.getHorairesList();

	}

	@Override
	public void deleteHoraires(final Long sesId) {
		final SessionFormation session = sessionServices.loadSessionbyId(sesId);
		for (final Horaires horaires : session.getHorairesList()) {
			horairesDAO.delete(horaires.getDatId());
		}
		sessionServices.saveSessionFormation(session);
	}

	@Override
	public int horaireDebut(final Long sesId) {
		return horairesPAO.horaireDebut(sesId);
	}

	@Override
	public int horaireFin(final Long sesId) {
		return horairesPAO.horaireFin(sesId);
	}
}
