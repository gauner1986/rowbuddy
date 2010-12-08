package de.rowbuddy.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.events.AbstractEvent;
import de.rowbuddy.client.events.AbstractEventHandler;
import de.rowbuddy.client.events.AbstractEventHandler.EventListener;
import de.rowbuddy.client.events.AddBoatEventHandler;
import de.rowbuddy.client.events.AddDamageHandler;
import de.rowbuddy.client.events.BoatDetailEventHandler;
import de.rowbuddy.client.events.BoatListEvent;
import de.rowbuddy.client.events.BoatListHandler;
import de.rowbuddy.client.events.DetailDamageHandler;
import de.rowbuddy.client.events.EditBoatDamageHandler;
import de.rowbuddy.client.events.EditBoatEventHandler;
import de.rowbuddy.client.events.ListDamageEventHandler;
import de.rowbuddy.client.events.ListPersonalTripsEventHandler;
import de.rowbuddy.client.presenter.MenuPresenter;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.StatusMessagePresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;
import de.rowbuddy.client.views.MenuView;
import de.rowbuddy.client.views.MessageView;

public class AppController implements Presenter, EventListener {
	private SimpleEventBus eventBus;
	private BoatRemoteServiceAsync boatService;
	private LogbookRemoteServiceAsync logbookService;
	private HasWidgets container;
	private StatusMessagePresenter statusPresenter;
	private Presenter menuPresenter;
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

	private void bindBoatEventHandlers() {
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
	}

	private void bindLogbookEventHandlers() {

		eventHandlers.add(new ListPersonalTripsEventHandler(container,
				eventBus, logbookService));

	}

	@Override
	public void start(HasWidgets container) {
		this.container = container;

		bindBoatEventHandlers();
		bindLogbookEventHandlers();

		// register fot presenter changes
		for (AbstractEventHandler eventHandler : eventHandlers) {
			eventHandler.addObserver(this);
		}

		if (History.getToken().equals("")) {
			eventBus.fireEvent(new BoatListEvent());
			// TODO welcome page
		} else {
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void eventProcessed(AbstractEvent<?> event) {
		statusPresenter.clear();
	}
}
