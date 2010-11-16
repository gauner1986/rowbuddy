package de.rowbuddy.client.presenter;

import java.util.Collection;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.business.BoatOverview;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;

public class BoatPresenter {
	public interface Display{
		Widget asWidget();
		HasClickHandlers getAddButton();
		void setData(Collection<BoatOverview> boats);
		int getClickedRow(ClickEvent event);
	}
	
	private Display view;
	private BoatRemoteServiceAsync boatService;
	private SimpleEventBus eventBus;
	
	public BoatPresenter(BoatRemoteServiceAsync boatService, Display view, SimpleEventBus eventBus) {
		this.view = view;
		this.boatService = boatService;
		this.eventBus = eventBus;
	}
	
	
}
