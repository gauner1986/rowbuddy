package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class BoatDetailEvent extends GwtEvent<BoatDetailEventHandler>
{
	public static Type<BoatDetailEventHandler> TYPE = new Type<BoatDetailEventHandler>();
	private Long id;
	
	public BoatDetailEvent(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	@Override
	protected void dispatch(BoatDetailEventHandler arg0) {
		arg0.onBoatDetailEvent(this);
	}

	@Override
	public Type<BoatDetailEventHandler> getAssociatedType() {
		return TYPE;
	}
}
