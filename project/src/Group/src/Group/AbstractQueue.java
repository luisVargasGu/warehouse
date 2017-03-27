package Group;

import java.util.LinkedList;

public abstract class AbstractQueue <T>{

	//array elements that will allow us to control our queue
    private LinkedList<T> arrayz;    
    
    /**
     * Initializes an empty queue of order.
     */
    public AbstractQueue(LinkedList<T> list) {
    	arrayz = list;
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
     * @param  item: the item that needs to be added to the queue
     */
    public void enqueue(T item) {
    	arrayz.add(item);
    }
        
    /**
     * Returns our private array list.
     * 
     * @return LinkedList<T>: list of elements  
     */
    public LinkedList<T> getArrayz() {
		return arrayz;
	}


}