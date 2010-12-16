package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.ListPersonalTripsPresenter;
import de.rowbuddy.client.presenter.ListPersonalTripsPresenter.ListType;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;
import de.rowbuddy.client.views.logbook.ListPersonalTripsView;

public class ListPersonalTripsPresenterChanger extends PresenterChanger {

	private final LogbookRemoteServiceAsync logbookService;

	public ListPersonalTripsPresenterChanger(HasWidgets targetWidget,
			EventBus eventBus, LogbookRemoteServiceAsync logbookService, MemberDTO member) {
		super(targetWidget, eventBus, member);
		this.logbookService = logbookService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new ListPersonalTripsEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new ListPersonalTripsPresenter(logbookService,
				new ListPersonalTripsView(PageTitles.LOGBOOK_PERSONAL_TRIPS),
				eventBus, ListType.ALL);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) ListPersonalTripsEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return ListPersonalTripsEvent.HISTORY_IDENTIFIER;
	}
}
