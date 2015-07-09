package userInterface;

import java.util.ArrayList;

import data.Race;

public class UserState {
	
	private static ArrayList<Race> m_dataset;
	
	public UserState(){
		m_dataset = new ArrayList<Race>();
	}

	public static  void updateDataset(ArrayList<Race> dataset){
		m_dataset = dataset;
	}
	
	public static ArrayList<Race> getDataset(){
		return m_dataset;
	}
}
