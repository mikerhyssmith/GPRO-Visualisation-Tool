package userInterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DisplayArea extends JPanel {
	
	private JFrame m_frame;
	
	public DisplayArea(JFrame frame){
		m_frame = frame;
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	
	
	@Override
    public Dimension getPreferredSize() {
		
        return new Dimension( (int) (m_frame.getWidth()*0.7), m_frame.getHeight());
    }
}
