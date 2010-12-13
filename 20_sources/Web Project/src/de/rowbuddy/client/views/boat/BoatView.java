package de.rowbuddy.client.views.boat;

import java.util.Collection;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.SessionHolder;
import de.rowbuddy.client.presenter.boat.BoatPresenter;
import de.rowbuddy.client.views.HeaderButtonView;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;

public class BoatView extends HeaderButtonView implements BoatPresenter.Display {

	private Button addButton;
	private FlexTable boatTable;

	public BoatView() {
		super(PageTitles.BOAT_OVERVIEW);

		addButton = new Button("Boot Hinzufügen");
		addButton.setStyleName("buttonAdd buttonRegular");
		addButton(addButton);

		boatTable = new FlexTable();
		boatTable.setWidth("100%");
		boatTable.setStyleName("boatTable");
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.add(boatTable);
		scrollPanel.setStyleName("scollTable");
		setContent(scrollPanel);
		setPermissions();
	}

	private void setPermissions() {
		SessionHolder.getSessionManager().getMember(
				new AsyncCallback<Member>() {

					@Override
					public void onSuccess(Member arg0) {
						if(!arg0.isInRole(Role.RoleName.ADMIN)){
							Logger logger = Logger.getLogger(BoatView.class.getName());
							logger.info(""+arg0.getRoles().size());
							for(Role r : arg0.getRoles()){
								logger.info(r.getName().toString());
							}
							addButton.setVisible(false);
						}
					}

					@Override
					public void onFailure(Throwable arg0) {
						Window.alert("error");
					}
				});
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
		boatTable.setCellPadding(5);

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
