package de.rowbuddy.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.MenuDisplay;
import de.rowbuddy.client.images.Images;

public class MenuView extends Composite implements MenuDisplay {

	private Button listBoats = null;

	public MenuView() {
		Images images = (Images) GWT.create(Images.class);

		listBoats = new Button();
		// initWidget(stackPanel);
		VerticalPanel verticalPanel = new VerticalPanel();
		DecoratedStackPanel menuPanel = new DecoratedStackPanel();

		menuPanel.add(createTripsMenu(), getHeaderString("Offene Fahrten", images.openTrips()), true);
		menuPanel.add(createProfilMenu(), getHeaderString("Profil", images.profil()), true);
		menuPanel.add(createViewTripMenu(), getHeaderString("Fahrtenbuch", images.logBook()), true);
		menuPanel.add(createStatistikMenu(), getHeaderString("Statistiken", images.statistics()), true);
		menuPanel.add(createBoatMenu(), getHeaderString("Boote", images.boat()), true);
		// menuPanel.add(null, "Mitglieder");
		// menuPanel.add(null, "Routen");
		// menuPanel.add(null, "Bootsschäden");
		// menuPanel.add(null, "Bootsreservierungen");
		// menuPanel.add(null, "Mitgliederverwaltung");

		Button logoutButton = new Button("Logout");	
		logoutButton.setStylePrimaryName("buttonLogout buttonNegative");

		verticalPanel.add(menuPanel);
		verticalPanel.add(logoutButton);
		initWidget(verticalPanel);
	}
	
	private String getHeaderString(String text, ImageResource image) {
	    // Add the image and text to a horizontal panel
	    HorizontalPanel hPanel = new HorizontalPanel();
	    hPanel.setSpacing(0);
	    hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	    hPanel.add(new Image(image));
	    HTML headerText = new HTML(text);
	    headerText.setStyleName("cw-StackPanelHeader");
	    hPanel.add(headerText);

	    // Return the HTML string for the panel
	    return hPanel.getElement().getString();
	  }

	private Widget createBoatMenu() {
		FlexTable tb1 = new FlexTable();
		
		Anchor browseBoats = new Anchor("Bootsübersicht");
		Anchor viewBoat = new Anchor("Boot ansehen");
		Anchor addBoat = new Anchor("Boot hinzufügen");
		Anchor editBoat = new Anchor("Boot bearbeiten");
		Anchor deleteBoat = new Anchor("Boot löschen");
		Anchor lockBoat = new Anchor("?");
		tb1.setWidget(0, 0, browseBoats);
		tb1.setWidget(1, 0, viewBoat);
		tb1.setWidget(2, 0, addBoat);
		tb1.setWidget(3, 0, editBoat);
		tb1.setWidget(4, 0, deleteBoat);
		tb1.setWidget(5, 0, lockBoat);
		
		tb1.getRowFormatter().setStyleName(0, "menuItem");
		tb1.getRowFormatter().setStyleName(1, "menuItem");
		tb1.getRowFormatter().setStyleName(2, "menuItem");
		tb1.getRowFormatter().setStyleName(3, "menuItem");
		tb1.getRowFormatter().setStyleName(4, "menuItem");
		tb1.getRowFormatter().setStyleName(5, "menuItem");
		
		
		return tb1;
	}

	private Widget createViewTripMenu() {
		FlexTable tb = new FlexTable();
		Anchor browseTrips = new Anchor("?");
		Anchor browseTrip = new Anchor("?");
		Anchor viewTrip = new Anchor("?");
		Anchor editTrip = new Anchor("Fahrt editieren");
		
		tb.setWidget(0, 0, browseTrip);
		tb.setWidget(1, 0, browseTrips);
		tb.setWidget(2, 0, viewTrip);
		tb.setWidget(3, 0, editTrip);
		
		tb.getRowFormatter().setStyleName(0, "menuItem");
		tb.getRowFormatter().setStyleName(1, "menuItem");
		tb.getRowFormatter().setStyleName(2, "menuItem");
		tb.getRowFormatter().setStyleName(3, "menuItem");
		
		return tb;
	}

	private Widget createTripsMenu() {
		FlexTable tb = new FlexTable();
		
		Anchor browseOpenTrips = new Anchor("Offene Fahrten");
		Anchor browsePersonalOpenTrips = new Anchor(
				"Persönliche offene Fahrten");
		Anchor stoprowing = new Anchor("Fahrten stoppen");
		tb.setWidget(0, 0, browseOpenTrips);
		tb.setWidget(1, 0, browsePersonalOpenTrips);
		tb.setWidget(2, 0, stoprowing);

		tb.getRowFormatter().setStyleName(0, "menuItem");
		tb.getRowFormatter().setStyleName(1, "menuItem");
		tb.getRowFormatter().setStyleName(2, "menuItem");
		return tb;
	}

	private Widget createStatistikMenu() {
		FlexTable tb = new FlexTable();
		
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
		tb.setWidget(0, 0, statisticsMain);
		tb.setWidget(1, 0, boatdamagesYear);
		tb.setWidget(2, 0, clubactivityMonth);
		tb.setWidget(3, 0, clubactivityWeekday);
		tb.setWidget(4, 0, highscoreYear);
		tb.setWidget(5, 0, highscoreMonth);
		tb.setWidget(6, 0, highscoreBoats);
		tb.setWidget(7, 0, personalYear);
		tb.setWidget(8, 0, personalMonth);
		tb.setWidget(9, 0, popularRoutes);
		
		tb.getRowFormatter().setStyleName(0, "menuItem");
		tb.getRowFormatter().setStyleName(1, "menuItem");
		tb.getRowFormatter().setStyleName(2, "menuItem");
		tb.getRowFormatter().setStyleName(3, "menuItem");
		tb.getRowFormatter().setStyleName(4, "menuItem");
		tb.getRowFormatter().setStyleName(5, "menuItem");
		tb.getRowFormatter().setStyleName(6, "menuItem");
		tb.getRowFormatter().setStyleName(7, "menuItem");
		tb.getRowFormatter().setStyleName(8, "menuItem");
		tb.getRowFormatter().setStyleName(9, "menuItem");

		return tb;
	}

	private Widget createProfilMenu() {
		FlexTable tb = new FlexTable();
		Anchor browseRowedRoutes = new Anchor("Meine geruderten Routen");
		Anchor changePassword = new Anchor("Passwort ändern");
		Anchor requestNewPassword = new Anchor("Neues Passwort anfordern");
		Anchor searchProfile = new Anchor("Profil suchen");
		Anchor viewProfile = new Anchor("Profil anzeigen");
		tb.setWidget(0, 0, viewProfile);
		tb.setWidget(1, 0, searchProfile);
		tb.setWidget(2, 0, browseRowedRoutes);
		tb.setWidget(3, 0, changePassword);
		tb.setWidget(4, 0, requestNewPassword);
		
		tb.getRowFormatter().setStyleName(0, "menuItem");
		tb.getRowFormatter().setStyleName(1, "menuItem");
		tb.getRowFormatter().setStyleName(2, "menuItem");
		tb.getRowFormatter().setStyleName(3, "menuItem");
		tb.getRowFormatter().setStyleName(4, "menuItem");
		return tb;
	}

	@Override
	public HasClickHandlers getListBoatButton() {
		return listBoats;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
