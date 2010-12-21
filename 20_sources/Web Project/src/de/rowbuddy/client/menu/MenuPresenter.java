package de.rowbuddy.client.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.events.AddDamageEvent;
import de.rowbuddy.client.events.ImportMembersEvent;
import de.rowbuddy.client.events.ListBoatsEvent;
import de.rowbuddy.client.events.ListDamageEvent;
import de.rowbuddy.client.events.ListMembersEvent;
import de.rowbuddy.client.events.ListPersonalOpenTripsEvent;
import de.rowbuddy.client.events.ListPersonalTripsEvent;
import de.rowbuddy.client.events.ListRoutesEvent;
import de.rowbuddy.client.events.LogRowedTripEvent;
import de.rowbuddy.client.events.MonthsStatisticEvent;
import de.rowbuddy.client.events.StartTripEvent;
import de.rowbuddy.client.menu.images.Images;
import de.rowbuddy.entities.Role.RoleName;

public class MenuPresenter implements Presenter {

	public interface MenuDisplay {

		public void setMenuItems(List<MenuItem> menuItems);
		
		public List<MenuItem> getMenu();

		public Widget asWidget();
	}

	private MenuDisplay view;
	private HasWidgets container;
	private SimpleEventBus eventBus;
	private static Logger logger = Logger.getLogger(MenuPresenter.class
			.getName());
	private MemberDTO sessionMember;
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();
	private Images images = (Images) GWT.create(Images.class);

	public MenuPresenter(MenuDisplay view, SimpleEventBus eventBus, MemberDTO sessionMember) {
		this.sessionMember = sessionMember;
		this.view = view;
		this.eventBus = eventBus;
	}
	
	private void createMenuItems(boolean admin){
		MenuItem fahrtenbuch = new MenuItem(0, "Fahrtenbuch", images.logBook(),null);
		fahrtenbuch.getSubItems().add(new MenuSubItem(0, "Nachtrag speichern", new LogRowedTripEvent()));
		fahrtenbuch.getSubItems().add(new MenuSubItem(1, "Neue Fahrt beginnen", new StartTripEvent()));
		fahrtenbuch.getSubItems().add(new MenuSubItem(2, "Meine Fahrten", new ListPersonalTripsEvent()));
		fahrtenbuch.getSubItems().add(new MenuSubItem(3, "Fahrtenübersicht", null));
		fahrtenbuch.getSubItems().add(new MenuSubItem(4, "Offene Fahrten", null));
		fahrtenbuch.getSubItems().add(new MenuSubItem(5, "Meine offenen Fahrten", new ListPersonalOpenTripsEvent()));
		
		MenuItem profil = new MenuItem(1, "Profil", images.profil(),null);
		profil.getSubItems().add(new MenuSubItem(0, "Meine geruderten Routen",null));
		profil.getSubItems().add(new MenuSubItem(1, "Passwort ändern",null));
		profil.getSubItems().add(new MenuSubItem(2, "Profil suchen",null));
		profil.getSubItems().add(new MenuSubItem(3, "Profil anzeigen",null));
		
		
		MenuItem statistiken = new MenuItem(2, "Statistiken", images.statistics(),null);
		statistiken.getSubItems().add(new MenuSubItem(0, "Aktivität - Monatsstatistik",null));
		statistiken.getSubItems().add(new MenuSubItem(1, "Aktivität - Tagesstatistik",null));
		statistiken.getSubItems().add(new MenuSubItem(2, "Boote - Jahresstatistik",null));
		statistiken.getSubItems().add(new MenuSubItem(3, "Monatsstatistik", new MonthsStatisticEvent()));
		statistiken.getSubItems().add(new MenuSubItem(4, "Jahresstatistik",null));
		statistiken.getSubItems().add(new MenuSubItem(5, "Beliebteste Routen",null));
		statistiken.getSubItems().add(new MenuSubItem(6, "Bootsschäden",null));
		
		MenuItem routen = new MenuItem(3, "Routen", images.map(),new ListRoutesEvent());
		
		
		MenuItem boote = new MenuItem(4, "Boote", images.boat(),null);
		boote.getSubItems().add(new MenuSubItem(0, "Bootsübersicht", new ListBoatsEvent()));
		boote.getSubItems().add(new MenuSubItem(1, "Reservierungen anzeigen",null));
		boote.getSubItems().add(new MenuSubItem(2, "Schaden registrieren",new AddDamageEvent()));
		boote.getSubItems().add(new MenuSubItem(3, "Schäden anzeigen",new ListDamageEvent()));
		
		menuItems.add(fahrtenbuch);
		menuItems.add(profil);
		menuItems.add(statistiken);
		menuItems.add(routen);
		menuItems.add(boote);
	
		if(admin){
			MenuItem mitgliederverwaltung = new MenuItem(5, "Mitgliederverwaltung", images.member(),null);
			mitgliederverwaltung.getSubItems().add(new MenuSubItem(0, "Mitglieder anzeigen",new ListMembersEvent()));
			mitgliederverwaltung.getSubItems().add(new MenuSubItem(1, "Mitglieder importieren",new ImportMembersEvent()));
			menuItems.add(mitgliederverwaltung);
		}
	}

	public void start(HasWidgets container) {
		createMenuItems(sessionMember.isInRole(RoleName.ADMIN));
		view.setMenuItems(menuItems);
		bind();
		this.container = container;
		this.container.clear();
		this.container.add(view.asWidget());
	}

	private void bind() {
		for(HasClickMenuHandlers handler : view.getMenu()){
			handler.addClickMenuHandler(new ClickMenuHandler() {
				
				@Override
				public void menuSubItemClicked(MenuSubItem clickedSubItem) {
					logger.info("menuSubItem: "+clickedSubItem.getTitle());
					if(clickedSubItem.getAssociatedEvent()!=null)
						eventBus.fireEvent(clickedSubItem.getAssociatedEvent());
				}
				
				@Override
				public void menuItemClicked(MenuItem clickedItem) {
					logger.info("menuItem: "+clickedItem.getTitle());
					if(clickedItem.getAssociatedEvent()!=null)
						eventBus.fireEvent(clickedItem.getAssociatedEvent());
				}
			});
		}
	}
}
