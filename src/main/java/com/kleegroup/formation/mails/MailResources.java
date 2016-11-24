/**
 *
 */
package com.kleegroup.formation.mails;

import io.vertigo.lang.MessageKey;

/**
 * Ressources des services Mail.
 *
 * @author mlaroche
 * @version $Id: MailResources.java 255 2015-02-09 17:14:17Z mlaroche $
 */
public enum MailResources implements MessageKey {

	/** Impossible d'envoyer le mail : v�rifier l'adresse mail du contributeur. */
	WRONG_MAIL_ADDRESS,

	/** Impossible d'envoyer le mail : r�essayez plus tard. */
	ERROR_MAILING,

	/***********************************************************
	 * Mail
	 ************************************************************/

	/**Confirmation d'inscription à la formation "{0}". */
	MAIL_VAL_SUBJECT_INSCRPITION_MOI,

	/**
	 * Bonjour,<br/>
	 * <br/>
	 * Ceci est un mail automatique envoy� depuis l''application de gestions des formations.<br/>
	 * Vous êtes inscrit à la formation : " <b>{0}</b> ", du <b>{1}</b> au <b>{2}</b> à lieu <b>{3}</b>
	 * Afin d'obtenir plus d'informations vous pouvez vous connecter sur l'application .
	 */
	MAIL_VAL_MESSAGE_INSCRIPTION_MOI,

	/**
	*Inscription à la formation "{0}" par votre responsable
	*/
	MAIL_SUBJECT_INSCRIIPTION_AUTRE,

	/**
	*Bonjour,<br/>
	*<br/>
	*Ceci est un mail automatique envoy� depuis l''application de gestions des formations.<br/>
	*Vous êtes inscrit à la formation : " <b>{0}</b> ", du <b>{1}</b> au <b>{2}</b> à lieu <b>{3}</b> .</br>
	*Afin d'obtenir plus d'informations vous pouvez vous connecter sur l'application .
	*/
	MAIL_MESSAGE_INSCRIPTION_AUTRE,

	/**Annulation de la fomation "{0}"*/
	MAIL_SUBJECT_ANNULATION,

	/**
	*Bonjour,<br/>
	*<br/>
	*Ceci est un mail automatique envoy� depuis l''application de gestions des formations.<br/>
	*La formation est annulée: " <b>{0}</b> ", qui avait lieux du <b>{1}</b> au <b>{2}</b>.
	*/
	MAIL_MESSAGE_ANNULATION,

	/**
	*Modification de la formation "{0}"
	*/
	MAIL_VAL_SUBJECT_MODIFICATION,

	/**
		*Bonjour,<br/>
		*<br/>
		*Ceci est un mail automatique envoy� depuis l''application de gestions des formations.
		*<br/>La formation : " <b>{0}</b> " a été modifié, du <b>{1}</b> au <b>{2}</b> à lieu <b>{3}</b> .</br>
		 *Afin d'obtenir plus d'informations vous pouvez vous connecter sur l'application .
	*/
	MAIL_VAL_MESSAGE_MODIFICATION,
}
