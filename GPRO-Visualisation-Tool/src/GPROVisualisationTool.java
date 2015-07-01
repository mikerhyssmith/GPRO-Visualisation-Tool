import java.awt.Dimension;

import data.Data;
import userInterface.UserInterface;

public class GPROVisualisationTool {
	
	public static void main(String[] args){
	    // First Call to initialize all data at start
	    Data.getInstance();
		UserInterface UI = new UserInterface();
		UI.setPreferredSize(new Dimension(1024,768));
		UI.pack();
		UI.initComponents();
		UI.setVisible(true);
	}
}
