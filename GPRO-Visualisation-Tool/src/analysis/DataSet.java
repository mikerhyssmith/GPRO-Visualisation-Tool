package analysis;

import java.util.ArrayList;

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
    public static ArrayList<RaceTrack> getTracks()
    {
        return (ArrayList<RaceTrack>) ((ArrayList<RaceTrack>) Data.getInstance().raceTracks().values()).clone();
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
