package de.rowbuddy.client.events;

public class EditBoatDamageEvent extends IdEvent<EditBoatDamageHandler> {

	public static final Type<EditBoatDamageHandler> TYPE = new Type<EditBoatDamageHandler>();
	public static final String HISTORY_IDENTIFIER = "EditBoatDamage";

	public EditBoatDamageEvent(Long id) {
		super(HISTORY_IDENTIFIER, id);
	}

	@Override
	public Type<EditBoatDamageHandler> getAssociatedType() {
		return TYPE;
	}
}
