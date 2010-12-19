package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.LogRowedTripPresenter;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;
import de.rowbuddy.client.views.logbook.LogRowedTripView;

public class LogRowedTripPresenterChanger extends PresenterChanger {

	private final LogbookRemoteServiceAsync logbookService;

	public LogRowedTripPresenterChanger(HasWidgets targetWidget,
			EventBus eventBus, LogbookRemoteServiceAsync logbookService) {
		super(targetWidget, eventBus, member);
		this.logbookService = logbookService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new LogRowedTripEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new LogRowedTripPresenter(logbookService,
				eventBus, new LogRowedTripView(PageTitles.LOGBOOK_LOG_ROWED_TRIP));
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) LogRowedTripEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return LogRowedTripEvent.HISTORY_IDENTIFIER;
	}
}
