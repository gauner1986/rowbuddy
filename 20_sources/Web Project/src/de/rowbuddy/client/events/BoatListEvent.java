package de.rowbuddy.client.events;

public class BoatListEvent extends AbstractEvent<BoatListHandler> {

	public static Type<BoatListHandler> TYPE = new Type<BoatListHandler>();
	public static String HISTORY_IDENTIFIER = "ListBoats";

	@Override
	public Type<BoatListHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}
}
