package io.vertigo.app.config.xml;

public class KleeFormationFeatures extends ModuleDiscoveryFeatures {

	public KleeFormationFeatures() {
		super("KleeFormation");
	}

	@Override
	protected String getPackageRoot() {
		return "com.kleegroup.formation";
	}

}
