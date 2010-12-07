package de.rowbuddy.client.views.boat;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.boat.EditBoatPresenter;
import de.rowbuddy.client.views.HeaderButtonView;

public class EditBoatView extends HeaderButtonView implements
		EditBoatPresenter.Display {

	private Button submitButton;
	private Button cancelButton;
	private FlexTable boatTable;
	private TextBox nameText;
	private TextBox numberOfSeats;
	private CheckBox coxed;
	private CheckBox locked;
	private DialogBox box;
	private Button deleteButton;
	private Button dialogButton;

	public EditBoatView() {
		super(PageTitles.BOAT_EDIT);

		submitButton = new Button("Boot speichern");
		submitButton.setStylePrimaryName("buttonSave buttonPositive");
		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");
		dialogButton = new Button("Löschen");
		dialogButton.setStylePrimaryName("buttonDelete buttonNegative");

		addButton(submitButton);
		addButton(dialogButton);
		addButton(cancelButton);

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

		box = new DialogBox(false, true);
		box.setGlassEnabled(true);
		box.setGlassStyleName("glassStyle");
		box.setAnimationEnabled(true);
		deleteButton = new Button("Ja");
		deleteButton.setStylePrimaryName("buttonApply buttonPositive");

		HorizontalPanel dialogContent = new HorizontalPanel();

		box.setText("Möchten Sie wirklich löschen?");
		box.setWidget(dialogContent);
		dialogContent.add(deleteButton);
		Button dialogClose = new Button("Nein");
		dialogClose.setStylePrimaryName("buttonCancel buttonNegative");
		dialogClose.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				// TODO Auto-generated method stub
				closeDialog();
			}
		});

		dialogContent.add(dialogClose);

		setContent(boatTable);
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

	@Override
	public HasClickHandlers getPopUpButton() {
		return dialogButton;
	}

	@Override
	public void showPopUp() {
		box.center();
	}

	@Override
	public HasClickHandlers getDeleteButton() {
		return deleteButton;
	}

	@Override
	public void closeDialog() {
		box.hide();
	}

}
