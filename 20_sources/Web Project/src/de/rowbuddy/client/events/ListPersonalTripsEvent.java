package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ListPersonalTripsEvent extends
		GwtEvent<ListPersonalTripsEventHandler> {

	public static Type<ListPersonalTripsEventHandler> TYPE = new Type<ListPersonalTripsEventHandler>();

	@Override
	protected void dispatch(ListPersonalTripsEventHandler arg0) {
		arg0.onListPersonalTripsEvent(this);
	}

	@Override
	public Type<ListPersonalTripsEventHandler> getAssociatedType() {
		return TYPE;
	}
}
