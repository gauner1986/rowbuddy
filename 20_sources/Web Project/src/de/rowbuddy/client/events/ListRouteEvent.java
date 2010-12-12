package de.rowbuddy.client.events;


public class ListRouteEvent extends AbstractEvent<ListRoutePresenterChanger> {

	public static Type<ListRoutePresenterChanger> TYPE = new Type<ListRoutePresenterChanger>();
	public static String HISTORY_IDENTIFIER = "ListRoutes";
	
	@Override
	public Type<ListRoutePresenterChanger> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}

}
