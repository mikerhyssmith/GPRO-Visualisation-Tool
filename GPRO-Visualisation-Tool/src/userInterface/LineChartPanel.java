package userInterface;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.alee.extended.button.WebSwitch;
import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.utils.swing.UnselectableButtonGroup;

public class LineChartPanel extends JPanel implements UIElement {
	
	
	public LineChartPanel(){
		initComponents();
	}

	@Override
	public void initComponents() {
		
		WebLabel choiceLabel = new WebLabel("Visualisation Choice:");
		final WebRadioButton radioButtonA = new WebRadioButton ( "Total Fuel Vs Distance" );
        radioButtonA.setSelected ( true );

        // Static radio button
        final WebRadioButton radioButtonS = new WebRadioButton ( "Some other Vis" );
        radioButtonS.setAnimated ( false );

        // Grouping buttons with custom button group that allows deselection
        UnselectableButtonGroup.group ( radioButtonA, radioButtonS );
        GroupPanel visGroup =  new GroupPanel ( 4, false, radioButtonA, radioButtonS );
        
        
        
        WebLabel showPoints = new WebLabel("Show Points ");
        WebSwitch pointsSwitch = new WebSwitch ();
        pointsSwitch.setRound ( 11 );
        
        WebLabel showTitles = new WebLabel("Show Titles ");
        WebSwitch titlesSwitch = new WebSwitch ();
        titlesSwitch.setRound ( 11 );
        
        

		WebButton button1 = new WebButton ( "Visualise" );
        button1.setLeftRightSpacing ( 40 );
        button1.setRound(11);
        button1.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        this.add(visGroup);
        this.add(Box.createHorizontalStrut(5));
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(Box.createHorizontalStrut(5));
        this.add(showPoints);
        this.add(pointsSwitch);
        this.add(showTitles);
        this.add(titlesSwitch);
        this.add(Box.createHorizontalStrut(5));
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(Box.createHorizontalStrut(5));
        this.add(button1);
		
	}
	

}
