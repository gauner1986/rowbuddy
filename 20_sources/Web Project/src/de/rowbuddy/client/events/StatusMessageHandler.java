package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface StatusMessageHandler extends EventHandler{
	void onNewStatusMessage(StatusMessageEvent event);
}
