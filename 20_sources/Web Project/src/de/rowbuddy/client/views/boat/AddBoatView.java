package de.rowbuddy.client.views.boat;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.AddBoatPresenter;

public class AddBoatView extends Composite implements AddBoatPresenter.Display{

	private Button resetButton = null;
	private Button addButton = null;
	private FlexTable contentTable = null;
	private DecoratorPanel decorator = null;
	private FlexTable boatTable = null;
	private TextBox nameText = null;
	private TextBox numberOfSeats = null;
	private CheckBox coxed = null;
	private Button addNextButton = null;
	
	public AddBoatView(){
		contentTable = new FlexTable();
		decorator = new DecoratorPanel();
		initWidget(decorator);
		decorator.add(contentTable);

		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(0);
		hPanel.setSpacing(0);
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		addButton = new Button("Boot anlegen");
		addButton.setStylePrimaryName("buttonPositive");
		resetButton = new Button("Abbrechen");
		resetButton.setStylePrimaryName("buttonNegative");
		addNextButton = new Button("Weiteres Boot hinzufügen");
		addNextButton.setStylePrimaryName("buttonPositive");
		hPanel.add(addButton);
		hPanel.add(addNextButton);
		hPanel.add(resetButton);
	
		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");
		contentTable.setWidget(2, 0, hPanel);

		boatTable = new FlexTable();
		
		boatTable.setText(0, 0, "Name:");
		nameText = new TextBox();
		boatTable.setWidget(0, 1, nameText);
		
		boatTable.setText(1, 0, "Bootsplätze:");
		numberOfSeats = new TextBox();
		boatTable.setWidget(1, 1, numberOfSeats);
		
		boatTable.setText(2, 0, "Gesteuert:");
		coxed = new CheckBox();
		boatTable.setWidget(2, 1, coxed);	
		
		contentTable.setWidget(1, 0, boatTable);
		
		contentTable.setText(0, 0, PageTitles.BOAT_ADD);
		HTMLTable.RowFormatter rf = contentTable.getRowFormatter();
		rf.setStylePrimaryName(0, "pageHeadLine");
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
	public HasClickHandlers getResetButton() {
		return resetButton;
	}

	@Override
	public HasValue<String> getName() {
		return nameText;
	}

	@Override
	public HasValue<String> getNumberOfSeats() {
		return numberOfSeats;
	}

	@Override
	public HasValue<Boolean> isCoxed() {
		return coxed;
	}

	@Override
	public void reset() {
		nameText.setText("");
		numberOfSeats.setText("");
		coxed.setValue(false);
	}

	@Override
	public HasClickHandlers getAdditionalBoat() {
		return addNextButton;
	}

}
