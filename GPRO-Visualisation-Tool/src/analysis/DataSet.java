package analysis;

import java.util.ArrayList;
import java.util.Collection;

import data.Data;
import data.Race;
import data.RaceTrack;
import data.User;

public class DataSet {
    
    @SuppressWarnings("unchecked")
    public static ArrayList<Race> getRaces()
    {
        return (ArrayList<Race>) Data.getInstance().races().clone();
    }
    
    @SuppressWarnings("unchecked")
    public static Collection<RaceTrack> getTracks()
    {
        return Data.getInstance().raceTracks().values();
    }
    
    @SuppressWarnings("unchecked")
    public static ArrayList<String> getTrackNames()
    {
        return (ArrayList<String>) ((ArrayList<String>) Data.getInstance().raceTracks().keySet()).clone();
    }
    
    @SuppressWarnings("unchecked")
    public static ArrayList<User> getUsers()
    {
        return (ArrayList<User>) Data.getInstance().users().clone();
    }
}
