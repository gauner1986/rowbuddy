package de.rowbuddy.client.presenter.boat;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.views.BoatSuggestOracle;

public class AddDamagePresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		HasValue<String> getDescription();

		HasValue<String> getAddInformation();

		HasKeyPressHandlers getBoatField();

		HasValue<String> getBoatName();

		void setAlternatives(List<String> alternatives);

		HasClickHandlers getAddButton();

		HasClickHandlers getCancelButton();

		void setOracle(SuggestOracle oracle);

	}

	private Display view;
	private BoatRemoteServiceAsync service;
	private SimpleEventBus eventBus;
	private BoatSuggestOracle oracle;
	private Logger logger = Logger
			.getLogger(AddDamagePresenter.class.getName());

	public AddDamagePresenter(BoatRemoteServiceAsync service,
			SimpleEventBus eventBus, Display view) {
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
				try {
					logger.info(oracle.getSuggestion().getName());
				} catch (IllegalArgumentException ex) {
					logger.info(ex.getMessage());
				}
			}
		});

	}
}
