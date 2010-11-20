package de.rowbuddy.client.views;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.MenuDisplay;

public class MenuView extends Composite implements MenuDisplay{

	private Button listBoats = null;
	
	public MenuView(){
		listBoats = new Button();
		//initWidget(stackPanel);
		VerticalPanel verticalPanel = new VerticalPanel();
		DecoratedStackPanel menuPanel = new DecoratedStackPanel();
		menuPanel.add(createTripsMenu(), "Offene Fahrten");
		menuPanel.add(createProfilMenu(), "Profil");
		menuPanel.add(createViewTripMenu(), "Fahrtenbuch");
		menuPanel.add(createStatistikMenu(), "Statistiken");
		menuPanel.add(createBootMenu(), "Boote");
		// menuPanel.add(null, "Mitglieder");
		// menuPanel.add(null, "Routen");
		// menuPanel.add(null, "Bootsschäden");
		// menuPanel.add(null, "Bootsreservierungen");
		// menuPanel.add(null, "Mitgliederverwaltung");
		Button logoutButton = new Button("Logout");
		verticalPanel.add(menuPanel);
		verticalPanel.add(logoutButton);
		initWidget(verticalPanel);
	}

	private Widget createBootMenu() {
		VerticalPanel panel = new VerticalPanel();
		Anchor browseBoats = new Anchor("Bootsübersicht");
		Anchor viewBoat = new Anchor("Boot ansehen");
		Anchor addBoat = new Anchor("Boot hinzufügen");
		Anchor editBoat = new Anchor("Boot bearbeiten");
		Anchor deleteBoat = new Anchor("Boot löschen");
		Anchor lockBoat = new Anchor("?");
		panel.add(browseBoats);
		panel.add(viewBoat);
		panel.add(addBoat);
		panel.add(editBoat);
		panel.add(deleteBoat);
		panel.add(lockBoat);
		return panel;
	}

	private Widget createViewTripMenu() {
		VerticalPanel panel = new VerticalPanel();
		Anchor browseTrips = new Anchor("?");
		Anchor browseTrip = new Anchor("?");
		Anchor viewTrip = new Anchor("?");
		Anchor editTrip = new Anchor("Fahrt editieren");
		panel.add(browseTrips);
		panel.add(browseTrip);
		panel.add(viewTrip);
		panel.add(editTrip);
		return panel;
	}

	private Widget createTripsMenu() {
		VerticalPanel panel = new VerticalPanel();
		Anchor browseOpenTrips = new Anchor("Offene Fahrten");
		Anchor browsePersonalOpenTrips = new Anchor(
				"Persönliche offene Fahrten");
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
	
	@Override
	public HasClickHandlers getListBoatButton() {
		return listBoats;
	}
	
	@Override	
	public Widget asWidget(){
		return this;
	}

}
