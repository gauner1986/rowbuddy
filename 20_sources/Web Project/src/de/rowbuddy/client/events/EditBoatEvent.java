package de.rowbuddy.client.events;

public class EditBoatEvent extends IdEvent<EditBoatPresenterChanger> {
	public static final Type<EditBoatPresenterChanger> TYPE = new Type<EditBoatPresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "EditBoat";

	public EditBoatEvent(Long id) {
		super(HISTORY_IDENTIFIER, id);
	}

	@Override
	public Type<EditBoatPresenterChanger> getAssociatedType() {
		return TYPE;
	}
}
