package nl.fontys.jee.rowbuddy.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

import de.chiaradia.messaging.entities.MessageEntity;

public class GWTEntryPoint implements EntryPoint {
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
		final FlexTable table = new FlexTable();
		
		MessageServiceAsync service = (MessageServiceAsync) GWT.create(MessageService.class);
		
        ServiceDefTarget serviceDef = (ServiceDefTarget) service;
        serviceDef.setServiceEntryPoint(GWT.getHostPageBaseURL() + "MessageServiceImpl");
        table.setText(0, 0, "ID");
        table.setText(0, 1, "Message");
        service.getMessages(new AsyncCallback<List<MessageEntity>>() {
			
			@Override
			public void onSuccess(List<MessageEntity> arg0) {
				// TODO Auto-generated method stub
				for(int i = 0; i < arg0.size(); i++) {
					MessageEntity message = arg0.get(i);
					table.setText(i + 1, 0, "" + message.getId());
					table.setText(i + 1, 1, "" + message.getMessage());
				}
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
				Window.alert("Failure!!\n" + GWT.getHostPageBaseURL() + "MessageServiceImpl");
				
			}
		});
        
        RootPanel.get().add(table);
	}

}
