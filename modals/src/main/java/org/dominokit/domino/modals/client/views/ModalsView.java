package org.dominokit.domino.modals.client.views;

import org.dominokit.domino.api.client.mvp.view.View;
import org.dominokit.domino.componentcase.shared.extension.ComponentCase;
import org.dominokit.domino.componentcase.shared.extension.DemoView;

public interface ModalsView extends View, DemoView{
    ComponentCase.ComponentRemoveHandler cleanup();
}