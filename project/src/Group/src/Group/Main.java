package Group;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class Main {

	public static void main(String[] args) {
		String basic_path = "C:/Users/lvargas/Desktop/CSC207Workspace/project";
		// Creates all the files we will interact with
		String fileWithSteps = basic_path + "/group_0406/project/16orders.txt";
		String fileWithSKUs = basic_path + "/group_0406/project/translation.csv";
		String fileWithWarehouseInfo = basic_path + "/group_0406/project/TestingFiles/initial.csv";
		String fileWithSpecs = basic_path + "/group_0406/project/specifications.txt";
		File fileToWriteToOrders = new File("orders.csv");
		// Orders that have been loaded into the truck
		File fileToWriteToFinal = new File("final.csv");

		// Logger Details
		Logger log = Logger.getLogger("my.logger");
		try {
			FileHandler fileHandler = new FileHandler("log.txt");
			log.addHandler(fileHandler);
			fileHandler.setFormatter(new SimpleFormatter());
			fileHandler.setLevel(Level.ALL);
		} catch (IOException e1) {
			log.warning("Location: Main, FileHandler: file handler cant be created.");
			System.exit(0);
		}
		log.setLevel(Level.ALL);

		// all instances that require interaction in main
		SKUReader SKUFile = new SKUReader(fileWithSKUs);
		// WarehouseFile = new Warehouse(fileWithWarehouseInfo);
		Warehouse WarehouseFile = null;
		QueueOfOrders orderQueue = new QueueOfOrders();
		QueueOfWorkers workerQueue = new QueueOfWorkers();
		Loading loader = new Loading();
		Sequencing sequencer = new Sequencing();
		Truck truck1 = new Truck();

		try {
			BufferedReader spec = new BufferedReader(new FileReader(fileWithSpecs));
			// Read the first line b4 the while so we skip the instructions.
			String line;
			String[] lineParts;
			while ((line = spec.readLine()) != null) {
				lineParts = line.split(" ");
				if (lineParts[0].equals("Warehouse:")) {
					// when we create the Warehouse before this loop the
					// parameters from the config file will not be set.
					// so our default Warehouse of 30 fasica is never set.
					// if we send them in as we create warehouse then the for
					// loop at the start actually runs
					WarehouseFile = new Warehouse(fileWithWarehouseInfo, Integer.parseInt(lineParts[1]),
							Integer.parseInt(lineParts[2]), Integer.parseInt(lineParts[3]),
							Integer.parseInt(lineParts[4]));

					// WarehouseFile.setZone(Integer.parseInt(lineParts[1]));
					// WarehouseFile.setAisle(Integer.parseInt(lineParts[2]));
					// WarehouseFile.setRacks(Integer.parseInt(lineParts[3]));
					// WarehouseFile.setLevels(Integer.parseInt(lineParts[4]));
				}
			}
			spec.close();
		} catch (IOException e2) {
			log.warning("Location: Main, File: cant be read from or cant be found.");
			System.exit(0);
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileWithSteps));
			// local variables to help us track and control each read in line
			String line;
			// this is to help figure and break down the read in line
			String[] lineParts;

			// line read in isn't empty
			while ((line = br.readLine()) != null) {
				// we split into the string array based on spaces
				lineParts = line.split(" ");
				log.info("Input Event:" + line);

				// if its an order request, I used matches because if there is a
				// space or miss type don't want to risk failing
				if (lineParts[0].matches("Order")) {
					ArrayList<String> modelInfo = new ArrayList<String>();
					modelInfo.add(lineParts[2]);
					modelInfo.add(lineParts[1]);
					ArrayList<Integer> SKUInfo = (ArrayList<Integer>) SKUFile.getSKU(modelInfo);
					Order o1 = new Order(modelInfo, SKUInfo);
					orderQueue.enqueue(o1);

				}
				// if its an picker request
				if ((lineParts[0].matches("Picker")) && (lineParts[2].matches("ready"))) {
					Worker w1 = new Worker(lineParts[1]);
					ArrayList<Order> pickingRequest = orderQueue.dequeue();
					PickingRequest workForWorker = new PickingRequest(pickingRequest);
					w1.givePickingRequest(workForWorker);
					workerQueue.enqueue(w1);
				}

				if ((lineParts[0].matches("Picker")) && (lineParts[lineParts.length - 1].matches("Marshaling"))) {
					if (workerQueue.getArrayz().get(0).finishedWork()) {
						Worker goodWorker = workerQueue.dequeue();
						sequencer.giveWork(goodWorker.getWork(), goodWorker.getFinishedwork());

					}
				}
				if ((lineParts[0].matches("Picker")) && (lineParts[2].matches("pick"))) {
					workerQueue.getWorker(lineParts[1]).pickUpOrder();

				}

				// if its an picker request
				if (lineParts[0].matches("Sequencer")) {
					sequencer.setId(lineParts[1]);
					// Could be put somewhere else?
					sequencer.setPalletSize(fileWithSpecs);
					sequencer.sequence();
				}
				// if its an loader request
				if (lineParts[0].matches("Loader")) {
					loader.setId(lineParts[1]);
					loader.loadOrders(sequencer.getPickingrequest(), sequencer.getFrontFasciaPallet(),
							sequencer.getBackFasciaPallet());
					truck1.addOrdersToTruck();
					sequencer = new Sequencing();
				}
				// if its an replenisher request
				if (lineParts[0].matches("Replenisher")) {

				}
			}

			// Output all the files that are needed
			try {
				WarehouseFile.saveToFile(fileToWriteToFinal);
			} catch (IOException e) {
				log.warning("Location: Main, File: Warehouse file is not intiated.");
				System.exit(0);
			}
			loader.outputOrdersLoaded(fileToWriteToOrders);
			br.close();
		}
		
		catch (IOException e) {
			log.warning("Location: Main, File: cant be read from or cant be found.");
			System.exit(0);
		}
		// catch any file exception
		catch (Exception e) {
			log.warning("Location: Main, File: cant be read from or cant be found.");
			System.exit(0);
		}
	}
}
