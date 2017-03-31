package Group;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

public class QueueOfWorkersTest {

	QueueOfWorkers queue1;

	Worker worker1, worker2, worker3, worker4;

	@Before
	public void setUp() throws Exception {
		worker1 = new Worker("Sam");
		worker2 = new Worker("Ben");
		worker3 = new Worker("Ali");
		worker4 = new Worker("Kate");
		queue1 = new QueueOfWorkers();
	}

	@Test
	public void test1Constructor() {
		int size = queue1.size();
		assertTrue(queue1.isEmpty());
		assertEquals(size, 0);
	}

	@Test
	public void test2Enque() {
		queue1.enqueue(worker1);
		assertFalse(queue1.isEmpty());
		int size = queue1.size();
		assertEquals(size, 1);
		assertEquals("id:Sam ", queue1.toString());

	}

	@Test
	public void test3toString() {
		queue1.enqueue(worker1);
		queue1.enqueue(worker2);
		assertFalse(queue1.isEmpty());
		int size = queue1.size();
		assertEquals(size, 2);
		assertEquals("id:Sam id:Ben ", queue1.toString());
	}

	@Test
	public void test4Dequeue() {
		queue1.enqueue(worker1);
		queue1.enqueue(worker2);
		queue1.enqueue(worker3);
		queue1.enqueue(worker4);
		assertFalse(queue1.isEmpty());
		int size = queue1.size();
		assertEquals(size, 4);
		queue1.dequeue();
	}

	@Test
	public void test5DequeueFail() {
		queue1.enqueue(worker1);
		queue1.enqueue(worker2);
		queue1.enqueue(worker3);
		assertFalse(queue1.isEmpty());
		int size = queue1.size();
		assertEquals(size, 3);
		queue1.dequeue();
	}

	@Test
	public void test6DequeueFail2() {
		assertTrue(queue1.isEmpty());
		int size = queue1.size();
		assertEquals(size, 0);
		queue1.dequeue();
	}

	@Test
	public void test7SearchWorker() throws Exception {
		queue1.enqueue(worker1);
		assertTrue(queue1.searchWorker("Sam"));
		assertFalse(queue1.searchWorker("jill"));
	}
	
	@Test
	public void test8getWorker() throws Exception {
		queue1.enqueue(worker1);
		assertEquals(queue1.getWorker("Sam"), worker1);
		assertFalse(queue1.searchWorker("jill"));
	}
	
	@Test (expected = NoSuchElementException.class)
	public void test8getWorkerNotFound() throws Exception {
		queue1.enqueue(worker1);
		queue1.getWorker("jill");
	}
}
