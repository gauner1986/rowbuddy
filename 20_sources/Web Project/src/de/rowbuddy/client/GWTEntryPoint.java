package de.rowbuddy.client;

//import java.util.logging.Logger;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.images.Images;
import de.rowbuddy.client.presenter.MenuPresenter;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteService;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.MenuView;

public class GWTEntryPoint implements EntryPoint {

	private static Logger logger = Logger.getLogger(GWTEntryPoint.class
			.getName());

	public void onModuleLoad() {
		BoatRemoteServiceAsync boatService = (BoatRemoteServiceAsync) GWT
				.create(BoatRemoteService.class);
		((ServiceDefTarget) boatService).setServiceEntryPoint(GWT
				.getHostPageBaseURL() + "BoatRemoteServiceImpl");
		// Window.alert(GWT.getHostPageBaseURL() +"BoatRemoteServiceImpl");

		HasWidgets mainPanel = initialMainPanel();
		FlowPanel messagePanel = new FlowPanel();
		SimpleEventBus eventBus = new SimpleEventBus();
		
		VerticalPanel vPanel = new VerticalPanel();
		
		RootPanel.get("Main").add(initalRootFlexTable(mainPanel, messagePanel, vPanel));

		
		
		AppController controller = new AppController(boatService, eventBus, messagePanel, vPanel);

		controller.start(mainPanel);
		logger.info("Application started");
	}

	private Widget initalRootFlexTable(HasWidgets mainPanel, Widget messagePanel, VerticalPanel vPanel) {
		Images images = (Images) GWT.create(Images.class);

		final FlexTable flexTable = new FlexTable();
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		flexTable.addStyleName("flexTable");
		flexTable.setWidth("80%");
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
		hPanel.setWidth("100%");
		hPanel.setStylePrimaryName("logoHeader");
		hPanel.add(new Image(images.logo()));
		Label loginLabel = new Label("Login: ich");
		loginLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hPanel.add(loginLabel);
		hPanel.setCellWidth(loginLabel, "20%");
		flexTable.setWidget(0, 0, hPanel);

		cellFormatter.setWidth(1, 0, "20%");
		
		
		
		flexTable.setWidget(1, 0, vPanel);

		//messagePanel.setStyleName("messages");
		DecoratorPanel panel = new DecoratorPanel();
		
		panel.setWidget(messagePanel);
		flexTable.setWidget(1, 1, panel);
		//panel.setStylePrimaryName("messages");
		flexTable.getCellFormatter().setStylePrimaryName(1, 0, "menuPanel");
		flexTable.getCellFormatter().setStylePrimaryName(1, 1, "messages");

		flexTable.setWidget(2, 0, (Widget) mainPanel);
		
		flexTable.getCellFormatter().setStylePrimaryName(2, 0, "viewPanel");
		
		return flexTable;
	}

	private HasWidgets initialMainPanel() {
		FlowPanel panel = new FlowPanel();
		
		return panel;
	}

}
