package de.rowbuddy.client.route;

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.HeaderButtonView;
import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.route.AddRoutePresenter.Display;

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

		routeTable.setText(1, 0, "Länge in km:");
		length = new TextBox();
		length.setText("automatische Berechnung");
		length.setWidth("100%");
		length.setEnabled(false);
		routeTable.setWidget(1, 1, length);

		routeTable.setText(2, 0, "Beschreibung:");
		description = new TextArea();
		description.setWidth("100%");
		routeTable.setWidget(2, 1, description);

		routeTable.setText(3, 0, "Veränderbar:");
		mutable = new CheckBox();
		routeTable.setWidget(3, 1, mutable);
		MapOptions options = MapOptions.newInstance();
		options.setDraggableCursor("crosshair");
		options.setDraggingCursor("text");
		LatLng krefeldCity = LatLng.newInstance(51.341256, 6.684687);
		map = new MapWidget(krefeldCity, 13, options);
		map.setStylePrimaryName("mapWidget");
		routeTable.getFlexCellFormatter().setColSpan(4, 0, 2);
		routeTable.getFlexCellFormatter().setColSpan(5, 0, 2);
		// Add some controls for the zoom level
		map.addControl(new LargeMapControl3D());
		map.addControl(new MapTypeControl(true));

		routeTable
				.setWidget(
						4,
						0,
						new HTML(
								"Zum Hinzufügen von GPS-Punkten, bitte auf die Karte klicken.<br/>Zum Löschen von GPS-Punkten, bitte auf einen existieren Punkt rechts klicken."));
		routeTable.setWidget(5, 0, map);

		setContent(routeTable);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	public Button getAddButton() {
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

	@Override
	public Button getResetButton() {
		return resetButton;
	}

	@Override
	public HasValue<String> getShortDescription() {
		return description;
	}

	@Override
	public HasValue<Boolean> isMutable() {
		return mutable;
	}

}
