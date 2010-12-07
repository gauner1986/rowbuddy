package de.rowbuddy.client.presenter.boat;

import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;

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

	}

	private Display view;
	private BoatRemoteServiceAsync service;
	private SimpleEventBus eventBus;

	public AddDamagePresenter(BoatRemoteServiceAsync service,
			SimpleEventBus eventBus, Display view) {
		this.view = view;
		this.service = service;
		this.eventBus = eventBus;
	}

	@Override
	public void start(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());

	}

	private void bind() {

	}

}
