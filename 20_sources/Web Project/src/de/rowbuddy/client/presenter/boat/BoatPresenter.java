package de.rowbuddy.client.presenter.boat;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.events.AddBoatEvent;
import de.rowbuddy.client.events.DetailsBoatEvent;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.entities.Role.RoleName;

public class BoatPresenter implements Presenter {
	public interface Display {
		Widget asWidget();
		
		UIObject getUiAddButton();

		HasClickHandlers getAddButton();

		HasClickHandlers getTable();

		void setData(Collection<BoatDTO> boats);

		int getClickedRow(ClickEvent event);

	}

	private Display view;
	private BoatRemoteServiceAsync boatService;
	private EventBus eventBus;
	private List<BoatDTO> fetchedBoats;
	private MemberDTO sessionMember;
	private static Logger logger = Logger.getLogger(BoatPresenter.class
			.getName());

	public BoatPresenter(BoatRemoteServiceAsync boatService, Display view,
			EventBus eventBus, MemberDTO member) {
		this.sessionMember = member;
		this.view = view;
		this.boatService = boatService;
		this.eventBus = eventBus;
	}

	private void bind() {
		view.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new AddBoatEvent());
			}
		});

		view.getTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = view.getClickedRow(event);
				logger.info("Row " + selectedRow + " selected");
				if (selectedRow > 0) {
					Long id = fetchedBoats.get(selectedRow - 1).getId();

					logger.info("Fire EditBoatEvent id:" + id);
					eventBus.fireEvent(new DetailsBoatEvent(id));
				}
			}
		});
	}

	@Override
	public void start(HasWidgets container) {
		bind();
		setPermissions();
		container.clear();
		container.add(view.asWidget());
		fetchBoats();
	}
	
	private void setPermissions(){
		if(!sessionMember.isInRole(RoleName.ADMIN))
			view.getUiAddButton().setVisible(false);
	}

	private void fetchBoats() {
		boatService.getBoatOverview(new AsyncCallback<List<BoatDTO>>() {

			@Override
			public void onSuccess(List<BoatDTO> arg0) {
				fetchedBoats = arg0;
				view.setData(fetchedBoats);
				logger.info("Boats fetched");
			}

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("error");
			}
		});
	}

}
