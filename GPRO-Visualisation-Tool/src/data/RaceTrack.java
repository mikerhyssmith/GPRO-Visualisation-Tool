package data;

import java.io.Serializable;

enum difficultyListing { veryLow, low, medium, high, veryHigh }
enum suspensionListing { soft, medium, hard }
enum overtakingListing { easy, normal, hard }
enum categoryListing { F1 }

public class RaceTrack implements Serializable {
	private String 				m_name;
	private String 				m_url;
	private float 				m_distance;
	private int 				m_lapNumber;
	private float 				m_lapDistance;
	private float 				m_averageSpeed;
	private int 				m_courners;
	private float 				m_pitTime;
	private int 				m_power;
	private int 				m_handeling;
	private int 				m_acceleration;
	private difficultyListing 	m_downforce;
	private overtakingListing 	m_overtaking;
	private suspensionListing 	m_suspension;
	private difficultyListing 	m_fuelConsumption;
	private difficultyListing 	m_tyreWear;
	private difficultyListing 	m_gripLevel;
	private categoryListing 	m_category;
	
	public RaceTrack(String name, String url, float distance, int lapNumber, float lapDistance, float averageSpeed, int courners, float pitTime,
	 int power, int handeling, int acceleration, difficultyListing downforce, overtakingListing overtaking, suspensionListing suspension,
	 difficultyListing fuelConsumption, difficultyListing tyreWear, difficultyListing gripLevel, categoryListing category)
	{
		m_name 				= name;
		m_url 				= url;
		m_distance			= distance;
		m_lapNumber			= lapNumber;
		m_lapDistance		= lapDistance;
		m_averageSpeed		= averageSpeed;
		m_courners			= courners;
		m_pitTime			= pitTime;
		m_power				= power;
		m_handeling			= handeling;
		m_acceleration		= acceleration;
		m_downforce			= downforce;
		m_overtaking		= overtaking;
		m_suspension		= suspension;
		m_fuelConsumption	= fuelConsumption;
		m_tyreWear			= tyreWear;
		m_gripLevel			= gripLevel;
		m_category			= category;
	}

	public String getM_name() {
		return m_name;
	}

	public String getM_url() {
		return m_url;
	}

	public float getM_distance() {
		return m_distance;
	}

	public int getM_lapNumber() {
		return m_lapNumber;
	}

	public float getM_lapDistance() {
		return m_lapDistance;
	}

	public float getM_averageSpeed() {
		return m_averageSpeed;
	}

	public int getM_courners() {
		return m_courners;
	}

	public float getM_pitTime() {
		return m_pitTime;
	}

	public int getM_power() {
		return m_power;
	}

	public int getM_handeling() {
		return m_handeling;
	}

	public int getAcceleration() {
		return m_acceleration;
	}

	public difficultyListing getM_downforce() {
		return m_downforce;
	}

	public overtakingListing getM_overtaking() {
		return m_overtaking;
	}

	public suspensionListing getM_suspension() {
		return m_suspension;
	}

	public difficultyListing getM_fuelConsumption() {
		return m_fuelConsumption;
	}

	public difficultyListing getM_tyreWear() {
		return m_tyreWear;
	}

	public difficultyListing getM_gripLevel() {
		return m_gripLevel;
	}

	public categoryListing getM_category() {
		return m_category;
	}
	
	
}
