package userInterface;

import java.awt.CardLayout;

import javax.swing.JPanel;

import com.alee.laf.tabbedpane.WebTabbedPane;

public class ManagePanel extends JPanel implements UIElement {
	
	private SearchPanel searchPanel;
	private ResultsPanel resultsPanel;
	private VisualisationOptionsPanel visOptPanel;

	public ManagePanel(){
		this.setLayout(new CardLayout());
		resultsPanel = new ResultsPanel();
		visOptPanel = new VisualisationOptionsPanel();
		searchPanel = new SearchPanel(resultsPanel);
		
		initComponents();
	}

	@Override
	public void initComponents() {
		WebTabbedPane tabbedPanel = new WebTabbedPane ();
		tabbedPanel.setTabPlacement ( WebTabbedPane.TOP );
		tabbedPanel.addTab ( "Search", searchPanel );
		tabbedPanel.addTab ( "Results", resultsPanel );
		tabbedPanel.addTab ( "Visualise", visOptPanel);
		
		
       
		this.add(tabbedPanel);
	}
}
