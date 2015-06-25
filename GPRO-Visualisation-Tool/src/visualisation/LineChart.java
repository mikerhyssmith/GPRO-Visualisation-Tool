package visualisation;

import java.util.ArrayList;

import javax.swing.JPanel;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class  LineChart<T extends Number> extends JPanel {
	
	private ArrayList<T> xValues;
	private ArrayList<T> yValues;
	private String xAxisLabel;
	private String yAxisLabel;
	private DataTable data;
	
	public  LineChart(ArrayList<T> xValues, ArrayList<T> yValues, String xAxisLabel, String yAxisLabel){
		
			this.xValues = xValues;
			this.yValues = yValues;
			this.xAxisLabel = xAxisLabel;
			this.yAxisLabel = yAxisLabel;
			
			data = new DataTable(Float.class,Float.class);
		
	}
	
	public void createDataTable(){
		for(T i : xValues){
			for(T j : yValues){
				
				float xVal;
				float yVal;
				try{
					xVal = (Float) i;
					yVal = (Float) j;
					data.add(xVal, yVal);
				}catch(NumberFormatException e){
					System.err.println("Wrong graph data format");
				}
				
			}
		}
	}
	
	public void plotLineGraph(){
		XYPlot plot = new XYPlot(data);
		InteractivePanel p = new InteractivePanel(plot);
		this.add(p);
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderer(data, lines);
		p.setVisible(true);
		
	}
	
	public void plotScatterGraph(){
		
		XYPlot plot = new XYPlot(data);
		this.add(new InteractivePanel(plot));
	}

}
