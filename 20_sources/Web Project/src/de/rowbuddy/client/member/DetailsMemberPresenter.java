package de.rowbuddy.client.member;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.ServerCallHandler;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.events.ListMembersEvent;
import de.rowbuddy.client.services.MemberRemoteServiceAsync;
import de.rowbuddy.entities.Member;

public class DetailsMemberPresenter implements Presenter {

	public interface Display {
		void setMember(Member member);

		HasClickHandlers getCancelButton();

		Widget asWidget();
	}

	private Long id;
	private Display view;
	private MemberRemoteServiceAsync service;
	private Member member;
	private EventBus eventBus;

	private Logger logger = Logger.getLogger(DetailsMemberPresenter.class
			.getName());

	public DetailsMemberPresenter(Long id, Display view,
			MemberRemoteServiceAsync service, EventBus eventBus) {
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
		fetchMember();
	}

	private void bind() {
		view.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListMembersEvent());
			}
		});
	}

	private void fetchMember() {
		service.getMember(id, new ServerCallHandler<Member>(eventBus, "Member Details anzeigen", null, new ListMembersEvent()) {
			@Override
			public void onSuccess(Member arg0) {
				member = arg0;
				view.setMember(member);
			}
		});
	}
}
