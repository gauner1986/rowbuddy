package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.route.DetailsRoute;
import de.rowbuddy.client.route.DetailsRoutePresenter;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;

public class DetailsRoutePresenterChanger extends PresenterChanger {
	
	private final RouteRemoteServiceAsync routeService;
	
	public DetailsRoutePresenterChanger(EventBus eventBus, HasWidgets target, RouteRemoteServiceAsync routeService, MemberDTO member){
		super(target, eventBus, member);
		this.routeService = routeService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		Long id = IdEvent.getIdFromHistoryItem(historyItem);
		return new DetailsRouteEvent(id);
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		DetailsRouteEvent e = (DetailsRouteEvent) event;
		return new DetailsRoutePresenter(new DetailsRoute(), routeService, eventBus, e.getId());
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) DetailsRouteEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return DetailsRouteEvent.HISTORY_IDENTIFIER;
	}

}
