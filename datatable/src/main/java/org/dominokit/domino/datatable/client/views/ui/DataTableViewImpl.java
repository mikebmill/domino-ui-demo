package org.dominokit.domino.datatable.client.views.ui;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.componentcase.client.ui.views.CodeCard;
import org.dominokit.domino.componentcase.client.ui.views.LinkToSourceCode;
import org.dominokit.domino.componentcase.shared.extension.ComponentView;
import org.dominokit.domino.datatable.client.presenters.DatatablePresenter;
import org.dominokit.domino.datatable.client.views.DatatableView;
import org.dominokit.domino.datatable.client.views.model.*;
import org.dominokit.domino.ui.badges.Badge;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.datatable.ColumnConfig;
import org.dominokit.domino.ui.datatable.DataTable;
import org.dominokit.domino.ui.datatable.TableConfig;
import org.dominokit.domino.ui.datatable.events.SimplePaginationPlugin;
import org.dominokit.domino.ui.datatable.events.TableDataUpdatedEvent;
import org.dominokit.domino.ui.datatable.events.TableEvent;
import org.dominokit.domino.ui.datatable.plugins.*;
import org.dominokit.domino.ui.datatable.plugins.filter.header.*;
import org.dominokit.domino.ui.datatable.store.LocalListDataStore;
import org.dominokit.domino.ui.datatable.store.LocalListScrollingDataSource;
import org.dominokit.domino.ui.forms.SelectOption;
import org.dominokit.domino.ui.header.BlockHeader;
import org.dominokit.domino.ui.icons.Icon;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.notifications.Notification;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.ColorScheme;
import org.dominokit.domino.ui.style.Style;
import org.dominokit.domino.ui.utils.TextNode;
import org.dominokit.rest.shared.RestfulRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.jboss.gwt.elemento.core.Elements.*;

@UiView(presentable = DatatablePresenter.class)
public class DataTableViewImpl extends ComponentView<HTMLDivElement> implements DatatableView {

    public static final String MODULE_NAME = "datatable";
    private HTMLDivElement element = div().asElement();
    private List<ContactListParseHandler> contactListParseHandlers = new ArrayList<>();

    @Override
    public void init() {
        element.appendChild(LinkToSourceCode.create(MODULE_NAME, this.getClass()).asElement());
        element.appendChild(BlockHeader.create("DATA TABLES", "For detailed demo code please visit: ")
                .appendChild(a().attr("href", "https://github.com/DominoKit/domino-ui-demo/tree/master/datatable")
                        .attr("target", "_blank")
                        .textContent("Data table demo source code").asElement())
                .asElement());

        basicTable();
        basicFixedTable();
        selectionPlugin();
        markerPlugin();
        recordDetailsPlugin();
        tableHeaderBarPlugin();
        sortAndSearch();
        simplePagination();
        scrollingPagination();
        advancedPagination();
        scrollableTable();
        topPanelPlugin();
        allInOne();
        RestfulRequest.get("https://raw.githubusercontent.com/DominoKit/domino-ui-demo/master/" + MODULE_NAME + "/src/main/java/org/dominokit/domino/" + MODULE_NAME + "/client/views/generated.json")
                .onSuccess(response -> {
                    ContactList contactList = ContactList.MAPPER.read(response.getBodyAsString());
                    contactListParseHandlers.forEach(contactListParseHandler ->
                            contactListParseHandler.onContactsParsed(contactList.getContacts()));
                }).send();

    }


    private void basicTable() {
        TableConfig<Contact> tableConfig = createBasicTableConfig();
        LocalListDataStore<Contact> localListDataStore = new LocalListDataStore<Contact>()
                .setAutoSort(true)
                .setRecordsSorter(new ContactSorter());
        DataTable<Contact> table = new DataTable<>(tableConfig, localListDataStore);

        element.appendChild(Card.create("BASIC TABLE", "By default a table will auto fit columns and allow custom cell content")
                .setCollapsible()
                .appendChild(new TableStyleActions(table))
                .appendChild(table)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            localListDataStore.setData(subList(contacts));
            table.load();
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "basicTable").asElement());
    }

    private void basicFixedTable() {
        TableConfig<Contact> tableConfig = new TableConfig<>();
        tableConfig
                .setFixed(true)
                .addColumn(ColumnConfig.<Contact>create("id", "#")
                        .textAlign("right")
                        .asHeader()
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getIndex() + 1 + "")))

                .addColumn(ColumnConfig.<Contact>create("status", "Status")
                        .textAlign("center")
                        .setCellRenderer(cell -> {
                            if (cell.getTableRow().getRecord().isActive()) {
                                return Style.of(Icons.ALL.check_circle()).setColor(Color.GREEN_DARKEN_3.getHex()).asElement();
                            } else {
                                return Style.of(Icons.ALL.highlight_off()).setColor(Color.RED_DARKEN_3.getHex()).asElement();
                            }
                        }))
                .addColumn(ColumnConfig.<Contact>create("firstName", "First name")
                        .setWidth("200px")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getName())))

                .addColumn(ColumnConfig.<Contact>create("gender", "Gender")
                        .setCellRenderer(cell -> ContactUiUtils.getGenderElement(cell.getRecord()))
                        .textAlign("center"))

                .addColumn(ColumnConfig.<Contact>create("eyeColor", "Eye color")
                        .setCellRenderer(cell -> ContactUiUtils.getEyeColorElement(cell.getRecord()))
                        .textAlign("center"))

                .addColumn(ColumnConfig.<Contact>create("balance", "Balance")
                        .setWidth("250px")
                        .setCellRenderer(cellInfo -> ContactUiUtils.getBalanceElement(cellInfo.getRecord())))

                .addColumn(ColumnConfig.<Contact>create("email", "Email")
                        .setWidth("300px")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getEmail())))

                .addColumn(ColumnConfig.<Contact>create("phone", "Phone")
                        .setWidth("150px")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getPhone())))

                .addColumn(ColumnConfig.<Contact>create("badges", "Badges")
                        .setCellRenderer(cell -> {
                            if (cell.getTableRow().getRecord().getAge() < 35) {
                                return Badge.create("Young")
                                        .setBackground(ColorScheme.GREEN.color()).asElement();
                            }
                            return TextNode.of("");
                        }));
        LocalListDataStore<Contact> localListDataStore = new LocalListDataStore<>();
        DataTable<Contact> defaultTable = new DataTable<>(tableConfig, localListDataStore);

        element.appendChild(Card.create("BASIC TABLE - FIXED", "Fixed tables will use the specified column width and will have scrolls when elements exceeds the body height. ")
                .setCollapsible()
                .appendChild(new TableStyleActions(defaultTable))
                .appendChild(defaultTable)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            localListDataStore.setData(subList(contacts));
            defaultTable.load();
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "fixedBasicTable").asElement());
    }

    private List<Contact> subList(List<Contact> contacts) {
        return subList(contacts, 0, 15);
    }

    private List<Contact> subList(List<Contact> contacts, int from, int to) {
        return contacts.subList(from, to).stream().map(Contact::new).collect(Collectors.toList());
    }

    private void selectionPlugin() {
        TableConfig<Contact> singleSelectionTableConfig = createBasicTableConfig();
        singleSelectionTableConfig.setMultiSelect(false);
        singleSelectionTableConfig.addPlugin(new SelectionPlugin<>(ColorScheme.LIGHT_BLUE));
        LocalListDataStore<Contact> singleLocalStore = new LocalListDataStore<>();
        DataTable<Contact> singleSelectionTable = new DataTable<>(singleSelectionTableConfig, singleLocalStore);
        singleSelectionTable.addSelectionListener((selectedTableRows, selectedRecords) -> {
            Notification.create(selectedRecords.size() + "").show();
        });

        TableConfig<Contact> multiSelectionTableConfig = createBasicTableConfig();
        multiSelectionTableConfig.addPlugin(new SelectionPlugin<>(ColorScheme.LIGHT_BLUE));
        LocalListDataStore<Contact> multiLocalStore = new LocalListDataStore<>();
        DataTable<Contact> multiSelectionTable = new DataTable<>(multiSelectionTableConfig, multiLocalStore);
        multiSelectionTable.addSelectionListener((selectedTableRows, selectedRecords) -> {
            Notification.create(selectedRecords.size() + "").show();
        });

        element.appendChild(Card.create("SELECTION PLUGIN", "Enable row selection by adding the selection plugin, pass different selection style colors in the constructor.")
                .setCollapsible()
                .appendChild(BlockHeader.create("SINGLE SELECTION"))
                .appendChild(new TableStyleActions(singleSelectionTable))
                .appendChild(singleSelectionTable.asElement())
                .appendChild(br())
                .appendChild(br())
                .appendChild(br())
                .appendChild(hr())
                .appendChild(BlockHeader.create("MULTI SELECTION"))
                .appendChild(new TableStyleActions(multiSelectionTable))
                .appendChild(multiSelectionTable)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            singleLocalStore.setData(subList(contacts));
            singleLocalStore.load();
            multiLocalStore.setData(subList(contacts));
            multiSelectionTable.load();
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "selectionPlugin").asElement());
    }

    private void markerPlugin() {
        TableConfig<Contact> tableConfig = createBasicTableConfig();
        tableConfig.addPlugin(new RowMarkerPlugin<>(tableCellInfo -> ContactUiUtils.getBalanceColor(tableCellInfo.getRecord())));
        LocalListDataStore<Contact> localListDataStore = new LocalListDataStore<>();
        DataTable<Contact> defaultTable = new DataTable<>(tableConfig, localListDataStore);

        element.appendChild(Card.create("MARKER PLUGIN", "Mark the left side of the row with custom colors")
                .setCollapsible()
                .appendChild(new TableStyleActions(defaultTable))
                .appendChild(defaultTable)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            localListDataStore.setData(subList(contacts));
            defaultTable.load();
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "markerPlugin").asElement());
    }

    private void recordDetailsPlugin() {
        TableConfig<Contact> tableConfig = createBasicTableConfig();
        tableConfig.addPlugin(new RecordDetailsPlugin<>(cell -> new ContactDetails(cell).asElement()));
        LocalListDataStore<Contact> localListDataStore = new LocalListDataStore<>();
        DataTable<Contact> defaultTable = new DataTable<>(tableConfig, localListDataStore);

        element.appendChild(Card.create("RECORD DETAILS PLUGIN", "Enable inline record details for rows.")
                .setCollapsible()
                .appendChild(new TableStyleActions(defaultTable))
                .appendChild(defaultTable)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            localListDataStore.setData(subList(contacts));
            defaultTable.load();
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "recordDetailsPlugin").asElement());
    }

    private void tableHeaderBarPlugin() {
        TableConfig<Contact> tableConfig = createBasicTableConfig();
        tableConfig.addPlugin(new SelectionPlugin<>());
        tableConfig.addPlugin(new HeaderBarPlugin<Contact>("Demo table", "this a sample table with all features")
                .addActionElement(dataTable -> {
                    Icon selectInactiveIcon = Icons.ALL.highlight_off()
                            .clickable()
                            .setTooltip("Select Inactive")
                            .addClickListener(evt ->
                                    dataTable.getItems().forEach(item -> {
                                        if (!item.getRecord().isActive()) {
                                            item.select();
                                        } else {
                                            item.deselect();
                                        }
                                    }));

                    return a().add(selectInactiveIcon).asElement();
                })
                .addActionElement(dataTable -> {
                    Icon selectInactiveIcon = Icons.ALL.check_circle()
                            .clickable()
                            .setTooltip("Select Active")
                            .addClickListener(evt ->
                                    dataTable.getItems().forEach(tableRow -> {
                                        if (tableRow.getRecord().isActive()) {
                                            tableRow.select();
                                        } else {
                                            tableRow.deselect();
                                        }
                                    }));

                    return a().add(selectInactiveIcon).asElement();

                }));

        LocalListDataStore<Contact> localListDataStore = new LocalListDataStore<>();
        DataTable<Contact> defaultTable = new DataTable<>(tableConfig, localListDataStore);

        element.appendChild(Card.create("HEADER BAR PLUGIN", "Adds a title and description for the table, and allow adding elements to the top right side of the table")
                .setCollapsible()
                .appendChild(new TableStyleActions(defaultTable))
                .appendChild(defaultTable)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            localListDataStore.setData(subList(contacts));
            defaultTable.load();
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "headerBarPlugin").asElement());
    }

    private void sortAndSearch() {
        TableConfig<Contact> tableConfig = createSortableTableConfig();
        tableConfig.addPlugin(ColumnHeaderFilterPlugin.<Contact>create()
                .addHeaderFilter("firstName", TextHeaderFilter.<Contact>create())
                .addHeaderFilter("email", TextHeaderFilter.<Contact>create())
                .addHeaderFilter("phone", TextHeaderFilter.<Contact>create())
                .addHeaderFilter("status", BooleanHeaderFilter.<Contact>create("Active", "Inactive", "Both"))
                .addHeaderFilter("gender", EnumHeaderFilter.<Contact, Gender>create(Gender.values()))
                .addHeaderFilter("balance", DoubleHeaderFilter.<Contact>create())
                .addHeaderFilter("eyeColor", SelectHeaderFilter.<Contact>create()
                        .appendChild(SelectOption.create("blue", "Blue"))
                        .appendChild(SelectOption.create("brown", "Brown"))
                        .appendChild(SelectOption.create("green", "Green"))
                )
        );
        LocalListDataStore<Contact> listStore = new LocalListDataStore<>();
        listStore.setRecordsSorter(new ContactSorter());
        listStore.setSearchFilter(new ContactSearchFilter());
        DataTable<Contact> table = new DataTable<>(tableConfig, listStore);

        element.appendChild(Card.create("SORT PLUGIN & SEARCH HEADER ACTION", "Allows the table to fire events useful for datasource to sort and search the data")
                .setCollapsible()
                .appendChild(new TableStyleActions(table))
                .appendChild(table)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            listStore.setData(subList(contacts));
            table.load();
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "sortAndSearch").asElement());
    }

    private void simplePagination() {
        SimplePaginationPlugin<Contact> simplePaginationPlugin = new SimplePaginationPlugin<>(10);//page size
        TableConfig<Contact> tableConfig = createSortableTableConfig();
        tableConfig.addPlugin(simplePaginationPlugin);

        LocalListDataStore<Contact> localListDataStore = new LocalListDataStore<>();
        localListDataStore.setRecordsSorter(new ContactSorter());
        localListDataStore.setSearchFilter(new ContactSearchFilter());
        localListDataStore.setPagination(simplePaginationPlugin.getSimplePagination());
        DataTable<Contact> table = new DataTable<>(tableConfig, localListDataStore);

        element.appendChild(Card.create("SIMPLE PAGINATION", "Simple pagination plugin allows the table to fire pagination events helpful for the datasource")
                .setCollapsible()
                .appendChild(new TableStyleActions(table))
                .appendChild(table)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            localListDataStore.setData(subList(contacts, 0, 100));
            table.load();
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "simplePagination").asElement());
    }

    private void scrollingPagination() {
        ScrollingPaginationPlugin<Contact> scrollingPagination = new ScrollingPaginationPlugin<>(10, 5);//page size
        TableConfig<Contact> tableConfig = createSortableTableConfig();
        tableConfig.addPlugin(scrollingPagination);

        LocalListDataStore<Contact> localListDataStore = new LocalListDataStore<>();
        localListDataStore.setRecordsSorter(new ContactSorter());
        localListDataStore.setSearchFilter(new ContactSearchFilter());
        localListDataStore.setPagination(scrollingPagination.getPagination());
        DataTable<Contact> table = new DataTable<>(tableConfig, localListDataStore);

        element.appendChild(Card.create("SCROLLING PAGINATION", "Scrolling pagination plugin allows navigation through a set of page at a time in datatable")
                .setCollapsible()
                .appendChild(new TableStyleActions(table))
                .appendChild(table)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            localListDataStore.setData(subList(contacts, 0, 100));
            table.load();
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "scrollingPagination").asElement());
    }

    private void advancedPagination() {
        AdvancedPaginationPlugin<Contact> advancedPagination = new AdvancedPaginationPlugin<>(10);//page size
        TableConfig<Contact> tableConfig = createSortableTableConfig();
        tableConfig.addPlugin(advancedPagination);

        LocalListDataStore<Contact> localListDataStore = new LocalListDataStore<>();
        localListDataStore.setRecordsSorter(new ContactSorter());
        localListDataStore.setSearchFilter(new ContactSearchFilter());
        localListDataStore.setPagination(advancedPagination.getPagination());
        DataTable<Contact> table = new DataTable<>(tableConfig, localListDataStore);

        element.appendChild(Card.create("ADVANCED PAGINATION", "Advanced pagination plugin allows navigation through pages from a dropdown list")
                .setCollapsible()
                .appendChild(new TableStyleActions(table))
                .appendChild(table)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            localListDataStore.setData(subList(contacts, 0, 100));
            table.load();
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "advancedPagination").asElement());
    }

    private TableConfig<Contact> createSortableTableConfig() {
        TableConfig<Contact> tableConfig = new TableConfig<>();
        tableConfig
                .addColumn(ColumnConfig.<Contact>create("id", "#")
                        .textAlign("right")
                        .asHeader()
                        .sortable()
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getIndex() + 1 + "")))

                .addColumn(ColumnConfig.<Contact>create("status", "Status")
                        .textAlign("center")
                        .setCellRenderer(cell -> {
                            if (cell.getTableRow().getRecord().isActive()) {
                                return Style.of(Icons.ALL.check_circle()).setColor(Color.GREEN_DARKEN_3.getHex()).asElement();
                            } else {
                                return Style.of(Icons.ALL.highlight_off()).setColor(Color.RED_DARKEN_3.getHex()).asElement();
                            }
                        }))
                .addColumn(ColumnConfig.<Contact>create("firstName", "First name")
                        .sortable()
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getName())))


                .addColumn(ColumnConfig.<Contact>create("gender", "Gender")
                        .setCellRenderer(cell -> ContactUiUtils.getGenderElement(cell.getRecord()))
                        .textAlign("center"))

                .addColumn(ColumnConfig.<Contact>create("eyeColor", "Eye color")
                        .setCellRenderer(cell -> ContactUiUtils.getEyeColorElement(cell.getRecord()))
                        .textAlign("center"))

                .addColumn(ColumnConfig.<Contact>create("balance", "Balance")
                        .sortable()
                        .setCellRenderer(cellInfo -> ContactUiUtils.getBalanceElement(cellInfo.getRecord())))

                .addColumn(ColumnConfig.<Contact>create("email", "Email")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getEmail())))

                .addColumn(ColumnConfig.<Contact>create("phone", "Phone")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getPhone())))

                .addColumn(ColumnConfig.<Contact>create("badges", "Badges")
                        .setCellRenderer(cell -> {
                            if (cell.getTableRow().getRecord().getAge() < 35) {
                                return Badge.create("Young")
                                        .setBackground(ColorScheme.GREEN.color()).asElement();
                            }
                            return TextNode.of("");
                        }));

        tableConfig
                .addPlugin(new SortPlugin<>())
                .addPlugin(new HeaderBarPlugin<Contact>("Demo table", "this a sample table with all features")
                        .addActionElement(new HeaderBarPlugin.ClearSearch<>())
                        .addActionElement(new HeaderBarPlugin.SearchTableAction<>())
                );
        return tableConfig;
    }

    private TableConfig<Contact> createBasicTableConfig() {
        TableConfig<Contact> tableConfig = new TableConfig<>();
        tableConfig
                .addColumn(ColumnConfig.<Contact>create("id", "#")
                        .textAlign("right")
                        .asHeader()
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getIndex() + 1 + "")))

                .addColumn(ColumnConfig.<Contact>create("status", "Status")
                        .textAlign("center")
                        .setCellRenderer(cell -> {
                            if (cell.getTableRow().getRecord().isActive()) {
                                return Style.of(Icons.ALL.check_circle()).setColor(Color.GREEN_DARKEN_3.getHex()).asElement();
                            } else {
                                return Style.of(Icons.ALL.highlight_off()).setColor(Color.RED_DARKEN_3.getHex()).asElement();
                            }
                        }))
                .addColumn(ColumnConfig.<Contact>create("firstName", "First name")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getName())))


                .addColumn(ColumnConfig.<Contact>create("gender", "Gender")
                        .setCellRenderer(cell -> ContactUiUtils.getGenderElement(cell.getRecord()))
                        .textAlign("center"))

                .addColumn(ColumnConfig.<Contact>create("eyeColor", "Eye color")
                        .setCellRenderer(cell -> ContactUiUtils.getEyeColorElement(cell.getRecord()))
                        .textAlign("center"))

                .addColumn(ColumnConfig.<Contact>create("balance", "Balance")
                        .setCellRenderer(cellInfo -> ContactUiUtils.getBalanceElement(cellInfo.getRecord())))

                .addColumn(ColumnConfig.<Contact>create("email", "Email")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getEmail())))

                .addColumn(ColumnConfig.<Contact>create("phone", "Phone")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getPhone())))

                .addColumn(ColumnConfig.<Contact>create("badges", "Badges")
                        .setCellRenderer(cell -> {
                            if (cell.getTableRow().getRecord().getAge() < 35) {
                                return Badge.create("Young")
                                        .setBackground(ColorScheme.GREEN.color()).asElement();
                            }
                            return TextNode.of("");
                        }));
        return tableConfig;
    }

    private void scrollableTable() {

        TableConfig<Contact> tableConfig = new TableConfig<>();
        tableConfig
                .setFixed(true)
                .addColumn(ColumnConfig.<Contact>create("id", "#")
                        .sortable()
                        .styleCell(cellElement -> Style.of(cellElement).setProperty("vertical-align", "middle"))
                        .textAlign("right")
                        .asHeader()
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getIndex() + 1 + ""))
                        .setWidth("70px"))
                .addColumn(ColumnConfig.<Contact>create("status", "Status")
                        .setWidth("80px")
                        .textAlign("center")
                        .setCellRenderer(cell -> {
                            if (cell.getTableRow().getRecord().isActive()) {
                                return Style.of(Icons.ALL.check_circle()).setColor(Color.GREEN_DARKEN_3.getHex()).asElement();
                            } else {
                                return Style.of(Icons.ALL.highlight_off()).setColor(Color.RED_DARKEN_3.getHex()).asElement();
                            }
                        }))
                .addColumn(ColumnConfig.<Contact>create("firstName", "First name")
                        .sortable()
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getName()))
                        .setWidth("200px"))
                .addColumn(ColumnConfig.<Contact>create("gender", "Gender")
                        .setWidth("100px")
                        .setCellRenderer(cell -> ContactUiUtils.getGenderElement(cell.getRecord()))
                        .textAlign("center"))
                .addColumn(ColumnConfig.<Contact>create("eyeColor", "Eye color")
                        .styleHeader(head -> Style.of(head).setWidth("100px"))
                        .setCellRenderer(cell -> ContactUiUtils.getEyeColorElement(cell.getRecord()))
                        .textAlign("center")
                        .maxWidth("120px"))
                .addColumn(ColumnConfig.<Contact>create("balance", "Balance")
                        .sortable()
                        .setCellRenderer(cellInfo -> ContactUiUtils.getBalanceElement(cellInfo.getRecord()))
                        .setWidth("200px"))
                .addColumn(ColumnConfig.<Contact>create("email", "Email")
                        .setWidth("250px")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getEmail())))
                .addColumn(ColumnConfig.<Contact>create("phone", "Phone")
                        .setWidth("200px")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getPhone())))
                .addColumn(ColumnConfig.<Contact>create("badges", "Badges")
                        .setCellRenderer(cell -> {
                            if (cell.getTableRow().getRecord().getAge() < 35) {
                                return Badge.create("Young")
                                        .setBackground(ColorScheme.GREEN.color()).asElement();
                            }
                            return TextNode.of("");
                        }))
                .addPlugin(new BodyScrollPlugin<>())
                .addPlugin(new HeaderBarPlugin<Contact>("Demo table", "this a sample table with all features")
                        .addActionElement(new HeaderBarPlugin.SearchTableAction<>())
                )

                .addPlugin(new SortPlugin<>());

        LocalListScrollingDataSource<Contact> scrollingDataSource = new LocalListScrollingDataSource<Contact>(10)
                .setSearchFilter(new ContactSearchFilter())
                .setRecordsSorter(new ContactSorter());

        DataTable<Contact> table = new DataTable<>(tableConfig, scrollingDataSource);

        element.appendChild(Card.create("SCROLL LOADING", "Scroll loading requires the table to be fixed. use the Body scroll plugin to fire scroll events.")
                .setCollapsible()
                .appendChild(new TableStyleActions(table))
                .appendChild(table)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            scrollingDataSource.setData(contacts.subList(0, 100));
            table.load();
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "scrollLoading").asElement());

    }

    private void topPanelPlugin() {
        ContactsTopPanel<Contact> topPanel = new ContactsTopPanel<>();
        TableConfig<Contact> tableConfig = new TableConfig<>();
        tableConfig
                .setFixed(true)
                .addColumn(ColumnConfig.<Contact>create("id", "#")
                        .sortable()
                        .styleCell(cellElement -> Style.of(cellElement).setProperty("vertical-align", "middle"))
                        .textAlign("right")
                        .asHeader()
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getIndex() + 1 + ""))
                        .setWidth("70px"))
                .addColumn(ColumnConfig.<Contact>create("status", "Status")
                        .setWidth("80px")
                        .textAlign("center")
                        .setCellRenderer(cell -> {
                            if (cell.getTableRow().getRecord().isActive()) {
                                return Style.of(Icons.ALL.check_circle().asElement()).setColor(Color.GREEN_DARKEN_3.getHex()).asElement();
                            } else {
                                return Style.of(Icons.ALL.highlight_off().asElement()).setColor(Color.RED_DARKEN_3.getHex()).asElement();
                            }
                        }))
                .addColumn(ColumnConfig.<Contact>create("firstName", "First name")
                        .sortable()
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getName()))
                        .setWidth("200px"))
                .addColumn(ColumnConfig.<Contact>create("gender", "Gender")
                        .setWidth("100px")
                        .setCellRenderer(cell -> ContactUiUtils.getGenderElement(cell.getRecord()))
                        .textAlign("center"))
                .addColumn(ColumnConfig.<Contact>create("eyeColor", "Eye color")
                        .styleHeader(head -> Style.of(head).setWidth("100px"))
                        .setCellRenderer(cell -> ContactUiUtils.getEyeColorElement(cell.getRecord()))
                        .textAlign("center")
                        .maxWidth("120px"))
                .addColumn(ColumnConfig.<Contact>create("balance", "Balance")
                        .sortable()
                        .setCellRenderer(cellInfo -> ContactUiUtils.getBalanceElement(cellInfo.getRecord()))
                        .setWidth("200px"))
                .addColumn(ColumnConfig.<Contact>create("email", "Email")
                        .setWidth("250px")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getEmail())))
                .addColumn(ColumnConfig.<Contact>create("phone", "Phone")
                        .setWidth("200px")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getPhone())))
                .addColumn(ColumnConfig.<Contact>create("badges", "Badges")
                        .setCellRenderer(cell -> {
                            if (cell.getTableRow().getRecord().getAge() < 35) {
                                return Badge.create("Young")
                                        .setBackground(ColorScheme.GREEN.color()).asElement();
                            }
                            return TextNode.of("");
                        }))
                .addPlugin(new BodyScrollPlugin<>())
                .addPlugin(new TopPanelPlugin<Contact>() {

                    @Override
                    public HTMLElement asElement() {
                        return topPanel.asElement();
                    }

                    @Override
                    public void handleEvent(TableEvent event) {
                        if (TableDataUpdatedEvent.DATA_UPDATED.equals(event.getType())) {
                            topPanel.update((TableDataUpdatedEvent<Contact>) event);
                        }
                    }
                })
                .addPlugin(new HeaderBarPlugin<Contact>("Demo table", "this a sample table with all features")
                        .addActionElement(new HeaderBarPlugin.SearchTableAction<>())
                )

                .addPlugin(new SortPlugin<>());

        LocalListScrollingDataSource<Contact> scrollingDataSource = new LocalListScrollingDataSource<Contact>(10)
                .setSearchFilter(new ContactSearchFilter())
                .setRecordsSorter(new ContactSorter());

        DataTable<Contact> table = new DataTable<>(tableConfig, scrollingDataSource);

        element.appendChild(Card.create("TOP PANEL PLUGIN", "A simple panel that listens to datatable events and update its content accordingly.")
                .setCollapsible()
                .appendChild(new TableStyleActions(table))
                .appendChild(table)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            List<Contact> data = subList(contacts, 0, 100);
            scrollingDataSource.setData(data);
            table.load();
            topPanel.update(data);
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "topPanelPlugin").asElement());

    }

    private void allInOne() {
        ContactsTopPanel<Contact> topPanel = new ContactsTopPanel<>();
        ScrollingPaginationPlugin<Contact> scrollingPaginationPlugin = new ScrollingPaginationPlugin<>(10, 5);
        TableConfig<Contact> tableConfig = new TableConfig<>();
        tableConfig
                .addColumn(ColumnConfig.<Contact>create("id", "#")
                        .sortable()
                        .styleCell(cellElement -> Style.of(cellElement).setProperty("vertical-align", "middle"))
                        .textAlign("right")
                        .asHeader()
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getIndex() + 1 + ""))
                        .setWidth("70px"))
                .addColumn(ColumnConfig.<Contact>create("status", "Status")
                        .setWidth("80px")
                        .textAlign("center")
                        .setCellRenderer(cell -> {
                            if (cell.getTableRow().getRecord().isActive()) {
                                return Style.of(Icons.ALL.check_circle().asElement()).setColor(Color.GREEN_DARKEN_3.getHex()).asElement();
                            } else {
                                return Style.of(Icons.ALL.highlight_off().asElement()).setColor(Color.RED_DARKEN_3.getHex()).asElement();
                            }
                        }))
                .addColumn(ColumnConfig.<Contact>create("firstName", "First name")
                        .sortable()
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getName()))
                        .setWidth("200px"))
                .addColumn(ColumnConfig.<Contact>create("gender", "Gender")
                        .setWidth("100px")
                        .setCellRenderer(cell -> ContactUiUtils.getGenderElement(cell.getRecord()))
                        .textAlign("center"))
                .addColumn(ColumnConfig.<Contact>create("eyeColor", "Eye color")
                        .styleHeader(head -> Style.of(head).setWidth("100px"))
                        .setCellRenderer(cell -> ContactUiUtils.getEyeColorElement(cell.getRecord()))
                        .textAlign("center")
                        .maxWidth("120px"))
                .addColumn(ColumnConfig.<Contact>create("balance", "Balance")
                        .sortable()
                        .setCellRenderer(cellInfo -> ContactUiUtils.getBalanceElement(cellInfo.getRecord()))
                        .setWidth("200px"))
                .addColumn(ColumnConfig.<Contact>create("email", "Email")
                        .setWidth("250px")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getEmail())))
                .addColumn(ColumnConfig.<Contact>create("phone", "Phone")
                        .setWidth("200px")
                        .setCellRenderer(cell -> TextNode.of(cell.getTableRow().getRecord().getPhone())))
                .addColumn(ColumnConfig.<Contact>create("badges", "Badges")
                        .setCellRenderer(cell -> {
                            if (cell.getTableRow().getRecord().getAge() < 35) {
                                return Badge.create("Young")
                                        .setBackground(ColorScheme.GREEN.color()).asElement();
                            }
                            return TextNode.of("");
                        }))
                .addPlugin(scrollingPaginationPlugin)
                .addPlugin(new TopPanelPlugin<Contact>() {

                    @Override
                    public HTMLElement asElement() {
                        return topPanel.asElement();
                    }

                    @Override
                    public void handleEvent(TableEvent event) {
                        if (TableDataUpdatedEvent.DATA_UPDATED.equals(event.getType())) {
                            topPanel.update((TableDataUpdatedEvent<Contact>) event);
                        }
                    }
                })
                .addPlugin(new HeaderBarPlugin<Contact>("Demo table", "this a sample table with all features")
                        .addActionElement(dataTable -> {
                            Icon selectInactiveIcon = Icons.ALL.highlight_off()
                                    .clickable()
                                    .setTooltip("Select Inactive")
                                    .addClickListener(evt ->
                                            dataTable.getItems().forEach(item -> {
                                                if (!item.getRecord().isActive()) {
                                                    item.select();
                                                } else {
                                                    item.deselect();
                                                }
                                            }));

                            return a().add(selectInactiveIcon).asElement();
                        })
                        .addActionElement(dataTable -> {
                            Icon selectInactiveIcon = Icons.ALL.check_circle()
                                    .clickable()
                                    .setTooltip("Select Active")
                                    .addClickListener(evt ->
                                            dataTable.getItems().forEach(tableRow -> {
                                                if (tableRow.getRecord().isActive()) {
                                                    tableRow.select();
                                                } else {
                                                    tableRow.deselect();
                                                }
                                            }));

                            return a().add(selectInactiveIcon).asElement();

                        })
                        .addActionElement(new HeaderBarPlugin.ClearSearch<>())
                        .addActionElement(new HeaderBarPlugin.SearchTableAction<>())
                )
                .addPlugin(new RecordDetailsPlugin<>(cell -> new ContactDetails(cell).asElement()))
                .addPlugin(new SelectionPlugin<>(ColorScheme.BLUE))
                .addPlugin(new RowMarkerPlugin<>(cellInfo -> ContactUiUtils.getBalanceColor(cellInfo.getRecord())))
                .addPlugin(new SortPlugin<>())
                .addPlugin(ColumnHeaderFilterPlugin.<Contact>create()
                        .addHeaderFilter("firstName", TextHeaderFilter.<Contact>create())
                        .addHeaderFilter("email", TextHeaderFilter.<Contact>create())
                        .addHeaderFilter("phone", TextHeaderFilter.<Contact>create())
                        .addHeaderFilter("status", BooleanHeaderFilter.<Contact>create("Active", "Inactive", "Both"))
                        .addHeaderFilter("gender", EnumHeaderFilter.<Contact, Gender>create(Gender.values()))
                        .addHeaderFilter("balance", DoubleHeaderFilter.<Contact>create())
                        .addHeaderFilter("eyeColor", SelectHeaderFilter.<Contact>create()
                                .appendChild(SelectOption.create("blue", "Blue"))
                                .appendChild(SelectOption.create("brown", "Brown"))
                                .appendChild(SelectOption.create("green", "Green"))
                        )
                );

        LocalListDataStore<Contact> localListDataSource = new LocalListDataStore<Contact>()
                .setSearchFilter(new ContactSearchFilter())
                .setRecordsSorter(new ContactSorter())
                .setPagination(scrollingPaginationPlugin.getPagination());

        DataTable<Contact> table = new DataTable<>(tableConfig, localListDataSource);

        element.appendChild(Card.create("ALL IN ONE", "Try all plugins and feature works together.")
                .setCollapsible()
                .appendChild(new TableStyleActions(table))
                .appendChild(table)
                .asElement());

        contactListParseHandlers.add(contacts -> {
            List<Contact> data = subList(contacts, 0, 100);
            localListDataSource.setData(data);
            table.load();
            topPanel.update(data);
        });

        element.appendChild(CodeCard.createCodeCard(MODULE_NAME, "allInOne").asElement());
    }

    public interface ContactListParseHandler {
        void onContactsParsed(List<Contact> contacts);
    }

    @Override
    public HTMLDivElement getElement() {
        return element;
    }
}