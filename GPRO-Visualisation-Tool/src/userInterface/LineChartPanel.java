package userInterface;

import javax.swing.JPanel;

import com.alee.laf.label.WebLabel;

public class LineChartPanel extends JPanel implements UIElement {
	
	
	public LineChartPanel(){
		initComponents();
	}

	@Override
	public void initComponents() {
		WebLabel testLabel = new WebLabel ( "TEST" );
		
		this.add(testLabel);
		
	}

}
