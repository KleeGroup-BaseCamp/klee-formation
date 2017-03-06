package com.kleegroup.formation.dao.formation;

import javax.inject.Inject;
import io.vertigo.dynamo.impl.store.util.DAO;
import io.vertigo.dynamo.store.StoreManager;
import io.vertigo.dynamo.store.StoreServices;
import io.vertigo.dynamo.task.TaskManager;
import com.kleegroup.formation.domain.formation.Niveau;

/**
 * DAO : Accès à un object (DTO, DTC). 
 * NiveauDAO
 */
public final class NiveauDAO extends DAO<Niveau, java.lang.String> implements StoreServices {

	/**
	 * Contructeur.
	 * @param storeManager Manager de persistance
	 * @param taskManager Manager de Task
	 */
	@Inject
	public NiveauDAO(final StoreManager storeManager, final TaskManager taskManager) {
		super(Niveau.class, storeManager, taskManager);
	}

}
