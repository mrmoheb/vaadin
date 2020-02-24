package com.aimsio.view;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;
import com.aimsio.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class ChartView extends VerticalLayout {
	private String statusName = "";
    public ChartView() {
    	List <String> assetUN = new ArrayList();
    	DBDriver db = new DBDriver();
    	assetUN = db.assetUNs();
        ComboBox<String> assetUNComboBox = new ComboBox<>("AssetUN:", assetUN);
        assetUNComboBox.addValueChangeListener(event -> {
            System.out.println("Value changed: " + assetUNComboBox.getValue());
          
        });
        this.addComponent(assetUNComboBox);
        List <String> cruiseStates = new ArrayList();
        cruiseStates.add("Active");
        cruiseStates.add("Engaged");
        cruiseStates.add("Override");
        ComboBox<String> cruiseStatesComboBox = new ComboBox<>("Cruise State:", cruiseStates);
        cruiseStatesComboBox.addValueChangeListener(event -> {
            System.out.println("Value changed: " + cruiseStatesComboBox.getValue());
        });
        this.addComponent(cruiseStatesComboBox);
        
        Chart timeline = new Chart();
        timeline.setSizeFull();

        Configuration configuration = timeline.getConfiguration();
        configuration.getChart().setType(ChartType.COLUMN);
        configuration.getTitle().setText("# of Signals over Time");

        configuration.getLegend().setEnabled(true);

        PlotOptionsColumn plotOptions = new PlotOptionsColumn();
        plotOptions.setStacking(Stacking.NORMAL);
        configuration.setPlotOptions(plotOptions);

        XAxis xAxis = configuration.getxAxis();
        xAxis.setType(AxisType.DATETIME);
        DateTimeLabelFormats timeLabelFormats = new DateTimeLabelFormats();
        timeLabelFormats.setDay("%e %b %Y");
        xAxis.setDateTimeLabelFormats(timeLabelFormats);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setMin(0);
        yAxis.setTitle("# of Signals");

        try {
			populateChart(configuration,assetUN.get(0));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.addComponent(timeline);
    }

    private void populateChart(Configuration configuration,String value) throws ParseException {
        DataSeries series = new DataSeries();
        series.setName("Active");
        DBDriver db = new DBDriver();
        List<AssetUN> ls = db.getAssetData(value,"Active");
        for (int i = 0; i < ls.size(); i++) { 
        	Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(ls.get(i).entryDate);  
            DataSeriesItem item = new DataSeriesItem();
            item.setX(date1.toInstant());
            item.setY(1);
            series.add(item);
        }
        configuration.addSeries(series);
    }
}
