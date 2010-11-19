package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ListBoatEvent extends GwtEvent<ListBoatEventHandler>{

	public static Type<ListBoatEventHandler> TYPE = new Type<ListBoatEventHandler>();

	@Override
	protected void dispatch(ListBoatEventHandler arg0) {
		arg0.onAddBoatEvent(this);
	}

	@Override
	public Type<ListBoatEventHandler> getAssociatedType() {
		return TYPE;
	}
}
