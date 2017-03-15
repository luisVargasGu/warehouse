package Group;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class QueueOfWorkers {
	// array elements that will allow us to control our queue
	private LinkedList<Worker> arrayz;

	public LinkedList<Worker> getArrayz() {
		return arrayz;
	}

	/**
	 * Initializes an empty queue of Workers.
	 */
	public QueueOfWorkers() {
		// start of our linked list queue
		arrayz = new LinkedList<Worker>();
	}

	/**
	 * Tells us if queue of workers is empty.
	 *
	 * @return boolean: true- queue is empty and false - queue not empty
	 */
	public boolean isEmpty() {
		return arrayz.size() == 0;
	}

	/**
	 * Returns the number of items in queue of workers.
	 *
	 * @return int: representing size (number of workers in queue)
	 */
	public int size() {
		return arrayz.size();
	}

	/**
	 * Adds the item to this queue of workers.
	 *
	 * @param item:Worker
	 *            - the order that needs to be added to the queue
	 */
	public void enqueue(Worker worker) {
		arrayz.add(worker);
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
				Worker worker1 = arrayz.peek();
				arrayz.remove();
				return worker1;
			}
		} catch (NoSuchElementException e) {
			System.out.println("No Workers have been given yet.");
			return null;
		}
	}

	public Worker getWorker(String name) throws NoSuchElementException {
		for (Worker worker : this.arrayz) {
			if (worker.getId() == name) {
				return (worker);
			}
		}
		NoSuchElementException e = new NoSuchElementException();
		throw e;
	}

	/**
	 * Returns a string representation of this queue of orders.
	 *
	 * @return the sequence of orders in FIFO order, separated by spaces
	 */
	@Override
	public String toString() {
		// String that will contain our final orders
		StringBuilder s = new StringBuilder();

		// loop through our array of orders
		for (int i = 0; i < arrayz.size(); i++) {
			// add the order details
			s.append("id:" + arrayz.get(i).getId());
			s.append(' ');
		}
		return s.toString();
	}

}
