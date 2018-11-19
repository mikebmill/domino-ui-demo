package org.dominokit.domino.menu.client.views.ui;

import elemental2.dom.HTMLElement;
import jsinterop.base.Js;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.api.shared.extension.Content;
import org.dominokit.domino.layout.shared.extension.IsLayout;
import org.dominokit.domino.menu.client.presenters.MenuPresenter;
import org.dominokit.domino.menu.client.views.MenuView;
import org.dominokit.domino.ui.Typography.Paragraph;
import org.dominokit.domino.ui.collapsible.Collapsible;
import org.dominokit.domino.ui.icons.Icon;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.mediaquery.MediaQuery;
import org.dominokit.domino.ui.notifications.Notification;
import org.dominokit.domino.ui.style.Calc;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.tabs.VerticalTab;
import org.dominokit.domino.ui.tabs.VerticalTabsPanel;
import org.dominokit.domino.ui.tree.Tree;
import org.dominokit.domino.ui.tree.TreeItem;

import static org.dominokit.domino.menu.shared.extension.MenuContext.CanAddMenuItem;
import static org.dominokit.domino.menu.shared.extension.MenuContext.OnMenuSelectedHandler;
import static org.dominokit.domino.ui.style.Unit.px;
import static org.dominokit.domino.ui.style.Unit.vh;
import static org.jboss.gwt.elemento.core.Elements.b;

@UiView(presentable = MenuPresenter.class)
public class MenuViewImpl implements MenuView {

    private Tree menu;
    private Icon lockIcon = Icons.ALL.lock_open()
            .setColor(Color.GREY)
            .style()
            .setMarginBottom("0px")
            .setMarginTop("0px")
            .setCursor("pointer")
            .add(Styles.pull_right)
            .get();
    private boolean locked = false;
    private Collapsible lockCollapsible = Collapsible.create(lockIcon).expand();

    public MenuViewImpl() {
    }

    @Override
    public void setTitle(String title) {
        menu.getTitle().setTextContent(title);
    }

    @Override
    public void init(IsLayout layout) {
        menu = Tree.create("Demo menu");
        menu.getHeader().appendChild(lockIcon.asElement());

        menu.enableSearch()
                .autoExpandFound()
                .style()
                .setHeight(Calc.sub(vh.of(100), px.of(267))).get();

        HTMLElement leftPanel = Js.cast(layout.getLeftPanel().get());
//        leftPanel.appendChild(menu.asElement());

        ;

        leftPanel.appendChild(VerticalTabsPanel.create()
                .textBelowIcon()
                .setColor(Color.RED)
                .appendChild(VerticalTab.create("HOME", Icons.ALL.home())
                        .appendChild(b().textContent("Home Content"))
                        .appendChild(Paragraph.create("HOME")))
                .appendChild(VerticalTab.create("PROFILE", Icons.ALL.face())
                        .appendChild(b().textContent("Profile Content"))
                        .appendChild(Paragraph.create("PROFILE")))
                .appendChild(VerticalTab.create("MESSAGES", Icons.ALL.email())
                        .appendChild(b().textContent("Messages Content"))
                        .appendChild(Paragraph.create("MESSAGES"))
                        .activate())
                .appendChild(VerticalTab.create("SETTINGS", Icons.ALL.settings())
                        .appendChild(b().textContent("Settings Content"))
                        .appendChild(Paragraph.create("SETTINGS")))
                .asElement());


        lockIcon.addClickListener(evt -> {
            if (locked) {
                layout.unfixLeftPanelPosition();
                lockIcon.asElement().textContent = Icons.ALL.lock().getName();
                layout.hideLeftPanel();
                locked = false;
            } else {
                layout.fixLeftPanelPosition();
                lockIcon.asElement().textContent = Icons.ALL.lock_open().getName();
                locked = true;
            }
        });

        MediaQuery.addOnXLargeListener(() -> {
            fixLeftPanel(layout);
            Notification.create("Switched to XLarge screen").show();
        });

        MediaQuery.addOnLargeListener(() -> {
            fixLeftPanel(layout);
            Notification.create("Switched to Large screen").show();
        });
        MediaQuery.addOnMediumListener(() -> {
            unfixLeftPanel(layout);
            Notification.create("Switched to Medium screen").show();
        });

        MediaQuery.addOnSmallListener(() -> {
            unfixLeftPanel(layout);
            Notification.create("Switched to Small screen").show();
        });

        MediaQuery.addOnXSmallListener(() -> {
            unfixLeftPanel(layout);
            Notification.create("Switched to XSmall screen").show();
        });

    }

    private void fixLeftPanel(IsLayout layout) {
        layout.fixLeftPanelPosition();
        lockCollapsible.expand();
        locked = true;
    }

    private void unfixLeftPanel(IsLayout layout) {
        layout.unfixLeftPanelPosition();
        layout.hideLeftPanel();
        lockCollapsible.collapse();
        locked = false;
    }

    @Override
    public CanAddMenuItem addMenuItem(String title, String iconName, OnMenuSelectedHandler selectionHandler) {
        TreeItem menuItem = TreeItem.create(title, Icon.create(iconName));
        menu.appendChild(menuItem);
        menuItem.addClickListener(e -> selectionHandler.onMenuSelected());
        return new SubMenu(menuItem);
    }

    @Override
    public CanAddMenuItem addMenuItem(String title, String iconName) {
        TreeItem menuItem = TreeItem.create(title, Icon.create(iconName));
        menu.appendChild(menuItem);
        return new SubMenu(menuItem);
    }

    @Override
    public Content getContent() {
        return () -> menu.asElement();
    }

    private class SubMenu implements CanAddMenuItem {


        private final TreeItem menuItem;

        private SubMenu(TreeItem menuItem) {
            this.menuItem = menuItem;
        }

        @Override
        public CanAddMenuItem addMenuItem(String title) {
            return addMenuItem(title, "");
        }

        @Override
        public CanAddMenuItem addMenuItem(String title, String iconName) {
            TreeItem item = TreeItem.create(title, Icon.create(iconName))
                    .setActiveIcon(Icons.ALL.keyboard_arrow_right());
            menuItem.appendChild(item);
            return new SubMenu(item);
        }

        @Override
        public CanAddMenuItem addMenuItem(String title, OnMenuSelectedHandler selectionHandler) {
            return addMenuItem(title, "", selectionHandler);
        }

        @Override
        public CanAddMenuItem addMenuItem(String title, String iconName, OnMenuSelectedHandler selectionHandler) {
            TreeItem item = TreeItem.create(title, Icon.create(iconName))
                    .setActiveIcon(Icons.ALL.keyboard_arrow_right());
            menuItem.appendChild(item);
            item.addClickListener(e -> selectionHandler.onMenuSelected());
            return new SubMenu(item);
        }
    }
}