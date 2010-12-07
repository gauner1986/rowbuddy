package de.rowbuddy.client.views;

import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.PageTitles;

public abstract class HeaderButtonView extends Composite{
	
	private DecoratorPanel decorator;
	private FlexTable contentTable;
	private HorizontalPanel hPanel;
	
	protected HeaderButtonView(String pageTitle){
		decorator = new DecoratorPanel();
		contentTable = new FlexTable();
		contentTable.setStylePrimaryName("contentTable");

		initWidget(decorator);
		decorator.add(contentTable);
		
		hPanel = new HorizontalPanel();
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);

		contentTable.setWidget(1, 0, hPanel);

		contentTable.setText(0, 0, PageTitles.BOAT_OVERVIEW);
		HTMLTable.RowFormatter rf = contentTable.getRowFormatter();
		rf.setStylePrimaryName(0, "pageHeadLine");
	}
	
	public void addButton(ButtonBase button){
		hPanel.add(button);
	}
	
	public void setContent(Widget contentWidget){
		contentTable.setWidget(2, 0, contentWidget);
	}

}
