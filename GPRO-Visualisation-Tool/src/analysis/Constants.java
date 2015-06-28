package analysis;

import data.Data;
import data.Race;

public class Constants {
    
    public enum Weather { na, VeryCloudy, PartiallyCloudy, Cloudy, Sunny, Rain}
    public enum Tyres   { na, ExtraSoft, Soft, Medium, Hard, Rain } 
    public enum Event   { na, DriverMistake, Pit, CarProblem }
    public enum PitReason { na, Tyres, Fuel, Weather }
    
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
        if(text.contains("Driver Mistake"))     { event = Event.DriverMistake;  }
        else if(text.contains("Pit"))           { event = Event.Pit;            }
        else if(text.contains("Car problem"))   { event = Event.CarProblem;     }
        return event;
    }

    public static String eventString(Event object)
    {
        String type = "na";
        switch(object)
        {
        case DriverMistake:     type = "Driver Mistake";    break;
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
}
