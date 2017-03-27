package Group;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

	public static void main(String[] args) {
		String basic_path = "/Users/AnnaZelisko/Desktop";
		// Creates all the files we will interact with
		String fileWithSteps = basic_path + "/group_0406/project/16orders.txt";
		String fileWithSKUs = basic_path + "/group_0406/project/translation.csv";
		String fileWithWarehouseInfo = basic_path + "/group_0406/project/TestingFiles/initial.csv";
		File fileToWriteToOrders = new File("orders.csv");
		// Orders that have been loaded into the truck
		File fileToWriteToFinal = new File("final.csv");
				
		//Logger Details
		Logger log = Logger.getLogger("my.logger");
		ConsoleHandler consoleHandler = new ConsoleHandler();
		log.addHandler(consoleHandler);
		consoleHandler.setFormatter(new SimpleFormatter());
		consoleHandler.setLevel(Level.ALL);
		
		try {
			FileHandler fileHandler = new FileHandler("log.txt");
			log.addHandler(fileHandler);
			fileHandler.setFormatter(new SimpleFormatter());
			fileHandler.setLevel(Level.ALL);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		log.setLevel(Level.ALL);
				
		// Warehouse final, informing us the number of fascia
		// creates SKu Reader and Warehouse instances that will be referred to
		// through out main
		SKUReader SKUFile = new SKUReader(fileWithSKUs, log);
		Warehouse WarehouseFile = new Warehouse(fileWithWarehouseInfo, log);
		QueueOfOrders orderQueue = new QueueOfOrders();
		QueueOfWorkers workerQueue = new QueueOfWorkers();
		Loading loader = new Loading(log);
		Sequencing sequencer = new Sequencing();
		Truck truck1 = new Truck();
		
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
				log.info("Input Event:"+ line);
				
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
						//sequencer.giveWork(goodWorker.getWork(), goodWorker.getFinishedwork())
						sequencer.setPickingrequest(goodWorker.getWork());
						sequencer.setSkus(goodWorker.getFinishedwork());
					}
				}
				if ((lineParts[0].matches("Picker")) && (lineParts[2].matches("pick"))) {
					workerQueue.getWorker(lineParts[1]).pickUpOrder(log);

				}

				// if its an picker request
				if (lineParts[0].matches("Sequencer")) {
					sequencer.setId(lineParts[1]);
					sequencer.sequence(log);
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
			WarehouseFile.saveToFile(fileToWriteToFinal);
			loader.outputOrdersLoaded(fileToWriteToOrders);
			br.close();
		}
		// catch any exception
		catch (IOException e) {
			// track
			e.printStackTrace();
		}
	}
}
