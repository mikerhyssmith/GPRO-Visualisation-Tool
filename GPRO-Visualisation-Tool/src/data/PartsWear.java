package data;

public class PartsWear {
	
	private Wear m_chasis;
	private Wear m_engine;
	private Wear m_frontWing;
	private Wear m_underbody;
	private Wear m_sidePods;
	private Wear m_cooling;
	private Wear m_gearbox;
	private Wear m_brakes;
	private Wear m_suspension;
	private Wear m_electronics;
	
	public PartsWear(Wear chasis, Wear engine, Wear frontWing, Wear underbody, Wear sidePods, Wear cooling, Wear gearbox, Wear brakes, Wear suspension, Wear electronics)
	{
		m_chasis 		= chasis;
		m_engine 		= engine;
		m_frontWing 	= frontWing;
		m_underbody 	= underbody;
		m_sidePods 		= sidePods;
		m_cooling 		= cooling;
		m_gearbox 		= gearbox;
		m_brakes 		= brakes;
		m_suspension 	= suspension;
		m_electronics 	= electronics;
	}

	public Wear getChasis() {
		return m_chasis;
	}

	public Wear getEngine() {
		return m_engine;
	}

	public Wear getFrontWing() {
		return m_frontWing;
	}

	public Wear getUnderbody() {
		return m_underbody;
	}

	public Wear getSidePods() {
		return m_sidePods;
	}

	public Wear getCooling() {
		return m_cooling;
	}

	public Wear getGearbox() {
		return m_gearbox;
	}

	public Wear getBrakes() {
		return m_brakes;
	}

	public Wear getSuspension() {
		return m_suspension;
	}

	public Wear getElectronics() {
		return m_electronics;
	}
}
