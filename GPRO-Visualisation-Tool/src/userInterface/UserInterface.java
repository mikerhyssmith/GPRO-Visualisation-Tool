package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;

public class UserInterface extends JFrame implements UIElement {
	
	

	public UserInterface() {
		WebLookAndFeel.install();
		this.setLayout(new BorderLayout());
		initComponents();
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
         menuBar.add ( new WebMenu ( "Menu 2" )
         {
             {
                 add ( new WebMenuItem ( "Menu item 1" ) );
                 add ( new WebMenuItem ( "Menu item 2" ) );
                 add ( new WebMenuItem ( "Menu item 3" ) );
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
		
         DisplayArea displayArea = new DisplayArea();
         displayArea.setSize(800, 800);
         JPanel test = new JPanel();
         test.setBackground(Color.WHITE);
         JPanel test2 = new JPanel();
         test2.setBackground(Color.RED);
         ComparisonPanel cp = new ComparisonPanel(test,test2, displayArea.getSize());
         this.add(cp, BorderLayout.CENTER);
         displayArea.setVisible(true);
         cp.setVisible(true);
	}
}
