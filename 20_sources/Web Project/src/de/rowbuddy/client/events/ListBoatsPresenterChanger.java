package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.boat.BoatPresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.boat.BoatView;

public class ListBoatsPresenterChanger extends PresenterChanger {

	private final BoatRemoteServiceAsync boatService;

	public ListBoatsPresenterChanger(EventBus eventBus, HasWidgets target,
			BoatRemoteServiceAsync boatService) {
		super(target, eventBus);
		this.boatService = boatService;
	}

	@Override
	protected String getHistoryIdentifier() {
		return ListBoatsEvent.HISTORY_IDENTIFIER;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new ListBoatsEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new BoatPresenter(this.boatService, new BoatView(), eventBus);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) ListBoatsEvent.TYPE;
	}
}
