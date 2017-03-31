package Group;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class QueueOfOrdersTest {

	QueueOfOrders queue1;
	ArrayList<String> modelInfo;
	ArrayList<Integer> skuInfo;
	Order order1;
	Order order2;
	Order order3;
	Order order4;

	@Before
	public void setUp() throws Exception {
		modelInfo = new ArrayList<String>();
		modelInfo.add("Blue");
		modelInfo.add("SES");
		skuInfo = new ArrayList<Integer>();
		skuInfo.add(5);
		skuInfo.add(6);
		order1 = new Order(modelInfo, skuInfo);
		order2 = new Order(modelInfo, skuInfo);
		order3 = new Order(modelInfo, skuInfo);
		order4 = new Order(modelInfo, skuInfo);
		queue1 = new QueueOfOrders();
	}

	@Test
	public void test1Constructor() {
		int size = queue1.size();
		assertTrue(queue1.isEmpty());
		assertEquals(size, 0);
	}

	@Test
	public void test2Enque() {
		queue1.enqueue(order1);
		int size = queue1.size();
		assertFalse(queue1.isEmpty());
		assertEquals(size, 1);
		assertEquals("colour:Blue model:SES ", queue1.toString());

	}

	@Test
	public void test3toString() {
		queue1.enqueue(order1);
		queue1.enqueue(order2);
		assertFalse(queue1.isEmpty());
		int size = queue1.size();
		assertEquals(size, 2);
		assertEquals("colour:Blue model:SES colour:Blue model:SES ", queue1.toString());
	}

	@Test
	public void test4Dequeue() {
		queue1.enqueue(order1);
		queue1.enqueue(order2);
		queue1.enqueue(order3);
		queue1.enqueue(order4);
		assertFalse(queue1.isEmpty());
		int size = queue1.size();
		assertEquals(size, 4);
		queue1.dequeue();
	}

	@Test
	public void test5DequeueFail() {
		queue1.enqueue(order1);
		queue1.enqueue(order2);
		queue1.enqueue(order3);
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

}
