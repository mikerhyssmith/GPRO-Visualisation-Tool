package data;

import java.util.ArrayList;

public class Data {
	private static Data m_instance = null;
	
	private ArrayList<RaceTrack> m_raceTracks;
	private ArrayList<Race> m_races;
	
	private Data()
	{
		m_raceTracks = new ArrayList<RaceTrack>();
		m_races = new ArrayList<Race>();
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
}
