package userInterface;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;

public class UserInterface extends JFrame {

	public UserInterface() {
		WebLookAndFeel.install();
		this.setLayout(new BorderLayout());
		initComponents();
	}
	
	private void initComponents(){
		
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
		
	}
}
