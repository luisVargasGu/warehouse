package Group;

import java.util.ArrayList;
import java.util.List;

public class PickingRequest extends SKUReader{
	
	private java.util.List<java.util.List<Order>> pickingRequests;
	private java.util.List<Integer> pickingRequestID = new ArrayList<Integer>();
	private java.util.List<Integer> SKUForOrders = new ArrayList<Integer>();
	
	/**
     * Initializes an new Picking Request.
     */
	public PickingRequest(String filePath) {
		super(filePath);
		pickingRequests = new ArrayList<java.util.List<Order>>();
	}

	/**
     * Adds a new picking request to our list.
     *
     * @param  List<Order> - a list of orders to be used as a picking request
     */
	// a new picking request is completed each time we have 4 orders
	public void addNewpickingRequest(java.util.List<Order> pickingOrders){
		pickingRequests.add(pickingOrders);
		pickingRequestID.add((int) Math.random());
		setSKUForOrders(getSKU(pickingOrders));
	}
	
	/**
     * Checks if we have enough pickingRequests. 
     * If we do a request is assigned.
     *
     * @return  List<Order> - order that will be assigned
     */
	public List<Order> assignPickingRequest(){
		List<Order> order;
		try {
			order = pickingRequests.get(0);
			pickingRequests.remove(0);
			
		}catch (NullPointerException e) {
			System.out.println("Not enough picking requests to be assign to a picker.");
			return null;
		} 
		return order;	
	}

	public java.util.List<Integer> getSKUForOrders() {
		return SKUForOrders;
	}

	public void setSKUForOrders(java.util.List<Integer> sKUForOrders) {
		SKUForOrders = sKUForOrders;
	}
	
	public Integer getpickingRequestID(int position) {
		return pickingRequestID.get(position);
	}

	public List<Order> getpickingRequests(int position) {
		return pickingRequests.get(position);
	}

}