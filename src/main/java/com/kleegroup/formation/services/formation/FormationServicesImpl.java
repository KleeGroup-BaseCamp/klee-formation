package com.kleegroup.formation.services.formation;

import javax.inject.Inject;

import com.kleegroup.formation.dao.formation.FormationDAO;
import com.kleegroup.formation.dao.services.formation.FormationPAO;
import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.FormationCritere;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.domain.model.DtListURIForCriteria;
import io.vertigo.dynamo.transaction.Transactional;

@Transactional
public class FormationServicesImpl implements FormationServices {

	@Inject
	private FormationDAO formationDAO;

	@Inject
	private FormationPAO formationPAO;

	/** {@inheritDoc} */
	@Override
	public Formation loadFormation(final Long fodId) {
		final Formation formation = formationDAO.get(fodId);
		return formation;
	}

	/** {@inheritDoc} */
	@Override
	public DtList<Formation> getFormationListByCritere(final FormationCritere criteres) {
		return formationDAO.findAll(DtListURIForCriteria.createCriteria(criteres), 100);
	}

	/** {@inheritDoc} */
	@Override
	public void saveFormation(final Formation formation) {
		formationDAO.save(formation);
	}

	/** {@inheritDoc} */
	@Override
	public void deleteFormation(final Long forId) {
		formationDAO.delete(forId);

	}

	/** {@inheritDoc} */
	@Override
	public void deleteFormationCascade(final Long forId) {
		formationPAO.deleteHoraires(forId);
		formationPAO.deleteSessionByForId(forId);
		//formationPAO.getDeleteFormationCascade(forId);
		formationDAO.delete(forId);
	}
}
