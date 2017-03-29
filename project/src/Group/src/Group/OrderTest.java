package Group;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	ArrayList<String> modelInfo;
	ArrayList<Integer> skuInfo;
	Order o1;

	@Before
	public void setUp() throws Exception {
		modelInfo = new ArrayList<String>();
		modelInfo.add("Blue");
		modelInfo.add("SES");
		skuInfo = new ArrayList<Integer>();
		skuInfo.add(5);
		skuInfo.add(6);
		o1 = new Order(modelInfo, skuInfo);
	}

	@Test
	public void Test1CreateOrder() {
		assertEquals(o1.getColour(), "Blue");
		assertEquals(o1.getModel(), "SES");
		assertEquals(o1.getSKUFront(), 5);
		assertEquals(o1.getSKUBack(), 6);
		assertTrue(o1.getOrderNum() == 1);
	}

	@Test
	public void Test2SetModel() {
		o1.setSKUFront(7);
		o1.setSKUBack(8);
		assertEquals(o1.getSKUFront(), 7);
		assertEquals(o1.getSKUBack(), 8);
	}

	@Test
	public void Test3ContainsSKU() throws Exception {
		assertFalse(o1.containsFrontSKU(7));
		assertFalse(o1.containsBackSKU(8));
		assertTrue(o1.containsFrontSKU(5));
		assertTrue(o1.containsBackSKU(6));
	}

	@Test
	public void Test4OrderNum() {
		ArrayList<String> modelInfo2 = new ArrayList<String>();
		modelInfo2.add("Red");
		modelInfo2.add("SE");
		ArrayList<Integer> skuInfo2 = new ArrayList<Integer>();
		skuInfo2.add(8);
		skuInfo2.add(9);
		Order o2 = new Order(modelInfo2, skuInfo2);
		// Its created 4 new order
		assertTrue(o1.getOrderNum() == 4);
		assertTrue(o2.getOrderNum() == 5);
	}

/*	@Test
	public void Test5EmptyOrder() {
		modelInfo = new ArrayList<String>();
		skuInfo = new ArrayList<Integer>();
		Order o2 = new Order(modelInfo, skuInfo);
	}*/

	/*@Test
	public void Test6ContainsSKUInvalid() throws Exception {
		assertTrue(o1.containsFrontSKU(Integer.parseInt("")));
	}*/
}
