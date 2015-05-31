package data;

public class Setup {
	
	private int m_frontWing;
	private int m_rearWing;
	private int m_engine;
	private int m_brakes;
	private int m_gear;
	private int m_suspension;
	
	public Setup(int frontWing, int rearWing, int engine, int brakes, int gear, int suspension)
	{
		m_frontWing 	= frontWing;
		m_rearWing	 	= rearWing;
		m_engine 		= engine;
		m_brakes 		= brakes;
		m_gear 			= gear;
		m_suspension 	= suspension;
	}

	public int getFrontWing() {
		return m_frontWing;
	}

	public int getRearWing() {
		return m_rearWing;
	}

	public int getEngine() {
		return m_engine;
	}

	public int getBrakes() {
		return m_brakes;
	}

	public int getGear() {
		return m_gear;
	}

	public int getSuspension() {
		return m_suspension;
	}
	
}
