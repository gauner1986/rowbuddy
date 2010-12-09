package de.rowbuddy.client.events;

public class ListBoatsEvent extends AbstractEvent<ListBoatsPresenterChanger> {

	public static Type<ListBoatsPresenterChanger> TYPE = new Type<ListBoatsPresenterChanger>();
	public static String HISTORY_IDENTIFIER = "ListBoats";

	@Override
	public Type<ListBoatsPresenterChanger> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
