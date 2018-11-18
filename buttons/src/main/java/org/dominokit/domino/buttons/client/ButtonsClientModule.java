package org.dominokit.domino.buttons.client;


import org.dominokit.domino.api.client.annotations.ClientModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="Buttons")
public class ButtonsClientModule {

	private static final Logger LOGGER = LoggerFactory.getLogger(ButtonsClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Buttons frontend module ...");
		//new ModuleConfigurator().configureModule(new ButtonsModuleConfiguration());
	}
}
