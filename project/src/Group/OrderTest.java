package Group;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderTest {

	String fileSKUs = "/Users/AnnaZelisko/Documents/group_0406/project/translation.csv";
	
	@Test
	public void Test1CreateOrder() {
		Order o1 = new Order("red","XL", fileSKUs);
		assertEquals(o1.colour, "red");
		assertEquals(o1.model, "XL");
	}


}
