package com.kleegroup.formation.dao.administration.utilisateur;

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
 * UtilisateurPAO
 */
public final class UtilisateurPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public UtilisateurPAO(final TaskManager taskManager) {
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
	 * Execute la tache TK_LOAD_UTILISATEUR_ACTIF_BY_LOGIN.
	 * @param login String 
	 * @param cryptedPassword String 
	*/
	public void loadUtilisateurActifByLogin(final String login, final String cryptedPassword) {
		final Task task = createTaskBuilder("TK_LOAD_UTILISATEUR_ACTIF_BY_LOGIN")
				.addValue("LOGIN", login)
				.addValue("CRYPTED_PASSWORD", cryptedPassword)
				.build();
		getTaskManager().execute(task);
	}

    
    private TaskManager getTaskManager(){
    	return taskManager;
    } 
}
