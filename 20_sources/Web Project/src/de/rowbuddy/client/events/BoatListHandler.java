package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.boat.BoatPresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.boat.BoatView;

public class BoatListHandler extends AbstractEventHandler {

	private final BoatRemoteServiceAsync boatService;

	public BoatListHandler(EventBus eventBus, HasWidgets target,
			BoatRemoteServiceAsync boatService) {
		super(target, eventBus);
		this.boatService = boatService;
	}

	@Override
	protected String getHistoryIdentifier() {
		return BoatListEvent.HISTORY_IDENTIFIER;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new BoatListEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new BoatPresenter(this.boatService, new BoatView(), eventBus);
	}

	@Override
	protected <T extends AbstractEventHandler> Type<T> getType() {
		return (Type<T>) BoatListEvent.TYPE;
	}
}
