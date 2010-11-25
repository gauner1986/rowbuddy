package de.rowbuddy.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.events.AddBoatEvent;
import de.rowbuddy.client.events.AddBoatEventHandler;
import de.rowbuddy.client.events.EditBoatEvent;
import de.rowbuddy.client.events.EditBoatEventHandler;
import de.rowbuddy.client.events.ListBoatEvent;
import de.rowbuddy.client.events.ListBoatEventHandler;
import de.rowbuddy.client.presenter.AddBoatPresenter;
import de.rowbuddy.client.presenter.BoatPresenter;
import de.rowbuddy.client.presenter.EditBoatPresenter;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.StatusMessagePresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.MessageView;
import de.rowbuddy.client.views.boat.AddBoatView;
import de.rowbuddy.client.views.boat.BoatView;
import de.rowbuddy.client.views.boat.EditBoatView;

public class AppController implements Presenter, ValueChangeHandler<String>{
	private SimpleEventBus eventBus;
	private BoatRemoteServiceAsync boatService;
	private HasWidgets container;
	private StatusMessagePresenter statusPresenter; 
	
	public AppController(BoatRemoteServiceAsync boatService, SimpleEventBus eventBus, HasWidgets messageContainer) {
		this.boatService = boatService;
		this.eventBus = eventBus;
		this.statusPresenter = new StatusMessagePresenter(new MessageView(), eventBus);
		statusPresenter.start(messageContainer);
		
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
		eventBus.addHandler(ListBoatEvent.TYPE, new ListBoatEventHandler() {
			
			@Override
			public void onListBoatEvent(ListBoatEvent event) {
				doOnListBoatEvent();
			}
		});
		
		eventBus.addHandler(EditBoatEvent.TYPE, new EditBoatEventHandler() {
			
			@Override
			public void onEditBoatEvent(EditBoatEvent event) {
				doOnEditBoat(event.getId());
			}
		});
	}
	
	private void doOnEditBoat(Long id){
		History.newItem(HistoryConstants.EDIT_BOAT, false);
		Presenter presenter = new EditBoatPresenter(new EditBoatView(), boatService, eventBus, id);
		presenter.start(container);
		statusPresenter.clear();
	}
	
	private void doOnListBoatEvent(){
		History.newItem(HistoryConstants.LIST_BOATS);
	}
	
	private void doOnAddBoatEvent(){
		History.newItem(HistoryConstants.ADD_BOAT);
	}
	@Override
	public void onValueChange(ValueChangeEvent<String> arg0) {
		String token = arg0.getValue();
		if(token != null) {
			Presenter presenter = null;
			statusPresenter.clear();
			if(token.equals(HistoryConstants.LIST_BOATS)) {
				presenter = new BoatPresenter(boatService, new BoatView(), eventBus);
			} else if(token.equals(HistoryConstants.ADD_BOAT)){
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
			History.newItem(HistoryConstants.LIST_BOATS);//welcome page
		} else {
			History.fireCurrentHistoryState();
		}
	}

}
