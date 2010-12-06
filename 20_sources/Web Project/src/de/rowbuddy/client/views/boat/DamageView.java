package de.rowbuddy.client.views.boat;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.boat.ListDamagePresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;

public class DamageView extends HeaderButtonView implements Display{

	private CheckBox checkBox;
	private FlexTable damageTable;
	int index = 1;
	
	public DamageView(){
		super(PageTitles.BOAT_DAMAGES);

		checkBox = new CheckBox("Alle Schäden");
		addButton(checkBox);

		damageTable = new FlexTable();
		damageTable.setWidth("100%");
		damageTable.setStyleName("boatTable");
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.add(damageTable);
	    scrollPanel.setStyleName("scollTable");
		setContent(scrollPanel);
		initTableHeader();
	}

	@Override
	public void addDamageRow(String name, String reporter, String date,
			boolean isOpen) {
		damageTable.setText(index, 0, name);
		damageTable.setText(index, 1, reporter);
		damageTable.setText(index, 2, date);
		CheckBox box = new CheckBox();
		box.setEnabled(false);
		box.setValue(isOpen);
		damageTable.setWidget(index, 3, box);
		
		RowFormatter rf = damageTable.getRowFormatter();
		if ((index % 2) != 0) {
			// rf.addStyleName(i, "FlexTable-OddRow");
			rf.setStylePrimaryName(index, "FlexTable-OddRow");
		} else {
			rf.addStyleName(index, "FlexTable-EvenRow");
		}
		index++;
	}
	
	
	@Override
	public Widget asWidget() {
		return this;
	}


	@Override
	public void clear() {
		for(int i = 1 ; i < index; i++){
			damageTable.getRowFormatter().removeStyleName(i, "FlexTable-OddRow");
			damageTable.getRowFormatter().removeStyleName(i, "FlexTable-EvenRow");
		}
		index = 1;
		damageTable.clear();
		damageTable.clear(true);
		
		initTableHeader();
	}
	
	private void initTableHeader(){
		damageTable.setText(0, 0, "Name");
		damageTable.setText(0, 1, "Gemeldet von");
		damageTable.setText(0, 2, "Erstellt am");
		damageTable.setText(0, 3, "Behoben");
		damageTable.getRowFormatter().setStyleName(0, "boatTableHeader");
		damageTable.setCellPadding(5);
	}


	@Override
	public HasValueChangeHandlers<Boolean> historyEnabled() {
		return checkBox;
	}

}
