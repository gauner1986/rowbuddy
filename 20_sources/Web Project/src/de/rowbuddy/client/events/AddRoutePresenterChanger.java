package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.route.AddRoutePresenter;
import de.rowbuddy.client.route.AddRouteView;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;

public class AddRoutePresenterChanger extends PresenterChanger {
	
	private final RouteRemoteServiceAsync routeService;

	public AddRoutePresenterChanger(HasWidgets targetWidget, EventBus eventBus, RouteRemoteServiceAsync routeService, MemberDTO member) {
		super(targetWidget, eventBus, member);
		this.routeService = routeService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new AddRouteEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new AddRoutePresenter(new AddRouteView(), routeService, eventBus);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) AddRouteEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return AddRouteEvent.HISTORY_IDENTIFIER;
	}

}
