package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.boat.DamageDetailPresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.boat.DamageDetailView;

public class DetailDamageHandler extends AbstractEventHandler {

	private final BoatRemoteServiceAsync boatService;

	public DetailDamageHandler(HasWidgets targetWidget, EventBus eventBus,
			BoatRemoteServiceAsync boatService) {
		super(targetWidget, eventBus);
		this.boatService = boatService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		Long id = IdEvent.getIdFromHistoryItem(historyItem);
		return new DetailDamageEvent(id);
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		DetailDamageEvent e = (DetailDamageEvent) event;
		return new DamageDetailPresenter(e.getId(), new DamageDetailView(),
				boatService, eventBus);
	}

	@Override
	protected <T extends AbstractEventHandler> Type<T> getType() {
		return (Type<T>) DetailDamageEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return DetailDamageEvent.HISTORY_IDENTIFIER;
	}
}
