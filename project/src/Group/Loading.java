package Group;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Loading {

	private ArrayList<ArrayList<Integer>> totalFront = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> totalBack = new ArrayList<ArrayList<Integer>>();
	private String fileToWriteTo = "/Users/AnnaZelisko/Desktop/group_0406/project/TestingFiles/order.csv";

	public Loading(ArrayList<Integer> frontFacia, ArrayList<Integer> rearFacia) {
		totalFront.add(frontFacia);
		totalBack.add(rearFacia);

	}

	// getters and setters
	public ArrayList<ArrayList<Integer>> getTotalFront() {
		return totalFront;
	}

	public ArrayList<ArrayList<Integer>> getTotalBack() {
		return totalBack;
	}

	/**
	 * Writes the orders loaded to file: orders.csv.
	 * 
	 * @param backFascia
	 *            - SKU for the back facsia
	 * @param frontFascia
	 * @param modelInfo
	 * @throws IOException
	 */
	public void saveToFile(ArrayList<String> modelInfo, Integer frontFascia, Integer backFascia) throws IOException {
		// Delimiter used in CSV file
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		// CSV file header
		final String FILE_HEADER = "Colour, Model, SKU (front), SKU (back)";

		FileWriter fileWriter = null;
		try {

			fileWriter = new FileWriter(fileToWriteTo);
			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			// Write a new object list to the CSV file
			fileWriter.append(modelInfo.get(1));
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(modelInfo.get(0));
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(Integer.toString(frontFascia));
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(Integer.toString(backFascia));
			fileWriter.append(NEW_LINE_SEPARATOR);

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

}
