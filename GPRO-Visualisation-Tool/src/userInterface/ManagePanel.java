package userInterface;

import java.awt.CardLayout;

import javax.swing.JPanel;

import com.alee.laf.label.WebLabel;
import com.alee.laf.tabbedpane.WebTabbedPane;

public class ManagePanel extends JPanel implements UIElement {
	
	private SearchPanel searchPanel;

	public ManagePanel(){
		this.setLayout(new CardLayout());
		searchPanel = new SearchPanel();
		
		initComponents();
	}

	@Override
	public void initComponents() {
		WebTabbedPane tabbedPanel = new WebTabbedPane ();
		tabbedPanel.setTabPlacement ( WebTabbedPane.TOP );
		tabbedPanel.addTab ( "Search", searchPanel );
		tabbedPanel.addTab ( "Results", new WebLabel () );
		tabbedPanel.addTab ( "Visualise", new WebLabel () );
		
		
       
		this.add(tabbedPanel);
	}
}
