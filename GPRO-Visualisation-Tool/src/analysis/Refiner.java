package analysis;

import java.util.ArrayList;
import data.*;
import analysis.Constants.Tyres;

public class Refiner {

    public static ArrayList<Race> removeWetRaces(ArrayList<Race> races)
    {
        for(Race race: races)
            if(race.getWasWetRace()) { races.remove(race); }
        return races;
    }
    
    public static ArrayList<Race> refineTyres(ArrayList<Race> races, Tyres tyres)
    {
        for(Race race : races)
        {
            if(race.getDryTyres() != tyres)
                races.remove(race);
        }
        return races;
    }
    
    public static ArrayList<Race> refineSeason(ArrayList<Race> races, int seasonNo)
    {
        for(Race race: races)
        {
            if(race.getSeason() != seasonNo)
                races.remove(race);
        }
        return races;
    }
    
    public static ArrayList<Race> refineRaceName(ArrayList<Race> races, String raceName)
    {
        for(Race race: races)
        {
            if(race.getName() != raceName)
                races.remove(race);
        }
        return races;
    }
    
    public static ArrayList<Race> refineRaceDistance(ArrayList<Race> races, float min, float max)
    {
        for(Race race : races)
        {
            float distance = race.getTrack().getDistance();
            if(distance < min || distance > max) { races.remove(race); }
        }
        return races;
    }
}
