package com.kleegroup.formation.dao.formation;

import javax.inject.Inject;
import io.vertigo.dynamo.impl.store.util.DAO;
import io.vertigo.dynamo.store.StoreManager;
import io.vertigo.dynamo.store.StoreServices;
import io.vertigo.dynamo.task.TaskManager;
import com.kleegroup.formation.domain.formation.Horaires;

/**
 * DAO : Accès à un object (DTO, DTC). 
 * HorairesDAO
 */
public final class HorairesDAO extends DAO<Horaires, java.lang.Long> implements StoreServices {
	 
	/**
	 * Contructeur.
	 * @param storeManager Manager de persistance
	 * @param taskManager Manager de Task
	 */
	@Inject
	public HorairesDAO(final StoreManager storeManager, final TaskManager taskManager) {
		super(Horaires.class, storeManager, taskManager);
	}
	

}
