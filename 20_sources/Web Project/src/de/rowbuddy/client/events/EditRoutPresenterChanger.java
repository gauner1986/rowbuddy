package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.route.EditRoutePresenter;
import de.rowbuddy.client.route.EditRouteView;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;

public class EditRoutPresenterChanger extends PresenterChanger {

	private final RouteRemoteServiceAsync routeService;

	public EditRoutPresenterChanger(HasWidgets targetWidget, EventBus eventBus, RouteRemoteServiceAsync routeService, MemberDTO member) {
		super(targetWidget, eventBus, member);
		this.routeService = routeService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		Long id = IdEvent.getIdFromHistoryItem(historyItem);
		return new EditRouteEvent(id);
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		EditRouteEvent e = (EditRouteEvent) event;
		return new EditRoutePresenter(new EditRouteView(), routeService, eventBus, e.getId());
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) EditRouteEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return EditRouteEvent.HISTORY_IDENTIFIER;
	}

}
