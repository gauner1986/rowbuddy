package de.rowbuddy.client.events;

public class AddDamageEvent extends AbstractEvent<AddDamageHandler> {
	public static final Type<AddDamageHandler> TYPE = new Type<AddDamageHandler>();
	public static final String HISTORY_IDENTIFIER = "AddDamage";

	@Override
	public Type<AddDamageHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
