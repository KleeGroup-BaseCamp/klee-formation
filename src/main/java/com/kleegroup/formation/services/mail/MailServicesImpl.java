/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2016, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
 * KleeGroup, Centre d'affaire la Boursidiere - BP 159 - 92357 Le Plessis Robinson Cedex - France
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kleegroup.formation.services.mail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;

import javax.inject.Inject;

import com.kleegroup.formation.domain.formation.Formation;
import com.kleegroup.formation.domain.formation.SessionFormation;
import com.kleegroup.formation.services.horaires.HorairesServices;

import io.vertigo.core.param.ParamManager;
import io.vertigo.dynamo.file.FileManager;
import io.vertigo.dynamo.file.model.VFile;
import io.vertigo.dynamo.file.util.TempFile;
import io.vertigo.dynamo.transaction.Transactional;
import io.vertigo.lang.MessageText;
import io.vertigo.tempo.mail.Mail;
import io.vertigo.tempo.mail.MailBuilder;
import io.vertigo.tempo.mail.MailManager;

/**
 * Test de l'implémentation standard.
 *
 * @author cmoutte
 */
@Transactional
public class MailServicesImpl implements MailServices {

	@Inject
	private MailManager mailManager;
	@Inject
	private FileManager fileManager;
	@Inject
	private ParamManager paramManager;
	@Inject
	private io.vertigo.dynamo.file.FileManager file;

	@Inject
	private HorairesServices horairesServices;

	@Override
	public void envoyerInvitation(final Formation formation, final SessionFormation session, final String email) {
		final VFile texte = UtilMail.createVFile(file, "test.ics", getClass());
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd/MM/yy");
		final String from = paramManager.getStringValue("mail.from");
		final Mail mail = new MailBuilder()
				.from(from)
				.to(email)
				.withSubject(new MessageText(MailResources.MAIL_VAL_SUBJECT_INSCRPITION_MOI, formation.getIntitule()).getDisplay())
				.withHtmlContent(new MessageText(MailResources.MAIL_VAL_MESSAGE_INSCRIPTION_MOI, formation.getIntitule(), formater.format(session.getDateDebut()).toString(), formater.format(session.getDateFin()).toString(), session.getLieux().toString()).getDisplay())
				.withAttachments(texte)
				.build();
		mailManager.sendMail(mail);
	}

	@Override
	public void envoyerInvitationManager(final Formation formation, final SessionFormation session, final String email, final VFile invitCalendar) {

		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd/MM/yy");
		final String from = paramManager.getStringValue("mail.from");
		final Mail mail = new MailBuilder()
				.from(from)
				.to(email)
				.withSubject(new MessageText(MailResources.MAIL_SUBJECT_INSCRIIPTION_AUTRE, formation.getIntitule()).getDisplay())
				.withHtmlContent(new MessageText(MailResources.MAIL_VAL_MESSAGE_INSCRIPTION_MOI, formation.getIntitule(), formater.format(session.getDateDebut()).toString(), formater.format(session.getDateFin()).toString(), session.getLieux().toString()).getDisplay())
				.withAttachments(invitCalendar)
				.build();
		mailManager.sendMail(mail);
	}

	@Override
	public void envoyerModification(final Formation formation, final SessionFormation session, final String email) {
		final String from = paramManager.getStringValue("mail.from");
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd/MM/yy");
		//"camille.moutte@kleegroup.com"
		final Mail mail = new MailBuilder()
				.from(from)
				.to(email)
				.withSubject(new MessageText(MailResources.MAIL_VAL_SUBJECT_MODIFICATION, formation.getIntitule()).getDisplay())
				.withHtmlContent(new MessageText(MailResources.MAIL_VAL_MESSAGE_MODIFICATION, formation.getIntitule(), formater.format(session.getDateDebut()).toString(), formater.format(session.getDateFin()).toString(), session.getLieux().toString()).getDisplay())
				.build();
		mailManager.sendMail(mail);
	}

	@Override
	public void envoyersupression(final Formation formation, final SessionFormation session, final String email) {
		final String from = paramManager.getStringValue("mail.from");
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd/MM/yy");
		final Mail mail = new MailBuilder()
				.from(from)
				.to(email)
				.withSubject(new MessageText(MailResources.MAIL_SUBJECT_ANNULATION, formation.getIntitule()).getDisplay())
				.withHtmlContent(new MessageText(MailResources.MAIL_MESSAGE_ANNULATION, formation.getIntitule(), formater.format(session.getDateDebut()).toString(), formater.format(session.getDateFin()).toString()).getDisplay())
				.build();
		mailManager.sendMail(mail);
	}

	/// SessionFormation
	@Override
	public VFile genererFichierIcvs(final Formation formation, final SessionFormation session) throws IOException {
		final File tmp = new TempFile("invitation", ".ics");
		try (
				FileWriter fw = new FileWriter(tmp);
				final PrintWriter printWriter = new PrintWriter(fw)) {

			final DateFormat dfDate = new SimpleDateFormat("yyyyMMdd'T'");
			final Format formatHeureMinute = new MessageFormat("{0,number,00}{1,number,00}");

			final ZoneId id = ZoneId.of("Europe/Paris");

			// date de début

			final int heureMinuteDebut = horairesServices.horaireDebut(session.getSesId());
			final Integer heureDebut = heureMinuteDebut / 60;
			final Integer minuteDebut = heureMinuteDebut % 60;

			//final ZonedDateTime zoned = ZonedDateTime.ofInstant(session.getDateDebut().toInstant(), ZoneId.of("Europe/Paris"));
			//final String tz = zoned.getOffset().toString();

			final String line2 = "DTSTART;TZID=Europe/Paris:" + dfDate.format(session.getDateDebut()) + formatHeureMinute.format(new Object[] { heureDebut, minuteDebut }) + "00";

			// date de fin
			final int heureMinuteFin = horairesServices.horaireFin(session.getSesId());
			final int heureFin = heureMinuteFin / 60;
			final int minuteFin = heureMinuteFin % 60;

			final String line3 = "DTEND;TZID=Europe/Paris:" + dfDate.format(session.getDateFin()) + formatHeureMinute.format(new Object[] { heureFin, minuteFin }) + "00";
			final String line4 = "SUMMARY:" + formation.getIntitule();
			printWriter.println("BEGIN:VCALENDAR");
			printWriter.println("VERSION: 2.0");
			printWriter.println("BEGIN:VEVENT");
			printWriter.println(line2);
			printWriter.println(line3);
			printWriter.println(line4);
			printWriter.print("END:VEVENT \nEND:VCALENDAR");
			printWriter.flush();
		}

		return fileManager.createFile("invitation.ics", "text/calendar", tmp);
	}

}
