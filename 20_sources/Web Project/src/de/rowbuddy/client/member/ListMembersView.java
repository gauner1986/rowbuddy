package de.rowbuddy.client.member;

import java.util.Collection;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.HeaderButtonView;
import de.rowbuddy.client.PageTitles;

public class ListMembersView extends HeaderButtonView implements
		ListMembersPresenter.Display {

	private FlexTable memberTable;

	public ListMembersView() {
		super(PageTitles.MEMBER_OVERVIEW);

		memberTable = new FlexTable();
		memberTable.setWidth("100%");
		memberTable.setStyleName("boatTable");
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.add(memberTable);
		scrollPanel.setStyleName("scollTable");
		setContent(scrollPanel);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = memberTable.getCellForEvent(event);

		if (cell != null) {
			selectedRow = cell.getRowIndex();
		}
		return selectedRow;
	}

	public void initTableHead() {
		memberTable.setText(0, 0, "ID");
		memberTable.setText(0, 1, "Name");
		memberTable.setText(0, 2, "Anschrift");
		memberTable.setText(0, 3, "Email");
		memberTable.getRowFormatter().setStyleName(0, "boatTableHeader");
		memberTable.setCellPadding(5);

	}

	@Override
	public void setData(Collection<MemberDTO> members) {
		memberTable.removeAllRows();
		initTableHead();
		int i = 1;
		for (MemberDTO member : members) {
			memberTable.setText(i, 0, member.getMemberId());
			memberTable.setText(i, 1, member.getFullName());
			memberTable.setText(i, 2, member.getAddress());
			memberTable.setText(i, 3, member.getEmail());

			HTMLTable.RowFormatter rf = memberTable.getRowFormatter();

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
		return memberTable;
	}

}
