package de.rowbuddy.client.events;

public class BoatListEvent extends AbstractEvent<BoatListHandler> {

	public static Type<BoatListHandler> TYPE = new Type<BoatListHandler>();

	@Override
	public Type<BoatListHandler> getAssociatedType() {
		return TYPE;
	}

}
