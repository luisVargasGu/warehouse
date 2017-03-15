package Group;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class WarehousePickingTest {

	@Test
	public void test() {
		WarehousePicking wp = new WarehousePicking();
		ArrayList<Integer> realSku = new ArrayList<>();
		realSku.add((Integer) 5);
		realSku.add((Integer) 6);
		ArrayList<String> result;
		result = wp.optimize(realSku);
		assertTrue(result.size() == 2);
		
	}
	
	@Test
	public void test2GreaterSize() {
		WarehousePicking wp = new WarehousePicking();
		ArrayList<Integer> realSku = new ArrayList<>();
		realSku.add((Integer) 5);
		realSku.add((Integer) 6);
		realSku.add((Integer) 7);
		ArrayList<String> result = wp.optimize(realSku);
		assertTrue(result.size() == 3);
		
	}

}
