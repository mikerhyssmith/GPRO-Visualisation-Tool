package data;

import java.util.ArrayList;

import dataReader.trackParser;

public class Data {
	private static Data m_instance = null;
	
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
		// Check if Race Track XML File exists, If not download a new listing.
		// For the moment we have no local copy, thus a new listing is required each time.
		m_raceTracks = trackParser.getTracks();
		
		// Read through all .xls files and load races into memory. 
		// Not yet constructed.
		m_races = new ArrayList<Race>();
	}
}
