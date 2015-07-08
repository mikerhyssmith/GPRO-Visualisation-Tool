package userInterface;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.alee.laf.label.WebLabel;
import com.alee.laf.scroll.WebScrollPane;

import data.Race;

public class ResultsPanel extends JPanel implements UIElement {
	
	ArrayList<Race> results = new ArrayList<Race>();
	private  boolean  resultsUpdated = true;
	private WebScrollPane scrollPane; 
	
	public ResultsPanel(){
		initComponents();
	}

	@Override
	public void initComponents() {
		WebLabel initResults = new WebLabel("Search to display results.");
		scrollPane = new WebScrollPane(initResults);
		this.add(scrollPane);
	}
	
	public boolean getUpdating(){
		return !resultsUpdated;
	}
	
	public  void updateResults(ArrayList<Race> results){
		resultsUpdated = false;
		this.removeAll();
		for(Race r : results){
			String name = r.getName();
			int season = r.getSeason();
			boolean wet = r.getWasWetRace();
			float dist = r.getTrack().getDistance();
			RacePanel r1 = new RacePanel(name,season,wet,dist);
			this.add(r1);
		}
		
		resultsUpdated = true;

		
	}

}
