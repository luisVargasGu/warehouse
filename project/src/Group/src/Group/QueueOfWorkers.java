package Group;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class QueueOfWorkers extends AbstractQueue<Worker>{

	/**
	 * Initializes an empty queue of Workers.
	 */
	public QueueOfWorkers() {
		super(new LinkedList<Worker>());
	}

	/**
	 * Send worker from the front of this queue to create a PickingReuest.
	 *
	 * @throws NoSuchElementException
	 *             if this queue is empty
	 */
	public Worker dequeue() {
		try {
			// if our queue is empty how can we remove anything
			if (isEmpty()) {
				throw new NoSuchElementException();
			} else {
				Worker worker1 = getArrayz().remove(0);
				return worker1;
			}
		} catch (NoSuchElementException e) {
			System.out.println("No Workers have been given yet.");
			return null;
		}
	}
	
	public boolean searchWorker(String name){
		for(Worker worker: this.getArrayz()){
			if(worker.getId() == name){
				return true;
			}
		}return false;
	}

	/**
     * Returns a worker object, that we are looking for.
     * 
     * @param   name:String - the name of that worker that we awant back
     * @return  Worker - from the front of the queue
     */
	public Worker getWorker(String name) throws NoSuchElementException {
		for (Worker worker : getArrayz()) {
			if (worker.getId().matches(name)) {
				return (worker);
			}else{
				continue;
			}
		}
		NoSuchElementException e = new NoSuchElementException();
		throw e;
	}

	/**
	 * Returns a string representation of this queue of workers.
	 *
	 * @return the sequence of workers in FIFO order, separated by spaces
	 */
	@Override
	public String toString() {
		// String that will contain our final orders
		StringBuilder s = new StringBuilder();

		// loop through our array of orders
		for (int i = 0; i < getArrayz().size(); i++) {
			// add the order details
			s.append("id:" + getArrayz().get(i).getId());
			s.append(' ');
		}
		return s.toString();
	}

}
