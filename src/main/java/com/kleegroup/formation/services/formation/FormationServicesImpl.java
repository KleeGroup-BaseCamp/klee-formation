package com.kleegroup.formation.services.formation;

import javax.inject.Inject;

import com.kleegroup.formation.dao.formation.FormationDAO;
import com.kleegroup.formation.dao.services.formation.FormationPAO;
import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.FormationCritere;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.domain.model.DtListURIForCriteria;
import io.vertigo.dynamo.transaction.Transactional;

/**
 * Implémentation des services associés à la gestion des produits.
 *
 * @author cgodard
 * @version $Id: ProduitServicesImpl.java,v 1.5 2014/02/07 16:48:27 npiedeloup Exp $
 */
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
		return formationDAO.getList(DtListURIForCriteria.createCriteria(criteres), 100);
	}

	/** {@inheritDoc} */
	@Override
	public void saveFormation(final Formation formation) {
		formationDAO.save(formation);
	}

	/** {@inheritDoc} */
	@Override
	public void deleteFormation(final Long forId) {
		formationPAO.getDeleteFormationCascade(forId);

	}

}
