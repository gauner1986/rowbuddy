package de.rowbuddy.client.boat;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.ServerRequestHandler;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.events.EditDamageEvent;
import de.rowbuddy.client.events.ListDamageEvent;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.entities.BoatDamage;

public class DetailDamagePresenter implements Presenter {

	public interface Display {
		void setBoot(String name);

		void setDescription(String description);

		void setAdditionalInformation(String information);

		HasValue<Boolean> getFixed();

		HasClickHandlers getEditButton();

		HasClickHandlers getCancelButton();

		Widget asWidget();
	}

	private Long id;
	private Display view;
	private BoatRemoteServiceAsync service;
	private BoatDamage damage;
	private EventBus eventBus;

	private Logger logger = Logger.getLogger(DetailDamagePresenter.class
			.getName());

	public DetailDamagePresenter(Long id, Display view,
			BoatRemoteServiceAsync service, EventBus eventBus) {
		this.id = id;
		this.view = view;
		this.service = service;
		this.eventBus = eventBus;
	}

	@Override
	public void start(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		bind();
		fetchDamage();
	}

	private void bind() {
		view.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListDamageEvent());
			}
		});

		view.getEditButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new EditDamageEvent(id));
			}
		});
	}

	private void fetchDamage() {
		
		service.getDamage(id, new ServerRequestHandler<BoatDamage>(eventBus, "Schaden-Details anzeigen", null, new ListDamageEvent()) {
			@Override
			public void onSuccess(BoatDamage arg0) {
				damage = arg0;
				view.setBoot(damage.getBoat().getName());
				view.setDescription(damage.getDamageDescription());
				view.setAdditionalInformation(damage.getAdditionalInformation());
				view.getFixed().setValue(damage.isFixed());
			}
		});
	}
}
