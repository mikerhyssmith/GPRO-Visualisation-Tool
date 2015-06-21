package data;

import java.util.ArrayList;
import java.util.HashMap;

import dataReader.FileHandler;
import dataReader.RaceParser;
import dataReader.TrackParser;
import dataReader.DriverParser;

public class Data {

    private static Data m_instance = null;

    // Basic Data
    private ArrayList<User> m_users;
    private ArrayList<RaceTrack> m_raceTracks;
    private ArrayList<Race> m_races;
    private ArrayList<Driver> m_drivers;
    
    // Complex Storage
    private HashMap<RaceTrack, Race> m_hashRaces;
    private HashMap<String, RaceTrack> m_hashTracks;
    private HashMap<Integer, Race> m_hashSeason;

    private Data()
    {
        Initialise();
    }

    public static Data getInstance()
    {
        if(m_instance == null)
            m_instance = new Data();
        return m_instance;
    }

    public ArrayList<RaceTrack> raceTracks()
    {
        return m_raceTracks;
    }

    public ArrayList<Race> races()
    {
        return m_races;
    }

    private void Initialise()
    {   
        m_users = new ArrayList<User>();
        m_raceTracks = new ArrayList<RaceTrack>();
        m_races = new ArrayList<Race>();
        m_drivers = new ArrayList<Driver>();
        m_hashRaces = new HashMap<RaceTrack, Race>();
        m_hashTracks = new HashMap<String, RaceTrack>();
        m_hashSeason = new HashMap<Integer, Race>();
        
        InitialiseUsers();
        InitialiseTracks();
        InitialiseRaces();
    }
    
    private void InitialiseUsers()
    {
        m_users = FileHandler.readUserFiles();
    }
    
    private void InitialiseTracks()
    {
        m_raceTracks = TrackParser.getTracks();
        for(RaceTrack track : m_raceTracks)
            m_hashTracks.put(track.getName(), track);
    }
    
    private void InitialiseRaces()
    {
        for(User user : m_users)
        {
            user.m_races = RaceParser.getRaces(user);
            user.m_drivers = DriverParser.getDrivers(user);
            m_races.addAll(user.getRaces());
            m_drivers.addAll(user.getDrivers());
        }
       
        for(Race race : m_races)
        {
            m_hashRaces.put(m_hashTracks.get(race.getName()), race);
            m_hashSeason.put(race.getSeason(), race);
        }
    }
}
