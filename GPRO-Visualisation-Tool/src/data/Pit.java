package data;

import data.Lap.Tyres;

public class Pit {
	
	public enum Reason { na, Tyres, Fuel }
	
	private int m_pitNumber;
	private int m_lap;
	private Reason m_reason;
	private int m_tyreCondition;
	private int m_fuelLeft;
	private int m_refil;
	private double m_pitTime;
	
	public Pit(int pitNumber, int lap, Reason reason, int tyreCondition, int fuelLeft, int refil, double pitTime)
	{
		m_pitNumber 		= pitNumber;
		m_lap 				= lap;
		m_reason 			= reason;
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

	public Reason getReason() {
		return m_reason;
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
		if(text == "The tyres could not do any more laps") 	{ reason = Reason.Tyres; 	}
		else if(text == "No more fuel was left") 			{ reason = Reason.Fuel; 	}
		return reason;
	}
	
	public static String reasonString(Reason object)
	{
		String type = "na";
		switch(object)
		{
		case Tyres: 	type = "The tyres could not do any more laps";	break;
		case Fuel:		type = "No more fuel was left";					break;
		default:		type = "na";									break;
		}
		return type;
	}
}
