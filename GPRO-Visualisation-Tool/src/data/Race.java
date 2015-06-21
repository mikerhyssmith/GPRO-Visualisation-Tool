package data;

import java.util.ArrayList;

import data.Lap.Event;
import data.Lap.Weather;

public class Race {
    private String m_name;
    private int m_season;
    private ArrayList<Lap> m_laps;
    private ArrayList<Pit> m_pitstops;
    private Risks m_risks;
    private Setup m_setup;
    private PartsWear m_partsWear;

    private int m_startingFuel;
    private int m_tyresAtEnd;
    private int m_fuelLeft;
    
    /* calculation variables */
    private boolean m_wetRace;
    private boolean m_carProblem;
    private int m_totalFuelUsed;
    private float m_averageHumidity;
    private float m_averageTemperature;
    private float m_averageFuelPerLap;
    private float m_averageTyreWearPerLap;
    
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
        m_averageTyreWearPerLap = 0;

        analyseRace();
    }
    
    private void analyseRace()
    {
        calculateWetRace();
        calculateTechicalProblem();
        calculateTotalFuelUsed();
        calculateAverageHimidity();
        calculateAverageTemperature();
        calculateAverageTyreWearPerLap();
        calculateAverageLapTime();
    }
    
    private void calculateWetRace()
    {
        for(Lap lap: getLaps())
            if(lap.getWeather() == Weather.Rain) { m_wetRace = true; return; }
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
    
    private void calculateAverageTyreWearPerLap()
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
    }
    
    private void calculateAverageLapTime()
    {
        
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

    public float getAverageTyreWearPerLap() {
        return m_averageTyreWearPerLap;
    }

    
    
}
