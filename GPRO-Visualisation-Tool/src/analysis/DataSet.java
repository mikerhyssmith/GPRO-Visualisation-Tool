package analysis;

import java.util.ArrayList;

import data.*;

public class DataSet {
    
    public static ArrayList<Race> getRaces()
    {
        return Data.getInstance().races();
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
