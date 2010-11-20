package de.rowbuddy.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.presenter.MenuPresenter;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteService;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.MenuView;

public class GWTEntryPoint implements EntryPoint {

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

		//controller.start(mainPanel);
		/*
		 * 
		 * 
		 * rootPanel.add(new OpenTrip()); rootPanel.add(new LogDamagedBoat());
		 * rootPanel.add(new LogTrip()); rootPanel.add(new StartTrip());
		 * rootPanel.add(new StopTrip()); rootPanel.add(new ViewTrip());
		 */
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

		cellFormatter.setWidth(1, 0, "25%");
		Presenter presenter = new MenuPresenter(new MenuView());
		VerticalPanel vetPanel = new VerticalPanel();
		presenter.start(vetPanel);
		flexTable.setWidget(1, 0, vetPanel);

		Label messagesLabel = new Label("messages");
		messagesLabel.setStyleName("messages");
		messagesLabel.setVisible(false);
		flexTable.setWidget(1, 1, messagesLabel);

		flexTable.setWidget(2, 0, (Widget) mainPanel);

		return flexTable;
	}

	private HasWidgets initialMainPanel() {
		VerticalPanel panel = new VerticalPanel();
		panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		Label mainLabel = new Label(
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."
						+ "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat."
						+ "Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi."
						+ "Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat."
						+ "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis."
						+ "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, At accusam aliquyam diam diam dolore dolores duo eirmod eos erat, et nonumy sed tempor et et invidunt justo labore Stet clita ea et gubergren, kasd magna no rebum. sanctus sea sed takimata ut vero voluptua. est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur");
		mainLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);

		  // Create a three-item tab panel, with the tab area 1.5em tall.
	    TabLayoutPanel p = new TabLayoutPanel(1.5, Unit.EM);
	    p.add(new HTML("this"), "[this]");
	    p.add(new HTML("that"), "[that]");
	    p.add(new HTML("the other"), "[the other]");


//		 TabLayoutPanel mainPanel = new TabLayoutPanel(1.5, Unit.EM);
//		 mainPanel.add(mainLabel, "Neue Fahrt");
//		 mainPanel.add(new Label("Testen"), "Nachtrag");
		panel.add(p);
		return p;
	}

	
}
