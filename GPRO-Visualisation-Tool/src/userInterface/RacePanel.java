package userInterface;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.alee.laf.label.WebLabel;

public class RacePanel extends JPanel implements UIElement {
	
	private String m_raceName;
	private int m_seasonNumber;
	private boolean m_wetTyres;
	private float m_raceDistance;
	
	public RacePanel(String raceName, int seasonNumber, boolean wetTyres, float raceDistance){
		this.setLayout(new BorderLayout());
		m_raceName = raceName;
		m_seasonNumber = seasonNumber;
		m_wetTyres = wetTyres;
		m_raceDistance = raceDistance;
		
		initComponents();
	}

	@Override
	public void initComponents() {
		JPanel titlePanel = new JPanel();
		WebLabel nameLabel = new WebLabel ( m_raceName );
		WebLabel seasonLabel = new WebLabel("Season: " + m_seasonNumber);
		titlePanel.add(nameLabel);
		titlePanel.add(seasonLabel);
		
		JPanel detailsPanel = new JPanel();
		String wetRace = "Dry Race";
		if(m_wetTyres){
			wetRace = "Wet Race";
		}
		WebLabel wetLabel = new WebLabel(wetRace);
		WebLabel distanceLabel = new WebLabel("Distance: " + m_raceDistance);
		detailsPanel.add(wetLabel);
		detailsPanel.add(distanceLabel);
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(titlePanel,BorderLayout.NORTH);
		p.add(detailsPanel,BorderLayout.SOUTH);
		
		this.add(new JSeparator(SwingConstants.HORIZONTAL),BorderLayout.NORTH);
		this.add(p);
		this.add(new JSeparator(SwingConstants.HORIZONTAL),BorderLayout.SOUTH);
		
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		RacePanel r1 = new RacePanel("test",12,false,325.6f);
		frame.add(r1);
		frame.setSize(200,100);
		frame.setVisible(true);
		
	}

}
