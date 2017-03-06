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
import com.kleegroup.formation.domain.formation.Inscription;

/**
 * DAO : Accès à un object (DTO, DTC). 
 * InscriptionDAO
 */
public final class InscriptionDAO extends DAO<Inscription, java.lang.Long> implements StoreServices {

	/**
	 * Contructeur.
	 * @param storeManager Manager de persistance
	 * @param taskManager Manager de Task
	 */
	@Inject
	public InscriptionDAO(final StoreManager storeManager, final TaskManager taskManager) {
		super(Inscription.class, storeManager, taskManager);
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
	 * Execute la tache TK_GET_INSCRIPTION_BY_UTI_SES_ID.
	 * @param utilisateurId Long 
	 * @param sessionFormationId Long 
	 * @return com.kleegroup.formation.domain.formation.Inscription dtc
	*/
	public com.kleegroup.formation.domain.formation.Inscription getInscriptionByUtiSesId(final Long utilisateurId, final Long sessionFormationId) {
		final Task task = createTaskBuilder("TK_GET_INSCRIPTION_BY_UTI_SES_ID")
				.addValue("UTILISATEUR_ID", utilisateurId)
				.addValue("SESSION_FORMATION_ID", sessionFormationId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_GET_LIST_INSCRIPTIONS_BY_SESSION_ID.
	 * @param sessionId Long 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Inscription> dtcInscriptions
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Inscription> getListInscriptionsBySessionId(final Long sessionId) {
		final Task task = createTaskBuilder("TK_GET_LIST_INSCRIPTIONS_BY_SESSION_ID")
				.addValue("SESSION_ID", sessionId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_GET_LIST_UTILISATEUR_BY_INSCRIPTION.
	 * @param utilisateurId Long 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Inscription> dtc
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.formation.Inscription> getListUtilisateurByInscription(final Long utilisateurId) {
		final Task task = createTaskBuilder("TK_GET_LIST_UTILISATEUR_BY_INSCRIPTION")
				.addValue("UTILISATEUR_ID", utilisateurId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

}
