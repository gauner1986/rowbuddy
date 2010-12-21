package de.rowbuddy.client.statistic;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.FlexTable;

import de.rowbuddy.boundary.dtos.MonthsStatisticDTO;
import de.rowbuddy.client.HeaderButtonView;
import de.rowbuddy.client.presenter.statistic.MonthsStatisticPresenter;

public class MonthsStatisticView extends HeaderButtonView implements
	MonthsStatisticPresenter.Display {

	private FlexTable content;
	private SafeHtml statisticView;
	
	public MonthsStatisticView(String pageTitle) {
		super(pageTitle);
		System.out.println("#MonthsStatisticView");

		content = new FlexTable();
		MonthsStatisticDTO dto = new MonthsStatisticDTO();
		dto.setMonths(new int[]{150, 200, 349, 344, 235, 456, 327, 438, 259, 510, 411, 312});
		setData(dto);
				
		setContent(content);
	}

	@Override
	public void setData(final MonthsStatisticDTO statistic) {
		System.out.println("setting DATA");
		
		statisticView = new SafeHtml() {
			@Override
			public String asString() {// TODO form values
				int maxValue= 0;
				for (int monthValue : statistic.getMonths()) {
					if(monthValue > maxValue) {
						maxValue = monthValue;
					}
				}
				String values = statistic.getMonths().toString();
				values = values.replace(" ", "");
				String valuesStriked = values.replace(",", "|");
				return "<img src='http://chart.apis.google.com/chart?chxl=0:|J|F|M|A|M|J|J|A|S|O|N|D|1:|"+valuesStriked+"&chxr=0,0,"+maxValue+"&chxs=0,676767,11.833,0,l,676767|1,676767,11.5,0,lt,676767&chxt=x,x&chbh=a&chs=440x220&cht=bvs&chco=336699&chds=0,20&chd=t:"+values+"&chma=0,0,0,10' width='440' height='220' alt='' />";
			}
		};
		content.setHTML(0, 1, this.statisticView);
	}
	
	

}
