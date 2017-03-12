package Group;

import java.util.ArrayList;

public class Worker {
	private String id;
	private PickingRequest orders;
	private ArrayList<Integer> location;
	private ArrayList<Object> finishedOrders;

	// getters and setters
	/**
	 * @return the orders
	 */
	public PickingRequest getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrders(PickingRequest orders) {
		this.orders = orders;
	}

	/**
	 * @return the location
	 */
	public ArrayList<Integer> getlocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setlocation(ArrayList<Integer> location) {
		this.location = location;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the finishedOrders
	 */
	public ArrayList<Object> getFinishedOrders() {
		return finishedOrders;
	}

	// constructor
	public Worker(String id) {
		this.id = id;
	}

	public void givePickingRequest(PickingRequest picks) {
		this.setOrders(picks);
	}

	public void getLocations() {

	}
}
