package de.rowbuddy.client.events;

public class EditDamageEvent extends IdEvent<EditDamagePresenterChanger> {

	public static final Type<EditDamagePresenterChanger> TYPE = new Type<EditDamagePresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "EditDamage";

	public EditDamageEvent(Long id) {
		super(HISTORY_IDENTIFIER, id);
	}

	@Override
	public Type<EditDamagePresenterChanger> getAssociatedType() {
		return TYPE;
	}
}
