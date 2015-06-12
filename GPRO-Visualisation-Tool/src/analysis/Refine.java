package analysis;

import java.util.ArrayList;
import data.*;

public class Refine {

    public static ArrayList<Race> removeWetRaces(ArrayList<Race> races)
    {
        for(Race race: races)
            if(race.getWasWetRace()) { races.remove(race); }
        return races;
    }
}
