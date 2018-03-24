package org.dominokit.domino.alerts.client.presenters;

import org.dominokit.domino.alerts.client.views.AlertsView;
import org.dominokit.domino.api.client.annotations.InjectContext;
import org.dominokit.domino.api.client.annotations.Presenter;
import org.dominokit.domino.api.client.mvp.presenter.BaseClientPresenter;
import org.dominokit.domino.api.shared.extension.Content;
import org.dominokit.domino.componentcase.shared.extension.ComponentCase;
import org.dominokit.domino.components.shared.extension.ComponentsContext;
import org.dominokit.domino.components.shared.extension.ComponentsExtensionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Presenter
public class AlertsPresenter extends BaseClientPresenter<AlertsView> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertsPresenter.class);

    @InjectContext(extensionPoint=ComponentsExtensionPoint.class)
    public void contributeToComponentsModule(ComponentsContext context) {
        context.getComponentCaseContext().addComponentCase(new ComponentCase() {
            @Override
            public String getHistoryToken() {
                return "components/alerts";
            }

            @Override
            public String getMenuPath() {
                return "Components/Alerts";
            }

            @Override
            public Content getContent() {
                return view.getContent();
            }
        });
    }
}