package de.rowbuddy.client.views.boat;
import java.util.Collection;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.business.BoatOverview;
import de.rowbuddy.client.presenter.BoatPresenter;

public class BoatView extends Composite implements BoatPresenter.Display{

	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasClickHandlers getAddButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setData(Collection<BoatOverview> boats) {
		// TODO Auto-generated method stub
		
	}

}
