package org.dominokit.domino.dialogs.client;


import org.dominokit.domino.api.client.annotations.ClientModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="Dialogs")
public class DialogsClientModule {

	private static final Logger LOGGER = LoggerFactory.getLogger(DialogsClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Dialogs frontend module ...");
		//new ModuleConfigurator().configureModule(new DialogsModuleConfiguration());
	}
}
