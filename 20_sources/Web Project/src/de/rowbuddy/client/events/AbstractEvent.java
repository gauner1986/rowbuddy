package de.rowbuddy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public abstract class AbstractEvent<T extends AbstractEventHandler> extends
		GwtEvent<T> {

	@Override
	protected void dispatch(T arg0) {
		arg0.processEvent(this);
	}

	@Override
	public abstract Type<T> getAssociatedType();

	public abstract String toHistoryItem();
}
