package de.rowbuddy.client.events;

public class ListPersonalOpenTripsEvent extends
		AbstractEvent<ListPersonalOpenTripsPresenterChanger> {

	public static final Type<ListPersonalOpenTripsPresenterChanger> TYPE = new Type<ListPersonalOpenTripsPresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "ListPersonalOpenTrips";

	@Override
	public Type<ListPersonalOpenTripsPresenterChanger> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
