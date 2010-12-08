package de.rowbuddy.client.events;

public class ListDamageEvent extends AbstractEvent<ListDamageEventHandler> {
	public static final Type<ListDamageEventHandler> TYPE = new Type<ListDamageEventHandler>();
	public static final String HISTORY_IDENTIFIER = "ListDamages";

	@Override
	public Type<ListDamageEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
