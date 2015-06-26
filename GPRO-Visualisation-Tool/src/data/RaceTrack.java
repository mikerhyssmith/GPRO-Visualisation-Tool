package data;

import java.io.Serializable;
import java.util.ArrayList;

public class RaceTrack implements Serializable {
    private static final long serialVersionUID = 3924515276085632600L;

    public enum difficultyListing 	{ na, VeryLow, Low, Medium, High, VeryHigh }
    public enum suspensionListing 	{ na, Soft, Medium, Hard }
    public enum overtakingListing 	{ na, VeryEasy, Easy, Normal, Hard, VeryHard }
    public enum categoryListing 	{ na, NonF1, F1 }
    
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
        return difficultyListingString(m_downforce);
    }

    public String getOvertakingString() {
        return overtakingListingString(m_overtaking);
    }

    public String getSuspensionString() {
        return suspensionListingString(m_suspension);
    }

    public String getFuelConsumptionString() {
        return difficultyListingString(m_fuelConsumption);
    }

    public String getTyreWearString() {
        return difficultyListingString(m_tyreWear);
    }

    public String getGripLevelString() {
        return difficultyListingString(m_gripLevel);
    }

    public String getCategoryString() {
        return categoryListingString(m_category);
    }
    
    public ArrayList<Race> getRaces()
    {
        return m_races;
    }

    public static difficultyListing difficultyListingObject(String text) 	
    { 
        difficultyListing type = difficultyListing.na;
        if(text.contains("Very low")) { type = difficultyListing.VeryLow; }
        else if(text.contains("Low")) { type = difficultyListing.Low; }
        else if(text.contains("Medium")) { type = difficultyListing.Medium; }
        else if(text.contains("High")) {type = difficultyListing.High; }
        else if(text.contains("Very high")) { type = difficultyListing.VeryHigh; }
        return type;
    }

    public static String difficultyListingString(difficultyListing object) 	
    { 
        String type = "na";
        switch(object)
        {
        case VeryLow: 	type = "Very low";	break;
        case Low: 		type = "Low";		break;
        case Medium: 	type = "Medium";	break;
        case High:		type = "High";		break;
        case VeryHigh: 	type = "Very High";	break;
        default :		type = "na";		break;
        }
        return type;
    }

    public static suspensionListing suspensionListingObject(String text) 	
    { 
        suspensionListing type = suspensionListing.na;
        if(text.contains("Soft")) 			{ type = suspensionListing.Soft; 	}
        else if(text.contains("Medium")) 	{ type = suspensionListing.Medium; 	}
        else if(text.contains("Hard")) 	    { type = suspensionListing.Hard; 	}
        return type;
    }

    public static String suspensionListingString(suspensionListing object) 	
    { 
        String type = "na";
        switch(object)
        {
        case Soft: 		type = "Soft";		break;
        case Medium:	type = "Medium";	break;
        case Hard:		type = "Hard";		break;
        default:		type = "na";		break;
        }
        return type;
    }

    public static overtakingListing overtakingListingObject(String text) 	
    { 
        overtakingListing type = overtakingListing.na;
        if(text.contains("Very easy"))			{ type = overtakingListing.VeryEasy;}
        else if(text.contains("Easy"))			{ type = overtakingListing.Easy; 	}
        else if(text.contains("Normal"))		{ type = overtakingListing.Normal; 	}
        else if(text.contains("Hard"))			{ type = overtakingListing.Hard; 	}
        else if(text.contains("Very hard"))	    { type = overtakingListing.VeryHard;}
        return type;
    }

    public static String overtakingListingString(overtakingListing object) 	
    { 
        String type = "na";
        switch(object)
        {
        case VeryEasy: 	type = "Very easy"; 	break;
        case Easy: 		type = "Easy"; 			break;
        case Normal: 	type = "Normal"; 		break;
        case Hard: 		type = "Hard"; 			break;
        case VeryHard: 	type = "Very hard"; 	break;
        default:		type = "na"; 			break;
        }
        return type;
    }

    public static categoryListing categoryListingObject(String text) 	
    { 
        categoryListing type = categoryListing.na;
        if(text.contains("F1")) 			{ type = categoryListing.F1; }
        else if(text.contains("non F1")) 	{ type = categoryListing.NonF1; }
        return type;
    }

    public static String categoryListingString(categoryListing object) 	
    { 
        String type = "na";
        switch(object)
        {
        case F1: 		type = "F1"; 		break;
        case NonF1: 	type = "non F1"; 	break;
        default : 		type = "na";		break;
        }
        return type;
    }
}
