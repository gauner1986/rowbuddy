package de.rowbuddy.client.views.route;

import java.util.Collection;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.route.RoutePresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;
import de.rowbuddy.entities.Route;

public class RouteView extends HeaderButtonView implements Display {

	private Button addButton;
	private FlexTable routeTable;

	public RouteView() {
		super(PageTitles.ROUTE_OVERVIEW);

		addButton = new Button("Route hinzufügen");
		addButton.setStyleName("buttonAdd buttonRegular");
		addButton(addButton);

		routeTable = new FlexTable();
		routeTable.setWidth("100%");
		routeTable.setStyleName("boatTable");
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.add(routeTable);
		scrollPanel.setStyleName("scollTable");

		setContent(scrollPanel);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getAddButton() {
		return addButton;
	}

	@Override
	public HasClickHandlers getTable() {
		return routeTable;
	}

	@Override
	public void setData(Collection<Route> routes) {
		routeTable.removeAllRows();
		initTableHead();
		int i = 1;
		for (Route route : routes) {
			routeTable.setText(i, 0, route.getName());
			routeTable.setText(i, 1, route.getLastEditor().getFullName());
			routeTable.setText(i, 2, String.valueOf(route.getLengthKM()));

			HTMLTable.RowFormatter rf = routeTable.getRowFormatter();

			if ((i % 2) != 0) {
				rf.setStylePrimaryName(i, "FlexTable-OddRow");
			} else {
				rf.addStyleName(i, "FlexTable-EvenRow");
			}
		}
	}

	private void initTableHead() {
		routeTable.setText(0, 0, "Name");
		routeTable.setText(0, 1, "Autor");
		routeTable.setText(0, 2, "Länge");

		routeTable.getRowFormatter().setStyleName(0, "boatTableHeader");
		routeTable.setCellPadding(5);
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = routeTable.getCellForEvent(event);

		if (cell != null) {
			selectedRow = cell.getRowIndex();
		}
		return selectedRow;
	}
}
