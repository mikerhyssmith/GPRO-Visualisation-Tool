package data;

public class Risks {

    private int m_overtake;
    private int m_defend;
    private int m_clearDry;
    private int m_clearWet;
    private int m_malfunction;

    public Risks(int overtake, int defend, int clearDry, int clearWet, int malfunction)
    {
        m_overtake = overtake;
        m_defend = defend;
        m_clearDry = clearDry;
        m_clearWet = clearWet;
        m_malfunction = malfunction;
    }

    public int getOvertake() {
        return m_overtake;
    }

    public int getDefend() {
        return m_defend;
    }

    public int getClearDry() {
        return m_clearDry;
    }

    public int getClearWet() {
        return m_clearWet;
    }

    public int getMalfunction() {
        return m_malfunction;
    }

}
