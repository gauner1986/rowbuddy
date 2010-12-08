package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.boat.BoatDetailPresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.boat.BoatDetail;

public class BoatDetailEventHandler extends AbstractEventHandler {

	private final BoatRemoteServiceAsync boatService;

	public BoatDetailEventHandler(EventBus eventBus, HasWidgets target,
			BoatRemoteServiceAsync boatService) {
		super(target, eventBus);
		this.boatService = boatService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		Long id = IdEvent.getIdFromHistoryItem(historyItem);
		return new BoatDetailEvent(id);
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		BoatDetailEvent e = (BoatDetailEvent) event;
		return new BoatDetailPresenter(new BoatDetail(), boatService, eventBus,
				e.getId());
	}

	@Override
	protected <T extends AbstractEventHandler> Type<T> getType() {
		return (Type<T>) BoatDetailEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return BoatDetailEvent.HISTORY_IDENTIFIER;
	}

}
