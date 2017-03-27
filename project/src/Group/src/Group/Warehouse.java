package Group;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class Warehouse {

	// Zone is the only string
	// the key: level as these id are unique 1-48 values: aisle, rack, amount
	private Map<List<Integer>, Integer> warehouseZoneA = new HashMap<List<Integer>, Integer>();
	private Map<List<Integer>, Integer> warehouseZoneB = new HashMap<List<Integer>, Integer>();
	private Logger log;

	/**
	 * Initializes a new Warehouse
	 * 
	 * @param filename:String
	 *            - path on the device to the file
	 * @param log: Logger
	 *            - takes all the events and documents them.
	 */	 
	public Warehouse(String fileWithWarehouseInfo, Logger log) {
	 //there might be redundancies in code.
		this.log = log;
		for (int j = 0; j < 2; j++) {
			for (int k = 0; k < 3; k++) {
				for (int r = 0; r < 4; r++) {
					Integer[] values = { j, k, r };
					List<Integer> tempkey = Arrays.asList(values);
					this.getWarehouseZoneA().put(tempkey, 30);
					this.getWarehouseZoneB().put(tempkey, 30);
				}
			}

		}

		// this method will read in the file information, we could call it the
		// constructors name
		// Buffered Read is a java system used to read in files
		// try statement because sometimes file can't be found
		try (

			BufferedReader br = new BufferedReader(new FileReader(fileWithWarehouseInfo))) {

			// local variables to help us track and control each read in line
			String line;
			// this is to help figure and break down the read in line
			String[] lineParts;

			// line read in isnt empty
			while ((line = br.readLine()) != null) {
				// we split into the string array based on spaces, csv is -1
				lineParts = line.split(",");
				// this list will act as our key
				Integer[] value = { Integer.parseInt(lineParts[1]), Integer.parseInt(lineParts[2]),
						Integer.parseInt(lineParts[3]) };
				List<Integer> warehouseKeys = Arrays.asList(value);

				if (lineParts[0].matches("A")) {
					// we set the splitting to Strings (first variable read in
					// is a
					// string)
					warehouseZoneA.replace(warehouseKeys, Integer.parseInt(lineParts[4]));
				}

				else if (lineParts[0].matches("B")) {
					warehouseZoneB.replace(warehouseKeys, Integer.parseInt(lineParts[4]));
				}

			}
			br.close();
			// catch any exception
		} catch (FileNotFoundException e) {
			log.warning("Input Event: Warehouse file to read from doesn't exsist.");

		} catch (IOException e) {
			log.warning("Input Event: Trouble reading Warehouse file provided.");
		}
	}

	// getters and setters
	/**
	 * Return the keys that are in each warehouse zone A.
	 *
	 * @return Map<List<Integer>> - all the columns in that zone A in the
	 *         Warehouse
	 */
	public Map<List<Integer>, Integer> getWarehouseZoneA() {
		return warehouseZoneA;
	}

	/**
	 * Return the keys that are in each warehouse zone B
	 *
	 * @return Map<List<Integer>> - all the columns in that zone B in the
	 *         Warehouse
	 */
	public Map<List<Integer>, Integer> getWarehouseZoneB() {
		return warehouseZoneB;
	}

	/**
	 * Returns the number of fascia in that column/lane of the Warehouse
	 * 
	 * @param zone:String
	 *            - the zone in the Warehouse
	 * @param aisle:int
	 *            - a aisle in the Warehouse
	 * @param rack:int
	 *            - a rack in the Warehouse
	 * @param level:int
	 *            - a level in the Warehouse
	 * 
	 * @return int - number of fasica at provided location
	 */
	public int getAmountInZone(String zone, int aisle, int rack, int level) {

		// we create our list so that we can check if we have a key in our map
		Integer[] value = { aisle, rack, level };
		List<Integer> keyz = Arrays.asList(value);

		try {
			// two separate zones
			if (zone == "A") {
				// check if we have that key in zone A
				return warehouseZoneA.get(keyz);
			} else if (zone == "B") {
				// check if we have that key in zone B
				return warehouseZoneB.get(keyz);
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			// if none of those zones have been found
			log.warning("Event:Zone: " + zone + ", Aisle: " + aisle + ", Rack: " + rack + ", Level: " + level
					+ " doesnt exsist in this Warehouse");

			return -1;
		}

	}

	/**
	 * Return the keys that are in each warehouse array
	 *
	 * @param zone:String
	 *            - the zone in the Warehouse
	 * 
	 * @return Set<List<Integer>> - all the columns in that zone in the
	 *         Warehouse
	 */
	public Set<List<Integer>> getWarehouseZoneKeys(String zone) {
		try {
			// two separate zones
			if (zone == "A") {
				// return keys in zone A
				return warehouseZoneA.keySet();
			} else if (zone == "B") {
				// return keys in zone B
				return warehouseZoneB.keySet();
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			// if none of those zones have been found
			log.warning("Event: Zone: " + zone + " doesnt exsist in this Warehouse");		
			return null;
		}
	}

	/**
	 * Return the size of each warehouse array
	 *
	 * @param zone:String
	 *            - the zone in the Warehouse
	 * 
	 * @return int - size of each warehouse array
	 */
	// method will
	public int getWarehouseSize(String zone) {
		try {
			// two separate zones
			if (zone == "A") {
				return warehouseZoneA.size();
			} else if (zone == "B") {
				return warehouseZoneB.size();
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			// if none of those zones have been found
			log.warning("Event:Zone: " + zone + " doesnt exsist in this Warehouse");
			return -1;
		}
	}
	public void takeOutFacsia(String location) {
		String zone = location.substring(0, 1);
		Integer aisle = Integer.valueOf(location.substring(1, 2));
		Integer rack = Integer.valueOf(location.substring(2, 3));
		Integer level = Integer.valueOf(location.substring(3, 4));
		Integer[] values = { aisle, rack, level };
		List<Integer> key = Arrays.asList(values);
		if (zone.matches("A")) {
			this.getWarehouseZoneA().replace(key, this.warehouseZoneA.get(key) - 1);
		} else {
			this.getWarehouseZoneB().replace(key, this.warehouseZoneA.get(key) - 1);
		}
	}

	public void resupplyRack(String location) {
		String zone = location.substring(0, 1);
		Integer aisle = Integer.valueOf(location.substring(1, 2));
		Integer rack = Integer.valueOf(location.substring(2, 3));
		Integer level = Integer.valueOf(location.substring(3, 4));
		Integer[] values = { aisle, rack, level };
		List<Integer> key = Arrays.asList(values);
		if (zone.matches("A") && this.warehouseZoneA.get(key) <= 5) {
			System.out.println("The area at zone " + zone + " aisle" + aisle.toString() + " rack" + rack.toString()
					+ zone.toString() + " has been resupplied");
			this.getWarehouseZoneA().replace(key, 30);
		}
		if (zone.matches("B") && this.warehouseZoneA.get(key) <= 5) {
			System.out.println("The area at zone " + zone + " aisle" + aisle.toString() + " rack" + rack.toString()
					+ zone.toString() + " has been resupplied");
			this.getWarehouseZoneB().replace(key, 30);
		}
	}

	public void resupplyAll() {
		for (List<Integer> key : this.warehouseZoneA.keySet()) {
			this.resupplyRack("A" + key.toString());
		}
		for (List<Integer> key : this.warehouseZoneB.keySet()) {
			this.resupplyRack("B" + key.toString());
		}
	}


	/**
	 * Writes the warehouse information to file: final.csv.
	 * 
	 * @param fileToWriteTo:
	 *            String the file to write the records to
	 * @throws IOException
	 */
	public void saveToFile(File fileToWriteToFinal) throws IOException {
		// Delimiter used in CSV file
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		// CSV file header
		final String FILE_HEADER = "Zone,Aisle,Rack,Level,Amount";

		FileWriter fileWriter = null;
		try {

			fileWriter = new FileWriter(fileToWriteToFinal);
			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			// Write a new object list to the CSV file
			for (List<Integer> key : this.getWarehouseZoneA().keySet()) {
				fileWriter.append(String.valueOf("A"));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(Integer.toString(key.get(0)));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(Integer.toString(key.get(1)));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(Integer.toString(key.get(2)));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(Integer.toString(this.getWarehouseZoneA().get(key)));
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			for (List<Integer> key : this.getWarehouseZoneB().keySet()) {
				fileWriter.append(String.valueOf("B"));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(Integer.toString(key.get(0)));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(Integer.toString(key.get(1)));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(Integer.toString(key.get(2)));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(Integer.toString(this.getWarehouseZoneB().get(key)));
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			log.info("Output Event: final.csv was created successfully !!!");
		} catch (Exception e) {
			log.warning("Output Event: Error in finding final.csv!!!");

		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				log.warning("Output Event: Error while flushing/closing fileWriter for final.csv!!!");
			}

		}

	}
}
