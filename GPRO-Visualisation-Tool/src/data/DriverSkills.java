package data;

public class DriverSkills {
    private int m_season;
    private int m_race;
    private int m_overall;
    private int m_concentration;
    private int m_talent;
    private int m_aggressiveness;
    private int m_experience;
    private int m_technicalInsight;
    private int m_stamina;
    private int m_charisma;
    private int m_motivation;
    private int m_reputation;
    private int m_weight;
    private int m_age;
    
    public DriverSkills(int season, int race, int overall, int concentration, int talent, int aggressiveness, int experience, int technicalInsight, int stamina, int charisma, int motivation, int reputation,int weight, int age)
    {
        m_season = season;
        m_race = race;
        m_overall = overall;
        m_concentration = concentration;
        m_talent = talent;
        m_aggressiveness = aggressiveness;
        m_experience = experience;
        m_technicalInsight = technicalInsight;
        m_stamina = stamina;
        m_charisma = charisma;
        m_motivation = motivation;
        m_reputation = reputation;
        m_weight = weight;
        m_age = age;
    }
    
    public int getSeason()
    {
        return m_season;
    }
    
    public int getRace()
    {
        return m_race;
    }

    public int getoverall() {
        return m_overall;
    }

    public int getConcentration() {
        return m_concentration;
    }

    public int getTalent() {
        return m_talent;
    }

    public int getAggressiveness() {
        return m_aggressiveness;
    }

    public int getExperience() {
        return m_experience;
    }

    public int getTechnicalInsight() {
        return m_technicalInsight;
    }

    public int getStamina() {
        return m_stamina;
    }

    public int getCharisma() {
        return m_charisma;
    }

    public int getMotivation() {
        return m_motivation;
    }

    public int getReputation() {
        return m_reputation;
    }

    public int getWeight() {
        return m_weight;
    }

    public int getAge() {
        return m_age;
    }
    
}
