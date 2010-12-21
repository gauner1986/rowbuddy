package de.rowbuddy.client.boat;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.DamageDTO;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.ServiceHolderFactory;
import de.rowbuddy.client.events.DetailsDamageEvent;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;

public class ListDamagesPresenter implements Presenter {

	public interface Display {
		HasValueChangeHandlers<Boolean> historyEnabled();

		void addDamageRow(String name, String reporter, String date,
				boolean isOpen);

		void clear();

		Widget asWidget();

		int getClickedRow(ClickEvent event);

		HasClickHandlers getTable();
	}

	private Display view;
	private BoatRemoteServiceAsync service;
	private EventBus eventBus;
	private Logger logger = Logger.getLogger(ListDamagesPresenter.class
			.getName());
	private List<DamageDTO> fetchedDamages;
	private DateTimeFormat dtf;

	public ListDamagesPresenter(Display view, BoatRemoteServiceAsync service,
			EventBus eventBus) {
		this.view = view;
		this.service = service;
		this.eventBus = eventBus;
		dtf = DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM);
	}

	@Override
	public void start(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		bind();
		fetchDamages(false);
	}

	private void fetchDamages(boolean allDamages) {
		AsyncCallback<List<DamageDTO>> callback = new AsyncCallback<List<DamageDTO>>() {

			@Override
			public void onSuccess(List<DamageDTO> arg0) {
				fetchedDamages = arg0;
				view.clear();

				for (DamageDTO damage : fetchedDamages) {
					view.addDamageRow(damage.getBootName(), damage.getMember(),
							dtf.format(damage.getDate()), damage.isFixed());
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				ServiceHolderFactory.handleSessionFailure(arg0);
				logger.severe(arg0.getMessage());
			}
		};
		if (allDamages) {
			service.getAllDamages(callback);
		} else {
			service.getOpenDamages(callback);
		}
	}

	private void bind() {
		view.historyEnabled().addValueChangeHandler(
				new ValueChangeHandler<Boolean>() {

					@Override
					public void onValueChange(ValueChangeEvent<Boolean> arg0) {
						fetchDamages(arg0.getValue());
					}
				});

		view.getTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				int row = view.getClickedRow(arg0);
				DamageDTO damage = fetchedDamages.get(row - 1);
				logger.info("Row selcted: " + row);
				eventBus.fireEvent(new DetailsDamageEvent(damage.getId()));
			}
		});
	}

}
