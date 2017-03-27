package Group;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class QueueOfOrders extends AbstractQueue<Order>{
	
	private Logger log = Logger.getLogger("my.logger");
	
    /**
     * Initializes an empty queue of order.
     */
    public QueueOfOrders() {
    	super(new LinkedList<Order>());
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
    		else if(getArrayz().size() < 4){
    			throw new IndexOutOfBoundsException();
    		}
    		else{
    			//we need to create a Picking Request 
    			ArrayList<Order> pickingOrders = new ArrayList<Order>();
    			//add all our orders
    			for (int i = 0; i <= 3; i++){
    				pickingOrders.add(getArrayz().peek());
    				getArrayz().remove();
    			}
    			return pickingOrders;
    		}
    	}
    	catch(NoSuchElementException e){
    		log.info("Location: QueueOfOrders, Event: No Orders have been given yet.");
    		return null;
    	}
    	catch(IndexOutOfBoundsException e){
    		log.info("Location: QueueOfOrders, Event: Not Orders to complete your request.");
    		return null;
    	}
    }
    
    /**
     * Returns a string representation of this queue of orders.
     *
     * @return the sequence of orders in FIFO order, separated by spaces
     */
    @Override
    public String toString() {
 	   //String that will contain our final orders
        StringBuilder s = new StringBuilder();

        // loop through our array of orders
    		for(int i = 0; i < getArrayz().size(); i++){
    			//add the order details
    			s.append("colour:"+ getArrayz().get(i).getColour() +" model:" + getArrayz().get(i).getModel());
    	        s.append(' ');
    		}
 	   	return s.toString();
 	}

}
