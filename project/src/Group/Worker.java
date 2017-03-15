package Group;

import java.util.ArrayList;

public class Worker {
	private String id;
	private PickingRequest work;
	private ArrayList<String> location;
	private ArrayList<Integer> finishedwork;

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
	public ArrayList<Integer> getFinishedwork() {
		return finishedwork;
	}
	public void setFinishedwork(ArrayList<Integer> finishedwork) {
		this.finishedwork = finishedwork;
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
		this.setlocation(WarehousePicking.optimize(locations));
		}
	}
	public void pickUpOrder() {
		String fascia = this.getlocation().remove(0);
		System.out.println(this.getId() + ",please go to zone" + fascia.charAt(0) + " " + fascia.charAt(1) + " "
				+ fascia.charAt(2) + " " + fascia.charAt(3));
		Integer skuFascia = Integer.parseInt(fascia.substring(3, fascia.length()-1));
		this.getFinishedwork().add(skuFascia);

	}

	public boolean finishedWork() {
		if ((this.getFinishedwork().size() == 8) && (this.getlocation().size() == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public void dropOffWork() {
		if (this.finishedWork()) {
			this.setlocation(null);
			this.setWork(null);
			this.setFinishedwork(null);
		}
	}
}
