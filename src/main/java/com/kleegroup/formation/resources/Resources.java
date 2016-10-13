package com.kleegroup.formation.resources;

import io.vertigo.lang.MessageKey;

/**
 * Ressources générales.
 *
 * @author cmoutte
 */
public enum Resources implements MessageKey {
	/** L'utilisateur est déjà inscrit à cette session. */
	INS_UNIQUE_UTI_SES_INDEX_9,

	/** La date de session doit être dans le futur. */
	SESSION_DATE_MUST_BE_IN_FUTURE,

	/** La date de fin ne doit pas être avant la date de début de la session. */
	SESSION_DATE_FIN_MUST_BE_IN_FUTURE,

	/** Vous n'avez pas les droits suffisants. */
	DROIT_PAS_SUFFISANT,

	/** Vous n'avez pas entré des horaires corrects. */
	HORAIRES_INCORRECTS,
}
