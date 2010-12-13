package de.rowbuddy.client.events;

public class EditRouteEvent extends IdEvent<EditRoutPresenterChanger> {
	
	public static final Type<EditRoutPresenterChanger> TYPE = new Type<EditRoutPresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "EditRoute";

	public EditRouteEvent(Long id) {
		super(HISTORY_IDENTIFIER, id);
	}

	@Override
	public Type<EditRoutPresenterChanger> getAssociatedType() {
		return TYPE;
	}

}
