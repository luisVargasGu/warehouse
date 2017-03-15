package Group;


import java.util.ArrayList;

public class PickingRequest{
	private static Integer n = 0;
	//attributes
	private Integer id;
	private ArrayList<Order> orders;
	//getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	
	//constructor
	public PickingRequest(ArrayList<Order> orders){
		this.setOrders(orders);
		this.setId(n);
		n++;
		
	}

}