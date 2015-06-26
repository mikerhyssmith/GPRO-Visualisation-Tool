package visualisation;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class  LineChart<T extends Number> extends JPanel {
	
	private ArrayList<T> xValues;
	private ArrayList<T> yValues;
	private String xAxisLabel;
	private String yAxisLabel;
	private String title;
	private XYSeries data;
	private JFreeChart chart;
	private XYSeriesCollection dataset;
	private Dimension chartSize;
	
	public  LineChart(ArrayList<T> xValues, ArrayList<T> yValues,String title, String xAxisLabel, String yAxisLabel,Dimension d){
		
			this.xValues = xValues;
			this.yValues = yValues;
			this.xAxisLabel = xAxisLabel;
			this.yAxisLabel = yAxisLabel;
			this.title = title;
			this.chartSize = d;
			
			data = new XYSeries("Series 1");
			dataset = new XYSeriesCollection();
		
	}
	
	public void createDataTable(){
		
		Iterator<T> xIter = xValues.iterator();
		Iterator<T> yIter = yValues.iterator();
		
		while(xIter.hasNext() && yIter.hasNext()){
			Double x = (Double) xIter.next();
			Double y = (Double) yIter.next();
			data.add(x, y);
		}
		
		dataset.addSeries(data);
	
	}
	
	public void plotLineGraph(){
		chart = ChartFactory.createXYLineChart(
		            title,      // chart title
		            xAxisLabel,                      // x axis label
		            yAxisLabel,                      // y axis label
		            dataset,                  // data
		            PlotOrientation.VERTICAL,
		            true,                     // include legend
		            true,                     // tooltips
		            false                     // urls
		        );
		
		
		ChartPanel panel = new ChartPanel(chart);
		panel.setSize(chartSize);
		this.setSize(chartSize);
		this.setPreferredSize(chartSize);
		this.add(panel);
		
	}
	
	/**
	public void plotScatterGraph(){
		
		XYPlot plot = new XYPlot(data);
		this.add(new InteractivePanel(plot));
	}
	*/

	
}
