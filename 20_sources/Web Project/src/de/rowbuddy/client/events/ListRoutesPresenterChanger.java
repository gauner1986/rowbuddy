package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.route.ListRoutesPresenter;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;
import de.rowbuddy.client.views.route.ListRoutesView;

public class ListRoutesPresenterChanger extends PresenterChanger {

	private RouteRemoteServiceAsync routeService;
	private EventBus eventBus;

	public ListRoutesPresenterChanger(HasWidgets targetWidget,
			EventBus eventBus, RouteRemoteServiceAsync routeService, MemberDTO member) {
		super(targetWidget, eventBus, member);
		this.routeService = routeService;
		this.eventBus = eventBus;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new ListRoutesEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new ListRoutesPresenter(routeService, new ListRoutesView(), eventBus);
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
