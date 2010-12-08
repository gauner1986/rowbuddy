package de.rowbuddy.client.events;

public class ListPersonalTripsEvent extends
		AbstractEvent<ListPersonalTripsEventHandler> {

	public static final Type<ListPersonalTripsEventHandler> TYPE = new Type<ListPersonalTripsEventHandler>();
	public static final String HISTORY_IDENTIFIER = "ListPersonalTrips";

	@Override
	public Type<ListPersonalTripsEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
