package org.dominokit.domino.advancedforms.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name = "AdvancedForms")
public class AdvancedFormsClientModule implements EntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvancedFormsClientModule.class);

    public void onModuleLoad() {
        LOGGER.info("Initializing AdvancedForms frontend module ...");
        new ModuleConfigurator().configureModule(new AdvancedFormsModuleConfiguration());
    }
}
