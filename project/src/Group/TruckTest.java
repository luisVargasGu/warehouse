package Group;

import org.junit.Test;

public class TruckTest {


	@Test
	public void testTruckConstructor() {
		Truck t1 = new Truck();
		for (int i = 0; i<=80; i++ ){
			t1.addOrdersToTruck();
		}
		for (int i = 0; i<=100; i++ ){
			t1.addOrdersToTruck();
		}
		for (int i = 0; i<=160; i++ ){
			t1.addOrdersToTruck();
		}
	}

}
