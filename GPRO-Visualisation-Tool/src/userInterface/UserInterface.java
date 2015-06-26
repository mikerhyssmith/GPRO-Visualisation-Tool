package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
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
         
		
         ArrayList<Double> x = new ArrayList<Double>();
         x.add(0,1.0);
         x.add(1,7.5);
         x.add(2,8.0);
         x.add(3,12.0);
         x.add(4,23.0);
         x.add(5,28.0);
         x.add(6,39.0);
         x.add(7,-41.0);
         
         
         ArrayList<Double> y = new ArrayList<Double>();
         y.add(0,1.0);
         y.add(1,1.6);
         y.add(2,7.4);
         y.add(3,19.6);
         y.add(4,29.6);
         y.add(5,24.0);
         y.add(6,9.0);
         y.add(7,-0.6);
         
        
         
         DisplayArea displayArea = new DisplayArea(this);
         Dimension displayAreaSize = new Dimension((int) (this.getSize().getWidth()*0.7), this.getHeight());
         displayArea.setPreferredSize(displayAreaSize);
         displayArea.setBackground(Color.red);
         System.out.println(displayAreaSize);
         LineChart<Double> l = new LineChart<Double>(x,y,"name","testX","testY",displayAreaSize);
         l.setBackground(Color.blue);
         l.createDataTable();
         l.plotLineGraph();
         displayArea.add(l);
         displayArea.setVisible(true);
         this.add(displayArea,BorderLayout.CENTER);
         
         this.pack();
         System.out.println(displayArea.getSize());
        
	}
}
