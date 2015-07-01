package data;

import java.io.Serializable;
import java.util.ArrayList;
import analysis.Constants;
import analysis.Constants.difficultyListing;
import analysis.Constants.overtakingListing;
import analysis.Constants.suspensionListing;
import analysis.Constants.categoryListing;

public class RaceTrack implements Serializable {
    private static final long serialVersionUID = 3924515276085632600L;

    
    protected ArrayList<Race>   m_races;

    private String 				m_name;
    private String 				m_url;
    private String				m_location;
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

    public RaceTrack(String name, String url, String location, float distance, int lapNumber, float lapDistance, float averageSpeed, int courners, float pitTime,
            int power, int handeling, int acceleration, difficultyListing downforce, overtakingListing overtaking, suspensionListing suspension,
            difficultyListing fuelConsumption, difficultyListing tyreWear, difficultyListing gripLevel, categoryListing category)
    {
        m_races             = new ArrayList<Race>();
        
        m_name 				= name;
        m_url 				= url;
        m_location			= location;
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

    public String getName() {
        return m_name;
    }

    public String getUrl() {
        return m_url;
    }

    public String getLocation() {
        return m_location;
    }

    public float getDistance() {
        return m_distance;
    }

    public int getLapNumber() {
        return m_lapNumber;
    }

    public float getLapDistance() {
        return m_lapDistance;
    }

    public float getAverageSpeed() {
        return m_averageSpeed;
    }

    public int getCourners() {
        return m_courners;
    }

    public float getPitTime() {
        return m_pitTime;
    }

    public int getPower() {
        return m_power;
    }

    public int getHandeling() {
        return m_handeling;
    }

    public int getAcceleration() {
        return m_acceleration;
    }

    public difficultyListing getDownforce() {
        return m_downforce;
    }

    public overtakingListing getOvertaking() {
        return m_overtaking;
    }

    public suspensionListing getSuspension() {
        return m_suspension;
    }

    public difficultyListing getFuelConsumption() {
        return m_fuelConsumption;
    }

    public difficultyListing getTyreWear() {
        return m_tyreWear;
    }

    public difficultyListing getGripLevel() {
        return m_gripLevel;
    }

    public categoryListing getCategory() {
        return m_category;
    }

    public String getDownforceString() {
        return Constants.difficultyListingString(m_downforce);
    }

    public String getOvertakingString() {
        return Constants.overtakingListingString(m_overtaking);
    }

    public String getSuspensionString() {
        return Constants.suspensionListingString(m_suspension);
    }

    public String getFuelConsumptionString() {
        return Constants.difficultyListingString(m_fuelConsumption);
    }

    public String getTyreWearString() {
        return Constants.difficultyListingString(m_tyreWear);
    }

    public String getGripLevelString() {
        return Constants.difficultyListingString(m_gripLevel);
    }

    public String getCategoryString() {
        return Constants.categoryListingString(m_category);
    }
    
    public ArrayList<Race> getRaces()
    {
        return m_races;
    }

}
