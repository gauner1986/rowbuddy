package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.boat.ListDamagePresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.boat.DamageView;

public class ListDamagePresenterChanger extends PresenterChanger {

	private final BoatRemoteServiceAsync boatService;

	public ListDamagePresenterChanger(HasWidgets targetWidget, EventBus eventBus,
			BoatRemoteServiceAsync boatService) {
		super(targetWidget, eventBus);

		this.boatService = boatService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new ListDamageEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new ListDamagePresenter(new DamageView(), boatService, eventBus);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) ListDamageEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return ListDamageEvent.HISTORY_IDENTIFIER;
	}
}
