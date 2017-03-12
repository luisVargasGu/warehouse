package Group;

public class Truck {
	int numOrders = 0;
	
	public Truck(){
		
	}
	/**
    *  a string representation of this queue of orders.
    *
    * @param 
    */
	public void addOrdersToTruck(){
		numOrders++;
		if ((numOrders == 80)||(numOrders > 80)){
			System.out.println("Truck fully loaded, leaving Warehouse.");
			numOrders = numOrders-80;
		}
	}

}
