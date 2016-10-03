package com.kleegroup.formation.services.horaires;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import com.kleegroup.formation.dao.formation.HorairesDAO;
import com.kleegroup.formation.domain.formation.Horaires;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.resources.Resources;
import com.kleegroup.formation.services.session.SessionServices;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.transaction.Transactional;
import io.vertigo.lang.MessageText;
import io.vertigo.lang.VUserException;
import io.vertigo.util.DateUtil;

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

	@Override
	public String saveHoraires(final DtList<Horaires> horairess, final Long sesId) {
		for (final Horaires horaires : horairess) {
			if (DateUtil.newDate().after(horaires.getDebut())) {
				throw new VUserException(new MessageText(Resources.SESSION_DATE_MUST_BE_IN_FUTURE));
			}
			if (horaires.getDebut().compareTo(horaires.getFin()) > 0) {
				throw new VUserException(new MessageText(Resources.SESSION_DATE_FIN_MUST_BE_IN_FUTURE));
			}
		}

		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy 'de' HH:mm");
		final String lineSeparator = System.getProperty("line.separator");
		final StringBuilder buffer = new StringBuilder();
		final SessionFormation session = sessionServices.loadSessionbyId(sesId);
		System.out.println(Integer.toString(horairess.size()));
		if (horairess.size() == 1) {
			String line = new String();
			line = "de ";
			line = line + formater.format(horairess.get(0).getFin()).substring(12, formater.format(horairess.get(0).getDebut()).length()).concat(" à ".concat(formater.format(horairess.get(0).getFin()).substring(12, formater.format(horairess.get(0).getDebut()).length())));
			sessionServices.saveSessionFormation(session);
			return line;
		} else {
			for (final Horaires horaires : horairess) {
				session.getHorairesList().add(horaires);
				//	session.getHorairesList().isEmpty()
				horaires.setSesId(sesId);

				horairesDAO.save(horaires);
				final Long tmp_test = (horaires.getDebut().getTime() - horaires.getFin().getTime()) / 3600000 / 24;
				if (tmp_test == 0) {
					formater = new SimpleDateFormat("dd/MM/yy 'de' HH:mm");
					buffer.append(lineSeparator).append(formater.format(horaires.getDebut()))
							.append(" à ").append(formater.format(horaires.getFin()).substring(12, formater.format(horaires.getFin()).length()));

				} else {
					formater = new SimpleDateFormat("dd/MM/yy 'à' HH:mm");
					buffer.append(lineSeparator).append(formater.format(horaires.getDebut())).append(" - ").append(formater.format(horaires.getFin()));
				}
			}
			sessionServices.saveSessionFormation(session);
			return buffer.toString();
		}

	}

	@Override
	public void deleteHoraires(final Long sesId) {
		final SessionFormation session = sessionServices.loadSessionbyId(sesId);
		for (final Horaires horaires : session.getHorairesList()) {
			horairesDAO.delete(horaires.getDatId());
		}
		sessionServices.saveSessionFormation(session);
	}
}
