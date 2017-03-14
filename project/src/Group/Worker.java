package Group;

import java.util.ArrayList;

public class Worker {
	private String id;
	private PickingRequest work;
	private ArrayList<String> location;
	private ArrayList<Object> finishedwork;

	// getters and setters
	/**
	 * @return the work
	 */
	public PickingRequest getWork() {
		return work;
	}

	/**
	 * @param work
	 *            the work to set
	 */
	public void setWork(PickingRequest work) {
		this.work = work;
	}

	/**
	 * @return the location
	 */
	public ArrayList<String> getlocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setlocation(ArrayList<String> location) {
		this.location = location;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the finished work
	 */
	public ArrayList<Object> getFinishedwork() {
		return finishedwork;
	}

	// constructor
	public Worker(String id) {
		this.id = id;
	}

	// methods

	public void givePickingRequest(PickingRequest picks) {
		this.setWork(picks);
		ArrayList<Integer> locations = new ArrayList<>();
		for (int i = 0; i < this.getWork().getOrders().size(); i++) {
			locations.add(this.getWork().getOrders().get(0).getSKUFront());
			locations.add(this.getWork().getOrders().get(0).getSKUBack());
		}
	}

	public void pickUpOrder() {
		this.getlocation().remove(0);

	}
}
