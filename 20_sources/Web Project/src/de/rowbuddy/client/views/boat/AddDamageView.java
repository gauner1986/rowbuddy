package de.rowbuddy.client.views.boat;

import java.util.List;

import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.boat.AddDamagePresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;

public class AddDamageView extends HeaderButtonView implements Display {

	private FlexTable content;

	public AddDamageView() {
		super(PageTitles.BOAT_DAMAGE_ADD);
		content = new FlexTable();
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

}
