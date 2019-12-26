package org.dominokit.domino.waves.client.views.ui;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.dominokit.domino.SampleClass;
import org.dominokit.domino.SampleMethod;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.componentcase.client.ui.views.CodeCard;
import org.dominokit.domino.componentcase.client.ui.views.LinkToSourceCode;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.button.ButtonSize;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.header.BlockHeader;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.lists.SimpleListGroup;
import org.dominokit.domino.ui.lists.SimpleListItem;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.style.WaveColor;
import org.dominokit.domino.componentcase.client.ui.views.BaseDemoView;
import org.dominokit.domino.ui.style.WaveStyle;
import org.dominokit.domino.ui.style.WavesSupport;
import org.dominokit.domino.waves.client.presenters.WavesProxy;
import org.dominokit.domino.waves.client.views.WavesView;

import static org.jboss.gwt.elemento.core.Elements.div;

@UiView(presentable = WavesProxy.class)
@SampleClass
public class WavesViewImpl extends BaseDemoView<HTMLDivElement> implements WavesView {

    private HTMLDivElement element;

    @Override
    protected void init(HTMLDivElement root) {
        element.appendChild(LinkToSourceCode.create("waves", this.getClass()).element());
        element.appendChild(BlockHeader.create("WAVES", "Click effect inspired by Google's Material Design")
                .element());


        element.appendChild(Row.create()
                .addColumn(Column.span6()
                        .appendChild(Card.create("COLOR VARIATIONS")
                                .appendChild(SimpleListGroup.create()
                                        .appendChild(SimpleListItem.create("Default").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.LIGHT").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createPrimary("CLICK ME")
                                                        .setWaveColor(WaveColor.LIGHT)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.RED").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.RED)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.PINK").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.PINK)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.PURPLE").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.PURPLE)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.DEEP_PURPLE").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.DEEP_PURPLE)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.INDIGO").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.INDIGO)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.BLUE").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.BLUE)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.LIGHT_BLUE").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.LIGHT_BLUE)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.CYAN").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.CYAN)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.TEAL").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.TEAL)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.GREEN").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.GREEN)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.LIGHT_GREEN").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.LIGHT_GREEN)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.LIME").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.LIME)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.YELLOW").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.YELLOW)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.AMBER").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.AMBER)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.ORANGE").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.ORANGE)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.DEEP_ORANGE").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.DEEP_ORANGE)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.BROWN").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.BROWN)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.GREY").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.GREY)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.BLUE_GREY").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.BLUE_GREY)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.BLACK").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault("CLICK ME")
                                                        .setWaveColor(WaveColor.BLACK)
                                                        .builder().style("min-width:120px;")
                                                        .css(Styles.pull_right))
                                        ))))
                .addColumn(Column.span6()
                        .appendChild(Card.create("CIRCLE")
                                .appendChild(SimpleListGroup.create()
                                        .appendChild(SimpleListItem.create("Default").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.mic())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.LIGHT").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createPrimary(Icons.ALL.keyboard())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.LIGHT)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.RED").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.content_copy())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.RED)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.PINK").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.content_cut())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.PINK)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.PURPLE").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.content_paste())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.PURPLE)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.DEEP_PURPLE").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.access_alarm())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.DEEP_PURPLE)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.INDIGO").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.access_time())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.INDIGO)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.BLUE").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.account_box())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.BLUE)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.LIGHT_BLUE").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.airline_seat_flat())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.LIGHT_BLUE)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.CYAN").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.alarm())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.CYAN)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.TEAL").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.ac_unit())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.TEAL)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.GREEN").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.apps())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.GREEN)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.LIGHT_GREEN").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.assessment())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.LIGHT_GREEN)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.LIME").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.android())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.LIME)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.YELLOW").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.album())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.YELLOW)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.AMBER").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.aspect_ratio())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.AMBER)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.ORANGE").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.autorenew())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.ORANGE)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.DEEP_ORANGE").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.add_a_photo())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.DEEP_ORANGE)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.BROWN").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.add_location())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.BROWN)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.GREY").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.add_box())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.GREY)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.BLUE_GREY").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.add_to_queue())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.BLUE_GREY)
                                                        .builder().css(Styles.pull_right))
                                        )
                                        .appendChild(SimpleListItem.create("WaveColor.BLACK").builder().css(Styles.clearfix).build()
                                                .appendChild(Button.createDefault(Icons.ALL.adjust())
                                                        .circle()
                                                        .setSize(ButtonSize.SMALL)
                                                        .setWaveColor(WaveColor.BLACK)
                                                        .builder().css(Styles.pull_right))
                                        ))))
                .element());

        element.appendChild(CodeCard.createCodeCard(
                "//Elements extending WaveElement will have waves by default\n" +
                        "//to add Waves to elements that does not extend from WaveElement use the WaveSupport class\n\n" +
                        CodeResource.INSTANCE.sample()
        ).element());
    }

    @SampleMethod
    private void sample() {
        HTMLElement element = div().element();
        WavesSupport.addFor(element)
                .setWavesColor(WaveColor.YELLOW)
                .applyWaveStyle(WaveStyle.CIRCLE);
    }

    @Override
    public HTMLDivElement createRoot() {
        element = div().element();
        return element;
    }
}