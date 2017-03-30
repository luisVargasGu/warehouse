package Group;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SequencingTest {
	String path;
	Sequencing seq;

	@Before
	public void setUp() throws Exception {
		seq = new Sequencing();

	}

	@Test
	public void test1round1() throws Exception {
		path = "C:/Users/lvargas/Desktop/CSC207Workspace/project/group_0406/project/specifications.txt";
		ArrayList<Order> orders = new ArrayList<Order>();
		ArrayList<String> modelInfo = new ArrayList<String>();
		modelInfo.add("Blue");
		modelInfo.add("SES");
		ArrayList<Integer> finalskuInfo = new ArrayList<Integer>();
		finalskuInfo.add(5);
		finalskuInfo.add(6);
		finalskuInfo.add(7);
		finalskuInfo.add(8);
		finalskuInfo.add(1);
		finalskuInfo.add(2);
		finalskuInfo.add(3);
		finalskuInfo.add(4);
		ArrayList<Integer> skuInfo = new ArrayList<Integer>();
		skuInfo.add(5);
		skuInfo.add(6);
		ArrayList<Integer> skuInfo1 = new ArrayList<Integer>();
		skuInfo1.add(7);
		skuInfo1.add(8);
		ArrayList<Integer> skuInfo2 = new ArrayList<Integer>();
		skuInfo2.add(3);
		skuInfo2.add(4);
		ArrayList<Integer> skuInfo3 = new ArrayList<Integer>();
		skuInfo3.add(1);
		skuInfo3.add(2);
		Order order1 = new Order(modelInfo, skuInfo);
		Order order2 = new Order(modelInfo, skuInfo1);
		Order order3 = new Order(modelInfo, skuInfo2);
		Order order4 = new Order(modelInfo, skuInfo3);
		orders.add(order1);
		orders.add(order2);
		orders.add(order3);
		orders.add(order4);
		PickingRequest picking = new PickingRequest(orders);

		seq.giveWork(picking, finalskuInfo);
		assertEquals(seq.getPickingrequest(), picking);
		assertEquals(seq.getSkus(), finalskuInfo);
		seq.setPalletSize("4");
		seq.sequence();
		Boolean finalz = seq.isSequenced();
		assertTrue(finalz);
	}

}
