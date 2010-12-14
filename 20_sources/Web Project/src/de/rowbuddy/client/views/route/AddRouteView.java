package de.rowbuddy.client.views.route;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.route.AddRoutePresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;

public class AddRouteView extends HeaderButtonView implements Display {

	private Button resetButton = null;
	private Button addButton = null;
	private FlexTable routeTable = null;
	private TextBox nameText = null;
	private TextBox length = null;
	private TextArea description = null;
	private CheckBox mutable = null;
	private MapWidget map = null;

	public AddRouteView() {
		super(PageTitles.ROUTE_ADD);
		addButton = new Button("Route anlegen");
		addButton.setStylePrimaryName("buttonApply buttonPositive");
		addButton(addButton);

		resetButton = new Button("Abbrechen");
		resetButton.setStylePrimaryName("buttonCancel buttonNegative");
		addButton(resetButton);

		routeTable = new FlexTable();

		routeTable.setText(0, 0, "Name:");
		nameText = new TextBox();
		nameText.setWidth("100%");
		routeTable.setWidget(0, 1, nameText);

		routeTable.setText(1, 0, "Länge:");
		length = new TextBox();
		length.setWidth("100%");
		routeTable.setWidget(1, 1, length);

		routeTable.setText(2, 0, "Beschreibung:");
		description = new TextArea();
		description.setWidth("100%");
		routeTable.setWidget(2, 1, description);

		routeTable.setText(3, 0, "Veränderbar:");
		mutable = new CheckBox();
		routeTable.setWidget(3, 1, mutable);

		map = new MapWidget();
		map.setStylePrimaryName("mapWidget");
		LatLng krefeldCity = LatLng.newInstance(51.341256, 6.684687);
		map.setCenter(krefeldCity, 13);
		routeTable.getFlexCellFormatter().setColSpan(4, 0, 2);
		// Add some controls for the zoom level
		map.addControl(new LargeMapControl());
		map.addControl(new MapTypeControl(true));

		routeTable.setWidget(4, 0, map);

		setContent(routeTable);
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
	public HasValue<String> getName() {
		return nameText;
	}

	@Override
	public HasValue<String> getLengthKM() {
		return length;
	}

	@Override
	public void reset() {
		nameText.setText("");
		length.setText("");
		description.setText("");
		mutable.setValue(false);
		map.clearOverlays();
	}

	@Override
	public MapWidget getMap() {
		return map;
	}

}
