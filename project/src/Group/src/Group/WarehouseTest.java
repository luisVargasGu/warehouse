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
		ware1 = new Warehouse(basic_path +"/group_0406/project/TestingFiles/WarehouseTest1.csv", 2, 2, 3, 4);
		ware2 = new Warehouse(basic_path +"/group_0406/project/traversal_table.csv", 2, 2, 3, 4);	
	}
	
	@Test
	public void WarehouseConstructor() {		
		assertTrue(ware1.getWarehouseSize() == 48);
		String[] value1 = { "A", String.valueOf(0), String.valueOf(0), String.valueOf(0) }; 
		List<String> list1 = Arrays.asList(value1);
		assertTrue(ware1.getWarehouseZoneKeys().contains(list1));
		String[] value2 = {"A", String.valueOf(0), String.valueOf(0), String.valueOf(1) }; 
		List<String> list2 = Arrays.asList(value2);
		assertTrue(ware1.getWarehouseZoneKeys().contains(list2));
		String[] value3 = {"B", String.valueOf(0), String.valueOf(0), String.valueOf(1) }; 
		List<String> list3 = Arrays.asList(value3);
		assertTrue(ware1.getWarehouseZoneKeys().contains(list3));
		
	}

	@Test
	public void FasicaAmount() throws Exception{

		int amount1 = ware2.getAmountInZone("A", 1, 0, 3);
		assertTrue(amount1 == 16);
		int amount2 = ware2.getAmountInZone("B", 0, 0, 0);
		assertTrue(amount2 == 25);
		ware2.resupplyAll();
		int amount11 = ware2.getAmountInZone("A", 1, 0, 3);
		assertTrue(amount11 == 16);
		int amount21 = ware2.getAmountInZone("B", 0, 0, 0);
		assertTrue(amount21 == 25);
		
		int amount3 = ware1.getAmountInZone("A", 0, 0, 2);
		assertTrue(amount3 == 3);
		ware1.resupplyAll();
		int amount31 = ware1.getAmountInZone("A", 0, 0, 2);
		assertTrue(amount31 == 30);
	}
	
	
	
}
