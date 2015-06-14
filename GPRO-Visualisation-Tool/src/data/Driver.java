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
    
    public String getName()
    {
        return m_name;
    }
    
    public ArrayList<DriverSkills> getSkills()
    {
       return m_previousSkills;
    }
    
    public DriverSkills getCurrentSkills()
    {
        if(!(m_previousSkills.size() > 0))
            return null;
        return m_previousSkills.get(0);
    }
    
}