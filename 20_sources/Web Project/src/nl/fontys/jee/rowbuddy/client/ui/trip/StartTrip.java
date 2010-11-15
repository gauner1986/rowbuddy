package nl.fontys.jee.rowbuddy.client.ui.trip;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

public class StartTrip extends Composite {

	public StartTrip() {
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
		
		mainTable.setWidget(0, 0, new Label("Route: "));
		mainTable.setWidget(1, 0, new Label("Mitfahrer: "));
		mainTable.setWidget(2, 0, new Label("Boot: "));
		mainTable.setWidget(3, 0, new Label("zusaetzliche Informationen: "));
		
		final SuggestBox routeSB = new SuggestBox(mwso);
		mainTable.setWidget(0, 1, routeSB);
		
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
		
		mainTable.setWidget(1, 1, rowersSubTable);
		
		
		final SuggestBox boatSB = new SuggestBox(mwso);
		mainTable.setWidget(2, 1, boatSB);
		
		final TextArea additionalInformationTA = new TextArea();
		mainTable.setWidget(3, 1, additionalInformationTA);
		
		final Button registerButton = new Button("Registrieren");
		mainTable.setWidget(4, 0, registerButton);
	}

}
