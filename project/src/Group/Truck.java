package Group;

import java.util.logging.Logger;

public class Truck {
	private int numOrders = 0;
	private Logger log = Logger.getLogger("my.logger");
	
	public Truck() {

	}

	/**
	 * a string representation of this queue of orders.
	 *
	 * @param
	 */
	public void addOrdersToTruck() {
		numOrders++;
		if ((numOrders == 80) || (numOrders > 80)) {
			log.info("Location: Truck, Event: Truck fully loaded, leaving Warehouse.");
			numOrders = numOrders - 80;
		}
	}

}
