package de.rowbuddy.client.views.boat;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.boat.EditDamagePresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;

public class EditDamageView extends HeaderButtonView implements Display {

	private Button saveButton;
	private Button cancelButton;
	private TextArea addInformation;
	private TextArea description;
	private TextBox bootName;
	private CheckBox box;

	private FlexTable content;

	public EditDamageView() {
		super(PageTitles.BOAT_DAMAGE_EDIT);

		saveButton = new Button("Speichern");
		saveButton.setStylePrimaryName("buttonSave buttonPositive");
		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");
		content = new FlexTable();
		description = new TextArea();
		addInformation = new TextArea();
		bootName = new TextBox();
		box = new CheckBox();

		addButton(saveButton);
		addButton(cancelButton);

		content.setText(0, 0, "Boot:");
		content.setText(1, 0, "Beschreibung:");
		content.setText(2, 0, "Weitere Informationen:");
		content.setText(3, 0, "Behoben:");

		content.setWidget(0, 1, bootName);
		content.setWidget(1, 1, description);
		content.setWidget(2, 1, addInformation);
		content.setWidget(3, 1, box);

		setContent(content);

	}

	@Override
	public HasValue<String> getBoatName() {
		return bootName;
	}

	@Override
	public HasValue<String> getDescription() {
		return description;
	}

	@Override
	public HasValue<String> getAddInformation() {
		return addInformation;
	}

	@Override
	public HasValue<Boolean> getFixed() {
		return box;
	}

	@Override
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

}
