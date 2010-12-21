package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.StartTripPresenter;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;
import de.rowbuddy.client.views.logbook.StartTripView;

public class StartTripPresenterChanger extends PresenterChanger {

	private final LogbookRemoteServiceAsync logbookService;

	public StartTripPresenterChanger(HasWidgets targetWidget,
			EventBus eventBus, LogbookRemoteServiceAsync logbookService) {
		super(targetWidget, eventBus, member);
		this.logbookService = logbookService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new StartTripEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new StartTripPresenter(logbookService,
				eventBus, new StartTripView(PageTitles.LOGBOOK_START_ROWING_TRIP));
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) StartTripEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return StartTripEvent.HISTORY_IDENTIFIER;
	}
}
