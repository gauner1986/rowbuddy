package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.boat.AddDamagePresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.boat.AddDamageView;

public class AddDamageHandler extends AbstractEventHandler {

	private final BoatRemoteServiceAsync boatService;

	public AddDamageHandler(HasWidgets targetWidget, EventBus eventBus,
			BoatRemoteServiceAsync boatService) {
		super(targetWidget, eventBus);

		this.boatService = boatService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new AddDamageEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new AddDamagePresenter(boatService, eventBus,
				new AddDamageView());
	}

	@Override
	protected <T extends AbstractEventHandler> Type<T> getType() {
		return (Type<T>) AddDamageEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return AddDamageEvent.HISTORY_IDENTIFIER;
	}
}
