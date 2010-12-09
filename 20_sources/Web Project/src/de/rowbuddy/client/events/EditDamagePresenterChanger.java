package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.boat.EditDamagePresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.boat.EditDamageView;

public class EditDamagePresenterChanger extends PresenterChanger {

	private final BoatRemoteServiceAsync boatService;

	public EditDamagePresenterChanger(HasWidgets targetWidget, EventBus eventBus,
			BoatRemoteServiceAsync boatService) {
		super(targetWidget, eventBus);
		this.boatService = boatService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		Long id = IdEvent.getIdFromHistoryItem(historyItem);
		return new EditDamageEvent(id);
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		EditDamageEvent e = (EditDamageEvent) event;
		return new EditDamagePresenter(e.getId(), new EditDamageView(),
				boatService, eventBus);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) EditDamageEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return EditDamageEvent.HISTORY_IDENTIFIER;
	}
}
