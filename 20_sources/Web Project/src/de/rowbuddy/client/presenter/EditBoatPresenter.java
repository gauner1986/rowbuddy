package de.rowbuddy.client.presenter;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.entities.Boat;

public class EditBoatPresenter implements Presenter{
	public interface Display{
		HasClickHandlers getSubmitButton();
		HasClickHandlers getCancelButton();
		HasValue<String> getName();
		String getNumberOfSeats();
		HasValue<Boolean> isCoxed();
		Widget asWidget();
	}
	
	private Display view;
	private BoatRemoteServiceAsync boatService;
	private SimpleEventBus eventBus;
	private Boat boat;
	private Long id;
	private Logger logger = Logger.getLogger(EditBoatPresenter.class.getName());
	
	public EditBoatPresenter(Display view, BoatRemoteServiceAsync boatService, SimpleEventBus eventBus, Long id){
		this.view = view;
		this.boatService = boatService;
		this.eventBus = eventBus;
	}
		
	@Override
	public void start(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
	
	private void fetchBoat(){
		boatService.getBoat(id, new AsyncCallback<Boat>() {
			
			@Override
			public void onSuccess(Boat arg0) {
				boat = arg0;
				logger.info("Boat feteched! id:" + boat.getId());
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void bind(){
		view.getSubmitButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				
				
			}
		});
	}

}
