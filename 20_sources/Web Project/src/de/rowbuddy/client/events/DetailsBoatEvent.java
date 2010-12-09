package de.rowbuddy.client.events;

public class DetailsBoatEvent extends IdEvent<DetailsBoatPresenterChanger> {

	public static final Type<DetailsBoatPresenterChanger> TYPE = new Type<DetailsBoatPresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "DetailsBoat";

	public DetailsBoatEvent(Long id) {
		super(HISTORY_IDENTIFIER, id);
	}

	@Override
	public Type<DetailsBoatPresenterChanger> getAssociatedType() {
		return TYPE;
	}

}
