package Group;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WarehouseTest {

	@Test
	public void Test1WarehouseConstructorMapA() {
		Warehouse ware1 = new Warehouse("/Users/AnnaZelisko/Documents/workspace/CSC207Workspace/group_0406/project/TestingFiles/WarehouseTest1.csv");

		assertTrue(ware1.warehouseZoneA.size() == 3);
		Integer[] value1 = { 0, 0, 0 }; 
		List<Integer> list1 = Arrays.asList(value1);
		assertTrue(ware1.warehouseZoneA.containsKey(list1));
		Integer[] value2 = { 0, 0, 1 }; 
		List<Integer> list2 = Arrays.asList(value2);
		assertTrue(ware1.warehouseZoneA.containsKey(list2));
		Integer[] value3 = { 0, 0, 2 }; 
		List<Integer> list3 = Arrays.asList(value3);
		assertTrue(ware1.warehouseZoneA.containsKey(list3));
		
	}

	@Test
	public void Test1WarehouseConstructorMapB() {
		Warehouse ware1 = new Warehouse("/Users/AnnaZelisko/Documents/workspace/CSC207Workspace/group_0406/project/TestingFiles/WarehouseTest1.csv");

		assertTrue(ware1.warehouseZoneB.size() == 2);
		Integer[] value4 = { 0, 0, 4 }; 
		List<Integer> list4 = Arrays.asList(value4);
		assertTrue(ware1.warehouseZoneB.containsKey(list4));
		Integer[] value5 = { 0, 0, 5 }; 
		List<Integer> list5 = Arrays.asList(value5);
		assertTrue(ware1.warehouseZoneB.containsKey(list5));
	}
	
	@Test
	public void Test2WarehouseConstructorFileException(){
		Warehouse ware2 = new Warehouse("/Users/AnnaZelisko/Documents/workspace/CSC207Workspace/group_0406/project/TestingFiles/Warehouse.csv");	
		assertTrue(ware2.tracing);
	}
	
	@Test
	public void Test3FasicaAmount(){
		Warehouse ware3 = new Warehouse("/Users/AnnaZelisko/Documents/workspace/CSC207Workspace/group_0406/project/traversal_table.csv");	
		int amount1 = ware3.getAmountInZone("A", 0, 0, 0);
		assertTrue(amount1 == 1);
		
		int amount2 = ware3.getAmountInZone("B", 0, 0, 0);
		assertTrue(amount2 == 25);
	}
	
	@Test
	public void Test4FasicaAmountFail(){
		Warehouse ware3 = new Warehouse("/Users/AnnaZelisko/Documents/workspace/CSC207Workspace/group_0406/project/traversal_table.csv");	
		int amount1 = ware3.getAmountInZone("C", 0, 0, 0);
		assertTrue(amount1 == -1);
		
		int amount2 = ware3.getAmountInZone("B", 6, 2, 3);
		System.out.println(amount2);
		Integer[] value = {6, 2, 3}; 
		List<Integer> keyz = Arrays.asList(value);
		System.out.println(ware3.warehouseZoneB.get(keyz));

	}
	
}
