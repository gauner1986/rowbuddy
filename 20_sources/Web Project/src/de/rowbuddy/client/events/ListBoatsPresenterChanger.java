package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.boat.ListBoatsPresenter;
import de.rowbuddy.client.boat.ListBoatsView;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;

public class ListBoatsPresenterChanger extends PresenterChanger {

	private final BoatRemoteServiceAsync boatService;

	public ListBoatsPresenterChanger(EventBus eventBus, HasWidgets target,
			BoatRemoteServiceAsync boatService, MemberDTO member) {
		super(target, eventBus, member);
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
		return new ListBoatsPresenter(this.boatService, new ListBoatsView(), eventBus, member);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) ListBoatsEvent.TYPE;
	}
}
