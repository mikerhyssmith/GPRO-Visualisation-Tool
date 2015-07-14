package analysis;

import java.util.ArrayList;

import data.*;

public class AxisHandler {
    
    public static ArrayList<Integer> getFuelLeftAtEndOfRace(ArrayList<Race> races)
    {
        ArrayList<Integer> fuel = new ArrayList<Integer>();
        for(Race race: races)
            fuel.add(race.getFuelLeft());
        return fuel;
    }
    
    public static ArrayList<Integer> getFuelUsed(ArrayList<Race> races)
    {
        ArrayList<Integer> fuelUsage = new ArrayList<Integer>();
        for(Race race: races)
            fuelUsage.add(race.getTotalFuelUsed());
        return fuelUsage;
    }
    
    public static ArrayList<ArrayList<Float>> getLapTimes(ArrayList<Race> races)
    {
        ArrayList<ArrayList<Float>> timings = new ArrayList<ArrayList<Float>>();
        for(Race race : races)
        {
            ArrayList<Float> times = new ArrayList<Float>();
            for(Lap lap : race.getLaps())
                times.add(lap.getLapTime());
            timings.add(times);
        }
        return timings;
    }
}
