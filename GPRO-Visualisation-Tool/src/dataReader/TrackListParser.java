package dataReader;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

/* Class to handle the download of the main directory of all races. Each race has a unique url which is captured here */
public class TrackListParser {
	
	private static String mainTrackWebPageURL = HTMLHelper.domainUrl + "ViewTracks.asp";
	
	public static ArrayList<String> getTrackUrls()
	{
		String htmlStream = HTMLHelper.downloadWebPage(mainTrackWebPageURL);
		return parseTrackUrls(htmlStream);
	}
	
	private static ArrayList<String> parseTrackUrls(String htmlStream)
	{
		ArrayList<String> urls = new ArrayList<String>();
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
												for(Node div4 : div3.childNodes())
												{
													if(div4.nodeName() == "div" && div4.attr("class").startsWith("inner"))
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
																					if(td.nodeName() == "td" && td.attr("class").startsWith("leftalign"))
																					{
																						for(Node url : td.childNodes())
																						{
																							if(url.nodeName() == "a")
																							{
																								urls.add(HTMLHelper.domainUrl + url.attr("href"));
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
		return urls;
	}	
}