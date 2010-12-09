package de.rowbuddy.client.events;

public class ListDamageEvent extends AbstractEvent<ListDamagePresenterChanger> {
	public static final Type<ListDamagePresenterChanger> TYPE = new Type<ListDamagePresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "ListDamages";

	@Override
	public Type<ListDamagePresenterChanger> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
