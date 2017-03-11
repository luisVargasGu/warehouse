package Group;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class QueueOfOrders extends PickingRequest{
	
	//array elements that will allow us to control our queue
    private List<Order> arrayz;    
    private int size = 0;               
    
    /**
     * Initializes an empty queue of order.
     */
    public QueueOfOrders() {
    	//start of our linked list queue
    	arrayz = new ArrayList<Order>();
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
        size++;
    }

    /**
     * Sends 4 orders from the front of this queue to create a PickingReuest.
     *
     * @throws NoSuchElementException if this queue is empty
     * @throws IndexOutOfBoundsException if this queue is has less than 4 elements 
     * as our for loop is set for <= 3
     */
    public void dequeue() {
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
    			List<Order> pickingOrders = new ArrayList<Order>();
    			//add all our orders
    			for (int i = 0; i <= 3; i++){
    				pickingOrders.add(arrayz.get(i));
    				pickingOrders.remove(arrayz.get(i));
    			}
    			
    			// add our orders to the picking list order
    			addNewpickingRequest(pickingOrders);

    	        //decrease the size by 4
    	        size = size-4;
    		}
    	}
    	catch(NoSuchElementException e){
    		System.out.println("No Orders have been given yet.");
    	}
    	catch(IndexOutOfBoundsException e){
    		System.out.println("Not Orders to complete your request.");
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
   			s.append("colour:"+ arrayz.get(i).colour +" model:" + arrayz.get(i).model);
   	        s.append(' ');
   		}
	   	return s.toString();
	}

}
