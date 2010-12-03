package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class LogRowedTripEvent extends GwtEvent<LogRowedTripEventHandler> {

	public static Type<LogRowedTripEventHandler> TYPE = new Type<LogRowedTripEventHandler>();

	@Override
	protected void dispatch(LogRowedTripEventHandler arg0) {
		arg0.onLogRowedTripEvent(this);
	}

	@Override
	public Type<LogRowedTripEventHandler> getAssociatedType() {
		return TYPE;
	}
}
