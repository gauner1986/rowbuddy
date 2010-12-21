package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.logbook.ListPersonalTripsPresenter;
import de.rowbuddy.client.logbook.ListPersonalTripsView;
import de.rowbuddy.client.logbook.ListPersonalTripsPresenter.ListType;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;

public class ListPersonalOpenTripsPresenterChanger extends PresenterChanger {

	private final LogbookRemoteServiceAsync logbookService;

	public ListPersonalOpenTripsPresenterChanger(HasWidgets targetWidget,
			EventBus eventBus, LogbookRemoteServiceAsync logbookService, MemberDTO member) {
		super(targetWidget, eventBus, member);
		this.logbookService = logbookService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new ListPersonalOpenTripsEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new ListPersonalTripsPresenter(logbookService,
				new ListPersonalTripsView(
						PageTitles.LOGBOOK_PERSONAL_OPEN_TRIPS), eventBus,
				ListType.OPEN);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) ListPersonalOpenTripsEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return ListPersonalOpenTripsEvent.HISTORY_IDENTIFIER;
	}
}
