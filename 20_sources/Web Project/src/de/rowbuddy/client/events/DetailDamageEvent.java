package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class DetailDamageEvent extends GwtEvent<DetailDamageHandler> {
	public static Type<DetailDamageHandler> TYPE = new Type<DetailDamageHandler>();
	private Long id;

	public DetailDamageEvent(Long id) {
		this.id = id;
	}

	@Override
	protected void dispatch(DetailDamageHandler arg0) {
		arg0.onDetailDamage(this);
	}

	@Override
	public Type<DetailDamageHandler> getAssociatedType() {
		return TYPE;
	}

	public Long getId() {
		return id;
	}
}
