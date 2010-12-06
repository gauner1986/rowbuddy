package de.rowbuddy.client.views.boat;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabBar;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabBar;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.boat.BoatDetailPresenter.Display;

public class BoatDetail extends Composite implements Display{
	

	private FlexTable contentTable;
	private DecoratorPanel decorator;
	private FlexTable detailTable;
	private FlexTable damagesTable;
	private FlexTable reservationTable;
	private Label nameText;
	private Label numberOfSeats;
	private CheckBox coxed; 
	private CheckBox locked;
	private Button editButton;
	private Button cancelButton;
	private TabBar bar;
	private int damageIndex = 1;
	private int reservationIndex = 1;
	private Logger logger = Logger.getLogger(BoatDetail.class.getName());
	
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
		editButton.setStylePrimaryName("buttonEdit buttonRegular");
		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");

		hPanel.add(editButton);
		hPanel.add(cancelButton);
	
		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");
		contentTable.setWidget(4, 0, hPanel);

		detailTable = new FlexTable();
		
		detailTable.setText(0, 0, "Name:");
		nameText = new Label();
		detailTable.setWidget(0, 1, nameText);
		
		detailTable.setText(1, 0, "Bootsplätze (ohne Steuermann):");
		numberOfSeats = new Label();
		detailTable.setWidget(1, 1, numberOfSeats);
		
		detailTable.setText(2, 0, "Gesteuert:");
		coxed = new CheckBox();
		coxed.setEnabled(false);
		detailTable.setWidget(2, 1, coxed);
		
		detailTable.setText(3, 0, "Gesperrt:");
		locked = new CheckBox();
		locked.setEnabled(false);
		detailTable.setWidget(3, 1, locked);
		
		bar = new DecoratedTabBar();
	    bar.addTab("Details");
	    bar.addTab("Schäden");
	    bar.addTab("Reservierung");
	    	    
	    damagesTable = new FlexTable();
	    damagesTable.setText(0, 0, "Bootschäden");
	    damagesTable.setText(0, 1, "Gemeldet von");
	    damagesTable.setText(0, 2, "Behoben");
	    reservationTable = new FlexTable();
	    
	    reservationTable.setText(0, 0, "Von");
	    reservationTable.setText(0, 1, "Bis");
	    reservationTable.setText(0, 2, "Reserviert von");
	    
	    reservationTable.setStylePrimaryName("boatTable");
	    reservationTable.getRowFormatter().setStyleName(0, "boatTableHeader");
	    damagesTable.setStylePrimaryName("boatTable");
	    damagesTable.getRowFormatter().setStyleName(0, "boatTableHeader");
	    
	    
	    
		contentTable.setWidget(1, 0, bar);
		contentTable.setWidget(2, 0, detailTable);
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

	@Override
	public HasSelectionHandlers<Integer> getTabBar() {
		return bar;
	}

	@Override
	public void setSelection(int value) {
		switch(value){
		case 0:
			contentTable.setWidget(2, 0, detailTable);
			break;
		case 1:
			contentTable.setWidget(2, 0, damagesTable);
			break;
		case 2:
			contentTable.setWidget(2, 0, reservationTable);
			break;
		}
	}

	@Override
	public void addDamageRow(String date, String member, boolean resolved) {
		damagesTable.setText(damageIndex, 0, date);
		damagesTable.setText(damageIndex, 1, member);
		CheckBox box = new CheckBox();
		box.setEnabled(false);
		box.setValue(resolved);
		damagesTable.setWidget(damageIndex, 2, box);
		logger.info("Row added: " + damageIndex);
		damageIndex++;
	}

	@Override
	public void addReservationRow(String date, String member, String logger) {
		damagesTable.setText(reservationIndex, 0, date);
		damagesTable.setText(reservationIndex, 1, member);
		damagesTable.setText(reservationIndex, 2, logger);
		reservationIndex++;
	}

}
