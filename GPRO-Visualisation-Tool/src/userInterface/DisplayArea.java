package userInterface;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.alee.laf.tabbedpane.WebTabbedPane;

public class DisplayArea extends JPanel implements UIElement {
	
	
	private WebTabbedPane tabbedPanel;
	
	public DisplayArea(){
		
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new BorderLayout());
		initComponents();
	}



	@Override
	public void initComponents() {
		tabbedPanel = new WebTabbedPane();
		tabbedPanel.setTabPlacement ( WebTabbedPane.TOP );
		tabbedPanel.addTab("Welcome !", new WelcomePanel());
		this.add(tabbedPanel,BorderLayout.CENTER);
		
	}
	
	public <T extends JPanel> void addTab(T tabElement){
		tabbedPanel.addTab(tabElement.getName(), tabElement);
		tabbedPanel.setSelectedIndex(tabbedPanel.getComponentCount()-1);
	}
	
}
