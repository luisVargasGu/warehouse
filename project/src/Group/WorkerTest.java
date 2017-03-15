package Group;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class WorkerTest {

	Worker worker;
	PickingRequest work;

	@Before
	public void setUp() throws Exception {
		worker = new Worker("Ali");
		ArrayList<Order> orders  = new ArrayList<Order>();
		ArrayList<String> modelInfo = new ArrayList<String>();
		modelInfo.add("Blue");
		modelInfo.add("SES");
		ArrayList<Integer> skuInfo = new ArrayList<Integer>();
		skuInfo.add(5);
		skuInfo.add(6);
		Order order1 = new Order(modelInfo, skuInfo);
		orders.add(order1);
		work = new PickingRequest(orders);
	}

	@Test
	public void test1Constructor() {
		assertEquals(worker.getId(), "Ali");
	}
	
	@Test
	public void test2Work() {
		worker.givePickingRequest(work); //automatically tests setWork()
		assertEquals(worker.getWork(), work);
	}
	
	@Test
	public void test3Location() {
		ArrayList<String> location = new ArrayList<String>();
		location.add("New York");
		worker.setlocation(location);
		assertEquals(worker.getlocation(), location);
	}

}
