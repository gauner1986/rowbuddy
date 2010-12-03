package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class StartTripEvent extends GwtEvent<StartTripEventHandler> {

	public static Type<StartTripEventHandler> TYPE = new Type<StartTripEventHandler>();

	@Override
	protected void dispatch(StartTripEventHandler arg0) {
		arg0.onStartTripEvent(this);
	}

	@Override
	public Type<StartTripEventHandler> getAssociatedType() {
		return TYPE;
	}
}
