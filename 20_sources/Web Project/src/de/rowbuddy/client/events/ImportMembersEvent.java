package de.rowbuddy.client.events;

public class ImportMembersEvent extends
		AbstractEvent<ImportMembersPresenterChanger> {

	public static Type<ImportMembersPresenterChanger> TYPE = new Type<ImportMembersPresenterChanger>();
	public static String HISTORY_IDENTIFIER = "ImportMembers";

	@Override
	public Type<ImportMembersPresenterChanger> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toHistoryItem() {
		return HISTORY_IDENTIFIER;
	}

}
