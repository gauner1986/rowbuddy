package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class AddDamageEvent extends GwtEvent<AddDamageHandler> {
	public static Type<AddDamageHandler> type = new Type<AddDamageHandler>();

	@Override
	protected void dispatch(AddDamageHandler arg0) {
		arg0.onAddDamage(this);
	}

	@Override
	public Type<AddDamageHandler> getAssociatedType() {
		return type;
	}

}
