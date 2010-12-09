package de.rowbuddy.client.events;

public class EditBoatDamageEvent extends IdEvent<EditBoatDamagePresenterChanger> {

	public static final Type<EditBoatDamagePresenterChanger> TYPE = new Type<EditBoatDamagePresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "EditBoatDamage";

	public EditBoatDamageEvent(Long id) {
		super(HISTORY_IDENTIFIER, id);
	}

	@Override
	public Type<EditBoatDamagePresenterChanger> getAssociatedType() {
		return TYPE;
	}
}
