package de.rowbuddy.client.views.boat;

import java.util.Collection;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.business.dtos.BoatDTO;
import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.BoatPresenter;

public class BoatView extends Composite implements BoatPresenter.Display {

	private DecoratorPanel decorator;
	private FlexTable contentTable;
	private Button addButton;
	private FlexTable boatTable;

	public BoatView() {
		decorator = new DecoratorPanel();
		contentTable = new FlexTable();

		initWidget(decorator);
		decorator.add(contentTable);

		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		addButton = new Button("Boot Hinzufügen");
		addButton.setStyleName("buttonRegular");
		hPanel.add(addButton);
		contentTable.setWidget(2, 0, hPanel);
		
		
		boatTable = new FlexTable();
		boatTable.setWidth("100%");
		boatTable.setStyleName("boatTable");
		
		
		contentTable.setWidget(1, 0, boatTable);
		contentTable.setText(0, 0, PageTitles.BOAT_OVERVIEW);
		HTMLTable.RowFormatter rf = contentTable.getRowFormatter();
		rf.setStylePrimaryName(0, "pageHeadLine");
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getAddButton() {
		return addButton;
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = boatTable.getCellForEvent(event);

		if (cell != null) {
			selectedRow = cell.getRowIndex();
		}
		return selectedRow;
	}

	public void initTableHead() {
		boatTable.setText(0, 0, "Name");
		boatTable.setText(0, 1, "Bootsplätze");
		boatTable.setText(0, 2, "Gesteuert");
		boatTable.setText(0, 3, "Gesperrt");
		boatTable.getRowFormatter().setStyleName(0, "boatTableHeader");

	}

	@Override
	public void setData(Collection<BoatDTO> boats) {
		boatTable.removeAllRows();
		initTableHead();
		int i = 1;
		for (BoatDTO boat : boats) {
			boatTable.setText(i, 0, boat.getName());
			boatTable.setText(i, 1, String.valueOf(boat.getNumberOfSeats()));

			CheckBox coxBox = new CheckBox();
			coxBox.setEnabled(false);
			coxBox.setValue(boat.isCoxed());
			boatTable.setWidget(i, 2, coxBox);

			CheckBox lockedBox = new CheckBox();
			lockedBox.setEnabled(false);
			lockedBox.setValue(boat.isLocked());
			boatTable.setWidget(i, 3, lockedBox);

			HTMLTable.RowFormatter rf = boatTable.getRowFormatter();
			
			if ((i % 2) != 0) {
				//rf.addStyleName(i, "FlexTable-OddRow");
				rf.setStylePrimaryName(i, "FlexTable-OddRow");
			} else {
				rf.addStyleName(i, "FlexTable-EvenRow");
			}
			i++;
		}
	}

	
	@Override
	public HasClickHandlers getTable() {
		return boatTable;
	}

}
