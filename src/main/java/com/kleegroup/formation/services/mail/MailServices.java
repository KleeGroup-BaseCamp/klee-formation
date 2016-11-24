package com.kleegroup.formation.services.mail;

import java.io.IOException;

import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.SessionFormation;

import io.vertigo.dynamo.file.model.VFile;
import io.vertigo.dynamo.store.StoreServices;

public interface MailServices extends StoreServices {

	void envoyerInvitation(Formation formation, final SessionFormation session, final String mail);

	public void envoyerInvitationManager(final Formation formation, final SessionFormation session, final String email, final VFile invitCalendar);

	public VFile genererFichierIcvs(final Formation formation, final SessionFormation session) throws IOException;

	public void envoyerModification(final Formation formation, final SessionFormation session, final String mail);

	void envoyersupression(final Formation formation, final SessionFormation session, final String mail);

}
