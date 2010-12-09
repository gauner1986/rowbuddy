package de.rowbuddy.client.events;

public class AddBoatEvent extends AbstractEvent<AddBoatPresenterChanger> {

	public static final String HISTORY_IDENTIFIER = "AddBoat";
	public static Type<AddBoatPresenterChanger> TYPE = new Type<AddBoatPresenterChanger>();

	@Override
	public Type<AddBoatPresenterChanger> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}

}
