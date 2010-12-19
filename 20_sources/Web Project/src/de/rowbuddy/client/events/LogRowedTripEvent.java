package de.rowbuddy.client.events;

public class LogRowedTripEvent extends 
		AbstractEvent<LogRowedTripPresenterChanger> {

	public static Type<LogRowedTripPresenterChanger> TYPE = new Type<LogRowedTripPresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "LogRowedTrip";
	
	@Override
	public Type<LogRowedTripPresenterChanger> getAssociatedType() {
		return TYPE;
	}
	
	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
