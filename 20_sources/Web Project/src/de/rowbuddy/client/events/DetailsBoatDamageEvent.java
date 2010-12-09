package de.rowbuddy.client.events;

public class DetailsBoatDamageEvent extends
		IdEvent<DetailsBoatDamagePresenterChanger> {
	public static final Type<DetailsBoatDamagePresenterChanger> TYPE = new Type<DetailsBoatDamagePresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "DetailsBoatDamage";

	public DetailsBoatDamageEvent(Long id) {
		super(HISTORY_IDENTIFIER, id);
	}

	@Override
	public Type<DetailsBoatDamagePresenterChanger> getAssociatedType() {
		return TYPE;
	}
}
