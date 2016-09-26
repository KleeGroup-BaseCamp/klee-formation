package com.kleegroup.formation.dao.formation;

import javax.inject.Inject;
import io.vertigo.dynamo.impl.store.util.DAO;
import io.vertigo.dynamo.store.StoreManager;
import io.vertigo.dynamo.store.StoreServices;
import io.vertigo.dynamo.task.TaskManager;
import com.kleegroup.formation.domain.formation.EtatSessionUtilisateur;

/**
 * DAO : Accès à un object (DTO, DTC). 
 * EtatSessionUtilisateurDAO
 */
public final class EtatSessionUtilisateurDAO extends DAO<EtatSessionUtilisateur, java.lang.String> implements StoreServices {
	 
	/**
	 * Contructeur.
	 * @param storeManager Manager de persistance
	 * @param taskManager Manager de Task
	 */
	@Inject
	public EtatSessionUtilisateurDAO(final StoreManager storeManager, final TaskManager taskManager) {
		super(EtatSessionUtilisateur.class, storeManager, taskManager);
	}
	

}
