package de.rowbuddy.client.views.boat;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.boat.AddBoatPresenter;
import de.rowbuddy.client.views.HeaderButtonView;

public class AddBoatView extends HeaderButtonView implements
		AddBoatPresenter.Display {

	private Button resetButton = null;
	private Button addButton = null;
	private FlexTable boatTable = null;
	private TextBox nameText = null;
	private TextBox numberOfSeats = null;
	private CheckBox coxed = null;
	private Button addNextButton = null;

	public AddBoatView() {
		super(PageTitles.BOAT_ADD);
		addButton = new Button("Boot anlegen");
		addButton.setStylePrimaryName("buttonApply buttonPositive");
		addButton(addButton);

		addNextButton = new Button("Weiteres Boot hinzufügen");
		addNextButton.setStylePrimaryName("buttonAdd buttonPositive");
		addButton(addNextButton);

		resetButton = new Button("Abbrechen");
		resetButton.setStylePrimaryName("buttonCancel buttonNegative");
		addButton(resetButton);

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

		setContent(boatTable);
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
