package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ListDamageEvent extends GwtEvent<ListDamageEventHandler>{
	public static Type<ListDamageEventHandler> TYPE = new Type<ListDamageEventHandler>();

	@Override
	protected void dispatch(ListDamageEventHandler arg0) {
		arg0.onListDamageEvent(this);
	}

	@Override
	public Type<ListDamageEventHandler> getAssociatedType() {
		return TYPE;
	}
}
