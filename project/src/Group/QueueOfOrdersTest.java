package Group;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QueueOfOrdersTest {

	private QueueOfOrders o1;
	String fileSKUs = "/Users/AnnaZelisko/Documents/group_0406/project/translation.csv";

	@Before
	public void setUp() throws Exception {
		o1 = new QueueOfOrders(fileSKUs);
	}

	@Test
	public void testconstructor() {
		assertTrue(o1.isEmpty());
		assertTrue(o1.size() == 0);
	}
	
	@Test
	public void testEnque() {
		Order orderz = new Order("red","XL", fileSKUs);
		o1.enqueue(orderz);
		assertFalse(o1.isEmpty());
		assertTrue(o1.size() == 1);
		assertEquals("colour:red model:XL ", o1.toString());

	}
	@Test
	public void testtoString() {
		Order orderz = new Order("red","XL", fileSKUs);
		o1.enqueue(orderz);
		Order orderz1 = new Order("green","XL", fileSKUs);
		o1.enqueue(orderz1);
		assertFalse(o1.isEmpty());
		assertTrue(o1.size() == 2);
		assertEquals("colour:red model:XL colour:green model:XL ", o1.toString());

	}
	
	@Test
	public void testDequeue() {
		Order orderz = new Order("red","XL", fileSKUs);
		o1.enqueue(orderz);
		Order orderz1 = new Order("blue","XL", fileSKUs);
		o1.enqueue(orderz1);
		Order orderz2 = new Order("green","XL", fileSKUs);
		o1.enqueue(orderz2);
		Order orderz3 = new Order("black","XL", fileSKUs);
		o1.enqueue(orderz3);
		assertFalse(o1.isEmpty());
		assertTrue(o1.size() == 4);
		o1.dequeue();
	}
	
	@Test
	public void testDequeueFail() {
		Order orderz = new Order("red","XL", fileSKUs);
		o1.enqueue(orderz);
		Order orderz1 = new Order("blue","XL", fileSKUs);
		o1.enqueue(orderz1);
		Order orderz2 = new Order("green","XL", fileSKUs);
		o1.enqueue(orderz2);
		assertFalse(o1.isEmpty());
		assertTrue(o1.size() == 3);
		o1.dequeue();
	}

	@Test
	public void testDequeueFail2() {
		assertTrue(o1.isEmpty());
		assertTrue(o1.size() == 0);
		o1.dequeue();
	}
	
}
