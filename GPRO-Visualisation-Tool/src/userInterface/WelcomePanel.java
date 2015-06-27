package userInterface;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusLabel;
import com.alee.extended.tree.WebFileTree;
import com.alee.laf.label.WebLabel;
import com.alee.laf.scroll.WebScrollPane;

public class WelcomePanel extends JPanel implements UIElement {
	
	
	
	public WelcomePanel(){
		
		this.setLayout(new BorderLayout());
		initComponents();
	}

	@Override
	public void initComponents() {

		JPanel filesPanel = new JPanel();
		filesPanel.setLayout(new BorderLayout());
		WebLabel l = new WebLabel("Available Data");
		WebFileTree fileTree = new WebFileTree ("Users");
        fileTree.setAutoExpandSelectedNode ( false );
        fileTree.setShowsRootHandles ( true );
        WebScrollPane fileTreeScroll = new WebScrollPane ( fileTree );
        
		filesPanel.add(l,BorderLayout.PAGE_START);
		filesPanel.add(fileTreeScroll,BorderLayout.CENTER);
		
		

	
		
		
		this.add(filesPanel,BorderLayout.CENTER);
		
	}

}
