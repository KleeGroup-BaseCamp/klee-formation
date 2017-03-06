package com.kleegroup.formation.dao.services.formation;

import javax.inject.Inject;

import io.vertigo.app.Home;
import io.vertigo.lang.Assertion;
import io.vertigo.dynamo.task.TaskManager;
import io.vertigo.dynamo.task.metamodel.TaskDefinition;
import io.vertigo.dynamo.task.model.Task;
import io.vertigo.dynamo.task.model.TaskBuilder;
import io.vertigo.dynamo.store.StoreServices;

/**
 * PAO : Accès aux objects du package. 
 * FormationPAO
 */
public final class FormationPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public FormationPAO(final TaskManager taskManager) {
		Assertion.checkNotNull(taskManager);
		//-----
		this.taskManager = taskManager;
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
	 * Execute la tache TK_DELETE_HORAIRES.
	 * @param forId Long 
	*/
	public void deleteHoraires(final Long forId) {
		final Task task = createTaskBuilder("TK_DELETE_HORAIRES")
				.addValue("FOR_ID", forId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TK_DELETE_SESSION_BY_FOR_ID.
	 * @param forId Long 
	*/
	public void deleteSessionByForId(final Long forId) {
		final Task task = createTaskBuilder("TK_DELETE_SESSION_BY_FOR_ID")
				.addValue("FOR_ID", forId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TK_GET_DELETE_FORMATION_CASCADE.
	 * @param formationId Long 
	*/
	public void getDeleteFormationCascade(final Long formationId) {
		final Task task = createTaskBuilder("TK_GET_DELETE_FORMATION_CASCADE")
				.addValue("FORMATION_ID", formationId)
				.build();
		getTaskManager().execute(task);
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
