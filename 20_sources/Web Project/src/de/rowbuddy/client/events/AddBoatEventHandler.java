package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.boat.AddBoatPresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.boat.AddBoatView;

public class AddBoatEventHandler extends AbstractEventHandler {

	private final BoatRemoteServiceAsync boatService;

	public AddBoatEventHandler(HasWidgets targetWidget, EventBus eventBus,
			BoatRemoteServiceAsync boatService) {
		super(targetWidget, eventBus);
		this.boatService = boatService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new AddBoatEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new AddBoatPresenter(new AddBoatView(), boatService, eventBus);
	}

	@Override
	protected <T extends AbstractEventHandler> Type<T> getType() {
		return (Type<T>) AddBoatEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return AddBoatEvent.HISTORY_IDENTIFIER;
	}
}
