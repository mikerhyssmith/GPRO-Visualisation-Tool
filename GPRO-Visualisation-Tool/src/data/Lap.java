package data;

public class Lap {
	
	public enum Weather { na, VeryCloudy, PartiallyCloudy, Cloudy, Sunny, Rain}
	public enum Tyres 	{ na, ExtraSoft, Soft, Medium, Hard, Rain } 
	public enum Event 	{ na, DriverMistake, Pit }
	
	private int 		m_lapNumber;
	private String 		m_lapTime;
	private int 		m_position;
	private Tyres 		m_tyres;
	private Weather 	m_weather;
	private int 		m_temperature;
	private int 		m_humidity;
	private Event 		m_event;
	
	public Lap(int lapNumber, String lapTime, int position, Tyres tyres, Weather weather, int temperature, int humidity, Event event)
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
	
	public static Weather weatherObject(String text)
	{
		Weather weather = Weather.na;
		if(text == "Very Cloudy") 				{ weather = Weather.VeryCloudy; 		}
		else if(text == "Particially Cloudy") 	{ weather = Weather.PartiallyCloudy; 	}
		else if(text == "Cloudy") 				{ weather = Weather.Cloudy; 			}
		else if(text == "Sunny") 				{ weather = Weather.Sunny; 				}
		else if(text == "Rain") 				{ weather = Weather.Rain; 				}
		return weather;
	}
	
	public static String weatherString(Weather object)
	{
		String type = "na";
		switch(object)
		{
		case VeryCloudy: 		type = "Very Cloudy";			break;
		case PartiallyCloudy:	type = "Particially Cloudy";	break;
		case Cloudy:			type = "Cloudy";				break;
		case Sunny:				type = "Sunny";					break;
		case Rain:				type = "Rain";					break;
		default:				type = "na";					break;
		}
		return type;
	}
	
	public static Tyres tyresObject(String text)
	{
		Tyres tyres = Tyres.na;
		if(text == "Extra Soft") 	{ tyres = Tyres.ExtraSoft; 	}
		else if(text == "Soft") 	{ tyres = Tyres.Soft; 		}
		else if(text == "Medium") 	{ tyres = Tyres.Medium; 	}
		else if(text == "Hard") 	{ tyres = Tyres.Hard; 		}
		else if(text == "Rain") 	{ tyres = Tyres.Rain; 		}
		return tyres;
	}
	
	public static String tyresString(Tyres object)
	{
		String type = "na";
		switch(object)
		{
		case ExtraSoft: 		type = "Extra Soft";			break;
		case Soft:				type = "Soft";					break;
		case Medium:			type = "Medium";				break;
		case Hard:				type = "Hard";					break;
		case Rain:				type = "Rain";					break;
		default:				type = "na";					break;
		}
		return type;
	}
	
	public static Event eventObject(String text)
	{
		// na, DriverMistake, Pit 
		Event event = Event.na;
		if(text == "Driver Mistake") 	{ event = Event.DriverMistake; 	}
		else if(text == "Pit") 			{ event = Event.Pit; 			}
		return event;
	}
	
	public static String eventString(Event object)
	{
		String type = "na";
		switch(object)
		{
			case DriverMistake: 	type = "Driver Mistake";	break;
			case Pit:				type = "Pit";				break;
			default:				type = "na";				break;
		}
		return type;
	}
}