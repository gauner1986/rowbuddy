package de.rowbuddy.client.events;

public class ListRoutesEvent extends AbstractEvent<ListRoutesPresenterChanger> {

	public static final Type<ListRoutesPresenterChanger> TYPE = new Type<ListRoutesPresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "ListRoutes";

	@Override
	public Type<ListRoutesPresenterChanger> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}

}
