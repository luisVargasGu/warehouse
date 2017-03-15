package Group;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class WarehouseTest {

	@Test
	public void Test1WarehouseConstructorMapA() {
		Warehouse ware1 = new Warehouse("/Users/AnnaZelisko/Documents/group_0406/project/TestingFiles/WarehouseTest1.csv");
		
		assertTrue(ware1.getWarehouseSize("A") == 24);
		Integer[] value1 = { 0, 0, 0 }; 
		List<Integer> list1 = Arrays.asList(value1);
		assertTrue(ware1.getWarehouseZoneKeys("A").contains(list1));
		Integer[] value2 = { 0, 0, 1 }; 
		List<Integer> list2 = Arrays.asList(value2);
		assertTrue(ware1.getWarehouseZoneKeys("A").contains(list2));
		Integer[] value3 = { 0, 0, 2 }; 
		List<Integer> list3 = Arrays.asList(value3);
		assertTrue(ware1.getWarehouseZoneKeys("A").contains(list3));
		
	}

	@Test
	public void Test1WarehouseConstructorMapB() {
		Warehouse ware1 = new Warehouse("/Users/AnnaZelisko/Documents/group_0406/project/TestingFiles/WarehouseTest1.csv");

		assertTrue(ware1.getWarehouseSize("B") == 24);
		Integer[] value4 = { 0, 0, 4 }; 
		List<Integer> list4 = Arrays.asList(value4);
		assertFalse(ware1.getWarehouseZoneKeys("B").contains(list4));
		Integer[] value5 = { 0, 1, 0 }; 
		List<Integer> list5 = Arrays.asList(value5);
		assertTrue(ware1.getWarehouseZoneKeys("B").contains(list5));
	}
	
	@Test
	public void Test3FasicaAmount(){
		Warehouse ware3 = new Warehouse("/Users/AnnaZelisko/Documents/group_0406/project/traversal_table.csv");	
		int amount1 = ware3.getAmountInZone("A", 0, 0, 0);
		assertTrue(amount1 == 30);
		
		int amount2 = ware3.getAmountInZone("B", 0, 0, 0);
		assertTrue(amount2 == 30);
	}
	
	@Test
	public void Test4FasicaAmountFail(){
		Warehouse ware3 = new Warehouse("/Users/AnnaZelisko/Documents/group_0406/project/traversal_table.csv");	
		int amount1 = ware3.getAmountInZone("C", 0, 0, 0);
		assertTrue(amount1 == -1);
		
		int amount2 = ware3.getAmountInZone("B", 6, 2, 3);
		assertTrue(amount2 == -1);
		
	}
	
	@Test
	public void Test5WarehouseZoneKeysFail(){
		Warehouse ware5 = new Warehouse("/Users/AnnaZelisko/Documents/group_0406/project/traversal_table.csv");	
		Set<List<Integer>> amount1 = ware5.getWarehouseZoneKeys("C");
		assertNull(amount1);

	}
	
	@Test
	public void Test6SizeFail(){
		Warehouse ware6 = new Warehouse("/Users/AnnaZelisko/Documents/group_0406/project/traversal_table.csv");	
		int amount1 = ware6.getWarehouseSize("C");
		assertTrue(amount1 == -1);
		
		int amount2 = ware6.getWarehouseSize("B");
		assertTrue(amount2 == 24);
		
		int amount3 = ware6.getWarehouseSize("A");
		assertTrue(amount3 == 24);
		
	}
	
}
