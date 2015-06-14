package data;

import java.util.ArrayList;
import java.util.HashMap;

import dataReader.RaceParser;
import dataReader.TrackParser;

public class Data {

    private static Data m_instance = null;

    private HashMap<RaceTrack, Race> m_hashRaces;
    private HashMap<String, RaceTrack> m_hashTracks;
    private HashMap<Integer, Race> m_hashSeason;
    private ArrayList<RaceTrack> m_raceTracks;
    private ArrayList<Race> m_races;

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
        m_hashRaces = new HashMap<RaceTrack, Race>();
        m_hashTracks = new HashMap<String, RaceTrack>();
        m_hashSeason = new HashMap<Integer, Race>();
        // Check if Race Track XML File exists, If not download a new listing.
        // For the moment we have no local copy, thus a new listing is required each time.
        m_raceTracks = TrackParser.getTracks();
        for(RaceTrack track : m_raceTracks)
            m_hashTracks.put(track.getName(), track);
        // Read through all .xls files and load races into memory. 
        m_races = RaceParser.getRaces();
        for(Race race : m_races)
        {
            m_hashRaces.put(m_hashTracks.get(race.getName()), race);
            m_hashSeason.put(race.getSeason(), race);
        }
        
    }
}
