package data;

import java.util.ArrayList;

public class Race {
	//private String m_name; The track stores the name
	private int m_season;
	private ArrayList<Lap> m_laps;
	private ArrayList<Pit> m_pitstops;
	private Risks m_risks;
	private Setup m_setup;
	private PartsWear m_partsWear;
	
	/* I'm not sure if these items should be here */
	private int m_startingFuel;
	private int m_tyresAtEnd;
	private int m_fuelLeft;
	
	public Race(int season, ArrayList<Lap> laps, ArrayList<Pit> pitstops, Risks risks, Setup setup, PartsWear partsWear, int startingFuel, int tyresAtEnd, int fuelLeft)
	{
		m_season = season;
		m_laps = laps;
		m_pitstops = pitstops;
		m_risks = risks;
		m_setup = setup;
		m_partsWear = partsWear;
		m_startingFuel = startingFuel;
		m_tyresAtEnd = tyresAtEnd;
		m_fuelLeft = fuelLeft;
		
		// Perform some calculations, eg average tyre degredation per lap, average fuel consumption
	}

	public int getSeason() {
		return m_season;
	}

	public ArrayList<Lap> getLaps() {
		return m_laps;
	}

	public ArrayList<Pit> getPitstops() {
		return m_pitstops;
	}

	public Risks getRisks() {
		return m_risks;
	}

	public Setup getSetup() {
		return m_setup;
	}

	public PartsWear getPartsWear() {
		return m_partsWear;
	}

	public int getStartingFuel() {
		return m_startingFuel;
	}

	public int getTyresAtEnd() {
		return m_tyresAtEnd;
	}

	public int getFuelLeft() {
		return m_fuelLeft;
	}
	
}
