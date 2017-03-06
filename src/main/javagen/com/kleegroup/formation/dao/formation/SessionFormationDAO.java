package com.kleegroup.formation.dao.formation;

import javax.inject.Inject;
import io.vertigo.app.Home;
import io.vertigo.dynamo.task.metamodel.TaskDefinition;
import io.vertigo.dynamo.task.model.Task;
import io.vertigo.dynamo.task.model.TaskBuilder;
import io.vertigo.dynamo.impl.store.util.DAO;
import io.vertigo.dynamo.store.StoreManager;
import io.vertigo.dynamo.store.StoreServices;
import io.vertigo.dynamo.task.TaskManager;
import com.kleegroup.formation.domain.formation.SessionFormation;

/**
 * DAO : Accès à un object (DTO, DTC). 
 * SessionFormationDAO
 */
public final class SessionFormationDAO extends DAO<SessionFormation, java.lang.Long> implements StoreServices {

	/**
	 * Contructeur.
	 * @param storeManager Manager de persistance
	 * @param taskManager Manager de Task
	 */
	@Inject
	public SessionFormationDAO(final StoreManager storeManager, final TaskManager taskManager) {
		super(SessionFormation.class, storeManager, taskManager);
	}


	/**
	 * Creates a taskBuilder.
	 * @param name  the name of the task
	 * @return the builder 
	 */
	private static TaskBuilder createTaskBuilder(final String name) {
		final TaskDefinition taskDefinition = Home.getApp().getDefinitionSpace().resolve(name, TaskDefinition.class);
		return new TaskBuilder(taskDefinition);
	}

	/**
	 * Execute la tache TK_GET_LIST_SESSION_BY_FORMATEUR_ID.
	 * @param formateurId Long 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.SessionFormation> dtc
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.SessionFormation> getListSessionByFormateurId(final Long formateurId) {
		final Task task = createTaskBuilder("TK_GET_LIST_SESSION_BY_FORMATEUR_ID")
				.addValue("FORMATEUR_ID", formateurId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_LIST_SESSION_BROUILLON.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.SessionFormation> dtcSessionFormation
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.SessionFormation> listSessionBrouillon() {
		final Task task = createTaskBuilder("TK_LIST_SESSION_BROUILLON")
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_LIST_SESSION_BY_ETAT.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.SessionFormation> dtcSessionFormation
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.SessionFormation> listSessionByEtat() {
		final Task task = createTaskBuilder("TK_LIST_SESSION_BY_ETAT")
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

}
