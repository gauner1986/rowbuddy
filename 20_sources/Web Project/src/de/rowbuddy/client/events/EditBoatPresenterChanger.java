package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.boat.EditBoatPresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.boat.EditBoatView;

public class EditBoatPresenterChanger extends PresenterChanger {

	private final BoatRemoteServiceAsync boatService;

	public EditBoatPresenterChanger(HasWidgets targetWidget, EventBus eventBus,
			BoatRemoteServiceAsync boatService, MemberDTO member) {
		super(targetWidget, eventBus, member);
		this.boatService = boatService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		Long id = IdEvent.getIdFromHistoryItem(historyItem);
		return new EditBoatEvent(id);
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		EditBoatEvent e = (EditBoatEvent) event;
		return new EditBoatPresenter(new EditBoatView(), boatService, eventBus,
				e.getId());
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) EditBoatEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return EditBoatEvent.HISTORY_IDENTIFIER;
	}
}
