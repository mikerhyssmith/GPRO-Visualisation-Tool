package factoryProviders;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import visualisation.LineChart;
import analysis.Constants;
import data.Race;

public class GraphFactory {

	
	public static JPanel produceChart(Constants.chartType chartType, ArrayList<Race> data){
		JPanel chartPanel = new JPanel();
		chartPanel.setName(Constants.chartTypeString(chartType));
		if(chartType.equals(Constants.chartType.TotalFuelVsDistance)){
			chartPanel.removeAll();
			chartPanel.add(generateTotalFuelVsDistanceChart(data));
		}

		return chartPanel;
		
	}
	
	private static JPanel generateTotalFuelVsDistanceChart(ArrayList<Race> data){
		ArrayList<Double> xValues = new ArrayList<Double>();
		ArrayList<Double> yValues = new ArrayList<Double>();
		for(Race r : data){
			xValues.add((double) r.getTrack().getDistance());
			yValues.add((double) r.getTotalFuelUsed());
		}
		
		LineChart<Double> chart = new LineChart<Double>(xValues,yValues,"Total Distance vs Fuel" ,"Distance (KM)","Fuel (litres)");
		chart.createDataTable();
		chart.plotLineGraph();
		
		return chart;
	}
}
