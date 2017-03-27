package Group;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class LoadingTest {
	
	// Picking-Request variables.
	ArrayList<String> modelInfo;
	ArrayList<Integer> skuInfo;
	ArrayList<Order> orders;
	PickingRequest picking;
	Order order1;
	Order order2;
	// Loading Variables.
	ArrayList<Integer> frontFacia;
	ArrayList<Integer> rearFacia;
	PickingRequest pickingRequest;
	Loading loder;
	
	
	@Before
	public void setUp() throws Exception {
		// Setup for Orders and Picking Request.
		orders  = new ArrayList<Order>();
		modelInfo = new ArrayList<String>();
		modelInfo.add("Blue");
		modelInfo.add("SES");
		skuInfo = new ArrayList<Integer>();
		skuInfo.add(5);
		skuInfo.add(3);
		order1 = new Order(modelInfo, skuInfo);
		orders.add(order1);
		modelInfo = new ArrayList<String>();
		modelInfo.add("Grey");
		modelInfo.add("SE");
		skuInfo = new ArrayList<Integer>();
		skuInfo.add(6);
		skuInfo.add(4);
		order2 = new Order(modelInfo, skuInfo);
		orders.add(order2);
		pickingRequest = new PickingRequest(orders);
		// Fascia and Loader setup.
		frontFacia = new ArrayList<Integer>();
		frontFacia.add(5);
		frontFacia.add(6);
		rearFacia = new ArrayList<Integer>();
		rearFacia.add(3);
		rearFacia.add(4);
		loder =  new Loading();
	}
	
	@Test
	public void test1getTotal() {

		ArrayList<ArrayList<Integer>> first_equal = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> first = new ArrayList<Integer>();
		first.add(5);
		first.add(6);
		ArrayList<ArrayList<Integer>> sec_equal = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> sec = new ArrayList<Integer>();
		sec.add(3);
		sec.add(4);
		sec_equal.add(sec);
		first_equal.add(first);
		loder.loadOrders(pickingRequest, first, sec);
		assertEquals(loder.getTotalFront(), first_equal);
		assertEquals(loder.getTotalBack(), sec_equal);

	}
	
	@Test
	// it will override the file if the link name is the same and create a new one if it doesn't exist
	public void test2saveToFile() {
		File file = new File("Loadingtest2saveToFile.csv");
		// you can check it there
		ArrayList<String> modelInfo = new ArrayList<String>();
		modelInfo.add("SES");
		modelInfo.add("IK");
		try {
			loder.outputOrdersLoaded(file);
		} catch (IOException e) {
			System.out.println("Test Not Passed");
		}
	}

}
