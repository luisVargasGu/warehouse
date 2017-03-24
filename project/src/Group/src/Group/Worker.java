package Group;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Worker {
	private String id;
	private PickingRequest work;
	private ArrayList<String> location = new ArrayList<>();
	private ArrayList<Integer> finishedwork = new ArrayList<>();

	// getters and setters
	/**
	 * Return the workers pickingRequest.
	 * 
	 * @return PickingRequest: the workers picking request
	 */
	public PickingRequest getWork() {
		return work;
	}

	/**
	 * Sets the workers pickingRequest.
	 * 
	 * @param work:PickingRequest
	 *            the work to set
	 */
	public void setWork(PickingRequest work) {
		this.work = work;
	}

	/**
	 * Return the workers location.
	 * 
	 * @return ArrayList<String> - the workers locations
	 */
	public ArrayList<String> getlocation() {
		return location;
	}

	/**
	 * Sets the workers location.
	 * 
	 * @param location:ArrayList<String>
	 *            the location to set
	 */
	public void setlocation(ArrayList<String> location) {
		this.location = location;
	}

	/**
	 * Returns the workers id.
	 * 
	 * @return String: the workers id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the workers finished work.
	 * 
	 * @return ArrayList<Integer> - the finished work
	 */
	public ArrayList<Integer> getFinishedwork() {
		return finishedwork;
	}

	/**
	 * Sets the workers finished work.
	 * 
	 * @param finishedwork:ArrayList<Integer>
	 *            the finished work
	 */
	public void setFinishedwork(ArrayList<Integer> finishedwork) {
		this.finishedwork = finishedwork;
	}

	// constructor
	/**
	 * Create a new worker.
	 * 
	 * @param id:
	 *            String the workers name aka id
	 */
	public Worker(String id) {
		this.id = id;

	}

	// methods
	/**
	 * Gives a picking request to a new worker.
	 * 
	 * @param picks:
	 *            PickingRequest a new picking request for the worker
	 */
	public void givePickingRequest(PickingRequest picks) {

		this.setWork(picks);
		ArrayList<Integer> locations = new ArrayList<>();
		for (int i = 0; i < this.getWork().getOrders().size(); i++) {
			
			locations.add(this.getWork().getOrders().get(i).getSKUFront());
			locations.add(this.getWork().getOrders().get(i).getSKUBack());
		}
		this.setlocation(WarehousePicking.optimize(locations));
	}

	/**
	 * Tells the system which worker is picking up which order.
	 * @param log:
	 * 				this is the log file that we will be writting to 
	 */
	public void pickUpOrder(Logger log) {
		String fascia = this.getlocation().remove(0);
		log.info("Event"+this.getId() + ", please go to zone " + fascia.charAt(0) + " " + fascia.charAt(1) + " "
				+ fascia.charAt(2) + " " + fascia.charAt(3));
		Integer skuFascia = Integer.parseInt(fascia.substring(4, fascia.length()));
		
		this.getFinishedwork().add(skuFascia);

	}

	/**
	 * Checks if all 8 orders have been picked up.
	 * 
	 * @return Boolean true if all orders gathered.
	 */
	public boolean finishedWork() {
		if ((this.getFinishedwork().size() == 8) && (this.getlocation().size() == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Worker is confirmed to have dropped off the work.
	 */
	public void dropOffWork() {
		if (this.finishedWork()) {
			ArrayList<String> empty1 = new ArrayList<>();
			ArrayList<Integer> empty2 = new ArrayList<>();
			this.setlocation(empty1);
			this.setFinishedwork(empty2);
			this.setWork(null);
		}
	}
}
