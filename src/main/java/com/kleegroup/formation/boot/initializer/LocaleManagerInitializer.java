package com.kleegroup.formation.boot.initializer;

import javax.inject.Inject;

import io.vertigo.core.component.ComponentInitializer;
import io.vertigo.core.locale.LocaleManager;

/**
 * Initializer de LocaleManager.
 * @author
 * @version $Id: LocaleManagerInitializer.java,v 1.4 2014/02/07 16:48:27 npiedeloup Exp $
 */
public final class LocaleManagerInitializer implements ComponentInitializer {

	@Inject
	private LocaleManager localeManager;

	/** {@inheritDoc} */
	@Override
	public void init() {
		localeManager.add("io.vertigo.dynamox.domain.constraint.Constraint", io.vertigo.dynamox.domain.constraint.Resources.values());
		localeManager.add("io.vertigo.dynamox.domain.formatter.Formatter", io.vertigo.dynamox.domain.formatter.Resources.values());
		localeManager.add("com.kleegroup.formation.domain.formation.DtResources", com.kleegroup.formation.domain.formation.DtResources.values());
		localeManager.add("com.kleegroup.formation.domain.session.DtResources", com.kleegroup.formation.domain.session.DtResources.values());
		localeManager.add("com.kleegroup.formation.domain.administration.utilisateur.DtResources", com.kleegroup.formation.domain.administration.utilisateur.DtResources.values());
		localeManager.add("com.kleegroup.formation.domain.inscription.DtResources", com.kleegroup.formation.domain.inscription.DtResources.values());
		
		// Messages Ui vertigo
		localeManager.add("io.vertigo.struts2.resources.Resources", io.vertigo.struts2.resources.Resources.values());
		localeManager.add("com.kleegroup.formation.mails.MailResources", com.kleegroup.formation.mails.MailResources.values());
		localeManager.add("com.kleegroup.formation.resources.Resources", com.kleegroup.formation.resources.Resources.values());
	}
}
