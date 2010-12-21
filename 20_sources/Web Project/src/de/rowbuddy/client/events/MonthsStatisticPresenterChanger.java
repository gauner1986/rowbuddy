package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.presenter.statistic.MonthsStatisticPresenter;
import de.rowbuddy.client.services.StatisticRemoteServiceAsync;
import de.rowbuddy.client.statistic.MonthsStatisticView;

public class MonthsStatisticPresenterChanger extends PresenterChanger {

	private final StatisticRemoteServiceAsync statisticService;

	public MonthsStatisticPresenterChanger(HasWidgets targetWidget,
			EventBus eventBus, StatisticRemoteServiceAsync statisitcService) {
		super(targetWidget, eventBus, member);
		this.statisticService = statisitcService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new MonthsStatisticEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new MonthsStatisticPresenter(statisticService,
				eventBus, new MonthsStatisticView(PageTitles.STATISTIC_MONTHS));
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) MonthsStatisticEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return MonthsStatisticEvent.HISTORY_IDENTIFIER;
	}
}
