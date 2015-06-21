package data;

import java.util.ArrayList;

public class Pit {

    public enum Reason { na, Tyres, Fuel, Weather }

    private int m_pitNumber;
    private int m_lap;
    ArrayList<Reason> m_reasons;
    private int m_tyreCondition;
    private int m_fuelLeft;
    private int m_refil;
    private double m_pitTime;

    public Pit(int pitNumber, int lap, ArrayList<Reason> reasons, int tyreCondition, int fuelLeft, int refil, double pitTime)
    {
        m_pitNumber 		= pitNumber;
        m_lap 				= lap;
        m_reasons 			= reasons;
        m_tyreCondition 	= tyreCondition;
        m_fuelLeft 			= fuelLeft;
        m_refil 			= refil;
        m_pitTime 			= pitTime;
    }

    public int getPitNumber() {
        return m_pitNumber;
    }

    public int getLap() {
        return m_lap;
    }

    public ArrayList<Reason> getReasons() {
        return m_reasons;
    }

    public int getTyreCondition() {
        return m_tyreCondition;
    }

    public int getFuelLeft() {
        return m_fuelLeft;
    }

    public int getRefil() {
        return m_refil;
    }

    public double getPitTime() {
        return m_pitTime;
    }
    public static Reason reasonObject(String text)
    {
        Reason reason = Reason.na;
        if(text.contains("The tyres could not do any more laps")) 	         { reason = Reason.Tyres; 	}
        else if(text.contains("No more fuel was left")) 			         { reason = Reason.Fuel; 	}
        else if(text.contains("Tyres change due to the weather change"))     { reason = Reason.Weather;  }
        return reason;
    }

    public static String reasonString(Reason object)
    {
        String type = "na";
        switch(object)
        {
        case Tyres: 	type = "The tyres could not do any more laps";	 break;
        case Fuel:		type = "No more fuel was left";					 break;
        case Weather:   type = "Tyres change due to the weather change"; break;
        default:		type = "na";								  	 break;
        }
        return type;
    }
}
