package de.rowbuddy.client.presenter.boat;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.client.events.ListBoatDamageEvent;
import de.rowbuddy.client.events.StatusMessageEvent;
import de.rowbuddy.client.model.StatusMessage;
import de.rowbuddy.client.model.StatusMessage.Status;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.exceptions.RowBuddyException;

public class AddDamagePresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		HasValue<String> getDescription();

		HasValue<String> getAddInformation();

		HasClickHandlers getAddButton();

		HasClickHandlers getCancelButton();

		void setOracle(SuggestOracle oracle);

		HasValue<String> getBootName();

	}

	private Display view;
	private BoatRemoteServiceAsync service;
	private EventBus eventBus;
	private BoatSuggestOracle oracle;

	public AddDamagePresenter(BoatRemoteServiceAsync service,
			EventBus eventBus, Display view) {
		this.view = view;
		this.service = service;
		this.eventBus = eventBus;
		oracle = new BoatSuggestOracle(service);
	}

	@Override
	public void start(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		bind();
	}

	private void bind() {
		view.setOracle(oracle);

		view.getAddButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				BoatDTO boat = oracle.getSuggestion(view.getBootName()
						.getValue());
				if (boat == null) {
					StatusMessage message = new StatusMessage(false);
					message.setStatus(Status.NEGATIVE);
					message.setMessage("Es muss ein Boot ausgewählt sein");
					eventBus.fireEvent(new StatusMessageEvent(message));
				} else {
					BoatDamage damage = new BoatDamage();
					try {
						damage.setAdditionalInformation(view
								.getAddInformation().getValue());
						damage.setDamageDescription(view.getDescription()
								.getValue());
						damage.setFixed(false);
						damage.setLogDate(new Date(System.currentTimeMillis()));

						service.addDamage(damage, boat.getId(),
								new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable arg0) {
										StatusMessage message = new StatusMessage(
												false);
										message.setStatus(Status.NEGATIVE);
										message.setMessage(arg0.getMessage());
										eventBus.fireEvent(new StatusMessageEvent(
												message));
									}

									@Override
									public void onSuccess(Void arg0) {
										StatusMessage message = new StatusMessage(
												false);
										message.setStatus(Status.POSITIVE);
										message.setMessage("Schaden erfolgreich hinzugefügt");
										eventBus.fireEvent(new ListBoatDamageEvent());
										eventBus.fireEvent(new StatusMessageEvent(
												message));
									}
								});
					} catch (RowBuddyException ex) {
						StatusMessage message = new StatusMessage(false);
						message.setStatus(Status.NEGATIVE);
						message.setMessage(ex.getMessage());
						eventBus.fireEvent(new StatusMessageEvent(message));
					}
				}
			}
		});

		view.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListBoatDamageEvent());
			}
		});

	}
}
