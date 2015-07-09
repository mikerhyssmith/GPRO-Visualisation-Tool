package factoryProviders;

import java.util.ArrayList;

import analysis.Constants;
import analysis.DataSet;
import analysis.Refiner;
import data.Race;

public class SearchFactory {
	

	
	public SearchFactory(){
		
		
	}
	
	public  ArrayList<Race> getRefinedRaceDataset(int minDist, int maxDist,int minTemp, int maxTemp, boolean problem, boolean mistake,ArrayList<Constants.Tyres> tyres, Constants.difficultyListing tUsage, ArrayList<Constants.difficultyListing> fUsage ){
		DataSet dataSet = new DataSet();
		Refiner refiner = new Refiner();
		
		ArrayList<Race> result = dataSet.getRaces();
		result = refiner.refineRaceDistance(result, minDist, maxDist);
		//NO TEMPERATURE IN REFINER
		//NO PROBLEMS IN REFINER
		//NO DRIVER MISTAKES IN REFINER
		for(Constants.Tyres t : tyres){
			result = refiner.refineTyres(result, t);
		}
		// NO TYRE USAGE IN REFINER
		/**
		for(Constants.difficultyListing d : fUsage){
			result = refiner.refineFuel(result, d);
		}
		*/
		
		
		return result;
		
	}

}
