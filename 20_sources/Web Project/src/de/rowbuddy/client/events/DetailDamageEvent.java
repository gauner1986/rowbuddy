package de.rowbuddy.client.events;

public class DetailDamageEvent extends IdEvent<DetailDamageHandler> {
	public static final Type<DetailDamageHandler> TYPE = new Type<DetailDamageHandler>();
	public static final String HISTORY_IDENTIFIER = "DamageDetails";

	public DetailDamageEvent(Long id) {
		super(HISTORY_IDENTIFIER, id);
	}

	@Override
	public Type<DetailDamageHandler> getAssociatedType() {
		return TYPE;
	}
}
