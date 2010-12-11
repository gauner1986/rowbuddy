package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;

public class ListRoutesPresenterChanger extends PresenterChanger {

	private RouteRemoteServiceAsync routeService;

	public ListRoutesPresenterChanger(HasWidgets targetWidget,
			EventBus eventBus, RouteRemoteServiceAsync routeService) {
		super(targetWidget, eventBus);
		this.routeService = routeService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new ListRoutesEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		// TODO Hier muss noch ein Presenter erzeugt werden
		return null;
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) ListRoutesEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return ListRoutesEvent.HISTORY_IDENTIFIER;
	}

}
