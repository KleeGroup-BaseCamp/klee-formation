package com.kleegroup.formation.boot.initializer;

import io.vertigo.core.spaces.component.ComponentInitializer;

/**
 * Initialisation du manager de Securit�.
 * @author npiedeloup
 * @version $Id: SecurityManagerInitializer.java,v 1.4 2014/07/11 10:54:57 npiedeloup Exp $
 */
public final class SecurityManagerInitializer implements ComponentInitializer {

	//	@Inject
	//	private VSecurityManager securityManager;

	/** {@inheritDoc} */
	@Override
	public void init() {
		//securityManager.registerResourceNameFactory(Produit.class.getSimpleName(), new BeanResourceNameFactory("/DATA/Produit/${prdId}/${famId}"));
	}
}
