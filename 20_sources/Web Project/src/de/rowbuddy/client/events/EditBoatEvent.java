package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class EditBoatEvent extends GwtEvent<EditBoatEventHandler>{
	public static Type<EditBoatEventHandler> TYPE = new Type<EditBoatEventHandler>();
	private Long id;
	
	public EditBoatEvent(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	@Override
	protected void dispatch(EditBoatEventHandler arg0) {
		arg0.onEditBoatEvent(this);
	}

	@Override
	public Type<EditBoatEventHandler> getAssociatedType() {
		return TYPE;
	}
}
