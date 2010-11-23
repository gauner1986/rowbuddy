package de.rowbuddy.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.events.AddBoatEvent;
import de.rowbuddy.client.events.AddBoatEventHandler;
import de.rowbuddy.client.presenter.AddBoatPresenter;
import de.rowbuddy.client.presenter.BoatPresenter;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.boat.AddBoatView;
import de.rowbuddy.client.views.boat.BoatView;

public class AppController implements Presenter, ValueChangeHandler<String>,HistoryConstants{
	private SimpleEventBus eventBus;
	private BoatRemoteServiceAsync boatService;
	private HasWidgets container;
	
	public AppController(BoatRemoteServiceAsync boatService, SimpleEventBus eventBus) {
		this.boatService = boatService;
		this.eventBus = eventBus;
	}  
	
	//bind Event handling here
	private void bind(){
		History.addValueChangeHandler(this);
		eventBus.addHandler(AddBoatEvent.TYPE, new AddBoatEventHandler() {
			@Override
			public void onAddBoatEvent(AddBoatEvent event) {
				doOnAddBoatEvent();
			}
		});
	}
	
	private void doOnAddBoatEvent(){
		History.newItem("addBoat");
	}
	@Override
	public void onValueChange(ValueChangeEvent<String> arg0) {
		String token = arg0.getValue();
		if(token != null) {
			Presenter presenter = null;
			if(token.equals(LIST_BOATS)) {
				presenter = new BoatPresenter(boatService, new BoatView(), eventBus);
			} else if(token.equals(ADD_BOAT)){
				presenter = new AddBoatPresenter(new AddBoatView(), boatService, eventBus);
			} else {
				Window.alert("Action undefined! - " + token);
			}

			if(presenter != null) {
				presenter.start(container);
			}
		}	
	}

	@Override
	public void start(HasWidgets container) {
		// TODO Auto-generated method stub
		bind();
		this.container = container;
		if(History.getToken().equals("")) {
			History.newItem(LIST_BOATS);//welcome page
		} else {
			History.fireCurrentHistoryState();
		}
	}

}
