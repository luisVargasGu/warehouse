package Group;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Warehouse {

	// Zone is the only string
	// the key: level as these id are unique 1-48 values: aisle, rack, amount
	private Map<List<Integer>, Integer> warehouseZoneA = new HashMap<List<Integer>, Integer>();
	private Map<List<Integer>, Integer> warehouseZoneB = new HashMap<List<Integer>, Integer>();
	boolean tracing = false;

	/**
	 * Initializes a new Warehouse
	 * 
	 * @param filename:String
	 *            - path on the device to the file
	 */
	public Warehouse(String fileName) {
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

				BufferedReader br = new BufferedReader(new FileReader(fileName))) {

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
			System.out.println("File doesn't exsist");
			tracing = true;

		} catch (IOException e) {
			System.out.println("Trouble read provided file.");
			tracing = true;
		}
	}

	// getters and setters
	public Map<List<Integer>, Integer> getWarehouseZoneA() {
		return warehouseZoneA;
	}

	public Map<List<Integer>, Integer> getWarehouseZoneB() {
		return warehouseZoneB;
	}

	/**
	 * Returns the number of fasica in that column/lane of the Warehouse
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
			System.out.println("Zone: " + zone + ", Aisle: " + aisle + ", Rack: " + rack + ", Level: " + level
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
			System.out.println("Zone: " + zone + ", doesnt exsist in this Warehouse");
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
				// return keys in zone A
				System.out.println("warehouseZoneA.size():" + warehouseZoneA.size());
				return warehouseZoneA.size();
			} else if (zone == "B") {
				// return keys in zone B
				System.out.println("warehouseZoneB.size():" + warehouseZoneB.size());
				return warehouseZoneB.size();
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			// if none of those zones have been found
			System.out.println("Zone: " + zone + ", doesnt exsist in this Warehouse");
			return -1;
		}
	}

	/**
	 * Writes the warehouse information to file: final.csv.
	 * 
	 * @param fileToWriteTo:
	 *            String the file to write the records to
	 * @throws IOException
	 */
	public void saveToFile(String fileToWriteTo) throws IOException {
		// Delimiter used in CSV file
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		// CSV file header
		final String FILE_HEADER = "Zone,Aisle,Rack,Level,Amount";

		FileWriter fileWriter = null;
		try {

			fileWriter = new FileWriter(fileToWriteTo);
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
			System.out.println("CSV file was created successfully !!!");
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");

		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
			}

		}

	}

	// public static void main(String[] args) throws IOException {
	//
	//
}
