package de.rowbuddy.client.views.member;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;

import de.rowbuddy.client.PageTitles;
import de.rowbuddy.client.presenter.member.DetailsMemberPresenter.Display;
import de.rowbuddy.client.views.HeaderButtonView;
import de.rowbuddy.entities.Member;

public class DetailsMemberView extends HeaderButtonView implements Display {
	private Button cancelButton;
	private FlexTable content;

	public DetailsMemberView() {
		super(PageTitles.BOAT_DAMAGE_DETAIL);

		cancelButton = new Button("Abbrechen");
		cancelButton.setStylePrimaryName("buttonCancel buttonNegative");
		addButton(cancelButton);

		content = new FlexTable();

		content = new FlexTable();
		content.setWidth("100%");
		content.setText(0, 0, "Member-ID:");
		content.setText(1, 0, "Nachname:");
		content.setText(2, 0, "Vorname:");
		content.setText(3, 0, "Geburtsdatum:");
		content.setText(4, 0, "Email:");
		content.setText(5, 0, "Strasse:");
		content.setText(6, 0, "PLZ:");
		content.setText(7, 0, "Stadt:");
		content.setText(8, 0, "Telefon:");
		content.setText(9, 0, "Mobilfunk:");

		setContent(content);
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	@Override
	public void setMember(Member member) {
		DateTimeFormat dateFormat = DateTimeFormat
				.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT);

		content.setText(0, 1, member.getMemberId());
		content.setText(1, 1, member.getSurname());
		content.setText(2, 1, member.getGivenname());
		if (member.getBirthdate() != null) {
			content.setText(3, 1, dateFormat.format(member.getBirthdate()));
		}
		content.setText(4, 1, member.getEmail());
		content.setText(5, 1, member.getStreet());
		content.setText(6, 1, member.getZipCode());
		content.setText(7, 1, member.getCity());
		content.setText(8, 1, member.getPhone());
		content.setText(9, 1, member.getMobilePhone());

	}
}
