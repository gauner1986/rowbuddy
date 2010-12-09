package de.rowbuddy.client.events;

public class AddBoatDamageEvent extends AbstractEvent<AddBoatDamagePresenterChanger> {
	public static final Type<AddBoatDamagePresenterChanger> TYPE = new Type<AddBoatDamagePresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "AddBoatDamage";

	@Override
	public Type<AddBoatDamagePresenterChanger> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
