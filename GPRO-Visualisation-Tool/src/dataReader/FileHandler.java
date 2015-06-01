package dataReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/* File to handle the Web Request to download a html page */
public class FileHandler {
	
	private static final String domainUrl 		= "http://gpro.net/gb/";
	private static String mainTrackWebPageURL 	= domainUrl + "ViewTracks.asp";
	private static final String raceFilesDir 	= "Races";
	
	public static String downloadRaceListPage()
	{
		return readFile(mainTrackWebPageURL);
	}
	
	public static String downloadRacePage(String url)
	{
		return readFile(domainUrl + url);
	}
	
	public static ArrayList<String> readRaceFiles()
	{
		ArrayList<String> files = new ArrayList<String>();
		File dir = new File(raceFilesDir);
		
		for (final File fileEntry : dir.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	if(fileEntry.getName().endsWith(".xls"))
	            	files.add(readFile(raceFilesDir + "/" + fileEntry.getName()));
	        } 
	    }
		return files;
	}
	
	private static String readFile(String location)
	{
		String stream = new String();
		try {
			BufferedReader reader = null;
			if(location.startsWith("http"))
				reader = new BufferedReader( new InputStreamReader(new URL(location).openStream(), "UTF-8"));
			else
				reader = new BufferedReader( new InputStreamReader(new FileInputStream(location), "UTF-8") );
			
			
		    for (String line; (line = reader.readLine()) != null;) {
		    	stream += line;
		    }
		    if(!location.startsWith("http"))
		    	stream = "<html><head></head><body>" + stream + "</body></html>";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream;
	}
}
