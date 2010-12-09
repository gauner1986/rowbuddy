package de.rowbuddy.client.presenter.boat;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;

public class AddDamagePresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		HasValue<String> getDescription();

		HasValue<String> getAddInformation();

		HasClickHandlers getAddButton();

		HasClickHandlers getCancelButton();

		void setOracle(SuggestOracle oracle);

		HasValueChangeHandlers<String> getSuggestionBox();

	}

	private Display view;
	private BoatRemoteServiceAsync service;
	private EventBus eventBus;
	private BoatSuggestOracle oracle;
	private Logger logger = Logger
			.getLogger(AddDamagePresenter.class.getName());

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
		view.getSuggestionBox().addValueChangeHandler(oracle);

		view.getAddButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				BoatDTO boat = oracle.getSuggestion();
				if (boat != null) {
					logger.info(boat.getName());
				} else {
					logger.info("Error");
				}
			}
		});

	}
}
