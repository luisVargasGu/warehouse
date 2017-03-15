package Group;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class QueueOfOrders {
	
	//array elements that will allow us to control our queue
    private LinkedList<Order> arrayz;              
    
    /**
     * Initializes an empty queue of order.
     */
    public QueueOfOrders() {
    	//start of our linked list queue
    	arrayz = new LinkedList<Order>();
    }

    /**
     * Tells us if queue of orders is empty.
     *
     * @return boolean: true- queue is empty and false - queue not empty
     */
    public boolean isEmpty() {
        return arrayz.size() == 0;
    }

    /**
     * Returns the number of items in queue of orders.
     *
     * @return int: representing size (number of orders in queue)
     */
    public int size() {
        return arrayz.size();
    }

    /**
     * Adds the item to this queue of orders.
     *
     * @param  item:Order - the order that needs to be added to the queue
     */
    public void enqueue(Order item) {
    	arrayz.add(item);
    }

    /**
     * Sends 4 orders from the front of this queue to create a PickingReuest.
     *
     * @throws NoSuchElementException if this queue is empty
     * @throws IndexOutOfBoundsException if this queue is has less than 4 elements 
     * as our for loop is set for <= 3
     */
    public ArrayList<Order> dequeue() {
    	try{
    		//if our queue is empty how can we remove anything
    		if(isEmpty()){
    			throw new NoSuchElementException();
    		}
    		else if(arrayz.size() < 4){
    			throw new IndexOutOfBoundsException();
    		}
    		else{
    			//we need to create a Picking Request 
    			ArrayList<Order> pickingOrders = new ArrayList<Order>();
    			//add all our orders
    			for (int i = 0; i <= 3; i++){
    				pickingOrders.add(arrayz.peek());
    				arrayz.remove();
    			}
    			return pickingOrders;
    		}
    	}
    	catch(NoSuchElementException e){
    		System.out.println("No Orders have been given yet.");
    		return null;
    	}
    	catch(IndexOutOfBoundsException e){
    		System.out.println("Not Orders to complete your request.");
    		return null;
    	}
    }
    
    /**
    * Returns a string representation of this queue of orders.
    *
    * @return the sequence of orders in FIFO order, separated by spaces
    */
   public String toString() {
	   //String that will contain our final orders
       StringBuilder s = new StringBuilder();

       // loop through our array of orders
   		for(int i = 0; i < arrayz.size(); i++){
   			//add the order details
   			s.append("colour:"+ arrayz.get(i).getColour() +" model:" + arrayz.get(i).getModel());
   	        s.append(' ');
   		}
	   	return s.toString();
	}

}
