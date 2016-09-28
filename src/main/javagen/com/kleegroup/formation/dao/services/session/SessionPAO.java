package com.kleegroup.formation.dao.services.session;

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
 * SessionPAO
 */
public final class SessionPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public SessionPAO(final TaskManager taskManager) {
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
	 * Execute la tache TK_LIST_SESSION_BY_DATE.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.session.SessionView> dtcSessionFormation
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.session.SessionView> listSessionByDate() {
		final Task task = createTaskBuilder("TK_LIST_SESSION_BY_DATE")
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_LIST_SESSION_BY_CRITERE.
	 * @param critere com.kleegroup.formation.domain.session.CritereSession 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.session.SessionView> dtcSessionFormation
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.session.SessionView> listSessionByCritere(final com.kleegroup.formation.domain.session.CritereSession critere) {
		final Task task = createTaskBuilder("TK_LIST_SESSION_BY_CRITERE")
				.addValue("CRITERE", critere)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_GET_LIST_SESSION_FORMATEUR_VENIR.
	 * @param formateurId Long 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.session.SessionView> dtc
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.session.SessionView> getListSessionFormateurVenir(final Long formateurId) {
		final Task task = createTaskBuilder("TK_GET_LIST_SESSION_FORMATEUR_VENIR")
				.addValue("FORMATEUR_ID", formateurId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_GET_LIST_SESSION_FORMATEUR_PASSER.
	 * @param formateurId Long 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.session.SessionView> dtc
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.session.SessionView> getListSessionFormateurPasser(final Long formateurId) {
		final Task task = createTaskBuilder("TK_GET_LIST_SESSION_FORMATEUR_PASSER")
				.addValue("FORMATEUR_ID", formateurId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_GET_LIST_SESSION_BY_FOR_ID.
	 * @param forId Long 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.session.SessionView> dtcSession
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.session.SessionView> getListSessionByForId(final Long forId) {
		final Task task = createTaskBuilder("TK_GET_LIST_SESSION_BY_FOR_ID")
				.addValue("FOR_ID", forId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_DELETE_SESSION_CASCADE.
	 * @param sesId Long 
	*/
	public void deleteSessionCascade(final Long sesId) {
		final Task task = createTaskBuilder("TK_DELETE_SESSION_CASCADE")
				.addValue("SES_ID", sesId)
				.build();
		getTaskManager().execute(task);
	}

    
    private TaskManager getTaskManager(){
    	return taskManager;
    } 
}
