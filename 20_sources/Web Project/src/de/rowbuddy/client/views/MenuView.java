package de.rowbuddy.client.views;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

import de.rowbuddy.client.presenter.MenuPresenter.MenuDisplay;
import de.rowbuddy.client.views.items.MenuItem;

public class MenuView extends Composite implements MenuDisplay {

	// TODO: Neue Fahrt, Nachtrag

	// private Images images = (Images) GWT.create(Images.class);
	// private Anchor browseBoats = new Anchor("Bootsübersicht");
	// private Anchor listPersonalTrips = new Anchor("Meine Fahrten");
	// private Anchor listPersonalOpenTrips = new
	// Anchor("Meine offenen Fahrten");
	// private Anchor browseDamages = new Anchor("Schäden anzeigen");
	// private Anchor boatdamagesYear = new Anchor("Bootsschäden");
	private DecoratedStackPanel menuPanel = new DecoratedStackPanel();
	// private HorizontalPanel route = getHeaderString("Route", images.map());
	// private Anchor logDamageBoat = new Anchor("Schaden registrieren");
	// private Anchor listMembers = new Anchor("Mitglieder anzeigen");
	// private Anchor importMembers = new Anchor("Mitglieder importieren");

	private List<MenuItem> menuItems;

	public MenuView() {

		// initWidget(stackPanel);
		VerticalPanel verticalPanel = new VerticalPanel();

		// menuPanel.add(createViewTripMenu(),
		// getHeaderString("Fahrtenbuch", images.logBook()).getElement()
		// .getString(), true);
		// menuPanel.add(createProfilMenu(),
		// getHeaderString("Profil", images.profil()).getElement()
		// .getString(), true);
		// menuPanel.add(createStatistikMenu(),
		// getHeaderString("Statistiken", images.statistics())
		// .getElement().getString(), true);
		// menuPanel.add(createRouteMenu(), route.getElement().getString(),
		// true);
		// menuPanel.add(
		// createMemberControl(),
		// getHeaderString("Mitgliederverwaltung",
		// images.member()).getElement()
		// .getString(), true);
		// menuPanel.add(createBoatMenu(), getHeaderString("Boote",
		// images.boat())
		// .getElement().getString(), true);
		//
		// menuPanel.addHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent arg0) {
		// int index = menuPanel.getSelectedIndex();
		// if (index == 3) {
		// NativeEvent event = Document.get().createClickEvent(0, 0,
		// 0, 0, 0, false, false, false, false);
		// ClickEvent.fireNativeEvent(event, route);
		// }
		// }
		// }, ClickEvent.getType());

		verticalPanel.add(menuPanel);
		initWidget(verticalPanel);
	}

	private void renderMenuItems() {
		for (final MenuItem m : menuItems) {
			final FlexTable tb1 = new FlexTable();
			for (int i = 0; i < m.getSubItems().size(); i++) {
				final int h = i;
				final Anchor toAdd = new Anchor(m.getSubItems().get(i).getTitle());
				tb1.setWidget(i, 0, toAdd);
				tb1.getRowFormatter().setStyleName(i, "menuItem");

				toAdd.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent arg0) {
						m.menuSubItemClicked(m.getSubItems().get(h));
					}
				});
			}
			if (m.getSubItems().size() == 0 && m.getAssociatedEvent() != null) { // if an item has no children and needs to be clickable
				menuPanel.addHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent arg0) {
						if(menuPanel.getSelectedIndex() == m.getId()){ // ID MUST equivalent to the order number it has been added within the menuPanel
							NativeEvent event = Document.get().createClickEvent(0, 0, 0, 0, 0, false, false, false, false);
							ClickEvent.fireNativeEvent(event, tb1);
							m.menuItemClicked(m);
						}
					}
				},ClickEvent.getType());
			}
			menuPanel.add(tb1, getHeaderString(m.getTitle(), m.getImage()).getElement().getString(), true);
		}
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

	// private Widget createBoatMenu() {
	// Anchor browseReservations = new Anchor("Reservierungen anzeigen");
	//
	// final FlexTable tb1 = new FlexTable();
	// tb1.setWidget(0, 0, browseBoats);
	// tb1.setWidget(1, 0, browseReservations);
	// tb1.setWidget(2, 0, logDamageBoat);
	// tb1.setWidget(3, 0, browseDamages);
	// tb1.getRowFormatter().setStyleName(3, "menuItem");
	// tb1.getRowFormatter().setStyleName(0, "menuItem");
	// tb1.getRowFormatter().setStyleName(1, "menuItem");
	// tb1.getRowFormatter().setStyleName(2, "menuItem");
	//
	// return tb1;
	// }
	//
	// private Widget createViewTripMenu() {
	// FlexTable tb = new FlexTable();
	// Anchor browseTrips = new Anchor("Fahrtenübersicht");
	// Anchor browseOpenTrips = new Anchor("Offene Fahrten");
	//
	// tb.setWidget(0, 0, listPersonalTrips);
	// tb.setWidget(1, 0, browseTrips);
	// tb.setWidget(2, 0, browseOpenTrips);
	// tb.setWidget(3, 0, listPersonalOpenTrips);
	//
	// tb.getRowFormatter().setStyleName(0, "menuItem");
	// tb.getRowFormatter().setStyleName(1, "menuItem");
	// tb.getRowFormatter().setStyleName(2, "menuItem");
	//
	// return tb;
	// }
	//
	// private Widget createStatistikMenu() {
	// final FlexTable tb = new FlexTable();
	//
	// Anchor clubactivityMonth = new Anchor("Aktivität - Monatsstatistik");
	// Anchor clubactivityWeekday = new Anchor("Aktivität - Tagesstatistik");
	// Anchor highscoreBoats = new Anchor("Boote - Jahresstatistik");
	// Anchor highscoreMonth = new Anchor("Monatsstatistik");
	// Anchor highscoreYear = new Anchor("Jahresstatistik");
	// Anchor popularRoutes = new Anchor("Beliebteste Routen");
	//
	// tb.setWidget(0, 0, clubactivityMonth);
	// tb.setWidget(1, 0, clubactivityWeekday);
	// tb.setWidget(2, 0, highscoreYear);
	// tb.setWidget(3, 0, highscoreMonth);
	// tb.setWidget(4, 0, highscoreBoats);
	// tb.setWidget(5, 0, popularRoutes);
	// tb.setWidget(6, 0, boatdamagesYear);
	// tb.getRowFormatter().setStyleName(6, "menuItem");
	// tb.getRowFormatter().setStyleName(0, "menuItem");
	// tb.getRowFormatter().setStyleName(1, "menuItem");
	// tb.getRowFormatter().setStyleName(2, "menuItem");
	// tb.getRowFormatter().setStyleName(3, "menuItem");
	// tb.getRowFormatter().setStyleName(4, "menuItem");
	// tb.getRowFormatter().setStyleName(5, "menuItem");
	//
	// return tb;
	// }
	//
	// private Widget createProfilMenu() {
	// FlexTable tb = new FlexTable();
	// Anchor browseRowedRoutes = new Anchor("Meine geruderten Routen");
	// Anchor changePassword = new Anchor("Passwort ändern");
	// Anchor searchProfile = new Anchor("Profil suchen");
	// Anchor viewProfile = new Anchor("Profil anzeigen");
	// tb.setWidget(0, 0, viewProfile);
	// tb.setWidget(1, 0, searchProfile);
	// tb.setWidget(2, 0, browseRowedRoutes);
	// tb.setWidget(3, 0, changePassword);
	//
	// tb.getRowFormatter().setStyleName(0, "menuItem");
	// tb.getRowFormatter().setStyleName(1, "menuItem");
	// tb.getRowFormatter().setStyleName(2, "menuItem");
	// tb.getRowFormatter().setStyleName(3, "menuItem");
	// return tb;
	// }
	//
	// private Widget createRouteMenu() {
	// FlexTable tb = new FlexTable();
	// // Anchor browseRoutes = new Anchor("Routen anzeigen");
	// //
	// // tb.setWidget(0, 0, browseRoutes);
	// //
	// // tb.getRowFormatter().setStyleName(0, "menuItem");
	//
	// return tb;
	// }
	//
	// private Widget createMemberControl() {
	// FlexTable tb = new FlexTable();
	//
	// tb.setWidget(0, 0, listMembers);
	// tb.setWidget(1, 0, importMembers);
	//
	// tb.getRowFormatter().setStyleName(0, "menuItem");
	// tb.getRowFormatter().setStyleName(1, "menuItem");
	//
	// return tb;
	// }
	//
	// @Override
	// public HasClickHandlers getListBoatButton() {
	// return browseBoats;
	// }

	@Override
	public Widget asWidget() {
		return this;
	}

	// @Override
	// public Panel getRoutes() {
	// return route;
	// }
	//
	// @Override
	// public HasClickHandlers getListPersonalTripsButton() {
	// return listPersonalTrips;
	// }
	//
	// @Override
	// public HasClickHandlers getListPersonalOpenTripsButton() {
	// return listPersonalOpenTrips;
	// }
	//
	// @Override
	// public HasClickHandlers getListDamageButton() {
	// return browseDamages;
	// }
	//
	// @Override
	// public HasClickHandlers getAddDamage() {
	// return logDamageBoat;
	// }
	//
	// @Override
	// public HasClickHandlers getListMembers() {
	// return listMembers;
	// }
	//
	// @Override
	// public HasClickHandlers getImportMembers() {
	// return importMembers;
	// }

	@Override
	public void setMenuItems(List<de.rowbuddy.client.views.items.MenuItem> menuItems) {
		this.menuItems = menuItems;
		renderMenuItems();
	}

	@Override
	public List<MenuItem> getMenu() {
		return menuItems;
	}
}
