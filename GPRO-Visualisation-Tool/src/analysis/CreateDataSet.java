package analysis;

import java.util.ArrayList;

import data.*;

public class CreateDataSet {
    
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
}
