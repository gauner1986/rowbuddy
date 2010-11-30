package de.rowbuddy.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.images.Images;
import de.rowbuddy.client.presenter.MenuPresenter.MenuDisplay;

public class MenuView extends Composite implements MenuDisplay {
	
	//TODO: Neue Fahrt, Nachtrag

	private Anchor browseBoats = new Anchor("Bootsübersicht");
	
	public MenuView() {
		Images images = (Images) GWT.create(Images.class);

		
		// initWidget(stackPanel);
		VerticalPanel verticalPanel = new VerticalPanel();
		DecoratedStackPanel menuPanel = new DecoratedStackPanel();

		menuPanel.add(createTripsMenu(), getHeaderString("Offene Fahrten", images.openTrips()), true);
		menuPanel.add(createProfilMenu(), getHeaderString("Profil", images.profil()), true);
		menuPanel.add(createViewTripMenu(), getHeaderString("Fahrtenbuch", images.logBook()), true);
		menuPanel.add(createStatistikMenu(), getHeaderString("Statistiken", images.statistics()), true);
		menuPanel.add(createRouteMenu(),getHeaderString("Route", images.map()), true);
		menuPanel.add(createBoatMenu(), getHeaderString("Boote", images.boat()), true);
		menuPanel.add(createMemberControl(), getHeaderString("Mitgliederverwaltung(admin)", images.member()), true);
		
		verticalPanel.add(menuPanel);
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
		Anchor browseReservations = new Anchor("Reservierungen anzeigen");
		Anchor browseDamages = new Anchor("Schäden anzeigen(admin)");
		Anchor logDamageBoat = new Anchor("Schaden registrieren");		

		FlexTable tb1 = new FlexTable();
		tb1.setWidget(0, 0, browseBoats);
		tb1.setWidget(1, 0, browseReservations);
		tb1.setWidget(2, 0, browseDamages);
		tb1.setWidget(3, 0, logDamageBoat);
		
		tb1.getRowFormatter().setStyleName(0, "menuItem");	
		tb1.getRowFormatter().setStyleName(1, "menuItem");
		tb1.getRowFormatter().setStyleName(2, "menuItem");
		tb1.getRowFormatter().setStyleName(3, "menuItem");
		
		
		return tb1;
	}

	private Widget createViewTripMenu() {
		FlexTable tb = new FlexTable();
		Anchor browseTrips = new Anchor("Fahrtenübersicht");
		
		tb.setWidget(0, 0, browseTrips);
		
		tb.getRowFormatter().setStyleName(0, "menuItem");
		
		return tb;
	}

	private Widget createTripsMenu() {
		FlexTable tb = new FlexTable();
		
		Anchor browseOpenTrips = new Anchor("Offene Fahrten");
		Anchor browsePersonalOpenTrips = new Anchor("Persönliche offene Fahrten");
		tb.setWidget(0, 0, browseOpenTrips);
		tb.setWidget(1, 0, browsePersonalOpenTrips);

		tb.getRowFormatter().setStyleName(0, "menuItem");
		tb.getRowFormatter().setStyleName(1, "menuItem");
		return tb;
	}

	private Widget createStatistikMenu() {
		FlexTable tb = new FlexTable();
		
		Anchor boatdamagesYear = new Anchor("Bootsschäden(admin)");
		Anchor clubactivityMonth = new Anchor("Aktivität - Monatsstatistik");
		Anchor clubactivityWeekday = new Anchor("Aktivität - Tagesstatistik");
		Anchor highscoreBoats = new Anchor("Boote - Jahresstatistik");
		Anchor highscoreMonth = new Anchor("Monatsstatistik");
		Anchor highscoreYear = new Anchor("Jahresstatistik");
		Anchor popularRoutes = new Anchor("Beleibteste Routen");
		tb.setWidget(0, 0, boatdamagesYear);
		tb.setWidget(1, 0, clubactivityMonth);
		tb.setWidget(2, 0, clubactivityWeekday);
		tb.setWidget(3, 0, highscoreYear);
		tb.setWidget(4, 0, highscoreMonth);
		tb.setWidget(5, 0, highscoreBoats);
		tb.setWidget(6, 0, popularRoutes);
		
		tb.getRowFormatter().setStyleName(0, "menuItem");
		tb.getRowFormatter().setStyleName(1, "menuItem");
		tb.getRowFormatter().setStyleName(2, "menuItem");
		tb.getRowFormatter().setStyleName(3, "menuItem");
		tb.getRowFormatter().setStyleName(4, "menuItem");
		tb.getRowFormatter().setStyleName(5, "menuItem");
		tb.getRowFormatter().setStyleName(6, "menuItem");

		return tb;
	}

	private Widget createProfilMenu() {
		FlexTable tb = new FlexTable();
		Anchor browseRowedRoutes = new Anchor("Meine geruderten Routen");
		Anchor changePassword = new Anchor("Passwort ändern");
		Anchor searchProfile = new Anchor("Profil suchen");
		Anchor viewProfile = new Anchor("Profil anzeigen");
		tb.setWidget(0, 0, viewProfile);
		tb.setWidget(1, 0, searchProfile);
		tb.setWidget(2, 0, browseRowedRoutes);
		tb.setWidget(3, 0, changePassword);
		
		tb.getRowFormatter().setStyleName(0, "menuItem");
		tb.getRowFormatter().setStyleName(1, "menuItem");
		tb.getRowFormatter().setStyleName(2, "menuItem");
		tb.getRowFormatter().setStyleName(3, "menuItem");
		return tb;
	}

	private Widget createRouteMenu() {
		FlexTable tb = new FlexTable();
		Anchor browseRoutes = new Anchor("Routen anzeigen");
		
		tb.setWidget(0, 0, browseRoutes);
		
		tb.getRowFormatter().setStyleName(0, "menuItem");
		
		return tb;
	}

	private Widget createMemberControl() {
		FlexTable tb = new FlexTable();
		Anchor browseMembers = new Anchor("Mitglieder anzeigen");
		Anchor importMembers = new Anchor("Mitglieder importieren");
		
		tb.setWidget(0, 0, browseMembers);
		tb.setWidget(1, 0, importMembers);
		
		tb.getRowFormatter().setStyleName(0, "menuItem");
		tb.getRowFormatter().setStyleName(1, "menuItem");
		
		return tb;
	}

	@Override
	public HasClickHandlers getListBoatButton() {
		return browseBoats;
	}

	@Override
	public Widget asWidget() {
		return this;
	}
}
