package analysis;

import java.util.ArrayList;
import java.util.Iterator;

import data.*;
import analysis.Constants.*;

public class Refiner {

    public static ArrayList<Race> removeWetRaces(ArrayList<Race> races)
    {
        Iterator<Race> i = races.iterator();
        while(i.hasNext())
        {
            Race race = i.next();
            if(race.getWasWetRace()) 
                i.remove(); 
        }
        return races;
    }
    
    public static ArrayList<Race> refineTyres(ArrayList<Race> races, Tyres tyres)
    {
        Iterator<Race> i = races.iterator();
        while(i.hasNext())
        {
            Race race = i.next();
            if(race.getDryTyres() != tyres)
                i.remove();
        }
        return races;
    }
    
    public static ArrayList<Race> refineSeason(ArrayList<Race> races, int seasonNo)
    {
        Iterator<Race> i = races.iterator();
        while(i.hasNext())
        {
            Race race = i.next();
            if(race.getSeason() != seasonNo)
                i.remove();
        }
        return races;
    }
    
    public static ArrayList<Race> refineRaceName(ArrayList<Race> races, String raceName)
    {
        Iterator<Race> i = races.iterator();
        while(i.hasNext())
        {
            Race race = i.next();
            if(race.getName() != raceName)
                i.remove();
        }
        return races;
    }
    
    public static ArrayList<Race> refineRaceDistance(ArrayList<Race> races, float min, float max)
    {
        Iterator<Race> i = races.iterator();
        while(i.hasNext())
        {
            Race race = i.next();
            float distance = race.getTrack().getDistance();
            if(distance < min || distance > max)
                i.remove();
        }
        return races;
    }
    
    public static ArrayList<Race> refineFuel(ArrayList<Race> races, difficultyListing difficulty)
    {
        Iterator<Race> i = races.iterator();
        while(i.hasNext())
        {
            Race race = i.next();
            if(race.getTrack().getFuelConsumption() != difficulty)
                i.remove();
        }
        return races;
    }
}
