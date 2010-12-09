package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.boat.DamageDetailPresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.boat.DamageDetailView;

public class DetailsBoatDamagePresenterChanger extends PresenterChanger {

	private final BoatRemoteServiceAsync boatService;

	public DetailsBoatDamagePresenterChanger(HasWidgets targetWidget, EventBus eventBus,
			BoatRemoteServiceAsync boatService) {
		super(targetWidget, eventBus);
		this.boatService = boatService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		Long id = IdEvent.getIdFromHistoryItem(historyItem);
		return new DetailsBoatDamageEvent(id);
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		DetailsBoatDamageEvent e = (DetailsBoatDamageEvent) event;
		return new DamageDetailPresenter(e.getId(), new DamageDetailView(),
				boatService, eventBus);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) DetailsBoatDamageEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return DetailsBoatDamageEvent.HISTORY_IDENTIFIER;
	}
}
