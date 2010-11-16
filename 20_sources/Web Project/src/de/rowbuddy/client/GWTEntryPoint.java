package de.rowbuddy.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.Widget;

public class GWTEntryPoint implements EntryPoint {

	public void onModuleLoad() {
//		BoatRemoteServiceAsync boatService = (BoatRemoteServiceAsync) GWT.create(BoatRemoteService.class);
//		((ServiceDefTarget) boatService).setServiceEntryPoint( GWT.getHostPageBaseURL() +"BoatRemoteServiceImpl");
//		Window.alert(GWT.getHostPageBaseURL() +"BoatRemoteServiceImpl");


//		SimpleEventBus eventBus = new SimpleEventBus();
//		AppController controller = new AppController(boatService, eventBus);
//		controller.start(RootPanel.get());
		RootPanel.get().add(initalRootFlexTable());
		/*
		 * 
		 * 
		 * rootPanel.add(new OpenTrip()); rootPanel.add(new LogDamagedBoat());
		 * rootPanel.add(new LogTrip()); rootPanel.add(new StartTrip());
		 * rootPanel.add(new StopTrip()); rootPanel.add(new ViewTrip());
		 */
	}

	private Widget initalRootFlexTable() {
		final FlexTable flexTable = new FlexTable();
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		flexTable.addStyleName("flexTable");
		flexTable.setWidth("80em");
		flexTable.setCellSpacing(5);
		flexTable.setCellPadding(3);
		
		cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter.setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		cellFormatter.setRowSpan(1, 0, 2);
		cellFormatter.setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_LEFT);
		cellFormatter.setColSpan(1, 1, 2);
		cellFormatter.setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		cellFormatter.setColSpan(2, 1, 2);
		
		
		Label headerLabel = new Label("HEADER");
		headerLabel.setWidth("60em");
		flexTable.setWidget(0, 0, headerLabel);
		
		Label loginLabel = new Label("Login: ich");
		loginLabel.setWidth("20em");
		flexTable.setWidget(0, 2, loginLabel);
		
		StackPanel menuPanel = new StackPanel();
		menuPanel.setHeight("20em");
		flexTable.setWidget(1, 0, menuPanel);
		
		Label messagesLabel = new Label("messages");
		messagesLabel.setHeight("10em");
		flexTable.setWidget(1, 1, messagesLabel);	
		
		Label mainLabel = new Label("main");
		flexTable.setWidget(2, 1, mainLabel);

		return flexTable;
	}
}
