package com.kleegroup.formation.dao.formation;

import javax.inject.Inject;
import io.vertigo.dynamo.impl.store.util.DAO;
import io.vertigo.dynamo.store.StoreManager;
import io.vertigo.dynamo.store.StoreServices;
import io.vertigo.dynamo.task.TaskManager;
import com.kleegroup.formation.domain.formation.Formation;

/**
 * DAO : Accès à un object (DTO, DTC). 
 * FormationDAO
 */
public final class FormationDAO extends DAO<Formation, java.lang.Long> implements StoreServices {

	/**
	 * Contructeur.
	 * @param storeManager Manager de persistance
	 * @param taskManager Manager de Task
	 */
	@Inject
	public FormationDAO(final StoreManager storeManager, final TaskManager taskManager) {
		super(Formation.class, storeManager, taskManager);
	}

}
