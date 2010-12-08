package de.rowbuddy.client.events;

public class BoatDetailEvent extends IdEvent<BoatDetailEventHandler> {

	public static final Type<BoatDetailEventHandler> TYPE = new Type<BoatDetailEventHandler>();
	public static final String HISTORY_IDENTIFIER = "BoatDetails";

	public BoatDetailEvent(Long id) {
		super(HISTORY_IDENTIFIER, id);
	}

	@Override
	public Type<BoatDetailEventHandler> getAssociatedType() {
		return TYPE;
	}

}
