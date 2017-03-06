package com.kleegroup.formation.boot.initializer;

import javax.inject.Inject;

import com.kleegroup.formation.ui.controller.menu.NavigationItem;

import io.vertigo.core.component.ComponentInitializer;
import io.vertigo.persona.impl.security.BeanResourceNameFactory;
import io.vertigo.persona.security.VSecurityManager;

/**
 * Initialisation du manager de Securit�.
 * @author npiedeloup
 * @version $Id: SecurityManagerInitializer.java,v 1.4 2014/07/11 10:54:57 npiedeloup Exp $
 */
public final class SecurityManagerInitializer implements ComponentInitializer {

	@Inject
	private VSecurityManager securityManager;

	/** {@inheritDoc} */
	@Override
	public void init() {
		registerResourceNameFactory(NavigationItem.class.getSimpleName(), "/PAGE/${rawAddress}", securityManager);

	}

	private static void registerResourceNameFactory(final String resourceType, final String securityPattern, final VSecurityManager securityManager) {
		final BeanResourceNameFactory beanResourceNameFactory = new BeanResourceNameFactory(securityPattern);
		securityManager.registerResourceNameFactory(resourceType, beanResourceNameFactory);
	}

}
