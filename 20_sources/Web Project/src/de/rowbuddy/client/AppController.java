package de.rowbuddy.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.events.AbstractEvent;
import de.rowbuddy.client.events.AddBoatPresenterChanger;
import de.rowbuddy.client.events.AddDamagePresenterChanger;
import de.rowbuddy.client.events.AddRoutePresenterChanger;
import de.rowbuddy.client.events.DetailsBoatPresenterChanger;
import de.rowbuddy.client.events.DetailsDamagePresenterChanger;
import de.rowbuddy.client.events.DetailsMemberPresenterChanger;
import de.rowbuddy.client.events.DetailsRoutePresenterChanger;
import de.rowbuddy.client.events.EditBoatPresenterChanger;
import de.rowbuddy.client.events.EditDamagePresenterChanger;
import de.rowbuddy.client.events.EditRoutPresenterChanger;
import de.rowbuddy.client.events.ImportMembersPresenterChanger;
import de.rowbuddy.client.events.ListBoatsEvent;
import de.rowbuddy.client.events.ListBoatsPresenterChanger;
import de.rowbuddy.client.events.ListDamagePresenterChanger;
import de.rowbuddy.client.events.ListMembersPresenterChanger;
import de.rowbuddy.client.events.ListPersonalOpenTripsPresenterChanger;
import de.rowbuddy.client.events.ListPersonalTripsPresenterChanger;
import de.rowbuddy.client.events.ListRoutesPresenterChanger;
import de.rowbuddy.client.events.PresenterChanger;
import de.rowbuddy.client.events.PresenterChanger.EventListener;
import de.rowbuddy.client.presenter.MenuPresenter;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.StatusMessagePresenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;
import de.rowbuddy.client.services.MemberRemoteServiceAsync;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;
import de.rowbuddy.client.views.MenuView;
import de.rowbuddy.client.views.MessageView;

public class AppController implements Presenter, EventListener {
	private SimpleEventBus eventBus;
	private BoatRemoteServiceAsync boatService;
	private RouteRemoteServiceAsync routeService;
	private LogbookRemoteServiceAsync logbookService;
	private MemberRemoteServiceAsync memberService;
	private HasWidgets container;
	private StatusMessagePresenter statusPresenter;
	private Presenter menuPresenter;
	private MemberDTO sessionMember;
	private List<PresenterChanger> eventHandlers = new LinkedList<PresenterChanger>();

	public AppController(SimpleEventBus eventBus, HasWidgets messageContainer,
			HasWidgets menuPanel, MemberDTO sessionMember) {
		this.sessionMember = sessionMember;
		this.boatService = ServiceHolderFactory.getBoatService();
		this.routeService = ServiceHolderFactory.getRouteService();
		this.logbookService = ServiceHolderFactory.getLogbookService();
		this.memberService = ServiceHolderFactory.getMemberService();
		this.eventBus = eventBus;
		this.statusPresenter = new StatusMessagePresenter(new MessageView(),
				eventBus);
		statusPresenter.start(messageContainer);
		menuPresenter = new MenuPresenter(new MenuView(), eventBus,
				sessionMember);
		menuPresenter.start(menuPanel);
	}

	private void bindBoatEventHandlers() {
		eventHandlers.add(new ListBoatsPresenterChanger(eventBus, container,
				boatService, sessionMember));
		eventHandlers.add(new DetailsBoatPresenterChanger(eventBus, container,
				boatService, sessionMember));
		eventHandlers.add(new AddBoatPresenterChanger(container, eventBus,
				boatService, sessionMember));
		eventHandlers.add(new AddDamagePresenterChanger(container, eventBus,
				boatService, sessionMember));
		eventHandlers.add(new DetailsDamagePresenterChanger(container,
				eventBus, boatService, sessionMember));
		eventHandlers.add(new EditDamagePresenterChanger(container, eventBus,
				boatService, sessionMember));

		eventHandlers.add(new EditBoatPresenterChanger(container, eventBus,
				boatService, sessionMember));

		eventHandlers.add(new ListDamagePresenterChanger(container, eventBus,
				boatService, sessionMember));
	}

	private void bindRoutEventHandlers() {
		eventHandlers.add(new DetailsRoutePresenterChanger(eventBus, container,
				routeService, sessionMember));
		eventHandlers.add(new AddRoutePresenterChanger(container, eventBus,
				routeService, sessionMember));
		eventHandlers.add(new ListRoutesPresenterChanger(container, eventBus,
				routeService, sessionMember));
		eventHandlers.add(new EditRoutPresenterChanger(container, eventBus,
				routeService, sessionMember));
	}

	private void bindLogbookEventHandlers() {

		eventHandlers.add(new ListPersonalTripsPresenterChanger(container,
				eventBus, logbookService, sessionMember));
		eventHandlers.add(new ListPersonalOpenTripsPresenterChanger(container,
				eventBus, logbookService, sessionMember));

	}

	private void bindMemberEventHandlers() {
		eventHandlers.add(new ListMembersPresenterChanger(memberService,
				container, eventBus, sessionMember));
		eventHandlers.add(new ImportMembersPresenterChanger(container,
				eventBus, memberService, sessionMember));
		eventHandlers.add(new DetailsMemberPresenterChanger(container,
				eventBus, memberService, sessionMember));
	}

	@Override
	public void start(HasWidgets container) {
		this.container = container;

		bindBoatEventHandlers();
		bindLogbookEventHandlers();
		bindRoutEventHandlers();
		bindMemberEventHandlers();

		// register fot presenter changes
		for (PresenterChanger eventHandler : eventHandlers) {
			eventHandler.addObserver(this);
		}

		if (History.getToken().equals("")) {
			eventBus.fireEvent(new ListBoatsEvent());
			// TODO welcome page
		} else {
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void eventProcessed(AbstractEvent<?> event) {
		statusPresenter.clear();
	}
}
