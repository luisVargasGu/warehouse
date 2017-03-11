package Group;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderTest {

	@Test
	public void Test1CreateOrder() {
		Order o1 = new Order("red","XL");
		assertEquals(o1.colour, "red");
		assertEquals(o1.model, "XL");
	}


}
