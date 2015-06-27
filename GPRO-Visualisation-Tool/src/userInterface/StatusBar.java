package userInterface;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusLabel;
import com.alee.laf.progressbar.WebProgressBar;

public class StatusBar extends JPanel implements UIElement {
	
	private int m_hoursTillRace = 0;
	private int m_minutesTillRace = 0;
	private int m_secondsTillRace = 0;
	private String m_nextTrack = "DATA NOT FOUND";
	
	public StatusBar(){
		this.setLayout(new BorderLayout());
		initComponents();
	}

	@Override
	public void initComponents() {
		WebStatusBar statusBar = new WebStatusBar ();

        // Simple label
        statusBar.add ( new WebStatusLabel ( "Next Race in: " + m_hoursTillRace + ":" + m_minutesTillRace + ":" + m_secondsTillRace + " " ));
        statusBar.add(new JSeparator(SwingConstants.VERTICAL));
        statusBar.add(new WebStatusLabel(" Next Track: " + m_nextTrack));
        
        WebProgressBar progressBar4 = new WebProgressBar ();
        progressBar4.setIndeterminate ( false );
  
        
        statusBar.add(progressBar4,"END");
        
        this.add(statusBar,BorderLayout.CENTER);
        
		
	}

}
