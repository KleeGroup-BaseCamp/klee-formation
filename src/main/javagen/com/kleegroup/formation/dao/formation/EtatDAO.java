package com.kleegroup.formation.dao.formation;

import javax.inject.Inject;
import io.vertigo.dynamo.impl.store.util.DAO;
import io.vertigo.dynamo.store.StoreManager;
import io.vertigo.dynamo.store.StoreServices;
import io.vertigo.dynamo.task.TaskManager;
import com.kleegroup.formation.domain.formation.Etat;

/**
 * DAO : Accès à un object (DTO, DTC). 
 * EtatDAO
 */
public final class EtatDAO extends DAO<Etat, java.lang.String> implements StoreServices {
	 
	/**
	 * Contructeur.
	 * @param storeManager Manager de persistance
	 * @param taskManager Manager de Task
	 */
	@Inject
	public EtatDAO(final StoreManager storeManager, final TaskManager taskManager) {
		super(Etat.class, storeManager, taskManager);
	}
	

}
