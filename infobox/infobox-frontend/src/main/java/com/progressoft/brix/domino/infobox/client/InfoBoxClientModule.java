package com.progressoft.brix.domino.infobox.client;

import com.google.gwt.core.client.EntryPoint;
import com.progressoft.brix.domino.api.client.ModuleConfigurator;
import com.progressoft.brix.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="InfoBox")
public class InfoBoxClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfoBoxClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing InfoBox frontend module ...");
		new ModuleConfigurator().configureModule(new InfoBoxModuleConfiguration());
	}
}
