package analysis;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;

import data.*;
import analysis.Constants.*;


public class Refiner {
    
    public enum refineBy             { wetRace, dryRace}
    
    
    public static ArrayList<Race> refine(ArrayList<Race> races, EnumSet<refineBy> critera)
    {
        if(critera.contains(refineBy.wetRace))
            races = removeWetRaces(races);
        return null;
    }

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
    
    public static ArrayList<Race> removeTyres(ArrayList<Race> races, Tyres tyres)
    {
        Iterator<Race> i = races.iterator();
        while(i.hasNext())
        {
            Race race = i.next();
            if(race.getDryTyres() == tyres)
                i.remove();
        }
        return races;
    }
    
    
    public static ArrayList<Race> removeFuel(ArrayList<Race> races, difficultyListing fuel)
    {
        Iterator<Race> i = races.iterator();
        while(i.hasNext())
        {
            Race race = i.next();
            if(race.getTrack().getFuelConsumption() == fuel)
                i.remove();
        }
        return races;
    }
    
    public static ArrayList<Race> removeSeason(ArrayList<Race> races, int seasonNo)
    {
        Iterator<Race> i = races.iterator();
        while(i.hasNext())
        {
            Race race = i.next();
            if(race.getSeason() == seasonNo)
                i.remove();
        }
        return races;
    }
    
    private static ArrayList<Race> refineRaceName(ArrayList<Race> races, String raceName)
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
}
