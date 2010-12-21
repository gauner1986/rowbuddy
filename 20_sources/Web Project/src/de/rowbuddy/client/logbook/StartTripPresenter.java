package de.rowbuddy.client.logbook;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.boundary.dtos.RouteDTO;
import de.rowbuddy.boundary.dtos.TripDTO;
import de.rowbuddy.boundary.dtos.TripMemberDTO;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.events.ListPersonalOpenTripsEvent;
import de.rowbuddy.client.events.ListPersonalTripsEvent;
import de.rowbuddy.client.events.StatusMessageEvent;
import de.rowbuddy.client.model.StatusMessage;
import de.rowbuddy.client.model.StatusMessage.Status;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.entities.TripMemberType;

public class StartTripPresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		HasClickHandlers getAddButton();

		HasClickHandlers getCancelButton();

		void setRouteOracle(SuggestOracle oracle);
		
		void setMemberOracle(SuggestOracle oracle);

		void setBoatOracle(SuggestOracle oracle);

		HasValue<String> getRouteName();

		HasValue<String> getBoatName();

		HasValue<String> getMemberName();
		
		SuggestBox getMember();
		
		public void showTripMembers(String[] tripMembers);

		ListBox getListBox();

		void setBoatInformation(String name, boolean coxed, int numberOfSeats);
		
		void setRouteInformation(double lenght);

		HasClickHandlers getDeleteTripMemberButton();

		HasClickHandlers getSetCoxButton();

	}

	private static Logger logger = Logger.getLogger(StartTripPresenter.class.getName());
	private Display view;
	private LogbookRemoteServiceAsync logbookService;
	private EventBus eventBus;
	private RouteSuggestOracle routeOracle;
	private BoatSuggestOracle boatOracle;
	private MemberSuggestOracle memberOracle;
	private List<TripMemberDTO> tripMembers;

	public StartTripPresenter(LogbookRemoteServiceAsync service,
			EventBus eventBus, Display view) {
		this.view = view;
		this.logbookService = service;
		this.eventBus = eventBus;
		this.tripMembers = new LinkedList<TripMemberDTO>(); 
		routeOracle = new RouteSuggestOracle(service);
		boatOracle = new BoatSuggestOracle(service);
		memberOracle = new MemberSuggestOracle(service);
	}

	@Override
	public void start(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		bind();
	}

	private void bind() {
		view.setRouteOracle(routeOracle);
		view.setMemberOracle(memberOracle);
		view.setBoatOracle(boatOracle);
		
		view.getMember().addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> arg0) {
				MemberDTO member = memberOracle.getSuggestion(view.getMemberName().getValue());
				
				TripMemberDTO tm = new TripMemberDTO();
				tm.setMember(member);
				for (TripMemberDTO dto : tripMembers) {
					if(dto.getMember().getId() == member.getId()) {
						return;
					}
				}
				BoatDTO boat = boatOracle.getSuggestion(view.getBoatName().getValue());
				if (boat == null) {
					StatusMessage message = new StatusMessage(false);
					message.setStatus(Status.NEGATIVE);
					message.setMessage("Es muss ein Boot ausgewählt sein");
					eventBus.fireEvent(new StatusMessageEvent(message));
				} else {
					int maxPlacesInBoat = boat.getNumberOfSeats();
					if(boat.isCoxed()) {
						++maxPlacesInBoat;
					}
					if (tripMembers.size() >= maxPlacesInBoat ) {
						return;
					}
				}
				tm.setTripMemberType(TripMemberType.Rower);
				tripMembers.add(tm);
				view.getMemberName().setValue("");
				
				updateTripMemberList();				
			}
		});
		
		view.getBoatName().addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> arg0) {
				BoatDTO boat = boatOracle.getSuggestion(view.getBoatName().getValue());
				view.setBoatInformation(boat.getName(), boat.isCoxed(), boat.getNumberOfSeats());
			}
			
		});
		
		view.getRouteName().addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> arg0) {
				RouteDTO route = routeOracle.getSuggestion(view.getRouteName().getValue());
				view.setRouteInformation(route.getLengthKM());
			}
			
		});

		view.getAddButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				startTrip(new AsyncCallback<Trip>() {

					@Override
					public void onFailure(Throwable arg0) {
						logger.severe("Cannot log trip: " + arg0.getMessage());
					}

					@Override
					public void onSuccess(Trip arg0) {
						logger.info("Trip successful logged; Reset View");
						eventBus.fireEvent(new ListPersonalOpenTripsEvent());
						StatusMessage message = new StatusMessage();
						message.setMessage("Fahrt erfolgreich gestartet");
						message.setStatus(Status.POSITIVE);
						message.setAttached(false);
						eventBus.fireEvent(new StatusMessageEvent(message));
					}
				});
			}
		});

		view.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListPersonalTripsEvent());
			}
		});
		
		view.getDeleteTripMemberButton().addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent arg0) {
				int index = view.getListBox().getSelectedIndex();
				if (index != -1) {
					tripMembers.remove(index);
				}
				updateTripMemberList();
			}
		});
		
		view.getSetCoxButton().addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent arg0) {
				BoatDTO boat = boatOracle.getSuggestion(view.getBoatName().getValue());
				if (boat == null || !boat.isCoxed()) {
					StatusMessage message = new StatusMessage(false);
					message.setStatus(Status.NEGATIVE);
					message.setMessage("Es muss ein Boot mit Steuerplatz ausgewählt sein");
					eventBus.fireEvent(new StatusMessageEvent(message));
				} else {
					int index = view.getListBox().getSelectedIndex();
					if (index != -1) {
						for (TripMemberDTO dto : tripMembers) {
							dto.setTripMemberType(TripMemberType.Rower);
						}
						tripMembers.get(index).setTripMemberType(TripMemberType.Cox);
					}
					updateTripMemberList();
				}
			}
		});

	}
	
	private void updateTripMemberList() {
		String[] tms = new String[tripMembers.size()];
		for (int i = 0; i< tripMembers.size(); i++) {
			tms[i] = tripMembers.get(i).getMember().getFullName();
			if(tripMembers.get(i).getTripMemberType().equals(TripMemberType.Cox)) {
				tms[i] += " (Cox)";
			}
		}
		view.showTripMembers(tms);
	}
	private void startTrip(AsyncCallback<Trip> action) {
		
		BoatDTO boat = boatOracle.getSuggestion(view.getBoatName().getValue());
		if (boat == null) {
			StatusMessage message = new StatusMessage(false);
			message.setStatus(Status.NEGATIVE);
			message.setMessage("Es muss ein Boot ausgewählt sein");
			eventBus.fireEvent(new StatusMessageEvent(message));
		} else {
			RouteDTO route = routeOracle.getSuggestion(view.getRouteName().getValue());
			if (route == null) {
				StatusMessage message = new StatusMessage(false);
				message.setStatus(Status.NEGATIVE);
				message.setMessage("Es muss eine Route ausgewählt sein");
				eventBus.fireEvent(new StatusMessageEvent(message));
			} else {
				MemberDTO member = memberOracle.getSuggestion(view.getMemberName().getValue());
				
				if (member == null) {
					StatusMessage message = new StatusMessage(false);
					message.setStatus(Status.NEGATIVE);
					message.setMessage("Es muss ein Ruderer ausgewählt sein");
					eventBus.fireEvent(new StatusMessageEvent(message));
				} else {
					TripDTO trip = new TripDTO();
					try {
						trip.setFinished(true);
						trip.setStartDate(new Date(System.currentTimeMillis()));
						logbookService.logRowedTrip(trip, boat.getId(), route.getId(), this.tripMembers , 
								new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable arg0) {
								StatusMessage message = new StatusMessage(
										false);
								message.setStatus(Status.NEGATIVE);
								message.setMessage(arg0.getMessage());
								eventBus.fireEvent(new StatusMessageEvent(
										message));
							}

							@Override
							public void onSuccess(Void arg0) {
								StatusMessage message = new StatusMessage(
										false);
								message.setStatus(Status.POSITIVE);
								message.setMessage("Trip erfolgreich hinzugefügt");
								eventBus.fireEvent(new ListPersonalTripsEvent());
								eventBus.fireEvent(new StatusMessageEvent(
										message));
							}
						});
					} catch (Exception e) {
						StatusMessage message = new StatusMessage();
						message.setMessage(e.getMessage());
						message.setStatus(Status.NEGATIVE);
						message.setAttached(false);
						eventBus.fireEvent(new StatusMessageEvent(message));
						logger.warning(e.getMessage());
					}
				}				
			}			
		}
	}
}
