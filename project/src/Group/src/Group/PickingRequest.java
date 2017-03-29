package Group;

import java.util.ArrayList;

public class PickingRequest{
	private static Integer n = 0;
	//attributes
	private Integer id;
	private ArrayList<Order> orders;
	//getters and setters
	/**
     * Returns the id of this picking request
     *
     * @return Integer: representing the picking request id
     */
	public Integer getId() {
		return id;
	}

	/**
     * Sets Id of this picking request.
     *
     * @param  Id: Integer - set the picking request order.
     */
	public void setId(int Id) throws Exception{
		this.id = Id;
		
	}
	/**
     * Returns list of orders for this picking request.
     *
     * @return ArrayList<Order>: list of orders 
     */
	public ArrayList<Order> getOrders() {
		return orders;
	}

	/**
     * Set orders in this picking request.
     *
     * @param  orders: ArrayList<Order> - list of orders that needs to be set
     */
	public void setOrders(ArrayList<Order> orders) throws Exception{
		this.orders = orders;
	}

	//constructor
	/**
     * New picking request created.
     *
     * @param  orders: ArrayList<Order> - list of orders that needs to be set
     */
	public PickingRequest(ArrayList<Order> orders) throws Exception{
		this.setOrders(orders);
		this.id = n;
		n++;
		
	}

	

}