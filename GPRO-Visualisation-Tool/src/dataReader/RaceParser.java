package dataReader;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import data.Lap;
import data.Pair;
import data.PartsWear;
import data.Pit;
import data.Race;
import data.Risks;
import data.Setup;
import data.User;
import data.Wear;
import analysis.Constants.Event;
import analysis.Constants.Tyres;
import analysis.Constants.Weather;
import analysis.Constants.PitReason;
import analysis.Constants;

public class RaceParser {

    private static final int tableCount			= 8;
    private static final int topOfTable			= 0;


    private static final int tableTitle 		= 0;
    private static final int tableLaps 			= 1;
    private static final int tableRisks 		= 2;
    private static final int tableSetup 		= 3;
    private static final int tableWear 			= 4;
    private static final int tableStartingFuel 	= 5;
    private static final int tablePits 			= 6;
    private static final int tableFinish		= 7;


    private static final int lapColumnCount		= 8;
    private static final int lapNumber			= 0;
    private static final int lapTime			= 1;
    private static final int lapPos				= 2;
    private static final int lapTyres			= 3;
    private static final int lapWeather			= 4;
    private static final int lapTemp			= 5;
    private static final int LapHumidty			= 6;
    private static final int lapEvent			= 7;


    private static final int riskColumnCount	= 5;
    private static final int riskOvertake		= 0;
    private static final int riskDefend			= 1;
    private static final int riskClearDry		= 2;
    private static final int riskClearWet		= 3;
    private static final int riskMalfunction	= 4;

    private static final int setupColumnCount	= 6;
    private static final int setupFrontWing		= 0;
    private static final int setupRearWing		= 1;
    private static final int setupEngine		= 2;
    private static final int setupBrakes		= 3;
    private static final int setupGear			= 4;
    private static final int setupSuspension	= 5;

    private static final int wearColumnCount	= 12;
    private static final int wearElementCount	= 11;
    private static final int wearPartLevel		= 0;
    private static final int wearPartStart		= 1;
    private static final int wearPartEnd		= 2;

    private static final int wearChasis 		= 1;
    private static final int wearEngine 		= 2;
    private static final int wearFrontWing 		= 3;
    private static final int wearRearWing 		= 4;
    private static final int wearUnderbody 		= 5;
    private static final int wearSidepods 		= 6;
    private static final int wearCooling 		= 7;
    private static final int wearGear 			= 8;
    private static final int wearBrakes 		= 9;
    private static final int wearSuspension 	= 10;
    private static final int wearElectronics 	= 11;


    private static final int pitColumnCount		= 6;
    private static final int pitLap				= 0;
    private static final int pitReason			= 1;
    private static final int pitTyresCondition	= 2;
    private static final int pitFuelLeft		= 3;
    private static final int pitRefil			= 4;
    private static final int pitTime			= 5;


    private static final int endRaceTyres		= 0;
    private static final int endRaceFuel		= 2;


    public static ArrayList<Race> getRaces(User user)
    {
        ArrayList<Race> races = new ArrayList<Race>();
        ArrayList<String> xmlStreams = FileHandler.readRaceFiles(user);
        for(String stream : xmlStreams)
        {
            Race race = parseRace(stream);
            if(race != null)
                races.add(race);
        }
        return races;
    }

    private static Race parseRace(String stream)
    {
        ArrayList<Node> tables = new ArrayList<Node>();

        Document document = Jsoup.parse(stream);
        for(Node html : document.childNodes())
        {
            if(html.nodeName() == "html")
            {
                for(Node body : html.childNodes())
                {
                    if(body.nodeName() == "body")
                    {
                        for(Node table : body.childNodes())
                        {
                            if(table.nodeName() == "table")
                            {
                                for(Node tableBody : table.childNodes())
                                {
                                    if(tableBody.nodeName() == "tbody")
                                    {
                                        tables.add(tableBody);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return parseTables(tables);
    }

    private static Race parseTables(ArrayList<Node> tables)
    {
        Race race = null;
        if(tables.size() == tableCount)
        {
            Pair<String, Integer> description	= parseTitleTable			(tables.get(tableTitle));
            ArrayList<Lap> laps 				= parseLapTable				(tables.get(tableLaps));
            ArrayList<Pit> pitstops				= parsePitstopTable 		(tables.get(tablePits));
            Risks risks							= parseRiskTable			(tables.get(tableRisks));
            Setup setup							= parseSetupTable			(tables.get(tableSetup));
            PartsWear partsWear					= parseWearTable 			(tables.get(tableWear));
            int startingFuel					= parseStartingFuelTable	(tables.get(tableStartingFuel));
            Pair<Integer, Integer> atEnd		= parseEndTable				(tables.get(tableFinish));
            int endingTyres						= atEnd.first;
            int endingFuel						= atEnd.second;
            //This is required later 
            String name     					= description.first;
            int season							= description.second;

            race = new Race(name, season, laps, pitstops, risks, setup, partsWear, startingFuel, endingTyres, endingFuel);
        }
        return race;
    }

    private static Pair<String, Integer> parseTitleTable(Node table)
    {
        String title = new String();
        int season = -1;
        for(Node tr : table.childNodes())
        {
            if(tr.nodeName() == "tr")
            {
                for(Node td : tr.childNodes())
                {
                    if(td.nodeName() == "td")
                    {
                        for(Node b : td.childNodes())
                        {
                            if(b.nodeName() == "b")
                            {
                                Element element =  (Element) b;
                                title = element.text();
                            }
                        }
                    }
                }
            }
        }
        String[] list = title.split(",");
        if(list.length == 2)
        {
            title = list[0];
            if(title.split("\\(").length > 1)
            {
                title = title.split("\\(")[0].trim();
            }
            String temp = list[1].replace("Season", "");
            season = Integer.parseInt(temp.substring(2, temp.length()));
        }
        return new Pair<String, Integer>(title, season);
    }

    private static ArrayList<Lap> parseLapTable(Node table)
    {
        ArrayList<Lap> laps = new ArrayList<Lap>();
        //We need to remove the headings of the table
        if(table.childNodeSize() > topOfTable)
            table.childNode(topOfTable).remove();

        for(Node tr : table.childNodes())
        {
            if(tr.nodeName() == "tr")
            {
                ArrayList<String> elements = new ArrayList<String>();
                for(Node td : tr.childNodes())
                {
                    if(td.nodeName() == "td")
                    {
                        elements.add(((Element) td).text());
                    }
                }
                if(elements.size() == lapColumnCount )
                {
                    int lapNum 				    = Integer.parseInt	       (elements.get(lapNumber));
                    float lapT 			        = parseLapTime	           (elements.get(lapTime));
                    int position 			    = Integer.parseInt	       (elements.get(lapPos));
                    Tyres tyres				    = Constants.tyresObject	   (elements.get(lapTyres));
                    Weather weather			    = Constants.weatherObject  (elements.get(lapWeather));
                    int temperature			    = Integer.parseInt	       (elements.get(lapTemp));
                    int humidity			    = Integer.parseInt	       (elements.get(LapHumidty));
                    ArrayList<Event> events		= parseEvents              (elements.get(lapEvent));

                    laps.add(new Lap(lapNum, lapT, position, tyres, weather, temperature, humidity, events));
                }
            }
        }
        return laps;
    }
    
    private static ArrayList<Event> parseEvents(String string)
    {
        ArrayList<Event> events = new ArrayList<Event>();
        String[] array = string.split(",");
        for(String s : array)
            events.add(Constants.eventObject(s));
        return events;
    }
    
    private static float parseLapTime(String string)
    {
        float time = 0;
        String[] array = string.split(":");
        if(array.length == 2)
        {
            time = time + Integer.parseInt(array[0]) * 60;
            time = time + Float.parseFloat(array[1]);
        }
        return time;
    }

    private static Risks parseRiskTable(Node table)
    {
        Risks risks = null;
        if(table.childNodeSize() > topOfTable)
            table.childNode(topOfTable).remove();
        if(table.childNodeSize() > topOfTable)
            table.childNode(topOfTable).remove();
        if(table.childNodeSize() > topOfTable)
            table.childNode(topOfTable).remove();
        if(table.childNodeSize() > topOfTable)
            table.childNode(topOfTable).remove();
        for(Node tr : table.childNodes())
        {
            if(tr.nodeName() == "tr")
            {
                ArrayList<Integer> elements = new ArrayList<Integer>();
                for(Node td : tr.childNodes())
                {
                    if(td.nodeName() == "td")
                    {
                        String elem = ((Element) td).text();
                        if(!elem.isEmpty())
                            elements.add(Integer.parseInt(elem));
                    }
                }
                if(elements.size() == riskColumnCount)
                {
                    int overtake 		= elements.get(riskOvertake);
                    int defend 			= elements.get(riskDefend);
                    int clearDry 		= elements.get(riskClearDry);
                    int clearWet 		= elements.get(riskClearWet);
                    int malfunction 	= elements.get(riskMalfunction);

                    risks = new Risks(overtake, defend, clearDry, clearWet, malfunction);
                }
            }
        }
        return risks;
    }

    private static Setup parseSetupTable(Node table)
    {
        Setup setup = null;
        if(table.childNodeSize() > topOfTable)
            table.childNode(topOfTable).remove();
        if(table.childNodeSize() > topOfTable)
            table.childNode(topOfTable).remove();
        if(table.childNodeSize() > topOfTable)
            table.childNode(topOfTable).remove();
        if(table.childNodeSize() > topOfTable)
            table.childNode(topOfTable).remove();
        for(Node tr : table.childNodes())
        {
            if(tr.nodeName() == "tr")
            {
                ArrayList<Integer> elements = new ArrayList<Integer>();
                for(Node td : tr.childNodes())
                {
                    if(td.nodeName() == "td")
                    {
                        elements.add(Integer.parseInt(((Element) td).text()));
                    }
                }
                if(elements.size() == setupColumnCount)
                {
                    int frontWing 	= elements.get(setupFrontWing);
                    int rearWing	= elements.get(setupRearWing);
                    int engine 		= elements.get(setupEngine);
                    int brakes		= elements.get(setupBrakes);
                    int gear 		= elements.get(setupGear);
                    int suspension 	= elements.get(setupSuspension);

                    setup = new Setup(frontWing, rearWing, engine, brakes, gear, suspension);
                }
            }
        }
        return setup;
    }

    private static PartsWear parseWearTable(Node table)
    {
        PartsWear partsWear = null;
        ArrayList<ArrayList<Integer>> items = new ArrayList<ArrayList<Integer>>();
        if(table.childNodeSize() > topOfTable)
            table.childNode(topOfTable).remove();
        if(table.childNodeSize() > topOfTable)
            table.childNode(topOfTable).remove();
        for(Node tr : table.childNodes())
        {
            if(tr.nodeName() == "tr")
            {
                ArrayList<Integer> elements = new ArrayList<Integer>();
                elements.add(0);
                for(Node td : tr.childNodes())
                {
                    if(td.nodeName() == "td")
                    {
                        String elem = ((Element) td).text();
                        if(!elem.contains("Car"))
                            elements.add(Integer.parseInt(elem));
                    }
                }
                if(elements.size() == wearColumnCount)
                {	
                    ArrayList<Integer> aspects  = new ArrayList<Integer>();
                    aspects.add(0, null);
                    aspects.add(wearChasis, 		elements.get(wearChasis));
                    aspects.add(wearEngine, 		elements.get(wearEngine));
                    aspects.add(wearFrontWing, 		elements.get(wearFrontWing));
                    aspects.add(wearRearWing, 		elements.get(wearRearWing));
                    aspects.add(wearUnderbody, 		elements.get(wearUnderbody));
                    aspects.add(wearSidepods, 		elements.get(wearSidepods));
                    aspects.add(wearCooling, 		elements.get(wearCooling));
                    aspects.add(wearGear, 			elements.get(wearGear));
                    aspects.add(wearBrakes, 		elements.get(wearBrakes));
                    aspects.add(wearSuspension, 	elements.get(wearSuspension));
                    aspects.add(wearElectronics, 	elements.get(wearElectronics));
                    items.add(aspects);
                }
            }
        }
        ArrayList<Wear> parts = new ArrayList<Wear>();
        parts.add(null);
        for(int i=1; i<items.get(0).size(); i++)
        {
            parts.add(new Wear(items.get(wearPartLevel).get(i), items.get(wearPartStart).get(i), items.get(wearPartEnd).get(i)));
        }

        if(parts.size() == wearElementCount + 1)
            partsWear = new PartsWear( parts.get(wearChasis),  parts.get(wearEngine),  parts.get(wearFrontWing),  parts.get(wearRearWing),  parts.get(wearUnderbody),  parts.get(wearSidepods),  
                    parts.get(wearCooling),  parts.get(wearGear),  parts.get(wearBrakes),  parts.get(wearSuspension), parts.get(wearElectronics));
        return partsWear;
    }

    private static int parseStartingFuelTable(Node table)
    {
        int fuelLeft = 0;
        for(Node tr : table.childNodes())
        {
            if(tr.nodeName() == "tr")
            {
                for(Node td : tr.childNodes())
                {
                    if(td.nodeName() == "td")
                    {
                        String elem = ((Element) td).text();
                        if(!elem.contains("Start fuel"))
                            fuelLeft = Integer.parseInt(elem);
                    }
                }
            }
        }
        return fuelLeft;
    }

    private static ArrayList<Pit> parsePitstopTable(Node table)
    {
        ArrayList<Pit> pits = new ArrayList<Pit>();
        //We need to remove the headings of the table
        if(table.childNodeSize() > topOfTable)
            table.childNode(topOfTable).remove();

        for(Node tr : table.childNodes())
        {
            if(tr.nodeName() == "tr")
            {
                ArrayList<String> elements = new ArrayList<String>();
                for(Node td : tr.childNodes())
                {
                    if(td.nodeName() == "td")
                    {
                        elements.add(((Element) td).text());
                    }
                }
                if(elements.size() == pitColumnCount )
                {
                    int number 			           = elements.size();
                    String lapDesc 		           = (elements.get(pitLap));
                    ArrayList<PitReason> reasons   = parsePitReasons(elements.get(pitReason));
                    int tyreCondition	           = Integer.parseInt		(elements.get(pitTyresCondition));
                    int fuelLeft		           = Integer.parseInt		(elements.get(pitFuelLeft));
                    double time			           = Double.parseDouble	(elements.get(pitTime));
                    int refil			           = 0;
                    if(!elements.get(pitRefil).contains("No refill"))
                        refil = Integer.parseInt		(elements.get(pitRefil).replace("liters", "").trim());

                    String temp = lapDesc.substring(lapDesc.indexOf("Lap")+4, lapDesc.length()-2);
                    int lap = Integer.parseInt(temp);

                    pits.add(new Pit(number, lap, reasons, tyreCondition, fuelLeft, refil, time));
                }
            }
        }
        return pits;
    }
    
    private static ArrayList<PitReason> parsePitReasons(String string)
    {
        ArrayList<PitReason> reasons = new ArrayList<PitReason>();
        for(String reason : string.split(","))
        {
            reasons.add(Constants.pitReasonObject(reason));
        }
        return reasons;
    }

    private static Pair<Integer, Integer> parseEndTable(Node table)
    {
        int tyres = 0;
        int fuel = 0;
        int currentRow = 0;
        for(Node tr : table.childNodes())
        {
            if(tr.nodeName() == "tr")
            {
                for(Node td : tr.childNodes())
                {
                    if(td.nodeName() == "td" && currentRow == endRaceTyres)
                    {
                        String elem = ((Element) td).text();
                        if(!elem.contains("Tyres"))
                            tyres = Integer.parseInt(elem);
                    }
                    if(td.nodeName() == "td" && currentRow == endRaceFuel)
                    {
                        String elem = ((Element) td).text();
                        if(!elem.contains("Fuel"))
                            fuel = Integer.parseInt(elem);
                    }
                }
                currentRow++;
            }
        }
        return new Pair<Integer, Integer>(tyres, fuel);
    }

}
