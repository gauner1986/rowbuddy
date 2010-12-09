package de.rowbuddy.client.events;

public class ListBoatDamageEvent extends AbstractEvent<ListBoatDamagePresenterChanger> {
	public static final Type<ListBoatDamagePresenterChanger> TYPE = new Type<ListBoatDamagePresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "ListBoatDamages";

	@Override
	public Type<ListBoatDamagePresenterChanger> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
