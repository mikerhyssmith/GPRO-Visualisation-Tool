package data;

public class Wear {

    private int m_level;
    private int m_start;
    private int m_end;

    public Wear(int level, int start, int end)
    {
        m_level 	= level;
        m_start 	= start;
        m_end 		= end;
    }

    public int getLevel() {
        return m_level;
    }

    public int getStart() {
        return m_start;
    }

    public int getEnd() {
        return m_end;
    }

}
