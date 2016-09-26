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
	 * Execute la tache TK_GET_DELETE_FORMATION_CASCADE.
	 * @param forId Long 
	*/
	public void getDeleteFormationCascade(final Long forId) {
		final Task task = createTaskBuilder("TK_GET_DELETE_FORMATION_CASCADE")
				.addValue("FOR_ID", forId)
				.build();
		getTaskManager().execute(task);
	}

    
    private TaskManager getTaskManager(){
    	return taskManager;
    } 
}
