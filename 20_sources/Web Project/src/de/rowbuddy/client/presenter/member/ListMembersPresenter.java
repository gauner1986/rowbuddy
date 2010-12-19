package de.rowbuddy.client.presenter.member;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.ServiceHolderFactory;
import de.rowbuddy.client.events.DetailsMemberEvent;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.MemberRemoteServiceAsync;

public class ListMembersPresenter implements Presenter {
	public interface Display {
		Widget asWidget();

		HasClickHandlers getTable();

		void setData(Collection<MemberDTO> members);

		int getClickedRow(ClickEvent event);
	}

	private Display view;
	private MemberRemoteServiceAsync memberService;
	private EventBus eventBus;
	private List<MemberDTO> fetchedMembers;
	private static Logger logger = Logger.getLogger(ListMembersPresenter.class
			.getName());

	public ListMembersPresenter(MemberRemoteServiceAsync memberService,
			Display view, EventBus eventBus) {
		this.view = view;
		this.memberService = memberService;
		this.eventBus = eventBus;
	}

	private void bind() {
		view.getTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				int row = view.getClickedRow(arg0);
				MemberDTO member = fetchedMembers.get(row - 1);
				logger.info("Row selcted: " + row);
				eventBus.fireEvent(new DetailsMemberEvent(member.getId()));
			}
		});
	}

	@Override
	public void start(HasWidgets container) {
		bind();
		container.clear();
		container.add(view.asWidget());
		fetchMembers();
	}

	private void fetchMembers() {
		memberService.getMembers(new AsyncCallback<List<MemberDTO>>() {

			@Override
			public void onSuccess(List<MemberDTO> arg0) {
				fetchedMembers = arg0;
				view.setData(fetchedMembers);
				logger.info("Members fetched");
			}

			@Override
			public void onFailure(Throwable arg0) {
				ServiceHolderFactory.handleSessionFailure(arg0);
				logger.severe(arg0.getMessage());
				// Window.alert("error");
			}
		});
	}

}
