package de.rowbuddy.client.events;

public abstract class IdEvent<T extends PresenterChanger> extends
		AbstractEvent<T> {

	private final Long id;
	private final String historyIdentifier;

	public IdEvent(String historyIdentifier, Long id) {
		super();
		this.id = id;
		this.historyIdentifier = historyIdentifier;
	}

	@Override
	public String toHistoryItem() {
		return historyIdentifier + "?id=" + id;
	}

	public Long getId() {
		return id;
	}

	public static Long getIdFromHistoryItem(String historyItem) {
		int pos = historyItem.indexOf("id=");
		if (pos == -1) {
			return new Long(-1);
		}
		String s = historyItem.substring(pos + 3);

		Long id = new Long(-1);
		try {
			id = Long.parseLong(s);
		} catch (NumberFormatException ex) {
		}
		return id;
	}
}
