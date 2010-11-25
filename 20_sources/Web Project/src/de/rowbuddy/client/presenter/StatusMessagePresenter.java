package de.rowbuddy.client.presenter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.events.StatusMessageEvent;
import de.rowbuddy.client.events.StatusMessageHandler;
import de.rowbuddy.client.model.StatusMessage;

public class StatusMessagePresenter implements Presenter {
	public interface Display {
		void displayMessages(String message, StatusMessage.Status status);

		void clear();

		Widget asWidget();
	}

	private HasWidgets container;
	private Queue<StatusMessage> messages;
	private Display view;
	private SimpleEventBus eventBus;
	private Logger logger = Logger.getLogger(StatusMessagePresenter.class
			.getName());

	public StatusMessagePresenter(Display view, SimpleEventBus eventBus) {
		messages = new LinkedList<StatusMessage>();
		this.eventBus = eventBus;
		this.view = view;

	}

	@Override
	public void start(HasWidgets container) {
		this.container = container;
		this.container.clear();
		this.container.add(view.asWidget());

		eventBus.addHandler(StatusMessageEvent.TYPE,
				new StatusMessageHandler() {

					@Override
					public void onNewStatusMessage(StatusMessageEvent event) {
						addMessage(event.getMessage());
					}
				});

	}

	private void addMessage(StatusMessage message) {
		logger.info("New message received");
		if(!message.isAttached()){
			logger.info("Clear messages");
			clear();
		}
		messages.add(message);
		view.displayMessages(message.getMessage(), message.getStatus());
	}

	public void clear() {
		messages.clear();
		view.clear();
	}

}
