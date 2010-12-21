package de.rowbuddy.boundary.dtos;

import java.io.Serializable;

public class MonthsStatisticDTO implements Serializable {

	private static final long serialVersionUID = 5901330792234841831L;

	private int[] months = new int[12];

	public MonthsStatisticDTO() {
	}

	public int[] getMonths() {
		return months;
	}

	public void setMonths(int[] months) {
		this.months = months;
	}
}
