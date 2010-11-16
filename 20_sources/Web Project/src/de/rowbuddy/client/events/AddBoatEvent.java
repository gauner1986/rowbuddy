package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;


public class AddBoatEvent extends GwtEvent<AddBoatEventHandler>{

	public static Type<AddBoatEventHandler> TYPE = new Type<AddBoatEventHandler>();
	
	@Override
	protected void dispatch(AddBoatEventHandler arg0) {
		arg0.onAddBoatEvent(this);
	}

	@Override
	public Type<AddBoatEventHandler> getAssociatedType() {
		return TYPE;
	}

}
