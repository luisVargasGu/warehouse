package Group;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Warehouse {
	
	// Zone is the only string 
	//the key: level as these id are unique 1-48 values: aisle, rack, amount
	Map<List<Integer>,  Integer> warehouseZoneA = new HashMap<List<Integer>,  Integer>();
	Map<List<Integer>,  Integer > warehouseZoneB = new HashMap<List<Integer>,  Integer>();
	boolean tracing = false;
	 
	
	public Warehouse(String fileName){
		//this method will read in the file information, we could call it the constructors name
		// Buffered Read is a java system used to read in files
		// try statement because sometimes file can't be found 
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

		//local variables to help us track and control each read in line
		String line;
		// this is to help figure and break down the read in line
		String[] lineParts;
		
		// line read in isnt empty
		while ( (line = br.readLine()) != null) {
			// we split into the string array based on spaces, csv is -1 
			lineParts = line.split(",");
			// this list will act as our key
			Integer[] value = {Integer.parseInt(lineParts[1]), Integer.parseInt(lineParts[2]), Integer.parseInt(lineParts[3]) }; 
			List<Integer> warehouseKeys = Arrays.asList(value);
			
			
			if (lineParts[0].matches("A")){
				// we set the splitting to Strings (first variable read in is a string)
				warehouseZoneA.put(warehouseKeys, Integer.parseInt(lineParts[4]));
			}
		
			else if (lineParts[0].matches("B")){
				warehouseZoneB.put(warehouseKeys, Integer.parseInt(lineParts[4]));
			}					
			
		}
	
		// catch any exception
		} catch (FileNotFoundException e) {
			System.out.println("File doesn't exsist");
			tracing = true;
			
		} catch (IOException e) {
			System.out.println("Trouble read provided file.");
			tracing = true;
		}
	}
	
	//returns the number of fasica in that column/lane 
	public int getAmountInZone(String zone, int aisle, int rack, int level){

		//we create our list so that we can check if we have a key in our map
		Integer[] value = {aisle, rack, level}; 
		List<Integer> keyz = Arrays.asList(value);
		
	    try{
	    	// two separate zones
	    	if (zone == "A"){
	    		//check if we have that key in zone A
	    		return warehouseZoneA.get(keyz);
	    	}
	    	else if (zone == "B"){
	    		//check if we have that key in zone B
	    		return warehouseZoneB.get(keyz);
	    	}
	    	else {
	    		throw new NullPointerException();
	    	}
	    }catch(NullPointerException e){
	    	// if none of those zones have been found
	        System.out.println("Zone: " +zone+ ", Aisle: "+aisle+", Rack: "+rack+", Level: "+level+" doesnt exsist in this Warehouse");
	        return -1;
	    }

	}

}
