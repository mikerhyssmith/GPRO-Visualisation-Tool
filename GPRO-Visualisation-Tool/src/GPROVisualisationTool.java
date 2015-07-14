import java.awt.Dimension;
import java.util.ArrayList;

import data.Race;
import data.RaceTrack;
import data.User;
import data.Data.*;
import analysis.*;
import userInterface.UserInterface;

public class GPROVisualisationTool {
	
	public static void main(String[] args){
		UserInterface UI = new UserInterface();
		UI.setPreferredSize(new Dimension(1024,768));
		UI.pack();
		UI.initComponents();
		UI.setVisible(true);
	}
}
