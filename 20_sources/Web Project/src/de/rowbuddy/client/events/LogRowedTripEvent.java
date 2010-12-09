package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class LogRowedTripEvent extends GwtEvent<LogRowedTripPresenterChanger> {

	public static Type<LogRowedTripPresenterChanger> TYPE = new Type<LogRowedTripPresenterChanger>();

	@Override
	protected void dispatch(LogRowedTripPresenterChanger arg0) {
		arg0.onLogRowedTripEvent(this);
	}

	@Override
	public Type<LogRowedTripPresenterChanger> getAssociatedType() {
		return TYPE;
	}
}
