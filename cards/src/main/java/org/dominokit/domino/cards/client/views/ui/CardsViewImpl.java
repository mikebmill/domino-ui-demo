package org.dominokit.domino.cards.client.views.ui;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.cards.client.presenters.CardsPresenter;
import org.dominokit.domino.cards.client.views.CardsView;
import org.dominokit.domino.componentcase.client.ui.views.CodeCard;
import org.dominokit.domino.componentcase.client.ui.views.LinkToSourceCode;
import org.dominokit.domino.componentcase.shared.extension.ComponentView;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.header.BlockHeader;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.utils.TextNode;
import org.jboss.gwt.elemento.core.Elements;

@UiView(presentable = CardsPresenter.class)
public class CardsViewImpl extends ComponentView<HTMLDivElement> implements CardsView {

    private static final String SAMPLE_CONTENT = "Quis pharetra a pharetra fames blandit. Risus faucibus velit Risus imperdiet mattis neque volutpat, etiam lacinia netus dictum magnis per facilisi sociosqu. Volutpat. Ridiculus nostra.";
    public static final String MODULE_NAME = "cards";

    private HTMLDivElement element = Elements.div().asElement();

    @Override
    public void init() {
        cardsWithHeaders();
        coloredCards();
        collapsibleCards();
        noHeaderCards();
    }
    private void cardsWithHeaders() {
        element.appendChild(LinkToSourceCode.create(MODULE_NAME, this.getClass()).asElement());
        element.appendChild(BlockHeader.create("CARDS WITH HEADERS", "cards can have a header that has a Title and an optional description.").asElement());
        element.appendChild(Row.create()
                .addColumn(Column.span4()
                        .appendChild(Card.create("Card Title", "Description text here...")
                                .appendChild(TextNode.of(SAMPLE_CONTENT))
                                .addHeaderAction(Icons.ALL.more_vert(), event -> {
                                    DomGlobal.console.info("More action selected");
                                })))

                .addColumn(Column.span4()
                        .appendChild(Card.create("Card Title", "Description text here...")
                                .appendChild(TextNode.of(SAMPLE_CONTENT))
                                .addHeaderAction(Icons.AV_ICONS.mic(), event -> {
                                    DomGlobal.console.info("Play sound");
                                })))

                .addColumn(Column.span4()
                        .appendChild(Card.create("Card Title", "Description text here...")
                                .appendChild(TextNode.of(SAMPLE_CONTENT))
                                .addHeaderAction(Icons.AV_ICONS.mic(), event -> DomGlobal.console.info("Play sound"))
                                .addHeaderAction(Icons.NAVIGATION_ICONS.more_vert(), event -> DomGlobal.console.info("More action selected"))))
                .asElement());

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME,"cardsWithHeaders").asElement());
    }

    private void coloredCards() {
        element.appendChild(BlockHeader.create("COLORED CARDS", "You can control the background color of card, card header and card body.").asElement());

        element.appendChild(Row.create()
                .addColumn(Column.span4().appendChild(Card.create("Light Blue Card", "Description text here...")
                        .setBackground(Color.LIGHT_BLUE)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))
                        .addHeaderAction(Icons.ALL.more_vert(), event -> DomGlobal.console.info("More action selected"))))

                .addColumn(Column.span4().appendChild(Card.create("Light Green Card", "Description text here...")
                        .setBackground(Color.LIGHT_GREEN)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))
                        .addHeaderAction(Icons.AV_ICONS.mic(), event -> DomGlobal.console.info("Play sound"))))

                .addColumn(Column.span4().appendChild(Card.create("Amber card", "Description text here...")
                        .setBackground(Color.AMBER)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))
                        .addHeaderAction(Icons.AV_ICONS.mic(), event -> DomGlobal.console.info("Play sound"))
                        .addHeaderAction(Icons.NAVIGATION_ICONS.more_vert(), event -> DomGlobal.console.info("More action selected"))))
                .asElement());

        element.appendChild(Row.create()
                .addColumn(Column.span4().appendChild(Card.create("Pink Card", "Description text here...")
                        .setHeaderBackground(Color.PINK)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))
                        .addHeaderAction(Icons.ALL.more_vert(), event -> DomGlobal.console.info("More action selected"))))

                .addColumn(Column.span4().appendChild(Card.create("Blue Grey Card", "Description text here...")
                        .setHeaderBackground(Color.BLUE_GREY)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))
                        .addHeaderAction(Icons.AV_ICONS.mic(), event -> DomGlobal.console.info("Play sound"))))

                .addColumn(Column.span4().appendChild(Card.create("Deep Orange card", "Description text here...")
                        .setHeaderBackground(Color.DEEP_ORANGE)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))
                        .addHeaderAction(Icons.AV_ICONS.mic(), event -> DomGlobal.console.info("Play sound"))
                        .addHeaderAction(Icons.NAVIGATION_ICONS.more_vert(), event -> DomGlobal.console.info("More action selected"))))
                .asElement());

        element.appendChild(Row.create()
                .addColumn(Column.span4().appendChild(Card.create("Light Blue Card", "Description text here...")
                        .setHeaderBackground(Color.BLUE)
                        .setBodyBackground(Color.LIGHT_BLUE)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))
                        .addHeaderAction(Icons.ALL.more_vert(), event -> DomGlobal.console.info("More action selected"))))

                .addColumn(Column.span4().appendChild(Card.create("Light Green Card", "Description text here...")
                        .setHeaderBackground(Color.GREEN)
                        .setBodyBackground(Color.LIGHT_GREEN)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))
                        .addHeaderAction(Icons.AV_ICONS.mic(), event -> DomGlobal.console.info("Play sound"))))

                .addColumn(Column.span4().appendChild(Card.create("Amber card", "Description text here...")
                        .setHeaderBackground(Color.ORANGE)
                        .setBodyBackground(Color.AMBER)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))
                        .addHeaderAction(Icons.AV_ICONS.mic(), event -> DomGlobal.console.info("Play sound"))
                        .addHeaderAction(Icons.NAVIGATION_ICONS.more_vert(), event -> DomGlobal.console.info("More action selected"))))
                .asElement());

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME,"coloredCards").asElement());
    }

    private void collapsibleCards() {
        element.appendChild(BlockHeader.create("COLLAPSIBLE CARDS", "cards can be collapsible.").asElement());

        element.appendChild(Row.create()
                .addColumn(Column.span4().appendChild(Card.create("Card Title", "Description text here...")
                        .setCollapsible()
                        .setCollapseDuration(50)
                        .setHeaderBackground(Color.THEME)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))
                        .addHeaderAction(Icons.ALL.more_vert(), event -> DomGlobal.console.info("More action selected"))))

                .addColumn(Column.span4().appendChild(Card.create("Card Title", "Description text here...")
                        .setCollapsible()
                        .setCollapseDuration(50)
                        .setHeaderBackground(Color.BROWN)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))
                        .addHeaderAction(Icons.AV_ICONS.mic(), event -> DomGlobal.console.info("Play sound"))))

                .addColumn(Column.span4().appendChild(Card.create("Card Title", "Description text here...")
                        .setCollapsible()
                        .setCollapseDuration(50)
                        .collapse()
                        .setHeaderBackground(Color.CYAN)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))
                        .addHeaderAction(Icons.AV_ICONS.mic(), event -> DomGlobal.console.info("Play sound"))
                        .addHeaderAction(Icons.NAVIGATION_ICONS.more_vert(), event -> DomGlobal.console.info("More action selected"))))
                .asElement());

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME,"collapsibleCards").asElement());
    }

    private void noHeaderCards() {
        element.appendChild(BlockHeader.create("NO HEADER CARDS", "You can also create cards without headers.").asElement());

        element.appendChild(Row.create()
                .addColumn(Column.span4().appendChild(Card.create()
                        .setBackground(Color.GREEN)
                        .appendChild(TextNode.of(SAMPLE_CONTENT))))
                .addColumn(Column.span4().appendChild(Card.create()
                        .setBackground(Color.LIGHT_BLUE).appendChild(TextNode.of(SAMPLE_CONTENT))))
                .addColumn(Column.span4().appendChild(Card.create()
                        .setBackground(Color.PURPLE).appendChild(TextNode.of(SAMPLE_CONTENT))))
                .asElement());

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME,"noHeaderCards").asElement());
    }

    @Override
    public HTMLDivElement getElement() {
        return element;
    }
}