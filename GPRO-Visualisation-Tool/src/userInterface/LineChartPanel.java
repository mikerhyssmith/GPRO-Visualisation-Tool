package userInterface;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import analysis.Constants;

import com.alee.extended.button.WebSwitch;
import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.utils.swing.UnselectableButtonGroup;

import factoryProviders.GraphFactory;

public class LineChartPanel extends JPanel implements UIElement,ActionListener {
	
	 WebRadioButton radioButtonA;
	 WebRadioButton radioButtonS; 
	
	public LineChartPanel(){
		initComponents();
	}

	@Override
	public void initComponents() {
		
		WebLabel choiceLabel = new WebLabel("Visualisation Choice:");
		radioButtonA = new WebRadioButton ( "Total Fuel VS Distance" );
        radioButtonA.setSelected ( true );

        // Static radio button
        radioButtonS = new WebRadioButton ( "Some other Vis" );
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
        button1.addActionListener(this);
        
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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Constants.chartType type = Constants.chartType.na;
		
		if(radioButtonA.isSelected()){
			type = Constants.chartType.TotalFuelVsDistance; 
		}
		
		UserInterface.getDisplayArea().addTab(GraphFactory.produceChart(type, UserState.getDataset()));
		
	}
	

}
