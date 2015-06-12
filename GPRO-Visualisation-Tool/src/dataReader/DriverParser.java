package dataReader;

import java.util.ArrayList;

import data.Driver;

public class DriverParser {
    
    public static ArrayList<Driver> getDrivers()
    {
        ArrayList<Driver> races = new ArrayList<Driver>();
        String xmlStream = FileHandler.readDriverMarket();

        return races;
    }
    
    
}

