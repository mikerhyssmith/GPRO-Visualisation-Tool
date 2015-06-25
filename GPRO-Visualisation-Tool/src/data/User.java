package data;

import java.io.Serializable;
import java.util.ArrayList;
import dataReader.FileHandler;

public class User implements Serializable{
    
    private static final long serialVersionUID = 479975101799943293L;
    private String m_name;
    protected ArrayList<Race> m_races;
    protected ArrayList<Driver> m_drivers;
    
    public User(String username)
    {
        m_name = username;
        
        m_races = new ArrayList<Race>();
        m_drivers = new ArrayList<Driver>();
    }
    
    public String getName()
    {
        return m_name;
    }
    
    public ArrayList<Race> getRaces()
    {
        return m_races;
    }
    
    public ArrayList<Driver> getDrivers()
    {
        return m_drivers;
    }
    
    public void save()
    {
        User user = new User(this.getName());
        FileHandler.writeUser(user);
    }
}
