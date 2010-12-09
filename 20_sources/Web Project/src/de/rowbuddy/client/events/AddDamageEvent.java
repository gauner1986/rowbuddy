package de.rowbuddy.client.events;

public class AddDamageEvent extends AbstractEvent<AddDamagePresenterChanger> {
	public static final Type<AddDamagePresenterChanger> TYPE = new Type<AddDamagePresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "AddDamage";

	@Override
	public Type<AddDamagePresenterChanger> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
