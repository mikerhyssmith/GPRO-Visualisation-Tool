package userInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;

import com.alee.laf.combobox.WebComboBox;

import factoryProviders.GraphFactory;

public class VisualisationOptionsPanel extends JPanel implements UIElement,ItemListener {
	
	 final static String LINEPANEL = "Line Charts";
	 final static String BARPANEL = "Bar Charts";
	 final static String SCATTERPANEL = "Scatter Charts";
	 private  JPanel cards;
	 private CardLayout cLayout;
	
	public VisualisationOptionsPanel(){
		cLayout = new CardLayout();
		this.setLayout(new BorderLayout());
		
		initComponents();
	}

	@Override
	public void initComponents() {
		JPanel comboPanel = new JPanel();
		String[] items = { "Line Charts", "Bar Charts", "Scatter Charts"};
        WebComboBox comboBox = new WebComboBox ( items );
        comboBox.addItemListener(this);
        comboPanel.add(comboBox, BorderLayout.PAGE_START);
        
        
        LineChartPanel linePanel = new LineChartPanel();
        BarChartPanel barPanel = new BarChartPanel();
        ScatterGraphPanel scatterPanel = new ScatterGraphPanel();
        
        cards = new JPanel();
        cards.setLayout(cLayout);
        cards.add(linePanel, LINEPANEL);
        cards.add(barPanel, BARPANEL);
        cards.add(scatterPanel,SCATTERPANEL);
        
        this.add(comboPanel,BorderLayout.PAGE_START);
        this.add(cards, BorderLayout.CENTER);
		
	}

	@Override
	public void itemStateChanged(ItemEvent evt) {
		
        cLayout.show(cards, (String)evt.getItem());
		
	}

	

}


