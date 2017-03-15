package Group;

import org.junit.Test;

public class SupplyTest {

	Supply supply1;
	
	@Test
	public void test1Constructor() {
		System.out.println("Test 1");
		supply1 = new Supply("green", "SEL", "F");
	}
	
	@Test
	public void test2checkSupply() {
		System.out.println("Test 2");
		supply1 = new Supply("green", "SEL", "F");
		supply1.checkSupply();
	}
	
	@Test
	public void test3checkSupplyModified() {
		System.out.println("Test 3");
		supply1 = new Supply("green", "SEL", "F");
		supply1.setSupplyNotDamaged(false);
		supply1.checkSupply();
	}

}
