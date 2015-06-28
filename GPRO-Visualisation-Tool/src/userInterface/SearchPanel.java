package userInterface;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JPanel;

import analysis.DataSet;

import com.alee.extended.button.WebSwitch;
import com.alee.extended.list.CheckBoxListModel;
import com.alee.extended.list.WebCheckBoxList;
import com.alee.extended.panel.WebCollapsiblePane;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.scroll.WebScrollPane;

import ernieyu_RangeSlider.slider.RangeSlider;

public class SearchPanel extends JPanel implements UIElement {
	
	private DataSet dataset = new DataSet();
	
	public SearchPanel(){
		this.setLayout(new FlowLayout());
		
		initComponents();
	}

	@Override
	public void initComponents() {
		WebLabel distLabel = new WebLabel ( "Race Distance" );
		
		RangeSlider distanceSlider = new RangeSlider();
		//distanceSlider.setPreferredSize(new Dimension(240, distanceSlider.getPreferredSize().height));
        distanceSlider.setMinimum(200);
        distanceSlider.setMaximum(400);
        distanceSlider.setValue(distanceSlider.getMinimum());
        distanceSlider.setUpperValue(distanceSlider.getMaximum());
        
        WebLabel tempLabel = new WebLabel ( "Race Temperature" );
        
        RangeSlider tempSlider = new RangeSlider();
		//tempSlider.setPreferredSize(new Dimension(240, distanceSlider.getPreferredSize().height));
        tempSlider.setMinimum(0);
        tempSlider.setMaximum(50);
        tempSlider.setValue(tempSlider.getMinimum());
        tempSlider.setUpperValue(tempSlider.getMaximum());
        
        WebLabel errorLabel = new WebLabel("Problem Occured: ");
        WebSwitch errorSwitch = new WebSwitch ();
        errorSwitch.setRound ( 11 );
        
        WebLabel mistakeLabel = new WebLabel("Driver Mistake: ");
        WebSwitch mistakeSwitch = new WebSwitch ();
        mistakeSwitch.setRound ( 11 );
       
        
        WebCheckBoxList tyreCheckBox = new WebCheckBoxList ( createTyreModel () );
        tyreCheckBox.setVisibleRowCount ( 4 );
        tyreCheckBox.setSelectedIndex ( 0 );
        tyreCheckBox.setEditable ( false );
        WebScrollPane tyreScrollPane = new WebScrollPane(tyreCheckBox);
        WebCollapsiblePane tyreCollapsePane = new WebCollapsiblePane("Tyres Used", tyreScrollPane);
        tyreCollapsePane.setExpanded(false);
        
        
        WebCheckBoxList tyreUsageCheckBox = new WebCheckBoxList ( createUsageModel () );
        tyreUsageCheckBox.setVisibleRowCount ( 4 );
        tyreUsageCheckBox.setSelectedIndex ( 0 );
        tyreUsageCheckBox.setEditable ( false );
        WebScrollPane tyreUsageScrollPane = new WebScrollPane(tyreUsageCheckBox);
        WebCollapsiblePane tyreUsageCollapsePane = new WebCollapsiblePane("Tyres Wear Level", tyreUsageScrollPane);
        tyreUsageCollapsePane.setExpanded(false);
        
        
        WebCheckBoxList fuelConsumptionCheckBox = new WebCheckBoxList ( createUsageModel () );
        fuelConsumptionCheckBox.setVisibleRowCount ( 4 );
        fuelConsumptionCheckBox.setSelectedIndex ( 0 );
        fuelConsumptionCheckBox.setEditable ( false );
        WebScrollPane fuelConsumptionPane = new WebScrollPane(fuelConsumptionCheckBox);
        WebCollapsiblePane fuelConsumptionCollapsePane = new WebCollapsiblePane("Fuel Consumption Level", fuelConsumptionPane);
        fuelConsumptionCollapsePane.setExpanded(false);
        
        this.add(distLabel);
        this.add(distanceSlider);
        this.add(Box.createVerticalGlue());
        
        this.add(tempLabel);
        this.add(tempSlider);
        this.add(Box.createVerticalGlue());
        
        this.add(errorLabel);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.add(errorSwitch);
        errorSwitch.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());
        
        this.add(mistakeLabel);
        mistakeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.add(mistakeSwitch);
        mistakeSwitch.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());
        
        
        
        this.add(tyreCollapsePane);
        this.add(Box.createVerticalGlue());
        this.add(tyreUsageCollapsePane);
        this.add(Box.createVerticalGlue());
        this.add(fuelConsumptionCollapsePane);
        this.add(Box.createVerticalGlue());
        
        
        WebButton button1 = new WebButton ( "Search" );
        button1.setMoveIconOnPress ( true );
        button1.setLeftRightSpacing ( 40 );
        button1.setRound(11);
        button1.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.add(button1);
        
		
		
	}

	
	private CheckBoxListModel createTyreModel ()
    {
        final CheckBoxListModel model = new CheckBoxListModel ();
        model.addCheckBoxElement ( "Soft", true );
        model.addCheckBoxElement ( "Medium", true );
        model.addCheckBoxElement ( "Hard", true );
        model.addCheckBoxElement ( "Rain", true );
        return model;
    }
	
	private CheckBoxListModel createUsageModel ()
    {
        final CheckBoxListModel model = new CheckBoxListModel ();
        model.addCheckBoxElement ( "Very Low", true );
        model.addCheckBoxElement ( "Low", true );
        model.addCheckBoxElement ( "Medium", true );
        model.addCheckBoxElement ( "High", true );
        model.addCheckBoxElement ( "Very High", true );
        return model;
    }
	
	private void search(){
		
		
		
	}
}
