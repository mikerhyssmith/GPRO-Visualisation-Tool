package userInterface;

import javax.swing.JPanel;

public class ResultsPanel extends JPanel implements UIElement {
	
	public ResultsPanel(){
		
		initComponents();
	}

	@Override
	public void initComponents() {
		RacePanel r1 = new RacePanel("test",12,false,325.6f);
		RacePanel r2 = new RacePanel("test2",16,true,323.612f);
		RacePanel r3 = new RacePanel("test3",16,false,273.6f);
		RacePanel r4 = new RacePanel("test4",117,false,212.6f);
		
		this.add(r1);
		this.add(r2);
		this.add(r3);
		this.add(r4);
		
		
	}

}
