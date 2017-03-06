package com.kleegroup.formation.dao.services.inscription;

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
 * InscriptionPAO
 */
public final class InscriptionPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public InscriptionPAO(final TaskManager taskManager) {
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
	 * Execute la tache TK_GET_INSCRIPTION_VIEW_PASSER_FORMATION.
	 * @param utilisateurId Long 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.inscription.InscriptionView> dtc
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.inscription.InscriptionView> getInscriptionViewPasserFormation(final Long utilisateurId) {
		final Task task = createTaskBuilder("TK_GET_INSCRIPTION_VIEW_PASSER_FORMATION")
				.addValue("UTILISATEUR_ID", utilisateurId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_GET_INSCRIPTION_VIEW_VENIR_FORMATION.
	 * @param utilisateurId Long 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.inscription.InscriptionView> dtc
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.inscription.InscriptionView> getInscriptionViewVenirFormation(final Long utilisateurId) {
		final Task task = createTaskBuilder("TK_GET_INSCRIPTION_VIEW_VENIR_FORMATION")
				.addValue("UTILISATEUR_ID", utilisateurId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_GET_LIST_INSCRIPTIONS_VIEW_BY_SESSION_ID.
	 * @param sessionId Long 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.inscription.InscriptionView> dtcInscriptions
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.inscription.InscriptionView> getListInscriptionsViewBySessionId(final Long sessionId) {
		final Task task = createTaskBuilder("TK_GET_LIST_INSCRIPTIONS_VIEW_BY_SESSION_ID")
				.addValue("SESSION_ID", sessionId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_GET_LIST_INSCRIPTION_VIEW_BY_UTI_ID.
	 * @param utilisateurId Long 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.inscription.InscriptionView> dtc
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.inscription.InscriptionView> getListInscriptionViewByUtiId(final Long utilisateurId) {
		final Task task = createTaskBuilder("TK_GET_LIST_INSCRIPTION_VIEW_BY_UTI_ID")
				.addValue("UTILISATEUR_ID", utilisateurId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
