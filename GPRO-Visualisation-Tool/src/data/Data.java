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
    private HashMap<String, RaceTrack> m_raceTracks;
    private ArrayList<Race> m_races;
    private ArrayList<Driver> m_drivers;
    
    // Complex Storage
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

    public HashMap<String, RaceTrack> raceTracks()
    {
        return m_raceTracks;
    }

    public ArrayList<Race> races()
    {
        return m_races;
    }
    
    public ArrayList<User> users()
    {
        return m_users;
    }

    private void Initialise()
    {   
        m_users = new ArrayList<User>();
        m_raceTracks = new HashMap<String, RaceTrack>();
        m_races = new ArrayList<Race>();
        m_drivers = new ArrayList<Driver>();
        m_hashSeason = new HashMap<Integer, Race>();
        
        InitialiseTracks();
        InitialiseUsers();
        InitialiseRaceAnalysis();
    }
    
    private void InitialiseTracks()
    {
        m_raceTracks = TrackParser.getTracks();
    }
    
    private void InitialiseUsers()
    {
        m_users = FileHandler.readUserFiles();
        for(User user : m_users)
        {
            user.m_races = RaceParser.getRaces(user);
            user.m_drivers = DriverParser.getDrivers(user);
            m_races.addAll(user.getRaces());
            m_drivers.addAll(user.getDrivers());
        }
    }
    
    private void InitialiseRaceAnalysis()
    {
        for(Race race : m_races)
        {
            m_hashSeason.put(race.getSeason(), race);
            if(m_raceTracks.containsKey(race.getName()))
            {
                m_raceTracks.get(race.getName()).m_races.add(race);
                race.m_raceTrack = m_raceTracks.get(race.getName());
                race.analyseRace();
            }
        }
    }
}