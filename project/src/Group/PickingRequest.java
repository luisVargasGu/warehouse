package Group;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;

public class PickingRequest {
	
	private Map<Double, java.util.List<Order>> pickingRequests = new HashMap<Double, java.util.List<Order>>();
	private double pickingRequestID;
	
	/**
     * Initializes an new Picking Request.
     */
	public PickingRequest() {
		
	}
	
	/**
     * Adds a new picking request to our map.
     *
     * @param  List<Order> - a list of orders to be used as a picking request
     */
	// a new picking request is completed each time we have 4 orders
	public void addNewpickingRequest(java.util.List<Order> pickingOrders){
		pickingRequestID = Math.random();
		pickingRequests.put(pickingRequestID, pickingOrders);
	}
	
	// a new picking request is completed each time we have 4 orders
	public void assignPickingRequest(){
		
	}
}