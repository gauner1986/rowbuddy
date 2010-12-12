package de.rowbuddy.client.presenter;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.events.AddDamageEvent;
import de.rowbuddy.client.events.ListBoatsEvent;
import de.rowbuddy.client.events.ListDamageEvent;
import de.rowbuddy.client.events.ListPersonalTripsEvent;
import de.rowbuddy.client.events.ListRoutesEvent;

public class MenuPresenter implements Presenter {

	public interface MenuDisplay {
		public HasClickHandlers getListBoatButton();

		public HasClickHandlers getListPersonalTripsButton();

		public HasClickHandlers getListDamageButton();

		public HasClickHandlers getAddDamage();

		public Panel getRoutes();

		public Widget asWidget();
	}

	private MenuDisplay view;
	private HasWidgets container;
	private SimpleEventBus eventBus;
	private static Logger logger = Logger.getLogger(MenuPresenter.class
			.getName());

	public MenuPresenter(MenuDisplay view, SimpleEventBus eventBus) {
		this.view = view;
		this.eventBus = eventBus;
	}

	@Override
	public void start(HasWidgets container) {
		bind();
		this.container = container;
		this.container.clear();
		this.container.add(view.asWidget());
	}

	private void bind() {
		view.getListBoatButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListBoatsEvent());
			}
		});

		view.getRoutes().addHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				logger.info("click ListroutesEvent");
				eventBus.fireEvent(new ListRoutesEvent());
			}
		}, ClickEvent.getType());

		view.getListPersonalTripsButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListPersonalTripsEvent());
			}
		});

		view.getListDamageButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListDamageEvent());
			}
		});

		view.getAddDamage().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new AddDamageEvent());
			}
		});
	}
}
