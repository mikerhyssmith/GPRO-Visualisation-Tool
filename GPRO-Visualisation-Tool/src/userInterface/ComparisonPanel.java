package userInterface;

import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.alee.laf.splitpane.WebSplitPane;

public class ComparisonPanel extends JPanel implements SwingConstants, UIElement {
	
	
	private JPanel m_leftPanel;
	private JPanel m_rightPanel;
	private Dimension m_dimensions;
	public <T extends JPanel> ComparisonPanel(T leftPanel, T rightPanel,Dimension d){
		m_leftPanel = leftPanel;
		m_rightPanel = rightPanel;
		m_dimensions = d;
	
		initComponents();
		
		
	}
	
	
	public void initComponents(){
		
		WebSplitPane splitPane = new WebSplitPane ( HORIZONTAL_SPLIT, m_leftPanel, m_rightPanel);
		
        splitPane.setOneTouchExpandable(true);
        splitPane.setPreferredSize (m_dimensions);
        splitPane.setDividerLocation(0.5);
        splitPane.setContinuousLayout(true);
        
        this.add(splitPane);
        this.setVisible(true);
	}
	

}
