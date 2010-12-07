package de.rowbuddy.client;

import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.events.AddBoatEvent;
import de.rowbuddy.client.events.AddBoatEventHandler;
import de.rowbuddy.client.events.BoatDetailEvent;
import de.rowbuddy.client.events.BoatDetailEventHandler;
import de.rowbuddy.client.events.EditBoatDamageEvent;
import de.rowbuddy.client.events.EditBoatDamageHandler;
import de.rowbuddy.client.events.EditBoatEvent;
import de.rowbuddy.client.events.EditBoatEventHandler;
import de.rowbuddy.client.events.ListBoatEvent;
import de.rowbuddy.client.events.ListBoatEventHandler;
import de.rowbuddy.client.events.ListDamageEvent;
import de.rowbuddy.client.events.ListDamageEventHandler;
import de.rowbuddy.client.events.ListPersonalTripsEvent;
import de.rowbuddy.client.events.ListPersonalTripsEventHandler;
import de.rowbuddy.client.presenter.ListPersonalTripsPresenter;
import de.rowbuddy.client.presenter.MenuPresenter;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.StatusMessagePresenter;
import de.rowbuddy.client.presenter.boat.AddBoatPresenter;
import de.rowbuddy.client.presenter.boat.BoatDetailPresenter;
import de.rowbuddy.client.presenter.boat.BoatPresenter;
import de.rowbuddy.client.presenter.boat.DamageDetailPresenter;
import de.rowbuddy.client.presenter.boat.EditBoatPresenter;
import de.rowbuddy.client.presenter.boat.ListDamagePresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;
import de.rowbuddy.client.views.MenuView;
import de.rowbuddy.client.views.MessageView;
import de.rowbuddy.client.views.boat.AddBoatView;
import de.rowbuddy.client.views.boat.BoatDetail;
import de.rowbuddy.client.views.boat.BoatView;
import de.rowbuddy.client.views.boat.DamageDetailView;
import de.rowbuddy.client.views.boat.DamageView;
import de.rowbuddy.client.views.boat.EditBoatView;
import de.rowbuddy.client.views.logbook.ListPersonalTripsView;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private SimpleEventBus eventBus;
	private BoatRemoteServiceAsync boatService;
	private LogbookRemoteServiceAsync logbookService;
	private HasWidgets container;
	private StatusMessagePresenter statusPresenter;
	private Presenter menuPresenter;
	private Logger logger = Logger.getLogger(AppController.class.getName());

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

		eventBus.addHandler(EditBoatDamageEvent.TYPE,
				new EditBoatDamageHandler() {

					@Override
					public void onEditBoatDamage(EditBoatDamageEvent event) {
						History.newItem(HistoryConstants.EDIT_DAMAGE, false);
						Presenter presenter = new DamageDetailPresenter(event
								.getId(), new DamageDetailView(), boatService,
								eventBus);
						statusPresenter.clear();
						FadeAnimation fade = new FadeAnimation(container,
								presenter);
						fade.run(400);
					}
				});

		eventBus.addHandler(EditBoatEvent.TYPE, new EditBoatEventHandler() {

			@Override
			public void onEditBoatEvent(EditBoatEvent event) {
				doOnEditBoat(event.getId());
			}
		});

		eventBus.addHandler(BoatDetailEvent.TYPE, new BoatDetailEventHandler() {

			@Override
			public void onBoatDetailEvent(BoatDetailEvent event) {
				doOnViewBoat(event.getId());
			}
		});
		eventBus.addHandler(ListPersonalTripsEvent.TYPE,
				new ListPersonalTripsEventHandler() {
					@Override
					public void onListPersonalTripsEvent(
							ListPersonalTripsEvent event) {
						History.newItem(HistoryConstants.LIST_PERSONAL_TRIPS);
						logger.info("ListPersonalTripsEvent fired!");
					}
				});
		eventBus.addHandler(ListDamageEvent.TYPE, new ListDamageEventHandler() {

			@Override
			public void onListDamageEvent(ListDamageEvent event) {
				History.newItem(HistoryConstants.LIST_DAMAGES);
			}
		});
	}

	private void doOnViewBoat(Long id) {
		History.newItem(HistoryConstants.VIEW_BOAT, false);
		Presenter presenter = new BoatDetailPresenter(new BoatDetail(),
				boatService, eventBus, id);
		statusPresenter.clear();
		FadeAnimation fade = new FadeAnimation(container, presenter);
		fade.run(400);
	}

	private void doOnEditBoat(Long id) {
		History.newItem(HistoryConstants.EDIT_BOAT, false);
		Presenter presenter = new EditBoatPresenter(new EditBoatView(),
				boatService, eventBus, id);
		statusPresenter.clear();
		FadeAnimation fade = new FadeAnimation(container, presenter);
		fade.run(400);
	}

	private void doOnListBoatEvent() {
		History.newItem(HistoryConstants.LIST_BOATS);
		logger.info("ListBoatEvent fired!");
	}

	private void doOnAddBoatEvent() {
		History.newItem(HistoryConstants.ADD_BOAT);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> arg0) {
		String token = arg0.getValue();
		logger.info("History token changed: " + arg0.getValue());
		if (token != null) {
			Presenter presenter = null;
			statusPresenter.clear();
			if (token.equals(HistoryConstants.LIST_BOATS)) {
				presenter = new BoatPresenter(boatService, new BoatView(),
						eventBus);
			} else if (token.equals(HistoryConstants.ADD_BOAT)) {
				presenter = new AddBoatPresenter(new AddBoatView(),
						boatService, eventBus);
			} else if (token.equals(HistoryConstants.LIST_PERSONAL_TRIPS)) {
				presenter = new ListPersonalTripsPresenter(logbookService,
						new ListPersonalTripsView(
								PageTitles.LOGBOOK_PERSONAL_TRIPS), eventBus);
			} else if (token.equals(HistoryConstants.LIST_DAMAGES)) {
				presenter = new ListDamagePresenter(new DamageView(),
						boatService, eventBus);
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
		bind();
		this.container = container;
		if (History.getToken().equals("")) {
			History.newItem(HistoryConstants.LIST_BOATS);// welcome page
		} else {
			History.fireCurrentHistoryState();
		}
	}

}
