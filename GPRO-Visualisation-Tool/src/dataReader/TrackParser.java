package dataReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import data.RaceTrack;
import analysis.Constants;

/* Class designed to handle the parsing of GPRO html web pages to create RaceTrack Objects */
public class TrackParser {

    /* Main static method to gain a new listing of GPRO tracks */
    public static HashMap<String, RaceTrack> getTracks()
    {
        HashMap<String, RaceTrack> tracks = FileHandler.readTrackFiles();
        if(!(tracks.size() > 0))
            tracks = downloadRaceTracks();
        return tracks;
    }
    
    private static HashMap<String, RaceTrack> downloadRaceTracks()
    {
        HashMap<String, RaceTrack> tracks = new HashMap<String, RaceTrack>();
        ArrayList<String> trackURLS = TrackListParser.getTrackUrls();
        for(String url : trackURLS)
        {
            String htmlStream = FileHandler.downloadRacePage(url);
            RaceTrack track = parseRaceTrack(htmlStream, url);
            if(track != null)
                tracks.put(track.getName(), track);
        }
        // Write these to file
        FileHandler.writeRaceTracks(tracks);
        return tracks;
    }

    /* Constructs the RaceTrack object using the hashtable, the key is the indicator and the value is the value */
    private static RaceTrack createRaceTrack(Hashtable<String, String> data)
    {
        String 							name 				= new String();
        String 							url 				= new String();
        String							location 			= new String();
        float 							distance 			= 0;
        int 							lapNumber 			= 0;
        float 							lapDistance 		= 0;
        float 							averageSpeed 		= 0;
        int 							courners 			= 0;
        float 							pitTime 			= 0;
        int 							power 				= 0;
        int 							handeling 			= 0;
        int 							acceleration 		= 0;
        Constants.difficultyListing 	downforce 			= Constants.difficultyListing.na;
        Constants.overtakingListing 	overtaking 			= Constants.overtakingListing.na;
        Constants.suspensionListing 	suspension 			= Constants.suspensionListing.na;
        Constants.difficultyListing 	fuelConsumption 	= Constants.difficultyListing.na;
        Constants.difficultyListing 	tyreWear 			= Constants.difficultyListing.na;
        Constants.difficultyListing 	gripLevel 			= Constants.difficultyListing.na;
        Constants.categoryListing 		category 			= Constants.categoryListing.na;

        Enumeration<String> dataSet = data.keys();
        while(dataSet.hasMoreElements())
        {
            String key = (String) dataSet.nextElement();
            String value = (String) data.get(key);
            if(key.startsWith("name:"))							{ name 				= value;													}	
            else if(key.startsWith("raceURL:"))					{ url 				= value; 													}
            else if(key.startsWith("Location:"))				{ location			= value;													}
            else if(key.startsWith("Race distance:"))			{ distance 			= Float.parseFloat(value.substring(0, value.length()-2));	}	
            else if(key.startsWith("Laps:"))					{ lapNumber 		= Integer.parseInt(value); 									}
            else if(key.startsWith("Lap distance:"))			{ lapDistance 		= Float.parseFloat(value.substring(0, value.length()-3));	}
            else if(key.startsWith("Average speed:"))			{ averageSpeed 		= Float.parseFloat(value.substring(0, value.length()-5));	}
            else if(key.startsWith("Number of corners:"))		{ courners 			= Integer.parseInt(value); 									}
            else if(key.startsWith("Time in/out of pits:"))		{ pitTime 			= Float.parseFloat(value.substring(0, value.length()-1));	}
            else if(key.startsWith("Power:"))					{ power				= Integer.parseInt(value); 									}
            else if(key.startsWith("Handling:"))				{ handeling			= Integer.parseInt(value); 									}
            else if(key.startsWith("Acceleration:"))			{ acceleration		= Integer.parseInt(value); 									}
            else if(key.startsWith("Downforce:"))				{ downforce 		= Constants.difficultyListingObject(value); 				}
            else if(key.startsWith("Overtaking:"))				{ overtaking 		= Constants.overtakingListingObject(value); 				}
            else if(key.startsWith("Suspension rigidity:"))		{ suspension 		= Constants.suspensionListingObject(value); 				}
            else if(key.startsWith("Fuel consumption:"))		{ fuelConsumption 	= Constants.difficultyListingObject(value); 				}
            else if(key.startsWith("Tyre wear:"))				{ tyreWear 			= Constants.difficultyListingObject(value); 				}
            else if(key.startsWith("Grip level:"))				{ gripLevel 		= Constants.difficultyListingObject(value); 				}
            else if(key.startsWith("Category:"))				{ category 			= Constants.categoryListingObject(value); 					}
        }

        return new RaceTrack(name, url, location, distance, lapNumber, lapDistance, averageSpeed, courners, pitTime, power, handeling, acceleration, downforce, overtaking, suspension, fuelConsumption, tyreWear, gripLevel, category);
    }

    /* Parses through the HTML table of each Race html page */
    private static RaceTrack parseRaceTrackInfo(Node node, String raceName, String raceURL)
    { 
        RaceTrack track = null;
        if(node.nodeName() == "tbody")
        {
            Hashtable<String, String> dataPair = new Hashtable<String, String>();
            for(Node tr : node.childNodes())
            {
                /* Each tr contains 4 td's. They are pairs, the first is the identifier, the second value is the reading */
                if(tr.nodeName() == "tr")
                {
                    String attributeName = new String();
                    for(Node td : tr.childNodes())
                    {
                        if(td.nodeName() == "td")
                        {
                            Element element = (Element) td;
                            if(attributeName.isEmpty())
                                attributeName = element.text().trim();
                            else
                            {
                                String value  = element.text().trim();
                                if(td.hasAttr("title"))
                                    value = td.attr("title").trim();
                                dataPair.put(attributeName, value);
                                attributeName = new String();
                            }
                        }
                    }
                }
            }
            /* These two items are picked up earlier on in the parsing process */
            dataPair.put("name:", raceName);
            dataPair.put("raceURL:", raceURL);
            track = createRaceTrack(dataPair);
        }
        return track;
    }

    /* Long and winded parser due to the difficulty parsing a webpage */
    private static RaceTrack parseRaceTrack(String htmlStream, String raceURL)
    {
        RaceTrack track = null;
        String raceName = new String();
        Document webpage = Jsoup.parse(htmlStream);
        for(Node html : webpage.childNodes())
        {
            if(html.nodeName() == "html")
            {
                for(Node body : html.childNodes())
                {
                    if(body.nodeName() == "body")
                    {
                        for(Node div : body.childNodes())
                        {
                            if(div.nodeName() == "div" && div.attr("id").startsWith("outer"))
                            {
                                for(Node div2 : div.childNodes())
                                {
                                    if(div2.nodeName() == "div" && div2.attr("id").startsWith("content"))
                                    {
                                        for(Node div3 : div2.childNodes())
                                        {
                                            if(div3.nodeName() == "div" && div3.attr("id").startsWith("contentinner"))
                                            {
                                                for(Node div33 : div3.childNodes())
                                                {
                                                    if(div33.nodeName() == "div" && div33.attr("class").startsWith("inner"))
                                                    {
                                                        for(Node div4 : div33.childNodes())
                                                        {
                                                            if(div4.nodeName() == "div" && div4.attr("align").startsWith("center"))
                                                            {
                                                                for(Node table : div4.childNodes())
                                                                {
                                                                    if(table.nodeName() == "h1")
                                                                    {
                                                                        String name = ((Element) table).text();
                                                                        raceName = name;
                                                                        if(name.indexOf("(") != -1)
                                                                            raceName = name.substring(0, name.indexOf("(")-1);
                                                                    }
                                                                    if(table.nodeName() == "table")
                                                                    {
                                                                        for(Node tableBody : table.childNodes())
                                                                        {
                                                                            if(tableBody.nodeName() == "tbody")
                                                                            {
                                                                                for(Node tr : tableBody.childNodes())
                                                                                {
                                                                                    if(tr.nodeName() == "tr")
                                                                                    {
                                                                                        for(Node td : tr.childNodes())
                                                                                        {
                                                                                            if(td.nodeName() == "td")
                                                                                            {
                                                                                                for(Node table2 : td.childNodes())
                                                                                                {
                                                                                                    if(table2.nodeName() == "table")
                                                                                                    {
                                                                                                        for(Node tableBody2 : table2.childNodes())
                                                                                                        {
                                                                                                            if(tableBody2.nodeName() == "tbody")
                                                                                                            {
                                                                                                                for(Node tr2 : tableBody2.childNodes())
                                                                                                                {
                                                                                                                    if(tr2.nodeName() == "tr")
                                                                                                                    {
                                                                                                                        for(Node td2 : tr2.childNodes())
                                                                                                                        {
                                                                                                                            if(td2.nodeName() == "td" && td2.attr("align").startsWith("left"))
                                                                                                                            {
                                                                                                                                for(Node table3 : td2.childNodes())
                                                                                                                                {
                                                                                                                                    if(table3.nodeName() == "table")
                                                                                                                                    {
                                                                                                                                        for(Node tbody3 : table3.childNodes())
                                                                                                                                        {
                                                                                                                                            if(tbody3.nodeName() == "tbody")
                                                                                                                                            {
                                                                                                                                                track = parseRaceTrackInfo(tbody3, raceName, raceURL);
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        return track;
    }
}