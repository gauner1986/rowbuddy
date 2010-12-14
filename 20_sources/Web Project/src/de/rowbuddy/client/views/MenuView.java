package de.rowbuddy.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.SessionHolder;
import de.rowbuddy.client.images.Images;
import de.rowbuddy.client.presenter.MenuPresenter.MenuDisplay;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;

public class MenuView extends Composite implements MenuDisplay {

	// TODO: Neue Fahrt, Nachtrag

	private Images images = (Images) GWT.create(Images.class);
	private Anchor browseBoats = new Anchor("Bootsübersicht");
	private Anchor listPersonalTrips = new Anchor("Meine Fahrten");
	private Anchor listPersonalOpenTrips = new Anchor("Meine offenen Fahrten");
	private Anchor browseDamages = new Anchor("Schäden anzeigen");
	private DecoratedStackPanel menuPanel = new DecoratedStackPanel();
	private HorizontalPanel route = getHeaderString("Route", images.map());
	private Anchor logDamageBoat = new Anchor("Schaden registrieren");

	public MenuView() {

		// initWidget(stackPanel);
		VerticalPanel verticalPanel = new VerticalPanel();

		menuPanel.add(createViewTripMenu(),
				getHeaderString("Fahrtenbuch", images.logBook()).getElement()
						.getString(), true);
		menuPanel.add(createProfilMenu(),
				getHeaderString("Profil", images.profil()).getElement()
						.getString(), true);
		menuPanel.add(createStatistikMenu(),
				getHeaderString("Statistiken", images.statistics())
						.getElement().getString(), true);
		menuPanel.add(createRouteMenu(), route.getElement().getString(), true);
		SessionHolder.getSessionManager().getMember(new AsyncCallback<Member>() {

			@Override
			public void onSuccess(Member arg0) {
				if (arg0.isInRole(Role.RoleName.ADMIN)) {
					menuPanel.add(createMemberControl(),
						getHeaderString("Mitgliederverwaltung", images.member())
							.getElement().getString(), true);
				} else {
					browseDamages.setVisible(false);
				}
			}

			@Override
			public void onFailure(Throwable arg0) {}
		});
		menuPanel.add(createBoatMenu(), getHeaderString("Boote", images.boat())
				.getElement().getString(), true);

		menuPanel.addHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				int index = menuPanel.getSelectedIndex();
				if (index == 3) {
					NativeEvent event = Document.get().createClickEvent(0, 0,
							0, 0, 0, false, false, false, false);
					ClickEvent.fireNativeEvent(event, route);
				}
			}
		}, ClickEvent.getType());

		verticalPanel.add(menuPanel);
		initWidget(verticalPanel);
	}

	private HorizontalPanel getHeaderString(String text, ImageResource image) {
		// Add the image and text to a horizontal panel
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setSpacing(0);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel.add(new Image(image));
		HTML headerText = new HTML(text);
		headerText.setStyleName("cw-StackPanelHeader");
		hPanel.add(headerText);

		// Return the HTML string for the panel
		return hPanel;
	}

	private Widget createBoatMenu() {
		Anchor browseReservations = new Anchor("Reservierungen anzeigen");

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
		Anchor browseOpenTrips = new Anchor("Offene Fahrten");

		tb.setWidget(0, 0, listPersonalTrips);
		tb.setWidget(1, 0, browseTrips);
		tb.setWidget(2, 0, browseOpenTrips);
		tb.setWidget(3, 0, listPersonalOpenTrips);

		tb.getRowFormatter().setStyleName(0, "menuItem");
		tb.getRowFormatter().setStyleName(1, "menuItem");
		tb.getRowFormatter().setStyleName(2, "menuItem");

		return tb;
	}

	private Widget createStatistikMenu() {
		FlexTable tb = new FlexTable();

		final Anchor boatdamagesYear = new Anchor("Bootsschäden");
		Anchor clubactivityMonth = new Anchor("Aktivität - Monatsstatistik");
		Anchor clubactivityWeekday = new Anchor("Aktivität - Tagesstatistik");
		Anchor highscoreBoats = new Anchor("Boote - Jahresstatistik");
		Anchor highscoreMonth = new Anchor("Monatsstatistik");
		Anchor highscoreYear = new Anchor("Jahresstatistik");
		Anchor popularRoutes = new Anchor("Beliebteste Routen");

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

		SessionHolder.getSessionManager().getMember(new AsyncCallback<Member>() {

			@Override
			public void onSuccess(Member arg0) {
				if (!arg0.isInRole(Role.RoleName.ADMIN)) {
					boatdamagesYear.setVisible(false);
				}
			}

			@Override
			public void onFailure(Throwable arg0) {}
		});

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
		// Anchor browseRoutes = new Anchor("Routen anzeigen");
		//
		// tb.setWidget(0, 0, browseRoutes);
		//
		// tb.getRowFormatter().setStyleName(0, "menuItem");

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

	@Override
	public Panel getRoutes() {
		return route;
	}

	@Override
	public HasClickHandlers getListPersonalTripsButton() {
		return listPersonalTrips;
	}

	@Override
	public HasClickHandlers getListPersonalOpenTripsButton() {
		return listPersonalOpenTrips;
	}

	@Override
	public HasClickHandlers getListDamageButton() {
		return browseDamages;
	}

	@Override
	public HasClickHandlers getAddDamage() {
		return logDamageBoat;
	}
}
