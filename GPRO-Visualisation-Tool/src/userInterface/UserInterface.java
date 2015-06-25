package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import visualisation.LineChart;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;

public  class UserInterface extends JFrame implements UIElement {
	
	

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
         
         JPanel eastFillerPanel = new JPanel();
         eastFillerPanel.setSize((int) (this.getSize().getWidth()/0.01),this.getSize().height);
         eastFillerPanel.setVisible(true);
         this.add(eastFillerPanel,BorderLayout.EAST);
         
		
         ArrayList<Float> x = new ArrayList<Float>();
         x.add(0,1.0f);
         x.add(1,7f);
         x.add(2,8f);
         x.add(3,19f);
         x.add(4,23f);
         x.add(5,28.0f);
         x.add(6,39f);
         x.add(7,-41f);
         
         
         ArrayList<Float> y = new ArrayList<Float>();
         y.add(0,1.0f);
         y.add(1,1.6f);
         y.add(2,7.4f);
         y.add(3,19.6f);
         y.add(4,29.6f);
         y.add(5,24.0f);
         y.add(6,9f);
         y.add(7,-0.6f);
         
         LineChart<Float> l = new LineChart<Float>(x,y,"test","test");
         l.createDataTable();
         l.plotLineGraph();

         
         DisplayArea displayArea = new DisplayArea(this);
         displayArea.setPreferredSize(new Dimension((int) (this.getSize().getWidth()*0.7), this.getHeight()));
         l.setSize(displayArea.getSize());
         displayArea.add(l);
         displayArea.setVisible(true);
         this.add(displayArea);
         this.pack();
        
	}
}
