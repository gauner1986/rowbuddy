package de.rowbuddy.client.events;

public class AddRouteEvent extends AbstractEvent<AddRoutePresenterChanger> {
	
	public static final String HISTORY_IDENTIFIER = "AddRoute";
	public static Type<AddRoutePresenterChanger> TYPE = new Type<AddRoutePresenterChanger>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AddRoutePresenterChanger> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}

}
