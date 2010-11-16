package de.rowbuddy.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.services.BoatRemoteService;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;

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
		flexTable.setWidth("1000em");
		flexTable.setCellSpacing(5);
		flexTable.setCellPadding(3);
		
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		cellFormatter.setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		
		flexTable.setWidget(0, 0, new Label("HEADER"));
		flexTable.setWidget(0, 2, new Label("Login: Ich"));

		return flexTable;
	}
}
