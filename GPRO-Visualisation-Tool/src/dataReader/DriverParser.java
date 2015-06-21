package dataReader;

import java.util.ArrayList;

import data.Driver;
import data.User;

public class DriverParser {
    
    public static ArrayList<Driver> getDrivers(User user)
    {
        ArrayList<Driver> races = new ArrayList<Driver>();
        String xmlStream = FileHandler.readDriverMarket();

        return races;
    }
    
    public static ArrayList<Driver> getDriversMarket()
    {
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        return drivers;
    }
}

