package de.rowbuddy.client.events;

public class DetailsMemberEvent extends IdEvent<DetailsMemberPresenterChanger> {
	public static final Type<DetailsMemberPresenterChanger> TYPE = new Type<DetailsMemberPresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "DetailsMember";

	public DetailsMemberEvent(Long id) {
		super(HISTORY_IDENTIFIER, id);
	}

	@Override
	public Type<DetailsMemberPresenterChanger> getAssociatedType() {
		return TYPE;
	}
}
