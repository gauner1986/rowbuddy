package de.rowbuddy.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.Widget;

public class GWTEntryPoint implements EntryPoint {

	public void onModuleLoad() {
		// BoatRemoteServiceAsync boatService = (BoatRemoteServiceAsync)
		// GWT.create(BoatRemoteService.class);
		// ((ServiceDefTarget) boatService).setServiceEntryPoint(
		// GWT.getHostPageBaseURL() +"BoatRemoteServiceImpl");
		// Window.alert(GWT.getHostPageBaseURL() +"BoatRemoteServiceImpl");

		// SimpleEventBus eventBus = new SimpleEventBus();
		// AppController controller = new AppController(boatService, eventBus);
		// controller.start(RootPanel.get());
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

		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_LEFT);
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter
				.setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		cellFormatter.setRowSpan(1, 0, 2);
		cellFormatter.setHorizontalAlignment(1, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		cellFormatter.setHorizontalAlignment(2, 0,
				HasHorizontalAlignment.ALIGN_LEFT);

		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setWidth("80em");
		Label headerLabel = new Label("HEADER");
		headerLabel.setWidth("60em");
		Label loginLabel = new Label("Login: ich");
		loginLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		loginLabel.setWidth("20em");
		hPanel.add(headerLabel);
		hPanel.add(loginLabel);
		flexTable.setWidget(0, 0, hPanel);

		StackPanel menuPanel = new StackPanel();
		menuPanel	.setHeight("30em");
		menuPanel.add(new Label("MENU Panel"));
		flexTable.setWidget(1, 0, menuPanel);

		Label messagesLabel = new Label("messages");
		messagesLabel.setHeight("1em");
		messagesLabel.setWidth("50em");
		flexTable.setWidget(1, 1, messagesLabel);

		Label mainLabel = new Label("main");
		mainLabel.setHeight("25em");
		flexTable.setWidget(2, 0, mainLabel);

		return flexTable;
	}
}
