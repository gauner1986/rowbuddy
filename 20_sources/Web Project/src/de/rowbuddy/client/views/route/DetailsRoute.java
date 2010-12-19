package de.rowbuddy.client.views.route;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.route.DetailsRoutePresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;

public class DetailsRoute extends HeaderButtonView implements Display {

	VerticalPanel vPanel;
	private FlexTable detailTable;
	private Label nameText;
	private Label lenght;
	private Label description;
	private CheckBox mutable;
	private Button editButton;
	private Button cancelButton;
	private final MapWidget map;

	public DetailsRoute() {
		super(PageTitles.ROUTE_DETAIL);

		vPanel = new VerticalPanel();
		vPanel.setWidth("100%");
		editButton = new Button("Route bearbeiten");
		editButton.setStylePrimaryName("buttonEdit buttonRegular");
		addButton(editButton);
		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");
		addButton(cancelButton);

		detailTable = new FlexTable();
		detailTable.setStylePrimaryName("detailContent");
		detailTable.setCellPadding(5);

		detailTable.setText(0, 0, "Name:");
		nameText = new Label();
		detailTable.setWidget(0, 1, nameText);

		detailTable.setText(1, 0, "Länge in km:");
		lenght = new Label();
		detailTable.setWidget(1, 1, lenght);

		detailTable.setText(2, 0, "Beschreibung:");
		description = new Label();
		detailTable.setWidget(2, 1, description);

		detailTable.setText(3, 0, "Veränderbar:");
		mutable = new CheckBox();
		mutable.setEnabled(false);
		detailTable.setWidget(3, 1, mutable);

		map = new MapWidget();
		map.setStylePrimaryName("mapWidget");
		LatLng krefeldCity = LatLng.newInstance(51.341256, 6.684687);
		map.setCenter(krefeldCity, 13);
		map.getInfoWindow().open(
				map.getCenter(),
				new InfoWindowContent("Crefelder Ruder-Club<br>"
						+ new Anchor("http://www.crefelder-rc.de/news.html")));
		detailTable.getFlexCellFormatter().setColSpan(4, 0, 2);
		// Add some controls for the zoom level
		map.addControl(new LargeMapControl3D());
		map.addControl(new MapTypeControl(true));

		detailTable.setWidget(4, 0, map);
		vPanel.add(detailTable);
		setContent(vPanel);
	}

	@Override
	public void setName(String name) {
		nameText.setText(name);
	}

	@Override
	public void setLenght(String lenght) {
		this.lenght.setText(lenght);
	}

	@Override
	public void setDescription(String description) {
		this.description.setText(description);
	}

	@Override
	public void setMutable(boolean value) {
		mutable.setValue(value);
	}

	@Override
	public HasClickHandlers getEditButton() {
		return editButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	@Override
	public void setMap(LatLng[] points) {
		if (points.length > 0) {
			map.clearOverlays();
			map.closeInfoWindow();
			for (LatLng point : points) {
				map.setCenter(point);
				map.addOverlay(new Marker(point));
			}
			map.addOverlay(new Polyline(points));
		}
	}

}
