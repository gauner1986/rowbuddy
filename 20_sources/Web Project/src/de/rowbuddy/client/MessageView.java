package de.rowbuddy.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.model.StatusMessage.Status;

public class MessageView extends Composite implements
		StatusMessagePresenter.Display {
	private FlexTable messages;
	private int index = 0;
	private FlexTable mainFlexTable;

	public MessageView() {
		messages = new FlexTable();
		initWidget(messages); 
	}

	@Override
	public void displayMessages(String message, Status status) {
		messages.setText(index, 0, message);

		mainFlexTable =(FlexTable) this.getParent().getParent();
		mainFlexTable.getCellFormatter().setStyleName(1, 1, (status == Status.POSITIVE ? "messagesPositive" : "messagesNegative"));
		
		index++;
	}

	@Override
	public void clear() {
		messages.clear(true);
		messages.clear();
		mainFlexTable =(FlexTable) this.getParent().getParent();
		mainFlexTable.getCellFormatter().setStyleName(1, 1, "messages");
		index = 0;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
