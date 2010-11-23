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

import de.rowbuddy.client.events.ListBoatEvent;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.entities.Boat;

public class EditBoatPresenter implements Presenter{
	public interface Display{
		HasClickHandlers getSubmitButton();
		HasClickHandlers getCancelButton();
		HasValue<String> getName();
		HasValue<String> getNumberOfSeats();
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
		this.id = id;
	}
		
	@Override
	public void start(HasWidgets container) {
		// TODO Auto-generated method stub
		container.clear();
		bind();
		fetchBoat();
		container.add(view.asWidget());
		
	}
	
	private void fetchBoat(){
		logger.info("Get Boat id:" + id);
		boatService.getBoat(id, new AsyncCallback<Boat>() {
			
			@Override
			public void onSuccess(Boat arg0) {
				boat = arg0;
				logger.info("Boat feteched! id:" + boat.getId());
				view.getName().setValue(boat.getName());
				view.getNumberOfSeats().setValue(String.valueOf(boat.getNumberOfSeats()));
				view.isCoxed().setValue(boat.isCoxed());
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				logger.severe("Cannot fetch boat:" + arg0.getMessage());
			}
		});
	}
	
	private void bind(){
		view.getSubmitButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				try{
					boat.setName(view.getName().getValue());
					boat.setCoxed(view.isCoxed().getValue());
					int seats = Integer.valueOf(view.getNumberOfSeats().getValue());
					boat.setNumberOfSeats(seats);	
					logger.info("Update boat with id:" + boat.getId());
					boatService.updateBoat(boat, new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void arg0) {
							logger.info("Submit successful GOTO List");
							eventBus.fireEvent(new ListBoatEvent());
						}
						
						@Override
						public void onFailure(Throwable arg0) {
							logger.warning("Cannout update Boat:" + arg0.getMessage());
						}
					});
					
				} catch(NumberFormatException ex){
					logger.warning("Seat is not a number");
				}
			}
		});
	}

}
