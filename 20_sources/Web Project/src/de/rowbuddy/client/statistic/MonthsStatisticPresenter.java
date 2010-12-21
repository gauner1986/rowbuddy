package de.rowbuddy.client.statistic;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.MonthsStatisticDTO;
import de.rowbuddy.client.ServerRequestHandler;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.services.StatisticRemoteServiceAsync;

public class MonthsStatisticPresenter implements Presenter {

	public interface Display {

		Widget asWidget();

		void setData(MonthsStatisticDTO statistic);

	}

	private static Logger logger = Logger
			.getLogger(MonthsStatisticPresenter.class.getName());
	private Display view;
	private StatisticRemoteServiceAsync statisitcService;
	private EventBus eventBus;
	private MonthsStatisticDTO months;

	public MonthsStatisticPresenter(StatisticRemoteServiceAsync service,
			EventBus eventBus, Display view) {
		this.view = view;
		this.statisitcService = service;
		this.eventBus = eventBus;
		this.months = new MonthsStatisticDTO();
	}

	@Override
	public void start(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchData();
	}

	private void fetchData() {
		statisitcService.getMonthsStatistic(2010,
				new ServerRequestHandler<MonthsStatisticDTO>(eventBus,
						"Monatsstatistik", null, null) {

					@Override
					public void onSuccess(MonthsStatisticDTO arg0) {
						months = arg0;
						view.setData(months);
					}
				});
	}
}
