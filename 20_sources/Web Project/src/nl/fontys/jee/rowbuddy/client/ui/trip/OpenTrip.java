package nl.fontys.jee.rowbuddy.client.ui.trip;

import java.text.SimpleDateFormat;

import nl.fontys.jee.rowbuddy.client.ui.trip.helper.TripDatabase;
import nl.fontys.jee.rowbuddy.client.ui.trip.helper.TripDatabase.TripInfo;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.impl.cldr.DateTimeFormatInfoImpl_de;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;

public class OpenTrip extends Composite {
	
	private final DateTimeFormat fmt = DateTimeFormat.getFormat("dd.MM.yy hh:mm");
	
	
	  public OpenTrip(){
		  setupUi();
	  }

	  CellTable<TripInfo> cellTable;
	  
	  SimplePager pager;


	  /**
	   * Initialize this example.
	   */
	  public void setupUi() {
	    // Create a CellTable.

	    // Set a key provider that provides a unique key for each contact. If key is
	    // used to identify contacts when fields (such as the name and address)
	    // change.
	    cellTable = new CellTable<TripInfo>(TripDatabase.TripInfo.KEY_PROVIDER);
	    
	    initWidget(cellTable);

	    // Create a Pager to control the table.
	    SimplePager.Resources pagerResources = GWT.create(
	        SimplePager.Resources.class);
	    pager = new SimplePager(
	        TextLocation.CENTER, pagerResources, false, 0, true);
	    pager.setDisplay(cellTable);

	    // Add a selection model so we can select cells.
	    final MultiSelectionModel<TripInfo> selectionModel = new MultiSelectionModel<TripInfo>(TripDatabase.TripInfo.KEY_PROVIDER);
	    cellTable.setSelectionModel(selectionModel);

	    // Initialize the columns.
	    initTableColumns(selectionModel);

	    // Add the CellList to the adapter in the database.
	    TripDatabase.get().addDataDisplay(cellTable);
	  }

	  /**
	   * Add the columns to the table.
	   */
	  private void initTableColumns(
	      final SelectionModel<TripInfo> selectionModel) {
	    // Checkbox column. This table will uses a checkbox column for selection.
	    // Alternatively, you can call cellTable.setSelectionEnabled(true) to enable
	    // mouse selection.
	    Column<TripInfo, Boolean> checkColumn = new Column<TripInfo, Boolean>(
	        new CheckboxCell(true)) {
	      @Override
	      public Boolean getValue(TripInfo object) {
	        // Get the value from the selection model.
	        return selectionModel.isSelected(object);
	      }
	    };
	    checkColumn.setFieldUpdater(new FieldUpdater<TripInfo, Boolean>() {
	      public void update(int index, TripInfo object, Boolean value) {
	        // Called when the user clicks on a checkbox.
	        selectionModel.setSelected(object, value);
	      }
	    });
	    cellTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br>"));

	    
	    
	    
	    
	    
	    // Route
	    Column<TripInfo, String> routeNameColumn = new Column<
	        TripInfo, String>(new EditTextCell()) {
	      @Override
	      public String getValue(TripInfo object) {
	        return object.getRouteName();
	      }
	    };
	    cellTable.addColumn(
	        routeNameColumn, "Routenname");
	    routeNameColumn.setFieldUpdater(new FieldUpdater<TripInfo, String>() {
	      public void update(int index, TripInfo object, String value) {
	        // Called when the user changes the value.
	        object.setRouteName(value);
	        TripDatabase.get().refreshDisplays();
	      }
	    });
	   
	    
	    
	    
	 // start date
	    Column<TripInfo, String> startDateColumn = new Column<
	        TripInfo, String>(new EditTextCell()) {
	      @Override
	      public String getValue(TripInfo object) {
	    	  
		        return fmt.format(object.getStartDate());
	      }
	    };
	    cellTable.addColumn(
	        startDateColumn, "Start");
	    startDateColumn.setFieldUpdater(new FieldUpdater<TripInfo, String>() {
	      public void update(int index, TripInfo object, String value) {
	        // Called when the user changes the value.
	        object.setStartDate(fmt.parse(value));
	        TripDatabase.get().refreshDisplays();
	      }
	    });
	    
	 // end date
	    Column<TripInfo, String> endDateColumn = new Column<
	        TripInfo, String>(new EditTextCell()) {
	      @Override
	      public String getValue(TripInfo object) {
	        return fmt.format(object.getEndDate());
	      }
	    };
	    cellTable.addColumn(
	        endDateColumn, "Ende");
	    endDateColumn.setFieldUpdater(new FieldUpdater<TripInfo, String>() {
	      public void update(int index, TripInfo object, String value) {
	        // Called when the user changes the value.
	        object.setEndDate(fmt.parse(value));
	        TripDatabase.get().refreshDisplays();
	      }
	    });
	    
	    
	    
	 // rowerCount
	    Column<TripInfo, String> rowerCountColumn = new Column<
	        TripInfo, String>(new EditTextCell()) {
	      @Override
	      public String getValue(TripInfo object) {
	        return ""+object.getRowerCount();
	      }
	    };
	    cellTable.addColumn(
	    		rowerCountColumn, "Anzal Mitfahrer");
	    rowerCountColumn.setFieldUpdater(new FieldUpdater<TripInfo, String>() {
	      public void update(int index, TripInfo object, String value) {
	        // Called when the user changes the value.
	        object.setRowerCount(Integer.parseInt(value));
	        TripDatabase.get().refreshDisplays();
	      }
	    });
	    
	  }

}
