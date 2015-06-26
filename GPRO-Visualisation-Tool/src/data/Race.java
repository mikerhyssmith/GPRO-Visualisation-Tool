package data;

import java.util.ArrayList;

import data.Lap.Weather;
import data.Lap.Event;

public class Race {
    protected RaceTrack m_raceTrack;
    
    private String m_name;
    private int m_season;
    private ArrayList<Lap> m_laps;
    private ArrayList<Pit> m_pitstops;
    private Risks m_risks;
    private Setup m_setup;
    private PartsWear m_partsWear;
    private Lap.Tyres m_dryTyres;

    private int m_startingFuel;
    private int m_tyresAtEnd;
    private int m_fuelLeft;
    
    /* calculation variables */
    private boolean m_wetRace;
    private boolean m_carProblem;
    private int m_totalFuelUsed;
    // Need total race time 
    private float m_averageHumidity;
    private float m_averageTemperature;
    private float m_averageFuelPerLap;
    private float m_averageFuelPerKM;
    private float m_averageTyreWearPerLap;
    private float m_averageTyreWearPerKM;
    private float m_totalRaceTime;
    private float m_averageLapTime;
    
    public final static int fuelTankSize = 180;

    public Race(String name, int season, ArrayList<Lap> laps, ArrayList<Pit> pitstops, Risks risks, Setup setup, PartsWear partsWear, int startingFuel, int tyresAtEnd, int fuelLeft)
    {
        m_name = name;
        m_season = season;
        m_laps = laps;
        m_pitstops = pitstops;
        m_risks = risks;
        m_setup = setup;
        m_partsWear = partsWear;
        m_startingFuel = startingFuel;
        m_tyresAtEnd = tyresAtEnd;
        m_fuelLeft = fuelLeft;
        
        m_wetRace = false;
        m_carProblem = false;
        m_totalFuelUsed = 0;
        m_averageHumidity = 0;
        m_averageTemperature = 0;
        m_averageFuelPerLap = 0;
        m_averageFuelPerKM = 0;
        m_averageTyreWearPerLap = 0;
        m_averageTyreWearPerKM = 0;
        m_totalRaceTime = 0;
    }
    
    public void analyseRace()
    {
        calculateWetRace();
        calculateTechicalProblem();
        calculateTotalFuelUsed();
        calculateAverageHimidity();
        calculateAverageTemperature();
        calculateAverageTyreWear();
        calculateAverageTime();
    }
    
    private void calculateWetRace()
    {
        for(Lap lap: getLaps())
        {
            if(lap.getWeather() == Weather.Rain) { m_wetRace = true; }
            else { m_dryTyres = lap.getTyres(); }
        }
    }
    
    private void calculateTechicalProblem()
    {
        for(Lap lap: getLaps())
            if(lap.getEvent() == Event.CarProblem) { m_carProblem = true; return; }
    }
    
    private void calculateTotalFuelUsed()
    {
        int fuelUsed = 0;
        int currentFuelLoad = m_startingFuel;
        for(Pit pit : getPitstops())
        {
            fuelUsed = fuelUsed + (currentFuelLoad - pit.getFuelLeft());
            currentFuelLoad = pit.getRefil();
        }
        fuelUsed = fuelUsed + ( currentFuelLoad - m_fuelLeft );
        m_totalFuelUsed = fuelUsed;
        m_averageFuelPerLap = fuelUsed / getLaps().size();
        m_averageFuelPerKM = m_averageFuelPerLap / m_raceTrack.getLapDistance();
    }
    
    private void calculateAverageHimidity()
    {
        int total = 0;
        for(Lap lap: getLaps())
            total = total + lap.getHumidity();
        m_averageHumidity = total / getLaps().size();
    }
    
    private void calculateAverageTemperature()
    {
        int total = 0;
        for(Lap lap: getLaps())
            total = total + lap.getTemperature();
        m_averageTemperature = total / getLaps().size();
    }
    
    private void calculateAverageTyreWear()
    {
        int originalTyreCondition = 100;
        int totalTyreWear = 0;
        int pittedLap = 0;
        for(Pit pit : getPitstops())
        {
            int pitStopTyresCondition = pit.getTyreCondition();
            int lapsCovered = pit.getLap() - pittedLap;
            int stintTyreWear = originalTyreCondition - pitStopTyresCondition;
            totalTyreWear = totalTyreWear + stintTyreWear / lapsCovered;
        }
        m_averageTyreWearPerLap = totalTyreWear / getPitstops().size();
        m_averageTyreWearPerKM = m_averageTyreWearPerLap / m_raceTrack.getLapDistance();
    }
    
    private void calculateAverageTime()
    {
        float totalTime = 0;
        for(Lap lap : getLaps())
        {
            totalTime = totalTime + lap.getLapTime();
        }
        m_totalRaceTime = totalTime;
        m_averageLapTime = totalTime / (getLaps().size() - 1);
    }
    
    public String getName()
    {
        return m_name;
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

    public boolean getWasWetRace() {
        return m_wetRace;
    }

    public boolean getHadCarProblem() {
        return m_carProblem;
    }

    public int getTotalFuelUsed() {
        return m_totalFuelUsed;
    }

    public float getAverageHumidity() {
        return m_averageHumidity;
    }

    public float getAverageTemperature() {
        return m_averageTemperature;
    }

    public float getAverageFuelPerLap() {
        return m_averageFuelPerLap;
    }
    
    public float getAverageFuelPerKM() {
        return m_averageFuelPerKM;
    }

    public float getAverageTyreWearPerLap() {
        return m_averageTyreWearPerLap;
    } 
    
    public float getAverageTyreWearPerKM() {
        return m_averageTyreWearPerKM;
    }
    
    public float getTotalRaceTime() {
        return m_totalRaceTime;
    }
    
    public float getAverageLapTime() {
        return m_averageLapTime;
    }
    
    public Lap.Tyres getDryTyres() {
        return m_dryTyres;
    }
    
    public RaceTrack getTrack() {
        return m_raceTrack;
    }
}