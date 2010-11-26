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
import com.google.gwt.user.client.ui.TextBox;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.EditBoatPresenter;

public class EditBoatView extends Composite implements EditBoatPresenter.Display{

	private Button submitButton;
	private Button cancelButton;
	private FlexTable contentTable;
	private DecoratorPanel decorator;
	private FlexTable boatTable;
	private TextBox nameText;
	private TextBox numberOfSeats;
	private CheckBox coxed; 
	private CheckBox locked;
	
	public EditBoatView(){
		contentTable = new FlexTable();
		decorator = new DecoratorPanel();
		initWidget(decorator);
		decorator.add(contentTable);

		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(0);
		hPanel.setSpacing(0);
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		submitButton= new Button("Boot speichern");
		submitButton.setStylePrimaryName("buttonSave buttonPositive");
		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");
		hPanel.add(submitButton);
		hPanel.add(cancelButton);
	
		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");
		contentTable.setWidget(2, 0, hPanel);

		boatTable = new FlexTable();
		
		boatTable.setText(0, 0, "Name:");
		nameText = new TextBox();
		boatTable.setWidget(0, 1, nameText);
		
		boatTable.setText(1, 0, "Bootsplätze (ohne Steuermann):");
		numberOfSeats = new TextBox();
		boatTable.setWidget(1, 1, numberOfSeats);
		
		boatTable.setText(2, 0, "Gesteuert:");
		coxed = new CheckBox();
		boatTable.setWidget(2, 1, coxed);
		
		boatTable.setText(3, 0, "Gesperrt:");
		locked = new CheckBox();
		boatTable.setWidget(3, 1, locked);
		
		contentTable.setWidget(1, 0, boatTable);
		contentTable.setText(0, 0, PageTitles.BOAT_EDIT);
		HTMLTable.RowFormatter rf = contentTable.getRowFormatter();
		rf.setStylePrimaryName(0, "pageHeadLine");
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
	public HasValue<String> getNumberOfSeats() {
		return numberOfSeats;
	}

	@Override
	public HasValue<Boolean> isCoxed() {
		return coxed;
	}

	@Override
	public HasValue<Boolean> isLocked() {
		return locked;
	}

}
