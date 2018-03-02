package com.progressoft.brix.domino.media.client;

import com.google.gwt.core.client.EntryPoint;
import com.progressoft.brix.domino.api.client.ModuleConfigurator;
import com.progressoft.brix.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="Media")
public class MediaClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(MediaClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Media frontend module ...");
		new ModuleConfigurator().configureModule(new MediaModuleConfiguration());
	}
}
