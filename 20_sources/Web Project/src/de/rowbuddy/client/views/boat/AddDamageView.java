package de.rowbuddy.client.views.boat;

import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.boat.AddDamagePresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;

public class AddDamageView extends HeaderButtonView implements Display {

	private FlexTable content;
	private TextBox boatName;
	private TextArea description;
	private TextArea addInformation;
	private Button addButton;
	private Button cancelButton;
	private SuggestBox boat;

	public AddDamageView() {
		super(PageTitles.BOAT_DAMAGE_ADD);
		content = new FlexTable();
		boatName = new TextBox();
		description = new TextArea();
		addInformation = new TextArea();
		addButton = new Button("Hinzuf&uuml;gen");
		addButton.setStylePrimaryName("buttonApply buttonPositive");
		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");

		addButton(addButton);
		addButton(cancelButton);

		content.setText(0, 0, "Bootname:");
		content.setWidget(0, 1, boatName);
		content.setText(1, 0, "Beschreibung:");
		content.setWidget(1, 1, description);
		content.setText(2, 0, "Weitere Informationen:");
		content.setWidget(2, 1, addInformation);

		setContent(content);
	}

	@Override
	public HasValue<String> getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasValue<String> getAddInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasKeyPressHandlers getBoatField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasValue<String> getBoatName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAlternatives(List<String> alternatives) {
		// TODO Auto-generated method stub

	}

	@Override
	public HasClickHandlers getAddButton() {
		return addButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOracle(SuggestOracle oracle) {
		boat = new SuggestBox(oracle, boatName);
		content.setWidget(0, 1, boat);
	}

}
