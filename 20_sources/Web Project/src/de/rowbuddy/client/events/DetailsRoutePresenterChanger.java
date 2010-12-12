package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.route.RouteDetailPresenter;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;
import de.rowbuddy.client.views.route.RouteDetail;

public class DetailsRoutePresenterChanger extends PresenterChanger {
	
	private final RouteRemoteServiceAsync routeService;
	
	public DetailsRoutePresenterChanger(EventBus eventBus, HasWidgets target, RouteRemoteServiceAsync routeService){
		super(target, eventBus);
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
		return new RouteDetailPresenter(new RouteDetail(), routeService, eventBus, e.getId());
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
