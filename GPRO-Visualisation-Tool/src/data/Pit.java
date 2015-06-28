package data;

import java.util.ArrayList;
import analysis.Constants.PitReason;

public class Pit {

    private int m_pitNumber;
    private int m_lap;
    ArrayList<PitReason> m_reasons;
    private int m_tyreCondition;
    private int m_fuelLeft;
    private int m_refil;
    private double m_pitTime;

    public Pit(int pitNumber, int lap, ArrayList<PitReason> reasons, int tyreCondition, int fuelLeft, int refil, double pitTime)
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

    public ArrayList<PitReason> getReasons() {
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
    
}
