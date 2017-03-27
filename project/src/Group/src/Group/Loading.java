package Group;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Loading {
	// id of our load
	private String id;
	// the orders to be loaded
	private ArrayList<Order> ordersLoaded = new ArrayList<Order>();
	
	// front and back fasica that are being loaded
	private ArrayList<ArrayList<Integer>> totalFront = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> totalBack = new ArrayList<ArrayList<Integer>>();
	private Logger log = Logger.getLogger("my.logger");
	
	/**
	 * Initializes a new load.
	 */
	public Loading() {
		
	}

	// getters and setters
	/**
	 * Returns the private list representing front fasica.
	 * 
	 * @return ArrayList<ArrayList<Integer>> - all the front facisa
	 */
	public ArrayList<ArrayList<Integer>> getTotalFront() {
		return totalFront;
	}

	/**
	 * Returns the private list for the back fasica.
	 * 
	 * @return ArrayList<ArrayList<Integer>> - all the back facisa
	 */
	public ArrayList<ArrayList<Integer>> getTotalBack() {
		return totalBack;
	}

	/**
	 * Returns the private list for the orders being loaded.
	 * 
	 * @return ArrayList<Order> - all the loaded orders
	 */
	public ArrayList<Order> getOrdersLoaded() {
		return ordersLoaded;
	}

	/**
	 * Sets the private list for the orders being loaded.
	 * 
	 * @param id:String - id of our load
	 */
	public void setId(String id) {
		this.id = id;
	}

	// Methods
	/**
	 * Loads our order onto a truck.
	 * 
	 * @param pickingRequest:
	 *            PickingRequest - this is the assigned picking request that is
	 *            being loaded
	 * @param frontPallet:
	 *            ArrayList<Integer> - array containing the front pallets
	 * @param backPallet:
	 *            ArrayList<Integer> - array containing the back pallets
	 */
	public void loadOrders(PickingRequest pickingRequest, ArrayList<Integer> frontPallet,
			ArrayList<Integer> backPallet) {
		log.info("Location: Loading, Event: "+ this.id + " loaded picking request " + pickingRequest.getId() + " onto the truck.");
		this.ordersLoaded.addAll(pickingRequest.getOrders());
		this.totalFront.add(frontPallet);
		this.totalBack.add(backPallet);
	}

	/**
	 * Writes the orders loaded to file: orders.csv.
	 * 
	 * @param fileToWriteTo:
	 *            String - this is the file that we are going to write the
	 *            orders to
	 * @throws IOException
	 */
	public void outputOrdersLoaded(File fileToWriteToOrders) throws IOException {
		// Delimiter used in CSV file
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		// CSV file header
		final String FILE_HEADER = "Colour, Model, SKU (front), SKU (back)";

		FileWriter fileWriter = null;
		try {

			fileWriter = new FileWriter(fileToWriteToOrders);
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
			log.fine("Location: Loading, Output Event: Order.csv file was created successfully!!!");
			
		} catch (Exception e) {
			log.warning("Location: Loading, Output Event: Error in trying to create order.csv!!!");
			System.exit(0);

		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				log.warning("Location: Loading, Output Event: Error while flushing/closing order.csv fileWriter!!!");
				System.exit(0);
			}

		}

	}

}
