package de.rowbuddy.client.events;

public class MonthsStatisticEvent extends
	AbstractEvent<MonthsStatisticPresenterChanger> {

	public static Type<MonthsStatisticPresenterChanger> TYPE = new Type<MonthsStatisticPresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "MonthsStatistic";
	
	@Override
	public Type<MonthsStatisticPresenterChanger> getAssociatedType() {
		return TYPE;
	}
	
	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
