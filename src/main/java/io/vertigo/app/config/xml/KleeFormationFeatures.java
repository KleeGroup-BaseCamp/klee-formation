package io.vertigo.app.config.xml;

import io.vertigo.app.config.discovery.ModuleDiscoveryFeatures;

public class KleeFormationFeatures extends ModuleDiscoveryFeatures {

	public KleeFormationFeatures() {
		super("KleeFormation");
	}

	@Override
	protected String getPackageRoot() {
		return "com.kleegroup.formation";
	}

}
