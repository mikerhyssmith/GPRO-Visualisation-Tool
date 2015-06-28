package analysis;

import java.util.ArrayList;

import data.Data;
import data.Race;
import data.RaceTrack;
import data.User;

public class DataSet {
    
    public static ArrayList<Race> getRaces()
    {
        return (ArrayList<Race>) Data.getInstance().races().clone();
    }
    
    public static ArrayList<RaceTrack> getTracks()
    {
        return (ArrayList<RaceTrack>) Data.getInstance().raceTracks().values();
    }
    
    @SuppressWarnings("unchecked")
    public static ArrayList<String> getTrackNames()
    {
        return (ArrayList<String>) Data.getInstance().raceTracks().keySet();
    }
    
    public static ArrayList<User> getUsers()
    {
        return Data.getInstance().users();
    }
}
