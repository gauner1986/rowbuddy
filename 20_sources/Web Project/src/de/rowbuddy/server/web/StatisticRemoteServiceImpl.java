package de.rowbuddy.server.web;

import de.rowbuddy.boundary.dtos.MonthsStatisticDTO;
import de.rowbuddy.client.services.StatisticRemoteService;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;

public class StatisticRemoteServiceImpl extends AbstractRemoteService implements
		StatisticRemoteService {

	private static final long serialVersionUID = 1L;

	@Override
	public MonthsStatisticDTO getMonthsStatistic(int year) throws RowBuddyException, NotLoggedInException {
			return getRowBuddyFacade().getMonthsStatistic(year);
	}
}
