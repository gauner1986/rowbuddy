package de.rowbuddy.client.logbook;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.client.ServerCallHandler;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.events.LogRowedTripEvent;
import de.rowbuddy.client.events.StartTripEvent;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;

public class ListPersonalTripsPresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		HasClickHandlers getStartTripButton();

		HasClickHandlers getLogRowedTripButton();

		HasClickHandlers getTable();

		void setData(Collection<PersonalTripDTO> trips);

		int getClickedRow(ClickEvent event);
	}

	public enum ListType {
		ALL, OPEN
	}

	private final Display view;
	private final LogbookRemoteServiceAsync logbook;
	private final EventBus eventBus;
	private List<PersonalTripDTO> fetchedTrips;
	private final ListType listType;
	private static Logger logger = Logger
			.getLogger(ListPersonalTripsPresenter.class.getName());

	public ListPersonalTripsPresenter(LogbookRemoteServiceAsync logbook,
			Display view, EventBus eventBus, ListType listType) {
		this.logbook = logbook;
		this.view = view;
		this.eventBus = eventBus;
		this.listType = listType;
	}

	private void bind() {
		view.getStartTripButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new StartTripEvent());
			}
		});

		view.getLogRowedTripButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new LogRowedTripEvent());
			}
		});

		view.getTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = view.getClickedRow(event);
				logger.info("Row " + selectedRow + " selected");
				if (selectedRow > 0) {
					Long id = fetchedTrips.get(selectedRow - 1).getId();

					logger.info("Fire TripDetailEvent id:" + id);
					// TODO: Implement
					// eventBus.fireEvent(new DetailsTripEvent(id));
				}
			}
		});
	}

	@Override
	public void start(HasWidgets container) {
		bind();
		container.clear();
		container.add(view.asWidget());

		switch (listType) {
		case ALL:
			fetchPersonalTrips();
			break;
		case OPEN:
			fetchPersonalOpenTrips();
			break;
		}
	}

	private void fetchPersonalTrips() {
		logbook.getPersonalTrips(new ServerCallHandler<List<PersonalTripDTO>>(
				eventBus, "Meine Fahrten laden", null, null) {

			public void onSuccess(java.util.List<PersonalTripDTO> arg0) {
				fetchedTrips = arg0;
				view.setData(fetchedTrips);
			};
		});
	}

	private void fetchPersonalOpenTrips() {
		logbook.getPersonalOpenTrips(new ServerCallHandler<List<PersonalTripDTO>>(
				eventBus, "Meine offenen Fahrten laden", null, null) {
			@Override
			public void onSuccess(List<PersonalTripDTO> arg0) {
				fetchedTrips = arg0;
				view.setData(arg0);
			}
		});
	}
}
