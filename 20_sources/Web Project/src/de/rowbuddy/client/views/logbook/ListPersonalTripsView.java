package de.rowbuddy.client.views.logbook;

import java.text.DateFormat;
import java.util.Collection;

import javax.xml.registry.infomodel.PersonName;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.ListPersonalTripsPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ListPersonalTripsView extends Composite implements
		ListPersonalTripsPresenter.Display {

	private DecoratorPanel decorator;
	private FlexTable contentTable;
	private Button startTripButton;
	private Button logRowedTripButton;
	private FlexTable tripTable;

	public ListPersonalTripsView(String pageTitle) {
		decorator = new DecoratorPanel();
		contentTable = new FlexTable();
		contentTable.setStylePrimaryName("contentTable");

		initWidget(decorator);
		decorator.add(contentTable);

		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);

		startTripButton = new Button("Trip starten");
		startTripButton.setStyleName("buttonAdd buttonRegular");
		hPanel.add(startTripButton);

		logRowedTripButton = new Button("Geruderten Trip eintragen");
		logRowedTripButton.setStyleName("buttonAdd buttonRegular");
		hPanel.add(logRowedTripButton);

		contentTable.setWidget(3, 0, hPanel);

		tripTable = new FlexTable();
		tripTable.setWidth("100%");
		tripTable.setStyleName("boatTable");

		contentTable.setWidget(1, 0, tripTable);
		contentTable.setText(0, 0, pageTitle);
		HTMLTable.RowFormatter rf = contentTable.getRowFormatter();
		rf.setStylePrimaryName(0, "pageHeadLine");
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getStartTripButton() {
		return startTripButton;
	}

	@Override
	public HasClickHandlers getLogRowedTripButton() {
		return logRowedTripButton;
	}

	@Override
	public HasClickHandlers getTable() {
		return tripTable;
	}

	private void initTableHead() {
		tripTable.setText(0, 0, "Route");
		tripTable.setText(0, 1, "Startzeit");
		tripTable.setText(0, 2, "Endzeit");
		tripTable.getRowFormatter().setStyleName(0, "boatTableHeader");

	}

	@Override
	public void setData(Collection<PersonalTripDTO> trips) {
		tripTable.removeAllRows();
		initTableHead();
		int i = 1;
		DateTimeFormat dateFormat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_SHORT);
		for (PersonalTripDTO trip : trips) {
			if (trip.getRoute() != null) {
				tripTable.setText(i, 0, trip.getRoute().getName());
			}
			tripTable.setText(i, 1, dateFormat.format(trip.getStartDate()));
			if (trip.getEndDate() != null) {
				tripTable.setText(i, 2, dateFormat.format(trip.getEndDate()));
			}
			
			HTMLTable.RowFormatter rf = tripTable.getRowFormatter();

			if ((i % 2) != 0) {
				// rf.addStyleName(i, "FlexTable-OddRow");
				rf.setStylePrimaryName(i, "FlexTable-OddRow");
			} else {
				rf.addStyleName(i, "FlexTable-EvenRow");
			}
			i++;
		}
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = tripTable.getCellForEvent(event);

		if (cell != null) {
			selectedRow = cell.getRowIndex();
		}
		return selectedRow;
	}

}
