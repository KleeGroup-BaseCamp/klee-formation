package com.kleegroup.formation.dao.administration.utilisateur;

import javax.inject.Inject;
import java.util.Optional;
import io.vertigo.app.Home;
import io.vertigo.dynamo.task.metamodel.TaskDefinition;
import io.vertigo.dynamo.task.model.Task;
import io.vertigo.dynamo.task.model.TaskBuilder;
import io.vertigo.dynamo.impl.store.util.DAO;
import io.vertigo.dynamo.store.StoreManager;
import io.vertigo.dynamo.store.StoreServices;
import io.vertigo.dynamo.task.TaskManager;
import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;

/**
 * DAO : Accès à un object (DTO, DTC). 
 * UtilisateurDAO
 */
public final class UtilisateurDAO extends DAO<Utilisateur, java.lang.Long> implements StoreServices {

	/**
	 * Contructeur.
	 * @param storeManager Manager de persistance
	 * @param taskManager Manager de Task
	 */
	@Inject
	public UtilisateurDAO(final StoreManager storeManager, final TaskManager taskManager) {
		super(Utilisateur.class, storeManager, taskManager);
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
	 * Execute la tache TK_GET_UTILISATEUR_BY_CRITERE.
	 * @param dtoUtilisateurCritere com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> dtcUtilisateur
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> getUtilisateurByCritere(final com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere dtoUtilisateurCritere) {
		final Task task = createTaskBuilder("TK_GET_UTILISATEUR_BY_CRITERE")
				.addValue("DTO_UTILISATEUR_CRITERE", dtoUtilisateurCritere)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_GET_UTILISATEUR_BY_EMAIL.
	 * @param email String 
	 * @return Option de com.kleegroup.formation.domain.administration.utilisateur.Utilisateur dtoUtilisateur
	*/
	public Optional<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> getUtilisateurByEmail(final String email) {
		final Task task = createTaskBuilder("TK_GET_UTILISATEUR_BY_EMAIL")
				.addValue("EMAIL", email)
				.build();
		return Optional.ofNullable((com.kleegroup.formation.domain.administration.utilisateur.Utilisateur) getTaskManager()
				.execute(task)
				.getResult());
	}

	/**
	 * Execute la tache TK_LIST_UTILISATEUR.
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> dtcUtilisateur
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> listUtilisateur() {
		final Task task = createTaskBuilder("TK_LIST_UTILISATEUR")
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TK_LIST_UTILISATEUR_BY_ROLE.
	 * @param rolCode String 
	 * @return io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> dtcUtilisateur
	*/
	public io.vertigo.dynamo.domain.model.DtList<com.kleegroup.formation.domain.administration.utilisateur.Utilisateur> listUtilisateurByRole(final String rolCode) {
		final Task task = createTaskBuilder("TK_LIST_UTILISATEUR_BY_ROLE")
				.addValue("ROL_CODE", rolCode)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

}
