package de.rowbuddy.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.HistoryConstants;
import de.rowbuddy.client.MenuDisplay;

public class MenuPresenter implements Presenter, HistoryConstants{

	private MenuDisplay view;
	private HasWidgets container;
	
	public MenuPresenter(MenuDisplay view) {
		this.view = view;
	}
	
	@Override
	public void start(HasWidgets container) {
		this.container = container;
		container.clear();
		container.add(view.asWidget());
	}
	
	private void bind(){
		view.getListBoatButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				History.newItem(LIST_BOATS);
			}
		});
		
	}

}
