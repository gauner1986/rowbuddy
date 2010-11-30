package de.rowbuddy.client.views.boat;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.BoatDetailPresenter.Display;

public class BoatDetail extends Composite implements Display{
	

	private FlexTable contentTable;
	private DecoratorPanel decorator;
	private FlexTable boatTable;
	private Label nameText;
	private Label numberOfSeats;
	private CheckBox coxed; 
	private CheckBox locked;
	private Button editButton;
	private Button cancelButton;
	
	public BoatDetail(){
		contentTable = new FlexTable();
		decorator = new DecoratorPanel();
		initWidget(decorator);
		decorator.add(contentTable);
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(0);
		hPanel.setSpacing(0);
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		editButton= new Button("Boot bearbeiten");
		editButton.setStylePrimaryName("buttonRegular");
		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");
		hPanel.add(editButton);
		hPanel.add(cancelButton);
	
		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");
		contentTable.setWidget(2, 0, hPanel);

		boatTable = new FlexTable();
		
		boatTable.setText(0, 0, "Name:");
		nameText = new Label();
		boatTable.setWidget(0, 1, nameText);
		
		boatTable.setText(1, 0, "Bootsplätze (ohne Steuermann):");
		numberOfSeats = new Label();
		boatTable.setWidget(1, 1, numberOfSeats);
		
		boatTable.setText(2, 0, "Gesteuert:");
		coxed = new CheckBox();
		coxed.setEnabled(false);
		boatTable.setWidget(2, 1, coxed);
		
		boatTable.setText(3, 0, "Gesperrt:");
		locked = new CheckBox();
		locked.setEnabled(false);
		boatTable.setWidget(3, 1, locked);
		
		contentTable.setWidget(1, 0, boatTable);
		contentTable.setText(0, 0, PageTitles.BOAT_DETAIL);
		HTMLTable.RowFormatter rf = contentTable.getRowFormatter();
		rf.setStylePrimaryName(0, "pageHeadLine");
	}

	@Override
	public void setName(String name) {
		nameText.setText(name);
	}

	@Override
	public void setNumberOfSeats(int value) {
		numberOfSeats.setText(String.valueOf(value));
	}

	@Override
	public void setCoxed(boolean value) {
		coxed.setValue(value);
	}

	@Override
	public void setLocked(boolean value) {
		locked.setValue(value);
	}

	@Override
	public HasClickHandlers getEditButton() {
		return editButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}
	

}
