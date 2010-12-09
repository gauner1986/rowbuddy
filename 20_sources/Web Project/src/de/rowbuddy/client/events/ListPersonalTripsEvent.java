package de.rowbuddy.client.events;

public class ListPersonalTripsEvent extends
		AbstractEvent<ListPersonalTripsPresenterChanger> {

	public static final Type<ListPersonalTripsPresenterChanger> TYPE = new Type<ListPersonalTripsPresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "ListPersonalTrips";

	@Override
	public Type<ListPersonalTripsPresenterChanger> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
