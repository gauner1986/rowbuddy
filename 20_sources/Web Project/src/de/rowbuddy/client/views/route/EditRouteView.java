package de.rowbuddy.client.views.route;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.route.EditRoutePresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;

public class EditRouteView extends HeaderButtonView implements Display {

	private Button submitButton;
	private Button cancelButton;
	private FlexTable routeTable;
	private TextBox nameText;
	private TextBox lengthText;
	private TextArea descriptionText;
	private CheckBox mutable;
	private DialogBox box;
	private Button deleteButton;
	private Button dialogButton;
	
	public EditRouteView(){
		super(PageTitles.ROUTE_EDIT);
		
		submitButton = new Button("Route speichern");
		submitButton.setStylePrimaryName("buttonSave buttonPositive");
		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");
		dialogButton = new Button("Löschen");
		dialogButton.setStylePrimaryName("buttonDelete buttonNegative");
		
		addButton(submitButton);
		addButton(dialogButton);
		addButton(cancelButton);
		
		routeTable = new FlexTable();
		
		routeTable.setText(0, 0, "Name:");
		nameText = new TextBox();
		nameText.setWidth("100%");
		routeTable.setWidget(0, 1, nameText);
		
		routeTable.setText(1, 0, "Länge:");
		lengthText = new TextBox();
		lengthText.setWidth("100%");
		routeTable.setWidget(1, 1, lengthText);
		
		routeTable.setText(2, 0, "Description:");
		descriptionText = new TextArea();
		descriptionText.setWidth("100%");
		routeTable.setWidget(2, 1, descriptionText);
		
		routeTable.setText(3, 0, "Veränderbar:");
		mutable = new CheckBox();
		routeTable.setWidget(3, 1, mutable);
		
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
				closeDialog();
			}
		});

		dialogContent.add(dialogClose);

		setContent(routeTable);
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
	public HasValue<String> getLength() {
		return lengthText;
	}

	@Override
	public HasValue<String> getDescription() {
		return descriptionText;
	}

	@Override
	public HasValue<Boolean> isMutable() {
		return mutable;
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
