package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.ListPersonalTripsPresenter;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;
import de.rowbuddy.client.views.logbook.ListPersonalTripsView;

public class ListPersonalTripsEventHandler extends AbstractEventHandler {

	private final LogbookRemoteServiceAsync logbookService;

	public ListPersonalTripsEventHandler(HasWidgets targetWidget,
			EventBus eventBus, LogbookRemoteServiceAsync logbookService) {
		super(targetWidget, eventBus);
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
				eventBus);
	}

	@Override
	protected <T extends AbstractEventHandler> Type<T> getType() {
		return (Type<T>) ListPersonalTripsEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return ListPersonalTripsEvent.HISTORY_IDENTIFIER;
	}
}
