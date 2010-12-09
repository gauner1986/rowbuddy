package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.boat.AddDamagePresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.boat.AddDamageView;

public class AddBoatDamagePresenterChanger extends PresenterChanger {

	private final BoatRemoteServiceAsync boatService;

	public AddBoatDamagePresenterChanger(HasWidgets targetWidget, EventBus eventBus,
			BoatRemoteServiceAsync boatService) {
		super(targetWidget, eventBus);

		this.boatService = boatService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new AddBoatDamageEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new AddDamagePresenter(boatService, eventBus,
				new AddDamageView());
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) AddBoatDamageEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return AddBoatDamageEvent.HISTORY_IDENTIFIER;
	}
}
