package de.rowbuddy.client.views.route;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.route.EditRoutePresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;

public class EditRouteView extends HeaderButtonView implements Display {

	private Button submitButton;
	private Button cancelButton;
	private FlexTable routeTable;
	private TextBox nameText;
	private TextBox lengthText;
	private TextArea descriptionText;
	private CheckBox mutable;
	private DialogBox box;
	private Button deleteButton;
	private Button dialogButton;
	private final MapWidget map;

	public EditRouteView() {
		super(PageTitles.ROUTE_EDIT);

		submitButton = new Button("Route speichern");
		submitButton.setStylePrimaryName("buttonSave buttonPositive");
		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");
		dialogButton = new Button("Löschen");
		dialogButton.setStylePrimaryName("buttonDelete buttonNegative");

		addButton(submitButton);
		addButton(dialogButton);
		addButton(cancelButton);

		routeTable = new FlexTable();

		routeTable.setText(0, 0, "Name:");
		nameText = new TextBox();
		nameText.setWidth("100%");
		routeTable.setWidget(0, 1, nameText);

		routeTable.setText(1, 0, "Länge in km:");
		lengthText = new TextBox();
		lengthText.setWidth("100%");
		routeTable.setWidget(1, 1, lengthText);

		routeTable.setText(2, 0, "Description:");
		descriptionText = new TextArea();
		descriptionText.setWidth("100%");
		routeTable.setWidget(2, 1, descriptionText);

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

		box = new DialogBox(false, true);
		box.setGlassEnabled(true);
		box.setGlassStyleName("glassStyle");
		box.setAnimationEnabled(true);
		deleteButton = new Button("Ja");
		deleteButton.setStylePrimaryName("buttonApply buttonPositive");

		HorizontalPanel dialogContent = new HorizontalPanel();

		box.setText("Möchten Sie wirklich löschen?");
		box.setWidget(dialogContent);
		dialogContent.add(deleteButton);
		Button dialogClose = new Button("Nein");
		dialogClose.setStylePrimaryName("buttonCancel buttonNegative");
		dialogClose.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				closeDialog();
			}
		});

		dialogContent.add(dialogClose);

		setContent(routeTable);
	}

	@Override
	public HasClickHandlers getSubmitButton() {
		return submitButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	@Override
	public HasValue<String> getName() {
		return nameText;
	}

	@Override
	public HasValue<String> getLength() {
		return lengthText;
	}

	@Override
	public HasValue<String> getDescription() {
		return descriptionText;
	}

	@Override
	public HasValue<Boolean> isMutable() {
		return mutable;
	}

	@Override
	public HasClickHandlers getPopUpButton() {
		return dialogButton;
	}

	@Override
	public void showPopUp() {
		box.center();
	}

	@Override
	public HasClickHandlers getDeleteButton() {
		return deleteButton;
	}

	@Override
	public void closeDialog() {
		box.hide();
	}

	@Override
	public void setMap(LatLng[] points, Polyline polyline) {
		if (points.length > 0) {
			map.clearOverlays();
			map.closeInfoWindow();
			for (LatLng point : points) {
				map.setCenter(point);
				map.addOverlay(new Marker(point));
			}
			map.addOverlay(polyline);
		}
	}

	@Override
	public MapWidget getMap() {
		return map;
	}

}
