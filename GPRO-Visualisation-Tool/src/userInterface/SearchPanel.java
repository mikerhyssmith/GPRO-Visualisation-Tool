package userInterface;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import com.alee.laf.label.WebLabel;

import ernieyu_RangeSlider.slider.RangeSlider;

public class SearchPanel extends JPanel implements UIElement {
	
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
        
        WebLabel tempLabel = new WebLabel ( "Race Temperature" );
        
        RangeSlider tempSlider = new RangeSlider();
		//tempSlider.setPreferredSize(new Dimension(240, distanceSlider.getPreferredSize().height));
        tempSlider.setMinimum(0);
        tempSlider.setMaximum(50);
        
        WebLabel errorLabel = new WebLabel("Error Commited");
        
        
        
        this.add(distLabel);
        this.add(distanceSlider);
        this.add(tempLabel);
        this.add(tempSlider);
        
        this.add(errorLabel);
        
		
		
	}

}
