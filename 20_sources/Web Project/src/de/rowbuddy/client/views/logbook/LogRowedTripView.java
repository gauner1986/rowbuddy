package de.rowbuddy.client.views.logbook;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.widgetideas.client.event.ChangeEvent;
import com.google.gwt.widgetideas.client.event.ChangeHandler;
import com.google.gwt.widgetideas.datepicker.client.DateTimePicker;

import de.rowbuddy.client.presenter.LogRowedTripPresenter;
import de.rowbuddy.client.views.HeaderButtonView;

public class LogRowedTripView extends HeaderButtonView implements
		LogRowedTripPresenter.Display {

	private FlexTable content;
	private TextBox routeName;
	private TextBox memberName;
	private TextBox boatName;
	private Button addButton;
	private Button cancelButton;
	private SuggestBox route;
	private SuggestBox member;
	private SuggestBox boat;
	private final DateTimePicker startDate;
	private final DateTimePicker endDate;
	private boolean startDateVisible = false;
	private boolean endDateVisible = false;

	private final ListBox multiBox;

	private static final int STARTDATE = 0;
	private static final int ENDDATE = 1;
	private Label startDateLabel = new Label();
	private Label endDateLabel = new Label();

	@SuppressWarnings("deprecation")
	public LogRowedTripView(String pageTitle) {
		super(pageTitle);

		content = new FlexTable();
		routeName = new TextBox();
		boatName = new TextBox();
		memberName = new TextBox();
		routeName = new TextBox();
		addButton = new Button("Hinzufügen");
		addButton.setStylePrimaryName("buttonApply buttonPositive");
		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");

		addButton(addButton);
		addButton(cancelButton);

		content.setText(0, 0, "Routenname:");
		content.setWidget(0, 1, routeName);

		content.setText(1, 0, "Boot:");
		content.setWidget(1, 1, boatName);

		content.setText(2, 0, "Mitglied(er):");
		content.setWidget(2, 1, memberName);

		multiBox = new ListBox(true);
		multiBox.ensureDebugId("cwListBox-multiBox");
		multiBox.setWidth("155px");
		multiBox.setVisibleItemCount(10);

		VerticalPanel multiBoxPanel = new VerticalPanel();
		multiBoxPanel.setSpacing(4);
		multiBoxPanel.add(multiBox);
		content.setWidget(3, 1, multiBoxPanel);

		startDate = new DateTimePicker(true);
		startDate.setWidth("200px");
		startDate.getDatePicker().setSelectedDate(
				new Date(System.currentTimeMillis()));
		startDate.getTimePicker().setDateTime(
				new Date(System.currentTimeMillis()));

		startDate.getDatePicker().addChangeHandler(new ChangeHandler<Date>() {
			@Override
			public void onChange(ChangeEvent<Date> event) {
				updateDateLabel(STARTDATE);
				updateEndDate();
			}
		});
		startDate.getTimePicker().addChangeHandler(new ChangeHandler<Date>() {
			@Override
			public void onChange(ChangeEvent<Date> event) {
				updateDateLabel(STARTDATE);
				updateEndDate();
			}
		});
		updateDateLabel(STARTDATE);
		startDateLabel.setStyleName("sandbox-Date");

		content.setHTML(5, 0, "Startzeit:");
		content.setWidget(5, 1, startDateLabel);
		startDateLabel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (startDateVisible) {
					content.setText(4, 1, "");
					startDateVisible = false;
				} else {
					content.setWidget(4, 1, startDate);
					startDateVisible = true;
				}
			}
		});

		endDate = new DateTimePicker(true);
		endDate.setWidth("200px");
		endDate.getDatePicker().setSelectedDate(
				new Date(System.currentTimeMillis()));
		endDate.getTimePicker().setDateTime(
				new Date(System.currentTimeMillis()));

		endDate.getDatePicker().addChangeHandler(new ChangeHandler<Date>() {
			@Override
			public void onChange(ChangeEvent<Date> event) {
				updateDateLabel(ENDDATE);
			}
		});
		endDate.getTimePicker().addChangeHandler(new ChangeHandler<Date>() {
			@Override
			public void onChange(ChangeEvent<Date> event) {
				updateDateLabel(ENDDATE);
			}
		});
		updateDateLabel(ENDDATE);
		endDateLabel.setStyleName("sandbox-Date");
		content.setHTML(7, 0, "Endzeit:");
		content.setWidget(7, 1, endDateLabel);
		endDateLabel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (endDateVisible) {
					content.setText(6, 1, "");
					endDateVisible = false;
				} else {
					content.setWidget(6, 1, endDate);
					endDateVisible = true;
				}
			}
		});

		content.setText(3, 2,
				"Die erste ausgew�hlte Person ist sofern vorhanden der Steuermann.");
		setContent(content);
	}

	public void setBoatInformation(String name, boolean coxed, int rowers) {
		if (coxed) {
			content.setText(1, 2, "Gesteuert: ja");
		} else {
			content.setText(1, 2, "Gesteuert: nein");
		}
		content.setText(2, 2, "Bootsplätze: " + rowers);
	}

	public void setRouteInformation(double length) {
		content.setText(0, 2, "Streckenlänge: " + length + " km");
	}

	private void updateDateLabel(int label) {
		switch (label) {
		case STARTDATE:
			startDateLabel.setText(DateTimeFormat.getMediumDateTimeFormat()
					.format(startDate.getDate()));
			break;
		case ENDDATE:
			endDateLabel.setText(DateTimeFormat.getMediumDateTimeFormat()
					.format(endDate.getDate()));
			break;
		}
	}

	private void updateEndDate() {
		endDate.getDatePicker().setSelectedDate(startDate.getDate());
		endDate.getTimePicker().setDateTime(startDate.getDate());
		content.setWidget(6, 1, endDate);
		updateDateLabel(ENDDATE);
	}

	@Override
	public void showTripMembers(String[] tripMembers) {
		multiBox.clear();
		for (String tm : tripMembers) {
			multiBox.addItem(tm);
		}
	}

	@Override
	public HasClickHandlers getAddButton() {
		return addButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	@Override
	public HasValue<String> getRouteName() {
		return route;
	}

	@Override
	public HasValue<String> getBoatName() {
		return boat;
	}

	@Override
	public HasValue<String> getMemberName() {
		return member;
	}

	@Override
	public SuggestBox getMember() {
		return member;
	}

	@Override
	public Date getStartDate() {
		return startDate.getDate();
	}

	@Override
	public Date getEndDate() {
		return endDate.getDate();
	}

	@Override
	public ListBox getListBox() {
		return multiBox;
	}

	@Override
	public void setRouteOracle(SuggestOracle oracle) {
		route = new SuggestBox(oracle, routeName);
		content.setWidget(0, 1, route);
	}

	@Override
	public void setBoatOracle(SuggestOracle oracle) {
		boat = new SuggestBox(oracle, boatName);
		content.setWidget(1, 1, boat);
	}

	@Override
	public void setMemberOracle(SuggestOracle oracle) {
		member = new SuggestBox(oracle, memberName);
		content.setWidget(2, 1, member);
	}

}
