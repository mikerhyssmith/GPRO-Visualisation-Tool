package dataReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;

import data.RaceTrack;

/* File to handle the Web Request to download a html page */
public class FileHandler {

    private static final String domainUrl 		    = "http://gpro.net/gb/";
    private static final String mainTrackWebPageURL = domainUrl + "ViewTracks.asp";
    private static final String driversMarket       = "DriversMarket/DriversMarket.xml";
    
    private static final String raceFilesDir        = "Races";
    private static final String raceFilesExtension  = ".xls";
    
    private static final String trackFilesDir       = "Tracks";
    private static final String trackFileExtension  = ".track";

    public static String downloadRaceListPage()
    {
        return readFile(mainTrackWebPageURL);
    }

    public static String downloadRacePage(String url)
    {
        return readFile(domainUrl + url);
    }
    
    public static void writeRaceTracks(ArrayList<RaceTrack> tracks)
    {
        for(RaceTrack track : tracks)
            writeFile(track, trackFilesDir + "/" + track.getName() + trackFileExtension);
    }
    
    public static ArrayList<RaceTrack> readTrackFiles()
    {
        ArrayList<RaceTrack> tracks = new ArrayList<RaceTrack>();
        ArrayList<String> files = FileHandler.collectFiles(trackFilesDir, trackFileExtension);
        @SuppressWarnings("unchecked")
        Class<RaceTrack> RaceTrack = (Class<data.RaceTrack>) new RaceTrack(null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null, null).getClass();
        if(files.size() > 0)
        {
            for(String file : files)
            {
                RaceTrack track = readFile(file, RaceTrack);
                if(track != null)
                    tracks.add(track);
            }
        }
        return tracks;
    }
    
    public static ArrayList<String> readRaceFiles()
    {
        ArrayList<String> xmlStreams = new ArrayList<String>();
        ArrayList<String> files = FileHandler.collectFiles(raceFilesDir, raceFilesExtension);
        if(files.size() > 0)
        {
            for(String file : files)
                xmlStreams.add(FileHandler.readFile(file));
        }
        return xmlStreams;
    }
    
    public static String readDriverMarket()
    {
        return readFile(driversMarket);
    }
    
    private static ArrayList<String> collectFiles(String directory, String fileExtension)
    {
        ArrayList<String> files = new ArrayList<String>();
        File dir = new File(directory);

        for (final File fileEntry : dir.listFiles()) {
            if (fileEntry.exists() &&!fileEntry.isDirectory() && fileEntry.getName() != "") {
                if(fileEntry.getName().endsWith(fileExtension))
                    files.add(directory + "/" + fileEntry.getName());
            } 
        }
        return files;
    }
    
    public static void writeFile(Object object, String fileName)
    {
        try
        {
            FileOutputStream f_out = new FileOutputStream(fileName);
            ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
            obj_out.writeObject ( object );
            obj_out.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    private static <T> T readFile(String location, Class<T> type) 
    {
        T returnObject = null;
        try
        {
            FileInputStream f_in = new FileInputStream(location);
            ObjectInputStream obj_in = new ObjectInputStream (f_in);
    
            // Read an object
            Object obj = obj_in.readObject();
            if(obj != null)
            {
                if(type.isAssignableFrom(obj.getClass()))
                    returnObject = (T) obj;
            }
            obj_in.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return returnObject;
    }

    private static String readFile(String location)
    {
        ClassLoader.getSystemResourceAsStream(location);
        
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
