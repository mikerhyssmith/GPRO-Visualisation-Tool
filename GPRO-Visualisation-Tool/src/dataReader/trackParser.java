package dataReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import data.RaceTrack;

/* Class designed to handle the parsing of GPRO html web pages to create RaceTrack Objects */
public class trackParser {
		
	/* Main static method to gain a new listing of GPRO tracks */
	public static ArrayList<RaceTrack> getTracks()
	{
		ArrayList<RaceTrack> tracks = new ArrayList<RaceTrack>();
		ArrayList<String> trackURLS = trackListParser.getTrackUrls();
		for(String url : trackURLS)
		{
			String htmlStream = htmlHelper.downloadWebPage(url);
			RaceTrack track = parseTrackInfo(htmlStream, url);
			if(track != null)
				tracks.add(track);
		}
		return tracks;
	}
	
	/* Constructs the RaceTrack object using the hashtable, the key is the indicator and the value is the value */
	private static RaceTrack createRaceTrack(Hashtable<String, String> data)
	{
		String 							name 				= new String();
		String 							url 				= new String();
		String							location 			= new String();
		double 							distance 			= 0;
		int 							lapNumber 			= 0;
		double 							lapDistance 		= 0;
		double 							averageSpeed 		= 0;
		int 							courners 			= 0;
		double 							pitTime 			= 0;
		int 							power 				= 0;
		int 							handeling 			= 0;
		int 							acceleration 		= 0;
		RaceTrack.difficultyListing 	downforce 			= RaceTrack.difficultyListing.na;
		RaceTrack.overtakingListing 	overtaking 			= RaceTrack.overtakingListing.na;
		RaceTrack.suspensionListing 	suspension 			= RaceTrack.suspensionListing.na;
		RaceTrack.difficultyListing 	fuelConsumption 	= RaceTrack.difficultyListing.na;
		RaceTrack.difficultyListing 	tyreWear 			= RaceTrack.difficultyListing.na;
		RaceTrack.difficultyListing 	gripLevel 			= RaceTrack.difficultyListing.na;
		RaceTrack.categoryListing 		category 			= RaceTrack.categoryListing.na;
		
		Enumeration<String> dataSet = data.keys();
		while(dataSet.hasMoreElements())
		{
			String key = (String) dataSet.nextElement();
			String value = (String) data.get(key);
			if(key.startsWith("name:"))							{ name 				= value;													}	
			else if(key.startsWith("raceURL:"))					{ url 				= value; 													}
			else if(key.startsWith("Location:"))				{ location			= value;													}
			else if(key.startsWith("Race distance:"))			{ distance 			= Double.parseDouble(value.substring(0, value.length()-2));	}	
			else if(key.startsWith("Laps:"))					{ lapNumber 		= Integer.parseInt(value); 									}
			else if(key.startsWith("Lap distance:"))			{ lapDistance 		= Double.parseDouble(value.substring(0, value.length()-3));	}
			else if(key.startsWith("Average speed:"))			{ averageSpeed 		= Double.parseDouble(value.substring(0, value.length()-5));	}
			else if(key.startsWith("Number of corners:"))		{ courners 			= Integer.parseInt(value); 									}
			else if(key.startsWith("Time in/out of pits:"))		{ pitTime 			= Double.parseDouble(value.substring(0, value.length()-1));	}
			else if(key.startsWith("Power:"))					{ power				= Integer.parseInt(value); 									}
			else if(key.startsWith("Handling:"))				{ handeling			= Integer.parseInt(value); 									}
			else if(key.startsWith("Acceleration:"))			{ acceleration		= Integer.parseInt(value); 									}
			else if(key.startsWith("Downforce:"))				{ downforce 		= RaceTrack.difficultyListingObject(value); 				}
			else if(key.startsWith("Overtaking:"))				{ overtaking 		= RaceTrack.overtakingListingObject(value); 				}
			else if(key.startsWith("Suspension rigidity:"))		{ suspension 		= RaceTrack.suspensionListingObject(value); 				}
			else if(key.startsWith("Fuel consumption:"))		{ fuelConsumption 	= RaceTrack.difficultyListingObject(value); 				}
			else if(key.startsWith("Tyre wear:"))				{ tyreWear 			= RaceTrack.difficultyListingObject(value); 				}
			else if(key.startsWith("Grip level:"))				{ gripLevel 		= RaceTrack.difficultyListingObject(value); 				}
			else if(key.startsWith("Category:"))				{ category 			= RaceTrack.categoryListingObject(value); 					}
		}
		
		return new RaceTrack(name, url, location, distance, lapNumber, lapDistance, averageSpeed, courners, pitTime, power, handeling, acceleration, downforce, overtaking, suspension, fuelConsumption, tyreWear, gripLevel, category);
	}
	
	/* Parses through the HTML table of each Race html page */
	private static RaceTrack parseRaceTrack(Node node, String raceName, String raceURL)
	{ 
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
			return createRaceTrack(dataPair);
		}
		return null;
	}
	
	/* Long and winded parser due to the difficulty parsing a webpage */
	private static RaceTrack parseTrackInfo(String htmlStream, String raceURL)
	{
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
																																				return parseRaceTrack(tbody3, raceName, raceURL);
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
		return null;
	}
}