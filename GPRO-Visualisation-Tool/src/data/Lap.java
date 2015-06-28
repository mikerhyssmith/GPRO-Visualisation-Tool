package data;

import analysis.Constants.Event;
import analysis.Constants.Tyres;
import analysis.Constants.Weather;

public class Lap {

    private int 		m_lapNumber;
    private float 		m_lapTime;
    private int 		m_position;
    private Tyres 		m_tyres;
    private Weather 	m_weather;
    private int 		m_temperature;
    private int 		m_humidity;
    private Event 		m_event;

    public Lap(int lapNumber, float lapTime, int position, Tyres tyres, Weather weather, int temperature, int humidity, Event event)
    {
        m_lapNumber		= lapNumber;
        m_lapTime 		= lapTime;
        m_position 		= position;
        m_tyres 		= tyres;
        m_weather 		= weather;
        m_temperature 	= temperature;
        m_humidity 		= humidity;
        m_event 		= event;
    }

    public int getLapNumber() {
        return m_lapNumber;
    }

    public float getLapTime() {
        return m_lapTime;
    }

    public int getPosition() {
        return m_position;
    }

    public Tyres getTyres() {
        return m_tyres;
    }

    public Weather getWeather() {
        return m_weather;
    }

    public int getTemperature() {
        return m_temperature;
    }

    public int getHumidity() {
        return m_humidity;
    }

    public Event getEvent() {
        return m_event;
    }
}
