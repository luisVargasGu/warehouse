package Group;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// Buffered Read is a java system used to read in files
		// try statement because sometimes file can't be found
		String fileWithSteps = "/Users/AnnaZelisko/Documents/group_0406/project/16orders.txt";
		String fileWithSKUs = "/Users/AnnaZelisko/Documents/group_0406/project/translation.csv";
		String fileWithWarehouseInfo = "/Users/AnnaZelisko/Documents/group_0406/project/traversal_table.csv";
		String fileToWriteToOrders = "/Users/AnnaZelisko/Desktop/group_0406/project/orders.csv";
		// Orders that have been loaded into the truck
		String fileToWriteToFinal = "/Users/AnnaZelisko/Desktop/group_0406/project/final.csv";
		// Warehouse final, informing us the number of facsia
		// creates SKu Reader and Warehouse instances that will be referred to
		// through out main
		SKUReader SKUFile = new SKUReader(fileWithSKUs);
		Warehouse WarehouseFile = new Warehouse(fileWithWarehouseInfo);
		QueueOfOrders orderQueue = new QueueOfOrders();
		QueueOfWorkers workerQueue = new QueueOfWorkers();
		Loading loader = new Loading();
		Sequencing sequencer = new Sequencing();

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileWithSteps));
			// local variables to help us track and control each read in line
			String line;
			// this is to help figure and break down the read in line
			String[] lineParts;

			// line read in isnt empty
			while ((line = br.readLine()) != null) {
				// we split into the string array based on spaces
				lineParts = line.split(" ");

				// if its an order request, I used matches because if there is a
				// space or miss type dont wanna risk == failing
				if (lineParts[0].matches("Order")) {
					ArrayList<String> modelInfo = new ArrayList<String>();
					modelInfo.add(lineParts[2]);
					modelInfo.add(lineParts[1]);
					ArrayList<Integer> SKUInfo = (ArrayList<Integer>) SKUFile.getSKU(modelInfo);
					Order o1 = new Order(modelInfo, SKUInfo);
					orderQueue.enqueue(o1);

				}
				// if its an picker request
				else if (lineParts[0].matches("Picker")) {
					if (lineParts[2].matches("ready")) {
						// loop through the worker queue to see if worker exists
						for (int i = 0; i < workerQueue.size(); i++) {
							if (workerQueue.getArrayz().get(i).getId() == lineParts[0]) {
								
							} else {
								// then create a new worker and add them to the
								// queue
								Worker w1 = new Worker(lineParts[1]);
								ArrayList<Order> pickingRequest = orderQueue.dequeue();
								PickingRequest workForWorker = new PickingRequest(pickingRequest);
								w1.givePickingRequest(workForWorker);
								workerQueue.enqueue(w1);
							}
						}
					}else if (lineParts[lineParts.length-1].matches("Marshaling")){
						if(workerQueue.getArrayz().get(0).finishedWork()){
							Worker goodworker = workerQueue.dequeue();
						}
					}

				}
				// if its an picker request
				else if (lineParts[0].matches("Sequencer")) {
					// if (SquencerWorked){
					//
					// }
				}
				// if its an loader request
				else if (lineParts[0].matches("Loader")) {
					// assume that u have the array of front and rear fascia
					// that is returned from Sequencer
					ArrayList<Integer> frontFascia = new ArrayList<Integer>();// will
																				// need
																				// to
																				// be
																				// replaced
																				// with
																				// local
																				// variables
					ArrayList<Integer> backFascia = new ArrayList<Integer>();
					// will need to  be replaced  with local  variables
					Loading l1 = new Loading();
					Truck truck1 = new Truck();
					for (int i = 0; i <= 3; i++) {
						// this list will act as our key
						Integer[] value = { frontFascia.get(i), backFascia.get(i) };
						ArrayList<Integer> sku = (ArrayList<Integer>) Arrays.asList(value);
						ArrayList<String> modelInfo = (ArrayList<String>) SKUFile.getModelInfo(sku);
						l1.outputOrdersLoaded();
					}
					truck1.addOrdersToTruck();
				}
				// if its an replenisher request
				else if (lineParts[0].matches("Replenisher")) {

				}

			}
			br.close();
		}
		// catch any exception
		catch (IOException e) {
			// track
			e.printStackTrace();
		}
	}

}
