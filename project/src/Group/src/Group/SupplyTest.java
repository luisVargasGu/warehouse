package Group;

import org.junit.Test;

public class SupplyTest {

	Supply supply1;
	
	@Test
	public void test1Constructor() throws Exception{
		supply1 = new Supply("green", "SEL", "F");
	}
	
	@Test
	public void test2checkSupply() throws Exception{
		supply1 = new Supply("green", "SEL", "F");
		supply1.checkSupply();
	}
	
	@Test
	public void test3checkSupplyModified() throws Exception{
		supply1 = new Supply("green", "SEL", "F");
		supply1.setSupplyNotDamaged(false);
		supply1.checkSupply();
	}

}
