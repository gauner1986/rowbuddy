package nl.fontys.jee.rowbuddy.client.ui.trip;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;

public class LogDamagedBoat extends Composite {

	public LogDamagedBoat() {
		setupUi();
	}
	
	private void setupUi(){
		final MultiWordSuggestOracle mwso = new MultiWordSuggestOracle();
		mwso.add("Suggestion 1");
		mwso.add("Suggestion 2");
		mwso.add("Boat 1");
		
		final FlexTable mainTable = new FlexTable();
		mainTable.addStyleName("cw-FlexTable");
		initWidget(mainTable);
		
		mainTable.setWidget(0, 0, new Label("Boot: "));
		mainTable.setWidget(1, 0, new Label("Beschaedigungsbeschreibung: "));
		mainTable.setWidget(2, 0, new Label("zusaetzliche Informationen: "));
		
		SuggestBox boatSB = new SuggestBox(mwso);
		mainTable.setWidget(0, 1, boatSB);
		
		final TextArea damageDescTA = new TextArea();
		mainTable.setWidget(1, 1, damageDescTA);
		
		final TextArea additionalInformationTA = new TextArea();
		mainTable.setWidget(2, 1, additionalInformationTA);
		
		final Button saveButton = new Button("Speichern");
		mainTable.setWidget(3, 0, saveButton);
	}

}
