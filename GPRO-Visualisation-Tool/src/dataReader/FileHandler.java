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
import java.util.HashMap;

import data.RaceTrack;
import data.User;

/* File to handle the Web Request to download a html page */
public class FileHandler {

    private static final String domainUrl 		    = "http://gpro.net/gb/";
    private static final String mainTrackWebPageURL = domainUrl + "ViewTracks.asp";
    private static final String driversMarket       = "DriversMarket/DriversMarket.xml";
    
    private static final String raceFilesExtension  = ".xls";
    private static final String trackFileExtension  = ".track";
    private static final String driverFileExtension = ".xls";
    private static final String usersFileExtention  = ".user";
    
    private static final String trackFilesDir       = "Tracks";
    private static final String usersDir            = "Users";
    
    private static String usersDir(String username) { return usersDir + "/" + username; };
    private static String userRaceFiles(String username) { return usersDir(username) + "/Races"; }
    private static String userDriverFiles(String username) { return usersDir(username) + "/Drivers"; }

    public static String downloadRaceListPage()
    {
        return readFile(mainTrackWebPageURL);
    }

    public static String downloadRacePage(String url)
    {
        return readFile(domainUrl + url);
    }
    
    public static void writeRaceTracks(HashMap<String, RaceTrack> tracks)
    {
        for(String trackName : tracks.keySet())
            writeFile(tracks.get(trackName), trackFilesDir + "/" + trackName + trackFileExtension);
    }
    
    public static void writeUser(User user)
    {
        writeFile(user, usersDir(user.getName()) + "/" + user.getName() + usersFileExtention);
    }
    
    public static HashMap<String, RaceTrack> readTrackFiles()
    {
        HashMap<String, RaceTrack> tracks = new HashMap<String, RaceTrack>();
        ArrayList<String> files = FileHandler.collectFiles(trackFilesDir, trackFileExtension);
        @SuppressWarnings("unchecked")
        Class<RaceTrack> RaceTrack = (Class<data.RaceTrack>) new RaceTrack(null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null, null).getClass();
        if(files.size() > 0)
        {
            for(String file : files)
            {
                RaceTrack track = readFile(file, RaceTrack);
                if(track != null)
                    tracks.put(track.getName(), track);
            }
        }
        return tracks;
    }
    
    public static ArrayList<User> readUserFiles()
    {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> userFiles = new ArrayList<String>();
        ArrayList<String> userDirectories = FileHandler.collectDirectories(usersDir);
        for(String dir : userDirectories)
        {
            ArrayList<String> files = FileHandler.collectFiles(dir, usersFileExtention);
            if(files.size() > 0)
            {
                userFiles.add(files.get(0));
            }
        }
        @SuppressWarnings("unchecked")
        Class<User> User = (Class<data.User>) new User(null).getClass();
        if(userFiles.size() > 0)
        {
            for(String file : userFiles)
            {
                User user = readFile(file, User);
                if(user != null)
                    users.add(user);
            }
        }
        return users;
    }
    
    public static ArrayList<String> readRaceFiles(User user)
    {
        ArrayList<String> xmlStreams = new ArrayList<String>();
        ArrayList<String> files = FileHandler.collectFiles(userRaceFiles(user.getName()), raceFilesExtension);
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
    
    public static ArrayList<String> readDriverFiles(User user)
    {
        ArrayList<String> xmlStreams = new ArrayList<String>();
        ArrayList<String> files = FileHandler.collectFiles(userDriverFiles(user.getName()), driverFileExtension);
        if(files.size() > 0)
        {
            for(String file : files)
                xmlStreams.add(FileHandler.readFile(file));
        }
        return xmlStreams;
    }
    
    private static ArrayList<String> collectFiles(String directory, String fileExtension)
    {
        ArrayList<String> files = new ArrayList<String>();
        File dir = new File(directory);
        if(dir.isDirectory())
        {
            for (final File fileEntry : dir.listFiles()) {
                if (fileEntry.exists() &&!fileEntry.isDirectory() && fileEntry.getName() != "") 
                {
                    if(fileEntry.getName().endsWith(fileExtension))
                        files.add(directory + "/" + fileEntry.getName());
                } 
            }   
        }
        return files;
    }
    
    private static ArrayList<String> collectDirectories(String directory)
    {
        ArrayList<String> directories = new ArrayList<String>();
        File dir = new File(directory);
        
        for (final File fileEntry : dir.listFiles()) {
            if (fileEntry.exists() && fileEntry.isDirectory()) {
                directories.add(directory + "/" + fileEntry.getName());
            } 
        }
        return directories;
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
