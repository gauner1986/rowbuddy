package de.rowbuddy.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.HistoryConstants;
import de.rowbuddy.client.events.ListBoatEvent;

public class MenuPresenter implements Presenter, HistoryConstants{
	
	public interface MenuDisplay {
		public HasClickHandlers getListBoatButton();
		public Widget asWidget();
	}
	private MenuDisplay view;
	private HasWidgets container;
	private SimpleEventBus eventBus;
	
	public MenuPresenter(MenuDisplay view, SimpleEventBus eventBus) {
		this.view = view;
		this.eventBus = eventBus;
	}
	
	@Override
	public void start(HasWidgets container) {
		bind();
		this.container = container;
		this.container.clear();
		this.container.add(view.asWidget());
	}
	
	private void bind(){
		view.getListBoatButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListBoatEvent());
			}
		});
		
	}

}
