package de.rowbuddy.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
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
		RootPanel.get("Main").add(initalRootFlexTable());
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
		flexTable.setWidget(1, 0, initialMenuPanel());

		Label messagesLabel = new Label("messages");
		messagesLabel.setStyleName("messages");
		flexTable.setWidget(1, 1, messagesLabel);

		flexTable.setWidget(2, 0, initialMainPanel());

		return flexTable;
	}

	private Widget initialMainPanel() {
		Label mainLabel = new Label(
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."
						+ "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat."
						+ "Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi."
						+ "Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat."
						+ "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis."
						+ "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, At accusam aliquyam diam diam dolore dolores duo eirmod eos erat, et nonumy sed tempor et et invidunt justo labore Stet clita ea et gubergren, kasd magna no rebum. sanctus sea sed takimata ut vero voluptua. est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur");
		mainLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);

		TabLayoutPanel mainPanel = new TabLayoutPanel(1.5, Unit.EM);
		mainPanel.add(mainLabel, "Neue Fahrt");
		mainPanel.add(new Label("Testen"), "Nachtrag");
		return mainLabel;
	}

	private Widget initialMenuPanel() {
		VerticalPanel verticalPanel = new VerticalPanel();
		DecoratedStackPanel menuPanel = new DecoratedStackPanel();
		menuPanel.add(createTripsMenu(), "Offene Fahrten");
		menuPanel.add(createProfilMenu(), "Profil");
		// menuPanel.add(null, "Fahrtenbuch");
		menuPanel.add(createStatistikMenu(), "Statistiken");
		// menuPanel.add(null, "Boote");
		// menuPanel.add(null, "Mitglieder");
		// menuPanel.add(null, "Routen");
		// menuPanel.add(null, "Bootssch�den");
		// menuPanel.add(null, "Bootsreservierungen");
		// menuPanel.add(null, "Mitgliederverwaltung");

		Button logoutButton = new Button("Logout");

		verticalPanel.add(menuPanel);
		verticalPanel.add(logoutButton);
		return verticalPanel;
	}

	private Widget createTripsMenu() {
		VerticalPanel panel = new VerticalPanel();
		Anchor browseOpenTrips = new Anchor("Offene Fahrten");
		Anchor browsePersonalOpenTrips = new Anchor("Persönliche offene Fahrten");
		Anchor stoprowing = new Anchor("Fahrten stoppen");
		panel.add(browseOpenTrips);
		panel.add(browsePersonalOpenTrips);
		panel.add(stoprowing);
		return panel;
	}

	private Widget createStatistikMenu() {
		VerticalPanel panel = new VerticalPanel();
		Anchor boatdamagesYear = new Anchor("Bootschäden");
		Anchor clubactivityMonth = new Anchor("Aktivität - Monatsstatistik");
		Anchor clubactivityWeekday = new Anchor("Aktivität - Tagesstatistik");
		Anchor highscoreBoats = new Anchor("Boote - Jahresstatistik");
		Anchor highscoreMonth = new Anchor("Monatsstatistik");
		Anchor highscoreYear = new Anchor("Jahresstatistik");
		Anchor personalMonth = new Anchor("Persönliche Monatsstatistik");
		Anchor personalYear = new Anchor("Persönliche Jahresstatistik");
		Anchor popularRoutes = new Anchor("Beleibteste Routen");
		Anchor statisticsMain = new Anchor("Statistiken");
		panel.add(statisticsMain);
		panel.add(boatdamagesYear);
		panel.add(clubactivityMonth);
		panel.add(clubactivityWeekday);
		panel.add(highscoreYear);
		panel.add(highscoreMonth);
		panel.add(highscoreBoats);
		panel.add(personalYear);
		panel.add(personalMonth);
		panel.add(popularRoutes);
		return panel;
	}

	private Widget createProfilMenu() {
		VerticalPanel panel = new VerticalPanel();
		Anchor browseRowedRoutes = new Anchor("Meine geruderten Routen");
		Anchor changePassword = new Anchor("Passwort ändern");
		Anchor requestNewPassword = new Anchor("Neues Passwort anfordern");
		Anchor searchProfile = new Anchor("Profil suchen");
		Anchor viewProfile = new Anchor("Profil anzeigen");
		panel.add(viewProfile);
		panel.add(searchProfile);
		panel.add(browseRowedRoutes);
		panel.add(changePassword);
		panel.add(requestNewPassword);
		return panel;
	}
}
