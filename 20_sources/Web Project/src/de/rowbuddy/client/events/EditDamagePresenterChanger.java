package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.boat.EditDamagePresenter;
import de.rowbuddy.client.boat.EditDamageView;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;

public class EditDamagePresenterChanger extends PresenterChanger {

	private final BoatRemoteServiceAsync boatService;

	public EditDamagePresenterChanger(HasWidgets targetWidget, EventBus eventBus,
			BoatRemoteServiceAsync boatService, MemberDTO member) {
		super(targetWidget, eventBus, member);
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
				boatService, eventBus, member);
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
