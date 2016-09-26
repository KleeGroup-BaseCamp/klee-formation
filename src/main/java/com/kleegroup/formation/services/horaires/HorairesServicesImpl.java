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

		final SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy 'de' HH:mm");
		final String lineSeparator = System.getProperty("line.separator");
		final StringBuilder buffer = new StringBuilder();
		for (final Horaires horaires : horairess) {
			horaires.setSesId(sesId);
			horairesDAO.save(horaires);
			final Long tmp_test = (horaires.getDebut().getTime() - horaires.getFin().getTime()) / 3600000 / 24;
			if (tmp_test == 0) {

				buffer.append(lineSeparator).append(formater.format(horaires.getDebut()))
						.append(" à ").append(formater.format(horaires.getFin()).substring(12, formater.format(horaires.getFin()).length()));

			} else {
				buffer.append(formater.format(horaires.getDebut())).append(" - ").append(formater.format(horaires.getFin()));
			}
		}
		return buffer.toString();

	}

	//public SessionFormationDAO sessionDAO;

	@Override
	public void deleteHoraires(final Long sesId) {
		final SessionFormation session = sessionServices.loadSessionbyId(sesId);
		int i = 0;
		while (i < session.getHorairesList().size()) {
			horairesDAO.delete(session.getHorairesList().get(i).getDatId());
			i++;
		}
		sessionServices.saveSessionFormation(session);
	}
}
