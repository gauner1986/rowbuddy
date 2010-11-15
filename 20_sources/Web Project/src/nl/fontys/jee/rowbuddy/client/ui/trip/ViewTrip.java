package nl.fontys.jee.rowbuddy.client.ui.trip;

import java.util.Date;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class ViewTrip extends Composite {

	public ViewTrip() {
		setupUi();
	}

	private void setupUi(){	
		final FlexTable mainTable = new FlexTable();
		mainTable.addStyleName("cw-FlexTable");
		initWidget(mainTable);
		
		mainTable.setWidget(0, 0, new Label("Start: "));
		mainTable.setWidget(1, 0, new Label("Stop: "));
		mainTable.setWidget(2, 0, new Label("Route: "));
		mainTable.setWidget(3, 0, new Label("Mitfahrer: "));
		mainTable.setWidget(4, 0, new Label("Boot: "));
		mainTable.setWidget(5, 0, new Label("zusaetzliche Informationen: "));
		
		final DateBox startDateBox = new DateBox(new DatePicker(), new Date(System.currentTimeMillis()), new DateBox.DefaultFormat());
		startDateBox.setEnabled(false);
		
		mainTable.setWidget(0, 1, startDateBox);
		
		
		final DateBox endDateBox = new DateBox(new DatePicker(), new Date(System.currentTimeMillis()), new DateBox.DefaultFormat());
		
		mainTable.setWidget(1, 1, endDateBox);
		endDateBox.setEnabled(false);
		
		final TextBox routeTB = new TextBox();
		routeTB.setText("Rhein");
		routeTB.setEnabled(false);
		
		mainTable.setWidget(2, 1, routeTB);
		
		final ListBox rowersLB = new ListBox();
		rowersLB.addItem("Rower 1");
		rowersLB.addItem("Rower 2");
		rowersLB.addItem("Rower 2342342");
		rowersLB.addItem("Rowersf 2");
		rowersLB.addItem("Rower safd2");
		rowersLB.addItem("Rower fdsg2");
		rowersLB.addItem("Rowedgfr 2");
		rowersLB.addItem("Rowerdsfg 2");
		rowersLB.addItem("Rowerfsdgg 2");
		rowersLB.setVisibleItemCount(5);
		rowersLB.setEnabled(false);
		
		mainTable.setWidget(3, 1, rowersLB);
		
		
		final TextBox boatTB = new TextBox();
		boatTB.setText("Jans Boot");
		mainTable.setWidget(4, 1, boatTB);
		boatTB.setEnabled(false);
		
		final TextArea additionalInformationTA = new TextArea();
		additionalInformationTA.setEnabled(false);
		mainTable.setWidget(5, 1, additionalInformationTA);
	}
}
