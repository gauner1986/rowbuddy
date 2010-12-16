package de.rowbuddy.client.views.boat;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratedTabBar;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.boat.BoatDetailPresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;

public class BoatDetail extends HeaderButtonView implements Display {

	VerticalPanel vPanel;
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

	public BoatDetail() {
		super(PageTitles.BOAT_DETAIL);

		vPanel = new VerticalPanel();
		vPanel.setWidth("100%");
		editButton = new Button("Boot bearbeiten");
		editButton.setStylePrimaryName("buttonEdit buttonRegular");
		addButton(editButton);
		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");
		addButton(cancelButton);

		detailTable = new FlexTable();
		detailTable.setStylePrimaryName("detailContent");
		detailTable.setCellPadding(5);

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
		bar.selectTab(0);

		damagesTable = new FlexTable();
		damagesTable.setStylePrimaryName("detailContent");
		damagesTable.setText(0, 0, "Bootschäden");
		damagesTable.setText(0, 1, "Gemeldet von");
		damagesTable.setText(0, 2, "Behoben");
		
		reservationTable = new FlexTable();
		reservationTable.setStylePrimaryName("detailContent");
		reservationTable.setText(0, 0, "Von");
		reservationTable.setText(0, 1, "Bis");
		reservationTable.setText(0, 2, "Reserviert von");

		reservationTable.getRowFormatter().setStyleName(0, "boatTableHeader");
		damagesTable.getRowFormatter().setStyleName(0, "boatTableHeader");

		vPanel.add(bar);
		vPanel.add(detailTable);
		setContent(vPanel);
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
		switch (value) {
		case 0:
			vPanel.remove(1);
			vPanel.add(detailTable);
			break;
		case 1:
			vPanel.remove(1);
			vPanel.add(damagesTable);
			break;
		case 2:
			vPanel.remove(1);
			vPanel.add(reservationTable);
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


	@Override
	public UIObject getUiEditButton() {
		return editButton;
	}

}
