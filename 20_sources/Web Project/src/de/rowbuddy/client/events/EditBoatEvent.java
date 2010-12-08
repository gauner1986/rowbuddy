package de.rowbuddy.client.events;

public class EditBoatEvent extends IdEvent<EditBoatEventHandler> {
	public static final Type<EditBoatEventHandler> TYPE = new Type<EditBoatEventHandler>();
	public static final String HISTORY_IDENTIFIER = "EditBoat";

	public EditBoatEvent(Long id) {
		super(HISTORY_IDENTIFIER, id);
	}

	@Override
	public Type<EditBoatEventHandler> getAssociatedType() {
		return TYPE;
	}
}
