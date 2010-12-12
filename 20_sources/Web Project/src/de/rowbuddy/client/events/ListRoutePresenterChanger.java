package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.route.RoutePresenter;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;
import de.rowbuddy.client.views.route.RouteView;

public class ListRoutePresenterChanger extends PresenterChanger {

	private final RouteRemoteServiceAsync routeService;

	public ListRoutePresenterChanger(HasWidgets targetWidget,
			EventBus eventBus, RouteRemoteServiceAsync routeService) {
		super(targetWidget, eventBus);
		this.routeService = routeService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new ListRouteEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new RoutePresenter(this.routeService, new RouteView(), eventBus);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) ListRouteEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return ListRouteEvent.HISTORY_IDENTIFIER;
	}

}
