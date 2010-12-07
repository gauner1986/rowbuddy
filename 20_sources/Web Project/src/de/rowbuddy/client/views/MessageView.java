package de.rowbuddy.client.views;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.model.StatusMessage.Status;
import de.rowbuddy.client.presenter.StatusMessagePresenter;

public class MessageView extends Composite implements StatusMessagePresenter.Display{
	private FlexTable messages;
	private int index = 0;
	public MessageView(){
		messages = new FlexTable();
		initWidget(messages);
	}
	
	@Override
	public void displayMessages(String message, Status status) {
		messages.setText(index, 0, message);
		messages.getRowFormatter().setStylePrimaryName(index, (status == Status.POSITIVE ? "messagePositive" : "messageNegative"));
		index++;
	}

	@Override
	public void clear() {
		messages.clear(true);
		messages.clear();
		index = 0;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
