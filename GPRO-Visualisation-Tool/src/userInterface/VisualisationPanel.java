package userInterface;

import javax.swing.JPanel;

import visualisation.VisualisationElement;

public class VisualisationPanel extends JPanel {
	
	private VisualisationElement m_element;
	
	public VisualisationPanel(VisualisationElement element){
		m_element = element;
		this.add(m_element);
		
	}
	
	public void clear(){
		this.remove(m_element);
	}
	
	public VisualisationElement getElement(){
		return m_element;
	}

}
