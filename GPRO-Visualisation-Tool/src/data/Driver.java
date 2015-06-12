package data;

import java.util.ArrayList;

public class Driver {
    
    private String m_name;
    private ArrayList<DriverSkills> m_previousSkills;
    
    public Driver(String name, ArrayList<DriverSkills> skills)
    {
        m_name = name;
        m_previousSkills = skills;
    }
    
}