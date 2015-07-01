package analysis;

import data.Data;
import data.Race;

public class Constants {
    
    public enum Weather             { na, VeryCloudy, PartiallyCloudy, Cloudy, Sunny, Rain}
    public enum Tyres               { na, ExtraSoft, Soft, Medium, Hard, Rain } 
    public enum Event               { na, DriverMistake, Pit, CarProblem }
    public enum PitReason           { na, Tyres, Fuel, Weather }
    public enum difficultyListing   { na, VeryLow, Low, Medium, High, VeryHigh }
    public enum suspensionListing   { na, Soft, Medium, Hard }
    public enum overtakingListing   { na, VeryEasy, Easy, Normal, Hard, VeryHard }
    public enum categoryListing     { na, NonF1, F1 }
    
    // Instance Variable
    private static Constants m_instance = null;
    
    // Global Constants
    public final static int m_fuelTankSize = 180;
    
    // Session Constants
    private float m_maxRaceDistance;
    private float m_minRaceDistance;
    private float m_maxFuelUsed;
    private float m_minFuelUsed;
    
    
    private Constants()
    {
       int maxFuel = 0;
       int minFuel = m_fuelTankSize;
       int maxDistance = 0;
       int minDistance = 300;
       
        for(Race race : Data.getInstance().races())
       {
            float distance = race.getTrack().getDistance();
            float fuelUsed = race.getTotalFuelUsed();
            
            if(distance > maxDistance) { m_maxRaceDistance = distance; }
            else if(distance < minDistance) { m_minRaceDistance = distance; }
            
            if(fuelUsed > maxFuel) { m_maxFuelUsed = fuelUsed; }
            else if(fuelUsed < minFuel) { m_minFuelUsed = fuelUsed; }
       }
 
    }
    
    public Constants getInstance()
    {
        if(m_instance == null)
            m_instance = new Constants();
        return m_instance;
    }
    
    public float getMaxRaceDistance()
    {
        return m_maxRaceDistance;
    }
    
    public float getMinRaceDistance()
    {
        return m_minRaceDistance;
    }
    
    public int getFuelTankSize()
    {
        return m_fuelTankSize;
    }
    
    public float getMaxFuelUsed()
    {
        return m_maxFuelUsed;
    }
    
    public float getMinFuelUsed()
    {
        return m_minFuelUsed;
    }
    
    public static Weather weatherObject(String text)
    {
        Weather weather = Weather.na;
        if(text.contains("Very Cloudy"))                { weather = Weather.VeryCloudy;         }
        else if(text.contains("Particially Cloudy"))    { weather = Weather.PartiallyCloudy;    }
        else if(text.contains("Cloudy"))                { weather = Weather.Cloudy;             }
        else if(text.contains("Sunny"))                 { weather = Weather.Sunny;              }
        else if(text.contains("Rain"))                  { weather = Weather.Rain;               }
        else                                            { System.out.println("Problem Weather String: " + text); }
        return weather;
    }

    public static String weatherString(Weather object)
    {
        String type = "na";
        switch(object)
        {
        case VeryCloudy:        type = "Very Cloudy";           break;
        case PartiallyCloudy:   type = "Particially Cloudy";    break;
        case Cloudy:            type = "Cloudy";                break;
        case Sunny:             type = "Sunny";                 break;
        case Rain:              type = "Rain";                  break;
        default:                type = "na";                    break;
        }
        return type;
    }

    public static Tyres tyresObject(String text)
    {
        Tyres tyres = Tyres.na;
        if(text.contains("Extra Soft"))     { tyres = Tyres.ExtraSoft;  }
        else if(text.contains("Soft"))      { tyres = Tyres.Soft;       }
        else if(text.contains("Medium"))    { tyres = Tyres.Medium;     }
        else if(text.contains("Hard"))      { tyres = Tyres.Hard;       }
        else if(text.contains("Rain"))      { tyres = Tyres.Rain;       }
        else                                { System.out.println("Problem Tyres String: " + text); }
        return tyres;
    }

    public static String tyresString(Tyres object)
    {
        String type = "na";
        switch(object)
        {
        case ExtraSoft:         type = "Extra Soft";            break;
        case Soft:              type = "Soft";                  break;
        case Medium:            type = "Medium";                break;
        case Hard:              type = "Hard";                  break;
        case Rain:              type = "Rain";                  break;
        default:                type = "na";                    break;
        }
        return type;
    }

    public static Event eventObject(String text)
    {
        // na, DriverMistake, Pit 
        Event event = Event.na;
        if(text.contains("Driver mistake"))     { event = Event.DriverMistake;  }
        else if(text.contains("Pit"))           { event = Event.Pit;            }
        else if(text.contains("Car problem"))   { event = Event.CarProblem;     }
        else if(text.contains("-"))             { event = Event.DriverMistake;  }
        else                                    { System.out.println("Problem Event String: " + text); }
        return event;
    }

    public static String eventString(Event object)
    {
        String type = "na";
        switch(object)
        {
        case DriverMistake:     type = "Driver mistake";    break;
        case Pit:               type = "Pit";               break;
        case CarProblem:        type = "Car problem";       break;
        default:                type = "na";                break;
        }
        return type;
    }
    
    public static PitReason pitReasonObject(String text)
    {
        PitReason reason = PitReason.na;
        if(text.contains("The tyres could not do any more laps"))            { reason = PitReason.Tyres;   }
        else if(text.contains("No more fuel was left"))                      { reason = PitReason.Fuel;    }
        else if(text.contains("Tyres change due to the weather change"))     { reason = PitReason.Weather;  }
        return reason;
    }

    public static String pitReasonString(PitReason object)
    {
        String type = "na";
        switch(object)
        {
        case Tyres:     type = "The tyres could not do any more laps";   break;
        case Fuel:      type = "No more fuel was left";                  break;
        case Weather:   type = "Tyres change due to the weather change"; break;
        default:        type = "na";                                     break;
        }
        return type;
    }
    
    public static difficultyListing difficultyListingObject(String text)    
    { 
        difficultyListing type = difficultyListing.na;
        if(text.contains("Very low")) { type = difficultyListing.VeryLow; }
        else if(text.contains("Low")) { type = difficultyListing.Low; }
        else if(text.contains("Medium")) { type = difficultyListing.Medium; }
        else if(text.contains("High")) {type = difficultyListing.High; }
        else if(text.contains("Very high")) { type = difficultyListing.VeryHigh; }
        else                                { System.out.println("Problem Difficulty String: " + text); }
        return type;
    }

    public static String difficultyListingString(difficultyListing object)  
    { 
        String type = "na";
        switch(object)
        {
        case VeryLow:   type = "Very low";  break;
        case Low:       type = "Low";       break;
        case Medium:    type = "Medium";    break;
        case High:      type = "High";      break;
        case VeryHigh:  type = "Very High"; break;
        default :       type = "na";        break;
        }
        return type;
    }

    public static suspensionListing suspensionListingObject(String text)    
    { 
        suspensionListing type = suspensionListing.na;
        if(text.contains("Soft"))           { type = suspensionListing.Soft;    }
        else if(text.contains("Medium"))    { type = suspensionListing.Medium;  }
        else if(text.contains("Hard"))      { type = suspensionListing.Hard;    }
        else                                { System.out.println("Problem Suspension String: " + text); }
        return type;
    }

    public static String suspensionListingString(suspensionListing object)  
    { 
        String type = "na";
        switch(object)
        {
        case Soft:      type = "Soft";      break;
        case Medium:    type = "Medium";    break;
        case Hard:      type = "Hard";      break;
        default:        type = "na";        break;
        }
        return type;
    }

    public static overtakingListing overtakingListingObject(String text)    
    { 
        overtakingListing type = overtakingListing.na;
        if(text.contains("Very easy"))          { type = overtakingListing.VeryEasy;}
        else if(text.contains("Easy"))          { type = overtakingListing.Easy;    }
        else if(text.contains("Normal"))        { type = overtakingListing.Normal;  }
        else if(text.contains("Hard"))          { type = overtakingListing.Hard;    }
        else if(text.contains("Very hard"))     { type = overtakingListing.VeryHard;}
        else                                    { System.out.println("Problem Overtaking String: " + text); }
        return type;
    }

    public static String overtakingListingString(overtakingListing object)  
    { 
        String type = "na";
        switch(object)
        {
        case VeryEasy:  type = "Very easy";     break;
        case Easy:      type = "Easy";          break;
        case Normal:    type = "Normal";        break;
        case Hard:      type = "Hard";          break;
        case VeryHard:  type = "Very hard";     break;
        default:        type = "na";            break;
        }
        return type;
    }

    public static categoryListing categoryListingObject(String text)    
    { 
        categoryListing type = categoryListing.na;
        if(text.contains("F1"))             { type = categoryListing.F1; }
        else if(text.contains("non F1"))    { type = categoryListing.NonF1; }
        else                                { System.out.println("Problem Category String: " + text); }
        return type;
    }

    public static String categoryListingString(categoryListing object)  
    { 
        String type = "na";
        switch(object)
        {
        case F1:        type = "F1";        break;
        case NonF1:     type = "non F1";    break;
        default :       type = "na";        break;
        }
        return type;
    }
}
