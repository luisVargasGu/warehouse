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
	// Ware house Size.
	// private int zone;
	// private int aisle;
	// private int racks;
	// private int levels;

	// the string key: zone aisle, rack, amount
	private Map<List<String>, Integer> warehouse = new HashMap<List<String>, Integer>();
	private Logger log = Logger.getLogger("my.logger");

	/**
	 * Initializes a new Warehouse
	 * 
	 * @param filename:String
	 *            - path on the device to the file
	 * @param log:
	 *            Logger - takes all the events and documents them.
	 */
	public Warehouse(String fileWithWarehouseInfo, int zone, int aisle, int racks, int levels) {
		// this for loop creates a default warehouse with 30 fasica per location
		for (int h = 0; h < zone; h++) {
			for (int j = 0; j < aisle; j++) {
				for (int k = 0; k < racks; k++) {
					for (int r = 0; r < levels; r++) {
						String[] values = { String.valueOf((char) (h + 65)), String.valueOf(j), String.valueOf(k),
								String.valueOf(r) };
						List<String> tempkey = Arrays.asList(values);
						this.getWarehouse().put(tempkey, 30);
					}
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
				String[] value = { lineParts[0], lineParts[1], lineParts[2], lineParts[3] };
				List<String> warehouseKeys = Arrays.asList(value);
				warehouse.replace(warehouseKeys, Integer.parseInt(lineParts[4]));

			}
			br.close();
			// catch any exception
		} catch (FileNotFoundException e) {
			log.warning("Location: Warehouse, Input Event: Warehouse file to read from doesn't exsist.");
			System.exit(0);

		} catch (IOException e) {
			log.warning("Location: Warehouse, Input Event: Trouble reading Warehouse file provided.");
			System.exit(0);
		}
	}

	// getters and setters

	/**
	 * Return the warehouse map.
	 *
	 * @return Map<List<String>, Integer> - all the columns in that Warehouse
	 */
	public Map<List<String>, Integer> getWarehouse() {
		return warehouse;
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
		String[] value = { zone, String.valueOf(aisle), String.valueOf(rack), String.valueOf(level) };
		List<String> keyz = Arrays.asList(value);

		try {
			return warehouse.get(keyz);
		} catch (NullPointerException e) {
			// if none of those zones have been found
			log.warning("Location: Warehouse, Event:Zone: " + zone + ", Aisle: " + aisle + ", Rack: " + rack
					+ ", Level: " + level + " doesnt exsist in this Warehouse");
			System.exit(0);
			return -1;
		}

	}

	/**
	 * Return the keys that are in each warehouse array
	 *
	 * @param zone:String
	 *            - the zone in the Warehouse
	 * 
	 * @return Set<List<Integer>> - all the columns in the Warehouse
	 */
	public Set<List<String>> getWarehouseZoneKeys() {
		try {
			return warehouse.keySet();
		} catch (NullPointerException e) {
			// if none of those zones have been found
			log.warning("Location: Warehouse, Event: That warehouse doesnt exsist.");
			System.exit(0);
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
	public int getWarehouseSize() {
		try {
			return warehouse.size();

		} catch (NullPointerException e) {
			// if none of those zones have been found
			log.warning("Location: Warehouse, Event: That warehouse doesnt exsist.");
			System.exit(0);
			return -1;
		}
	}
	// /**
	// * Changes the zone Parameter.
	// * @param zone: Integer - number of zones in the Warehouse
	// */
	// public void setZone(int zone) {
	// System.out.println("Set zone:"+zone);
	// this.zone = zone;
	// System.out.println("?Set zone:"+this.zone);
	// }
	//
	// /**
	// * Changes the Aisle Parameter.
	// * @param aisle: Integer - number of aisle in the Warehouse
	// */
	// public void setAisle(int aisle) {
	// System.out.println("Set aisle:"+aisle);
	// this.aisle = aisle;
	// System.out.println("?Set aisle:"+this.aisle);
	// }
	// /**
	// * Changes the Racks Parameter.
	// * @param racks: Integer - number of racks in the Warehouse
	// */
	// public void setRacks(int racks) {
	// this.racks = racks;
	// }
	// /**
	// * Changes the Racks Parameter.
	// * @param levels: Integer - number of levels in the Warehouse
	// */
	// public void setLevels(int levels) {
	// this.levels = levels;
	// }

	/**
	 * Remove fascia at a certain location.
	 *
	 * @param location:
	 *            String - the location in the Warehouse
	 * 
	 */
	public void takeOutFacsia(String location) throws Exception {
		String zone = location.substring(0, 1);
		String aisle = location.substring(1, 2);
		String rack = location.substring(2, 3);
		String level = location.substring(3, 4);
		String[] values = { zone, aisle, rack, level };
		List<String> key = Arrays.asList(values);
		if (this.getAmountInZone(zone, Integer.valueOf(aisle), Integer.valueOf(rack), Integer.valueOf(level)) != 0) {
			this.getWarehouse().replace(key, this.warehouse.get(key) - 1);
		}
	}

	/**
	 * Re-supply at a certain location
	 *
	 * @param location:
	 *            String - the location in the Warehouse
	 * 
	 */
	public void resupplyRack(String location) throws Exception {
		String zone = location.substring(0, 1);
		String aisle = location.substring(1, 2);
		String rack = location.substring(2, 3);
		String level = location.substring(3, 4);
		String[] values = { zone, aisle, rack, level };
		List<String> key = Arrays.asList(values);
		if (zone.matches("A") && this.warehouse.get(key) <= 5) {
			log.info("Location: Warehouse, Event: Zone: The area at zone: " + zone + " aisle: " + aisle + " rack: "
					+ rack + " level: " + level + " has been resupplied");
			this.getWarehouse().replace(key, 30);
		} 
		
	}

	/**
	 * Checks if we can resupply any of the warehouses levels
	 * 
	 * @throws Exception
	 */
	public void resupplyAll() throws Exception {
		for (List<String> key : this.warehouse.keySet()) {
			String keyz = "";
			for (int i = 0; i < key.size(); i++) {
				keyz = keyz + key.get(i);
			}
			this.resupplyRack(keyz);
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
			for (List<String> key : this.getWarehouse().keySet()) {
				fileWriter.append(key.get(0));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(key.get(1));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(key.get(2));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(key.get(3));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(Integer.toString(this.getWarehouse().get(key)));
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			log.info("Location: Warehouse, Output Event: final.csv was created successfully !!!");
		} catch (Exception e) {
			log.warning("Location: Warehouse, Output Event: Error in interacting with final.csv!!!");
			System.exit(0);

		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				log.warning(
						"Location: Warehouse, Output Event: Error while flushing/closing fileWriter for final.csv!!!");
				System.exit(0);
			}

		}

	}
}
