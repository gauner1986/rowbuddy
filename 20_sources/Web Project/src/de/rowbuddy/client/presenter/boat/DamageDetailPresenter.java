package de.rowbuddy.client.presenter.boat;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.events.EditBoatDamageEvent;
import de.rowbuddy.client.events.ListDamageEvent;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.entities.BoatDamage;

public class DamageDetailPresenter implements Presenter {

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
	private SimpleEventBus eventBus;

	private Logger logger = Logger.getLogger(DamageDetailPresenter.class
			.getName());

	public DamageDetailPresenter(Long id, Display view,
			BoatRemoteServiceAsync service, SimpleEventBus eventBus) {
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
				eventBus.fireEvent(new EditBoatDamageEvent(id));
			}
		});
	}

	private void fetchDamage() {
		service.getDamage(id, new AsyncCallback<BoatDamage>() {

			@Override
			public void onSuccess(BoatDamage arg0) {
				damage = arg0;
				view.setBoot(damage.getBoat().getName());
				view.setDescription(damage.getDamageDescription());
				view.setAdditionalInformation(damage.getAdditionalInformation());
				view.getFixed().setValue(damage.isFixed());
			}

			@Override
			public void onFailure(Throwable arg0) {
				logger.severe(arg0.getMessage());
			}
		});
	}
}
