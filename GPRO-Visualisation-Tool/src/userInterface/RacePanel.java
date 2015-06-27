package userInterface;

import java.awt.BorderLayout;

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
		
		this.add(new JSeparator(SwingConstants.HORIZONTAL),BorderLayout.PAGE_START);
		this.add(titlePanel,BorderLayout.NORTH);
		this.add(detailsPanel,BorderLayout.SOUTH);
		this.add(new JSeparator(SwingConstants.HORIZONTAL),BorderLayout.PAGE_END);
		
	}

}
