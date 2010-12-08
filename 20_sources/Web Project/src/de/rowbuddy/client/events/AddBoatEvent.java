package de.rowbuddy.client.events;

public class AddBoatEvent extends AbstractEvent<AddBoatEventHandler> {

	public static final String HISTORY_IDENTIFIER = "AddBoat";
	public static Type<AddBoatEventHandler> TYPE = new Type<AddBoatEventHandler>();

	@Override
	public Type<AddBoatEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}

}
