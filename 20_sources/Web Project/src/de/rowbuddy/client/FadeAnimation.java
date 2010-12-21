package de.rowbuddy.client;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;


public class FadeAnimation extends Animation {

	private Widget widget;
	private Presenter presenter;
	private boolean started = false;
	

	public FadeAnimation(HasWidgets widget, Presenter presenter) {
		if (widget instanceof Widget) {
			this.widget = (Widget) widget;
		} else {
			throw new IllegalArgumentException("Widget required");
		}
		this.presenter = presenter;
	}

	@Override
	protected void onUpdate(double arg0) {
		if (arg0 < 0.5) {
			widget.getElement().getStyle().setOpacity(1 - (arg0 * 2));
		} else {
			if(!started) {
			presenter.start((HasWidgets) widget);
			started = true;
			}
			widget.getElement().getStyle().setOpacity((arg0 - 0.5) * 2);
		}

	}

}
