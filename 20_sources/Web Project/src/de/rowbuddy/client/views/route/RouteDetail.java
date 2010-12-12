package de.rowbuddy.client.views.route;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.route.RouteDetailPresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;

public class RouteDetail extends HeaderButtonView implements Display {
	
	VerticalPanel vPanel;
	private FlexTable detailTable;
	private Label nameText;
	private Label lenght;
	private Label description;
	private CheckBox mutable;
	private Button editButton;
	private Button cancelButton;
	
	public RouteDetail(){
		super(PageTitles.ROUTE_DETAIL);
		
		vPanel = new VerticalPanel();
		vPanel.setWidth("100%");
		editButton = new Button("Route bearbeiten");
		editButton.setStylePrimaryName("buttonEdit buttonRegular");
		addButton(editButton);
		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");
		addButton(cancelButton);
		
		detailTable = new FlexTable();
		detailTable.setStylePrimaryName("detailContent");
		detailTable.setCellPadding(5);
		
		detailTable.setText(0, 0, "Name:");
		nameText = new Label();
		detailTable.setWidget(0, 1, nameText);
		
		detailTable.setText(1, 0, "Länge:");
		lenght = new Label();
		detailTable.setWidget(1, 1, lenght);
		
		detailTable.setText(2, 0, "Beschreibung:");
		description = new Label();
		detailTable.setWidget(2, 1, description);
		
		detailTable.setText(3, 0, "Veränderbar:");
		mutable = new CheckBox();
		mutable.setEnabled(false);
		detailTable.setWidget(3, 1, mutable);
		
		vPanel.add(detailTable);
		setContent(vPanel);
	}

	@Override
	public void setName(String name) {
		nameText.setText(name);
	}

	@Override
	public void setLenght(String lenght) {
		this.lenght.setText(lenght);
	}

	@Override
	public void setDescription(String description) {
		this.description.setText(description);
	}

	@Override
	public void setMutable(boolean value) {
		mutable.setValue(value);
	}

	@Override
	public HasClickHandlers getEditButton() {
		return editButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

}
