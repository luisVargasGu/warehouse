package Group;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WarehouseTest {
	String basic_path = "/Users/AnnaZelisko/Desktop";
	Warehouse ware1, ware2;
	
	@Before
	public void setUp() throws Exception {
		ware1 = new Warehouse(basic_path +"/group_0406/project/TestingFiles/WarehouseTest1.csv");
		ware2 = new Warehouse(basic_path +"/group_0406/project/traversal_table.csv");	
	}
	
	@Test
	public void Test1WarehouseConstructorMapA() {		
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
		int amount1 = ware2.getAmountInZone("A", 0, 0, 0);
		assertTrue(amount1 == 30);
		
		int amount2 = ware2.getAmountInZone("B", 0, 0, 0);
		assertTrue(amount2 == 30);
	}
	
	@Test
	public void Test4FasicaAmountFail(){
		
		int amount1 = ware2.getAmountInZone("C", 0, 0, 0);
		assertTrue(amount1 == -1);
		
		int amount2 = ware2.getAmountInZone("B", 6, 2, 3);
		assertTrue(amount2 == -1);
		
	}
	
	
	@Test
	public void Test6SizeFail(){
		int amount1 = ware2.getWarehouseSize("C");
		assertTrue(amount1 == -1);
		
		int amount2 = ware2.getWarehouseSize("B");
		assertTrue(amount2 == 24);
		
		int amount3 = ware2.getWarehouseSize("A");
		assertTrue(amount3 == 24);
		
	}
	
}
