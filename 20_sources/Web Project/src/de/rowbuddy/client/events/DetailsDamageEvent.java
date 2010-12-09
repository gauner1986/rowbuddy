package de.rowbuddy.client.events;

public class DetailsDamageEvent extends IdEvent<DetailsDamagePresenterChanger> {
	public static final Type<DetailsDamagePresenterChanger> TYPE = new Type<DetailsDamagePresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "DetailsDamage";

	public DetailsDamageEvent(Long id) {
		super(HISTORY_IDENTIFIER, id);
	}

	@Override
	public Type<DetailsDamagePresenterChanger> getAssociatedType() {
		return TYPE;
	}
}
