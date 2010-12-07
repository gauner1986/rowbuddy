package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class EditBoatDamageEvent extends GwtEvent<EditBoatDamageHandler> {

	public static Type<EditBoatDamageHandler> TYPE = new Type<EditBoatDamageHandler>(); 

	private Long id;
	
	public EditBoatDamageEvent(Long id){
		this.id = id;
	}
	
	@Override
	protected void dispatch(EditBoatDamageHandler arg0) {
		arg0.onEditBoatDamage();
	}

	@Override
	public Type<EditBoatDamageHandler> getAssociatedType() {
		return TYPE;
	}

	public Long getId() {
		return id;
	}
}
