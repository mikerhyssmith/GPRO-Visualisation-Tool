import java.awt.Dimension;

import userInterface.UserInterface;
import data.Data;

public class GPROVisualisationTool {
	
	public static void main(String[] args){
		Data data = Data.getInstance();
		UserInterface UI = new UserInterface();
		UI.setPreferredSize(new Dimension(1024,768));
		UI.pack();
		UI.setVisible(true);
	}

}
