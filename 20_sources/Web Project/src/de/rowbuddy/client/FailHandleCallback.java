package de.rowbuddy.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.rowbuddy.client.events.StatusMessageEvent;
import de.rowbuddy.client.model.StatusMessage;
import de.rowbuddy.client.model.StatusMessage.Status;
import de.rowbuddy.exceptions.NotLoggedInException;

public class FailHandleCallback<ReturnType> implements
		AsyncCallback<ReturnType> {

	private String action;
	private GwtEvent<?> successEvent;
	private GwtEvent<?> failEvent;
	private EventBus eventBus;
	private static Logger logger = Logger.getLogger(FailHandleCallback.class
			.toString());

	public FailHandleCallback(EventBus eventBus, String action,
			GwtEvent<?> successEvent, GwtEvent<?> failEvent) {
		this.eventBus = eventBus;
		this.action = action;
		this.successEvent = successEvent;
		this.failEvent = failEvent;
	}

	@Override
	public void onFailure(Throwable failure) {
		logger.severe(action + ": " + failure.toString());
		if (failure instanceof NotLoggedInException) {
			Window.Location.assign(GWT.getHostPageBaseURL() + "Login.jsp");
			return;
		}
		if (failEvent != null) {
			eventBus.fireEvent(failEvent);
		}
		StatusMessage message = new StatusMessage();
		message.setMessage(action + ": " + failure.getMessage());
		message.setStatus(Status.NEGATIVE);
		message.setAttached(false);

		eventBus.fireEvent(new StatusMessageEvent(message));
	}

	@Override
	public void onSuccess(ReturnType arg0) {
		logger.severe("Aktion erfolgreich: " + action);
		if (successEvent != null) {
			eventBus.fireEvent(successEvent);
		}
		StatusMessage message = new StatusMessage();
		message.setMessage(action + " erfolgreich");
		message.setStatus(Status.POSITIVE);
		message.setAttached(false);
		eventBus.fireEvent(new StatusMessageEvent(message));
	}

}
