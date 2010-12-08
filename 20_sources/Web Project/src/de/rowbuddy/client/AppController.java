package de.rowbuddy.client;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.events.AbstractEventHandler;
import de.rowbuddy.client.events.AddBoatEventHandler;
import de.rowbuddy.client.events.AddDamageHandler;
import de.rowbuddy.client.events.BoatDetailEventHandler;
import de.rowbuddy.client.events.BoatListHandler;
import de.rowbuddy.client.events.DetailDamageHandler;
import de.rowbuddy.client.events.EditBoatDamageHandler;
import de.rowbuddy.client.events.EditBoatEventHandler;
import de.rowbuddy.client.events.ListDamageEventHandler;
import de.rowbuddy.client.events.ListPersonalTripsEvent;
import de.rowbuddy.client.events.ListPersonalTripsEventHandler;
import de.rowbuddy.client.presenter.ListPersonalTripsPresenter;
import de.rowbuddy.client.presenter.MenuPresenter;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.StatusMessagePresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;
import de.rowbuddy.client.views.MenuView;
import de.rowbuddy.client.views.MessageView;
import de.rowbuddy.client.views.logbook.ListPersonalTripsView;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private SimpleEventBus eventBus;
	private BoatRemoteServiceAsync boatService;
	private LogbookRemoteServiceAsync logbookService;
	private HasWidgets container;
	private StatusMessagePresenter statusPresenter;
	private Presenter menuPresenter;
	private Logger logger = Logger.getLogger(AppController.class.getName());
	private List<AbstractEventHandler> eventHandlers = new LinkedList<AbstractEventHandler>();

	public AppController(BoatRemoteServiceAsync boatService,
			LogbookRemoteServiceAsync logbookService, SimpleEventBus eventBus,
			HasWidgets messageContainer, HasWidgets menuPanel) {
		this.boatService = boatService;
		this.logbookService = logbookService;
		this.eventBus = eventBus;
		this.statusPresenter = new StatusMessagePresenter(new MessageView(),
				eventBus);
		statusPresenter.start(messageContainer);
		menuPresenter = new MenuPresenter(new MenuView(), eventBus);
		menuPresenter.start(menuPanel);
	}

	// bind Event handling here
	private void bind() {

		History.addValueChangeHandler(this);

		eventHandlers
				.add(new BoatListHandler(eventBus, container, boatService));
		eventHandlers.add(new BoatDetailEventHandler(eventBus, container,
				boatService));
		eventHandlers.add(new AddBoatEventHandler(container, eventBus,
				boatService));
		eventHandlers
				.add(new AddDamageHandler(container, eventBus, boatService));
		eventHandlers.add(new DetailDamageHandler(container, eventBus,
				boatService));
		eventHandlers.add(new EditBoatDamageHandler(container, eventBus,
				boatService));

		eventHandlers.add(new EditBoatEventHandler(container, eventBus,
				boatService));

		eventHandlers.add(new ListDamageEventHandler(container, eventBus,
				boatService));

		eventBus.addHandler(ListPersonalTripsEvent.TYPE,
				new ListPersonalTripsEventHandler() {
					@Override
					public void onListPersonalTripsEvent(
							ListPersonalTripsEvent event) {
						History.newItem(HistoryConstants.LIST_PERSONAL_TRIPS);
						logger.info("ListPersonalTripsEvent fired!");
					}
				});
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> arg0) {
		String token = arg0.getValue();
		logger.info("History token changed: " + arg0.getValue());
		if (token != null) {
			Presenter presenter = null;
			statusPresenter.clear();
			if (token.equals(HistoryConstants.LIST_PERSONAL_TRIPS)) {
				presenter = new ListPersonalTripsPresenter(logbookService,
						new ListPersonalTripsView(
								PageTitles.LOGBOOK_PERSONAL_TRIPS), eventBus);
			} else {
				logger.info("Action undefined " + token);
			}

			if (presenter != null) {
				FadeAnimation fade = new FadeAnimation(container, presenter);
				fade.run(400);
			}
		}
	}

	@Override
	public void start(HasWidgets container) {
		// TODO Auto-generated method stub
		this.container = container;
		bind();
		if (History.getToken().equals("")) {
			eventBus.fireEvent(BoatListHandler.createEvent());// welcome page
		} else {
			History.fireCurrentHistoryState();
		}
	}

}
