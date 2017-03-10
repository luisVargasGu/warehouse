package Group;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Warehouse {
	
	// Zone is the only string 
	//the key: level as these id are unique 1-48 values: aisle, rack, amount
	 Map<List<Integer>,  Integer> warehouseZoneA = new HashMap<List<Integer>,  Integer>();
	 Map<List<Integer>,  Integer > warehouseZoneB = new HashMap<List<Integer>,  Integer>();
	 
	
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
		while ((line = br.readLine()) != null) {
			// we split into the string array based on spaces
			lineParts = line.split(",");
			// this list will act as our key
			List<Integer> warehouseKeys = new ArrayList<Integer>();
			
			// if its an order request, I used matches because if there is a space or miss type dont wanna risk == failing
			if (lineParts[0].matches("A")){
				// we set the splitting to Strings (first variable read in is a string)
				warehouseKeys.add(Integer.parseInt(lineParts[1]));
				warehouseKeys.add(Integer.parseInt(lineParts[2]));
				warehouseKeys.add(Integer.parseInt(lineParts[3]));
				warehouseZoneA.put(warehouseKeys, Integer.parseInt(lineParts[4]));
			}
			// if its an picker request
			else if (lineParts[0].matches("B")){
				warehouseKeys.add(Integer.parseInt(lineParts[1]));
				warehouseKeys.add(Integer.parseInt(lineParts[2]));
				warehouseKeys.add(Integer.parseInt(lineParts[3]));
				warehouseZoneB.put(warehouseKeys, Integer.parseInt(lineParts[4]));
			}					
			
		}
	
		// catch any exception
		} catch (IOException e) {
			//track
			e.printStackTrace();
			
		}
	}
	
	//returns the number of fasica in that column/lane 
	public int getAmountInZone(String zone, int aisle, int rack, int level){
		boolean parsable = true;
		List<Integer> keyz = new ArrayList<Integer>();
		keyz.add(aisle);
		keyz.add(rack);
		keyz.add(level);
	    try{
	    	if (zone == "A"){
	    		return warehouseZoneA.get(keyz);
	    	}
	    	else if (zone == "B"){
	    		return warehouseZoneB.get(keyz);
	    	}
	    }catch(NumberFormatException e){
	        System.out.println("Zone: " +zone+ " doesnt exsist");
	    }
	    return -1;
	}

}
