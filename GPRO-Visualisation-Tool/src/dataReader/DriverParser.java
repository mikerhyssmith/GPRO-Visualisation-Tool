package dataReader;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import data.Driver;
import data.DriverSkills;
import data.Pair;
import data.User;

public class DriverParser {
    
    private static final int tableCount                 = 2;
    
    private static final int tableTitle                 = 0;
    private static final int tableSkills                = 1;
    
    private static final int columnCount                = 13;
    
    private static final int raceColumn                 = 0;
    private static final int overallColumn              = 1;
    private static final int concentrationColumn        = 2;
    private static final int talentColumn               = 3;
    private static final int aggressivenessColumn       = 4;
    private static final int experienceColumn           = 5;
    private static final int technicalInsightColumn     = 6;
    private static final int staminaColumn              = 7;
    private static final int charismaColumn             = 8;
    private static final int motivationColumn           = 9;
    private static final int reputationColumn           = 10;
    private static final int weightColumn               = 11;
    private static final int ageColumn                  = 12;
    
    
    public static ArrayList<Driver> getDrivers(User user)
    {        
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        ArrayList<String> xmlStreams = FileHandler.readDriverFiles(user);
        for(String stream : xmlStreams)
        {
            Driver driver = parseDriver(stream);
            if(driver != null)
                drivers.add(driver);
        }
        return drivers;
    }
    
    public static ArrayList<Driver> getDriversMarket()
    {
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        return drivers;
    }
    
    private static Driver parseDriver(String xmlStream)
    {
        ArrayList<Node> tables = new ArrayList<Node>();

        Document document = Jsoup.parse(xmlStream);
        for(Node html : document.childNodes())
        {
            if(html.nodeName() == "html")
            {
                for(Node body : html.childNodes())
                {
                    if(body.nodeName() == "body")
                    {
                        for(Node table : body.childNodes())
                        {
                            if(table.nodeName() == "table")
                            {
                                for(Node tableBody : table.childNodes())
                                {
                                    if(tableBody.nodeName() == "tbody")
                                    {
                                        tables.add(tableBody);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return parseTables(tables);
    }
    
    private static Driver parseTables(ArrayList<Node> tables)
    {
        Driver driver = null;
        if(tables.size() == tableCount)
        {
            String name                     = parseTitleTable(tables.get(tableTitle));
            ArrayList<DriverSkills> skills  = parseSkillsTable(tables.get(tableSkills));
            driver = new Driver(name, skills);
        }
        return driver;
    }
    
    private static String parseTitleTable(Node node)
    {
        String name = "";
        for(Node body : node.childNodes())
        {
            if(body.nodeName() == "tr")
            {
                for(Node tr : body.childNodes())
                {
                    if(tr.nodeName() == "td")
                    {
                        for(Node td : tr.childNodes())
                        {
                            if(td.nodeName() == "b")
                            {
                                Element element =  (Element) td;
                                name = element.text();
                            }
                        }
                    }
                }
            }
        }
        return name;
    }
    
    private static ArrayList<DriverSkills> parseSkillsTable(Node node)
    {
        ArrayList<DriverSkills> skills = new ArrayList<DriverSkills>();
        for(int i=1; i<node.childNodes().size(); i++)
        {
            DriverSkills skill = parseSkills(node.childNode(i));
            if(skill != null)
                skills.add(skill);
        }
        return skills;
    }
    
    private static DriverSkills parseSkills(Node node)
    {
        DriverSkills skills = null;
        List<Node> nodes = node.childNodes();
        if(nodes.size() == columnCount)
        {
            Pair<Integer, Integer> pair =   parseSeason(((Element) nodes.get(       raceColumn                  )).text());
            int season              = pair.first;
            int race                = pair.second;
            int overall             = Integer.parseInt( ((Element) nodes.get(       overallColumn               )).text());
            int concentration       = Integer.parseInt( ((Element) nodes.get(       concentrationColumn         )).text());
            int talent              = Integer.parseInt( ((Element) nodes.get(       talentColumn                )).text());
            int aggressiveness      = Integer.parseInt( ((Element) nodes.get(       aggressivenessColumn        )).text());
            int experience          = Integer.parseInt( ((Element) nodes.get(       experienceColumn            )).text());
            int technicalInsight    = Integer.parseInt( ((Element) nodes.get(       technicalInsightColumn      )).text());
            int stamina             = Integer.parseInt( ((Element) nodes.get(       staminaColumn               )).text());
            int charisma            = Integer.parseInt( ((Element) nodes.get(       charismaColumn              )).text());
            int motivation          = Integer.parseInt( ((Element) nodes.get(       motivationColumn            )).text());
            int reputation          = Integer.parseInt( ((Element) nodes.get(       reputationColumn            )).text());
            int weight              = Integer.parseInt( ((Element) nodes.get(       weightColumn                )).text());
            int age                 = Integer.parseInt( ((Element) nodes.get(       ageColumn                   )).text());
            if(season != -1)
                skills = new DriverSkills(season, race, overall, concentration, talent, aggressiveness, experience, technicalInsight, stamina, charisma, motivation, reputation, weight, age);
        }
        return skills;
    }
    
    private static Pair<Integer, Integer> parseSeason(String string)
    {
        // S47 R04
        Pair<Integer, Integer> pair = new Pair<Integer, Integer>(-1,-1);
        if(string.contains("Driver training") || string.contains("Season break"))
            return pair;
        String[] section = string.split(" ");
        if(section.length == 2)
        {
            pair.first = Integer.parseInt(section[0].replace("S", ""));
            pair.second = Integer.parseInt(section[1].replace("R", ""));
        }
        return pair;
    }
}

