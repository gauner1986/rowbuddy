package de.rowbuddy.client.views.boat;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.boat.DamageDetailPresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;

public class DamageDetailView extends HeaderButtonView implements Display {
	private Button editButton;
	private Button cancelButton;
	private FlexTable content;
	private CheckBox box;

	public DamageDetailView() {
		super(PageTitles.BOAT_DAMAGE_DETAIL);
		editButton = new Button("Schaden bearbeiten");
		cancelButton = new Button("Abbrechen");

		editButton.setStylePrimaryName("buttonEdit buttonRegular");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");

		content = new FlexTable();
		box = new CheckBox();
		box.setEnabled(false);

		addButton(editButton);
		addButton(cancelButton);

		content = new FlexTable();
		content.setWidth("100%");
		content.setText(0, 0, "Boot:");
		content.setText(1, 0, "Beschreibung:");
		content.setText(2, 0, "Weitere Informationen:");
		content.setText(3, 0, "Behoben:");
		// content.setStyleName("boatTable");

		setContent(content);
	}

	@Override
	public void setBoot(String name) {
		content.setText(0, 1, name);
	}

	@Override
	public void setDescription(String description) {
		content.setText(1, 1, description);
	}

	@Override
	public void setAdditionalInformation(String information) {
		content.setText(2, 1, information);
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
	public HasValue<Boolean> getFixed() {
		return box;
	}

}
