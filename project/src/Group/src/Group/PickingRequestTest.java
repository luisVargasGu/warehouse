package Group;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PickingRequestTest {

	ArrayList<String> modelInfo;
	ArrayList<Integer> skuInfo;
	ArrayList<Order> orders;
	Order order1;
	PickingRequest picking;
	
	@Before
	public void setUp() throws Exception {
		orders  = new ArrayList<Order>();
		modelInfo = new ArrayList<String>();
		modelInfo.add("Blue");
		modelInfo.add("SES");
		skuInfo = new ArrayList<Integer>();
		skuInfo.add(5);
		skuInfo.add(6);
		order1 = new Order(modelInfo, skuInfo);
		orders.add(order1);
		picking = new PickingRequest(orders);
	}
	
	@Test
	public void test1Constructor() {
		//assertTrue(picking.getId() == 0); // just Picking Request is run
		assertTrue(picking.getId() == 2);// if u just run test suite, then other picking Requests are created before
		assertEquals(picking.getOrders(), orders);
	}

	@Test
	public void test2SettingID() throws Exception {
		picking.setId(3);
		assertTrue(picking.getId() == 3);
		
	}
	
	@Test
	public void test3SettingOrders() throws Exception {
		ArrayList<Order> orderz  = new ArrayList<Order>();
		ArrayList<String> modelInfoz = new ArrayList<String>();
		modelInfoz.add("Red");
		modelInfoz.add("Black");
		ArrayList<Integer> skuInfoz = new ArrayList<Integer>();
		skuInfoz.add(5);
		skuInfoz.add(6);
		Order order1 = new Order(modelInfoz, skuInfoz);
		Order order2 = new Order(modelInfoz, skuInfoz);
		orderz.add(order1);
		orderz.add(order2);
		picking.setOrders(orderz);
		assertEquals(picking.getOrders(), orderz);
		
	}
}
