package de.rowbuddy.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;

public class AppController implements Presenter, ValueChangeHandler<String>{
	private SimpleEventBus eventBus;
	private BoatRemoteServiceAsync boatService;
	
	public AppController(BoatRemoteServiceAsync boatService, SimpleEventBus eventBus) {
		this.boatService = boatService;
	}
	
	private void bind(){
		
	}
	
	@Override
	public void onValueChange(ValueChangeEvent<String> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}

}
