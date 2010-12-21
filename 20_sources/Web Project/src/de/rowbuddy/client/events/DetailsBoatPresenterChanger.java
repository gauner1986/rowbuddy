package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.boat.DetailsBoatPresenter;
import de.rowbuddy.client.boat.DetailsBoatView;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;

public class DetailsBoatPresenterChanger extends PresenterChanger {

	private final BoatRemoteServiceAsync boatService;

	public DetailsBoatPresenterChanger(EventBus eventBus, HasWidgets target,
			BoatRemoteServiceAsync boatService, MemberDTO member) {
		super(target, eventBus, member);
		this.boatService = boatService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		Long id = IdEvent.getIdFromHistoryItem(historyItem);
		return new DetailsBoatEvent(id);
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		DetailsBoatEvent e = (DetailsBoatEvent) event;
		return new DetailsBoatPresenter(new DetailsBoatView(), boatService, eventBus,
				e.getId(), member);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) DetailsBoatEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return DetailsBoatEvent.HISTORY_IDENTIFIER;
	}

}
