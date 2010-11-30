package de.rowbuddy.client.presenter;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.events.EditBoatEvent;
import de.rowbuddy.client.events.ListBoatEvent;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.entities.Boat;

public class BoatDetailPresenter implements Presenter{

	public interface Display{
		void setName(String name );
		void setNumberOfSeats(int value);
		void setCoxed(boolean value);
		void setLocked(boolean value);
		HasClickHandlers getEditButton();
		HasClickHandlers getCancelButton();
		Widget asWidget();
	}
	
	private Display view;
	private BoatRemoteServiceAsync service;
	private static Logger logger = Logger.getLogger(BoatDetailPresenter.class.getName());
	private Long id;
	private SimpleEventBus eventBus;
	
	public BoatDetailPresenter(Display view, BoatRemoteServiceAsync service, SimpleEventBus eventBus, Long id){
		this.view = view;
		this.service = service;
		this.id = id;
		this.eventBus = eventBus;
	}
	
	@Override
	public void start(HasWidgets container) {
		bind();
		container.clear();
		container.add(view.asWidget());
		fetchBoat();
	}
	
	private void fetchBoat(){
		service.getBoat(id, new AsyncCallback<Boat>() {
			
			@Override
			public void onSuccess(Boat arg0) {
				view.setName(arg0.getName());
				view.setNumberOfSeats(arg0.getNumberOfSeats());
				view.setCoxed(arg0.isCoxed());
				view.setLocked(arg0.isLocked());
			}
			
			@Override
			public void onFailure(Throwable arg0) {		
				logger.severe(arg0.getMessage());
			}
		});
	}
	
	private void bind(){
		view.getEditButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new EditBoatEvent(id));
			}
		});
		
		view.getCancelButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListBoatEvent());
			}
		});
	}
}
