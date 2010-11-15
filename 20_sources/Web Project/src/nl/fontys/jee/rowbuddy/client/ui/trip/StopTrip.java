package nl.fontys.jee.rowbuddy.client.ui.trip;

import java.util.Date;

import nl.fontys.jee.rowbuddy.client.ui.TimeBox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class StopTrip extends Composite {

	public StopTrip() {
		setupUi();
	}
	
	private void setupUi(){	
		final FlexTable mainTable = new FlexTable();
		mainTable.addStyleName("cw-FlexTable");
		initWidget(mainTable);
		
		mainTable.setWidget(0, 0, new Label("Start: "));
		mainTable.setWidget(1, 0, new Label("Route: "));
		mainTable.setWidget(2, 0, new Label("Mitfahrer: "));
		mainTable.setWidget(3, 0, new Label("Boot: "));
		mainTable.setWidget(4, 0, new Label("zusaetzliche Informationen: "));
		
		final DateBox startDateBox = new DateBox(new DatePicker(), new Date(System.currentTimeMillis()), new DateBox.DefaultFormat());
		startDateBox.setEnabled(false);
		
		final TimeBox startTimeBox = new TimeBox();
		startTimeBox.setEnabled(false);
		
		final Grid startSubTable = new Grid(1,2);
		startSubTable.setWidget(0, 0, startDateBox);
		startSubTable.setWidget(0, 1, startTimeBox);
		
		mainTable.setWidget(0, 1, startSubTable);
		
		final TextBox routeTB = new TextBox();
		routeTB.setText("Rhein");
		routeTB.setEnabled(false);
		
		mainTable.setWidget(1, 1, routeTB);
		
		final ListBox rowersLB = new ListBox();
		rowersLB.addItem("Rower 1");
		rowersLB.addItem("Rower 2");
		rowersLB.setVisibleItemCount(5);
		rowersLB.setEnabled(false);
		
		mainTable.setWidget(2, 1, rowersLB);
		
		
		final TextBox boatTB = new TextBox();
		mainTable.setWidget(3, 1, boatTB);
		boatTB.setEnabled(false);
		
		final TextArea additionalInformationTA = new TextArea();
		mainTable.setWidget(4, 1, additionalInformationTA);
		
		final Button endButton = new Button("Fahrt beenden");
		mainTable.setWidget(5, 0, endButton);
	}

}
