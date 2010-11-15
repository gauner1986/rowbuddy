package nl.fontys.jee.rowbuddy.client.ui.trip;

import nl.fontys.jee.rowbuddy.client.ui.TimeBox;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class LogTrip extends Composite {

	public LogTrip() {
		setupUi();
	}
	
	private void setupUi(){
		final MultiWordSuggestOracle mwso = new MultiWordSuggestOracle();
		mwso.add("Suggestion 1");
		mwso.add("Suggestion 2");
		mwso.add("Route 1");
		mwso.add("Route 2");
		mwso.add("Route 3");
		mwso.add("Rower 1");
		mwso.add("Rower 2");
		mwso.add("Rower 3");
		
		final FlexTable mainTable = new FlexTable();
		mainTable.addStyleName("cw-FlexTable");
		initWidget(mainTable);
		
		mainTable.setWidget(0, 0, new Label("Start: "));
		mainTable.setWidget(1, 0, new Label("Ende: "));
		mainTable.setWidget(2, 0, new Label("Route: "));
		mainTable.setWidget(3, 0, new Label("Mitfahrer: "));
		mainTable.setWidget(4, 0, new Label("Boot: "));
		mainTable.setWidget(5, 0, new Label("zusaetzliche Informationen: "));
		
		final DateBox startDateBox = new DateBox();
		
		final TimeBox startTimeBox = new TimeBox();
		
		final Grid startSubTable = new Grid(1,2);
		startSubTable.setWidget(0, 0, startDateBox);
		startSubTable.setWidget(0, 1, startTimeBox);
		
		mainTable.setWidget(0, 1, startSubTable);
		
		
		final DateBox endDateBox = new DateBox();
		
		final TimeBox endTimeBox = new TimeBox();
		
		final Grid endSubTable = new Grid(1,2);
		endSubTable.setWidget(0, 0, endDateBox);
		endSubTable.setWidget(0, 1, endTimeBox);
		
		mainTable.setWidget(1, 1, endSubTable);
		
		
		final SuggestBox routeSB = new SuggestBox(mwso);
		mainTable.setWidget(2, 1, routeSB);
		
		final ListBox rowersLB = new ListBox();
		rowersLB.setVisibleItemCount(5);
		
		final SuggestBox rowersSB = new SuggestBox(mwso);
		
		final FlexTable rowersSubTable = new FlexTable();
		final Button rowersAddButton = new Button("Hinzufuegen");
		rowersAddButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				if(!rowersSB.getText().trim().equals("")){
					rowersLB.addItem(rowersSB.getText());
					rowersSB.setText("");
				}
			}
		});
		final Button rowersDelButton = new Button("Entfernen");
		rowersDelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				rowersLB.removeItem(rowersLB.getSelectedIndex());
			}
		});
		rowersSubTable.setWidget(0, 0, rowersSB);
		rowersSubTable.setWidget(1, 0, rowersLB);
		rowersSubTable.setWidget(0, 1, rowersAddButton);
		rowersSubTable.setWidget(1, 1, rowersDelButton);
		
		mainTable.setWidget(3, 1, rowersSubTable);
		
		
		final SuggestBox boatSB = new SuggestBox(mwso);
		mainTable.setWidget(4, 1, boatSB);
		
		final TextArea additionalInformationTA = new TextArea();
		mainTable.setWidget(5, 1, additionalInformationTA);
		
		final Button registerButton = new Button("Registrieren");
		mainTable.setWidget(6, 0, registerButton);
	}

}
