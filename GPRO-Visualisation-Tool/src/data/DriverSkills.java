package data;

public class DriverSkills {
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
    
    public DriverSkills(int overall, int concentration, int talent, int aggressiveness, int experience, int technicalInsight, int stamina, int charisma, int motivation, int reputation,int weight, int age)
    {
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

    public int getM_overall() {
        return m_overall;
    }

    public int getM_concentration() {
        return m_concentration;
    }

    public int getM_talent() {
        return m_talent;
    }

    public int getM_aggressiveness() {
        return m_aggressiveness;
    }

    public int getM_experience() {
        return m_experience;
    }

    public int getM_technicalInsight() {
        return m_technicalInsight;
    }

    public int getM_stamina() {
        return m_stamina;
    }

    public int getM_charisma() {
        return m_charisma;
    }

    public int getM_motivation() {
        return m_motivation;
    }

    public int getM_reputation() {
        return m_reputation;
    }

    public int getM_weight() {
        return m_weight;
    }

    public int getM_age() {
        return m_age;
    }
    
}
