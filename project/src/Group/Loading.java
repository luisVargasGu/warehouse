package Group;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Loading {
	// Attributes
	private ArrayList<Order> ordersLoaded;
	private ArrayList<ArrayList<Integer>> totalFront = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> totalBack = new ArrayList<ArrayList<Integer>>();
	private String fileToWriteTo = "/Users/AnnaZelisko/Desktop/group_0406/project/TestingFiles/order.csv";

	// Constructor
	public Loading() {

	}

	// getters and setters
	public ArrayList<ArrayList<Integer>> getTotalFront() {
		return totalFront;
	}

	public ArrayList<ArrayList<Integer>> getTotalBack() {
		return totalBack;
	}

	// Methods

	public void loadOrders(PickingRequest pickingRequest, ArrayList<Integer> frontPallet,
			ArrayList<Integer> backPallet) {
		this.ordersLoaded.addAll(pickingRequest.getOrders());
		this.totalFront.add(frontPallet);
		this.totalBack.add(backPallet);

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
	public void outputOrdersLoaded() throws IOException {
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
			for (Order order : this.ordersLoaded) {
				// Write a new object list to the CSV file
				fileWriter.append(order.getColour());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(order.getModel());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(((Integer) order.getSKUFront()).toString());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(((Integer) order.getSKUBack()).toString());
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

}
