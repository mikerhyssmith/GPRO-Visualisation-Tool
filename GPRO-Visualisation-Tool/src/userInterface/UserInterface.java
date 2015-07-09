package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import visualisation.LineChart;

import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusLabel;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;

public  class UserInterface extends JFrame implements UIElement {
	
	
	private static DisplayArea displayArea;

	public UserInterface() {
		WebLookAndFeel.install();
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void initComponents(){
		
		 final WebMenuBar menuBar = new WebMenuBar ();
         menuBar.setUndecorated ( false );
         menuBar.setBorder ( BorderFactory.createEmptyBorder ( 2, 2, 2, 2 ) );
         menuBar.add ( new WebMenu ( "File" )
         {
             {
                 add ( new WebMenuItem ( "Load" ) );
                 add ( new WebMenuItem ( "Export" ) );
                 addSeparator ();
                 add ( new WebMenuItem ( "Exit" ) );
             }
         } );
         menuBar.add ( new WebMenu ( "Analysis" )
         {
             {
                 add ( new WebMenuItem ( "Optimal Setup Tool" ) );

             }
         } );
         menuBar.add ( new WebMenu ( "Help" )
         {
             {
                 add ( new WebMenuItem ( "Help Contents" ) );
                 add ( new WebMenuItem ( "About" ) );
             }
         } );
         setJMenuBar ( menuBar );
         
         JPanel eastFillerPanel = new JPanel();
         eastFillerPanel.setSize((int) (this.getSize().getWidth()/0.01),this.getSize().height);
         eastFillerPanel.setVisible(true);
         this.add(eastFillerPanel,BorderLayout.EAST);
         
         ManagePanel manPanel = new ManagePanel();
         Dimension manageAreaSize = new Dimension((int) (this.getWidth()*0.20), this.getHeight());
         manPanel.setPreferredSize(manageAreaSize);
         this.add(manPanel,BorderLayout.WEST);
         
      
         //TODO add global Constants
         StatusBar statusBar = new StatusBar();
         this.add(statusBar,BorderLayout.SOUTH);
         
         displayArea = new DisplayArea();
         displayArea.setVisible(true);
         this.add(displayArea,BorderLayout.CENTER);
        
	}
	public static DisplayArea getDisplayArea(){
		return displayArea;
	}
}
