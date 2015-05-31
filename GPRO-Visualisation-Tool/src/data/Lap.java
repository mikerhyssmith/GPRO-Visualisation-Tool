package data;

public class Lap {
	
	public enum Weather { na, VeryCloudy, PartiallyCloudy, Cloudy, Sunny, Rain}
	public enum Tyre 	{ na, ExtraSoft, Soft, Medium, Hard, Rain } 
	public enum Event 	{ na, DriverMistake, Pit }
	
	private int m_lapNumber;
	private String m_lapTime;
	private int m_position;
	private Tyre m_tyres;
	private Weather m_weather;
	private int m_temperature;
	private int m_humidity;
	private Event m_event;
	
	public Lap(int lapNumber, String lapTime, int position, Tyre tyres, Weather weather, int temperature, int humidity, Event event)
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

	public String getLapTime() {
		return m_lapTime;
	}

	public int getPosition() {
		return m_position;
	}

	public Tyre getTyres() {
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
