package io.vertigo.app.config.xml;

import io.vertigo.app.config.ComponentDiscovery;
import io.vertigo.app.config.Features;
import io.vertigo.lang.Component;

public abstract class ModuleDiscoveryFeatures extends Features {

	protected ModuleDiscoveryFeatures(final String name) {
		super(name);
	}

	protected abstract String getPackageRoot();

	protected String getServicesRoot() {
		return getPackageRoot() + ".services";
	}

	protected String getDefinitionsRoot() {
		return getPackageRoot() + ".domain";
	}

	protected String getDaoRoot() {
		return getPackageRoot() + ".dao";
	}

	/** {@inheritDoc} */
	@Override
	protected void buildFeatures() {
		getModuleConfigBuilder()
				.addDefinitionResource("classes2", getDefinitionsRoot())
				.addDefinitionResource("kpr", getPackageRoot().replaceAll("\\.", "/") + "/execution.kpr");

		//Services
		ComponentDiscovery.registerComponents(Component.class, getPackageRoot(), getModuleConfigBuilder());

	}
}
