package de.rowbuddy.client;

//import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.presenter.MenuPresenter;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteService;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.MenuView;

public class GWTEntryPoint implements EntryPoint {

//	private static Logger logger = Logger.getLogger(GWTEntryPoint.class.getName());

	public void onModuleLoad() {
		BoatRemoteServiceAsync boatService = (BoatRemoteServiceAsync) GWT
				.create(BoatRemoteService.class);
		((ServiceDefTarget) boatService).setServiceEntryPoint(GWT
				.getHostPageBaseURL() + "BoatRemoteServiceImpl");
		// Window.alert(GWT.getHostPageBaseURL() +"BoatRemoteServiceImpl");	

		SimpleEventBus eventBus = new SimpleEventBus();
		AppController controller = new AppController(boatService, eventBus);

		HasWidgets mainPanel = initialMainPanel();
		RootPanel.get("Main").add(initalRootFlexTable(mainPanel));

		controller.start(mainPanel);
//		logger.info("Application started");
	}

	private Widget initalRootFlexTable(HasWidgets mainPanel) {
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
		Label headerLabel = new Label("HEADER");
		Label loginLabel = new Label("Login: ich");
		loginLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hPanel.add(headerLabel);
		hPanel.add(loginLabel);
		hPanel.setCellWidth(headerLabel, "80%");
		flexTable.setWidget(0, 0, hPanel);

		cellFormatter.setWidth(1, 0, "20%");
		Presenter presenter = new MenuPresenter(new MenuView());
		VerticalPanel vetPanel = new VerticalPanel();
		presenter.start(vetPanel);
		flexTable.setWidget(1, 0, vetPanel);

		Label messagesLabel = new Label("messages");
		messagesLabel.setStyleName("messages");
		messagesLabel.setVisible(true);
		flexTable.setWidget(1, 1, messagesLabel);

		flexTable.setWidget(2, 0, (Widget) mainPanel);

		return flexTable;
	}

	private HasWidgets initialMainPanel() {
		VerticalPanel panel = new VerticalPanel();
		panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		return panel;
	}

}
