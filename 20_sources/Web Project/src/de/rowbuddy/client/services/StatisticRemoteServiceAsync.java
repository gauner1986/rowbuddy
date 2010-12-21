package de.rowbuddy.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.rowbuddy.boundary.dtos.MonthsStatisticDTO;

public interface StatisticRemoteServiceAsync {

	void getMonthsStatistic(int year, AsyncCallback<MonthsStatisticDTO> callback);
}
