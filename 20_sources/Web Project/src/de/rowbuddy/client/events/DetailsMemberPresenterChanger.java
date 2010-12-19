package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.member.DetailsMemberPresenter;
import de.rowbuddy.client.services.MemberRemoteServiceAsync;
import de.rowbuddy.client.views.member.DetailsMemberView;

public class DetailsMemberPresenterChanger extends PresenterChanger {

	private final MemberRemoteServiceAsync memberService;

	public DetailsMemberPresenterChanger(HasWidgets targetWidget,
			EventBus eventBus, MemberRemoteServiceAsync memberService,
			MemberDTO member) {
		super(targetWidget, eventBus, member);
		this.memberService = memberService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		Long id = IdEvent.getIdFromHistoryItem(historyItem);
		return new DetailsMemberEvent(id);
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		DetailsMemberEvent e = (DetailsMemberEvent) event;
		return new DetailsMemberPresenter(e.getId(), new DetailsMemberView(),
				memberService, eventBus);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) DetailsMemberEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return DetailsMemberEvent.HISTORY_IDENTIFIER;
	}
}
