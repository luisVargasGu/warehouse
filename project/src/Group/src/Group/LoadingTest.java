package Group;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class LoadingTest {

	ArrayList<Integer> frontFacia;
	ArrayList<Integer> rearFacia;
	PickingRequest pickingRequest;
	Loading loder;
	
	@Before
	public void setUp() throws Exception {
		frontFacia = new ArrayList<Integer>();
		frontFacia.add(5);
		frontFacia.add(6);
		rearFacia = new ArrayList<Integer>();
		rearFacia.add(3);
		rearFacia.add(4);
		loder =  new Loading();
	}
	
	@Test
	public void test1getTotal() {

		ArrayList<ArrayList<Integer>> first_equal = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> first = new ArrayList<Integer>();
		first.add(5);
		first.add(6);
		first_equal.add(first);
		System.out.println(loder.getTotalFront());
		System.out.println(first_equal);
		assertEquals(loder.getTotalFront(), first_equal);
		ArrayList<ArrayList<Integer>> sec_equal = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> sec = new ArrayList<Integer>();
		sec.add(3);
		sec.add(4);
		sec_equal.add(sec);
		assertEquals(loder.getTotalBack(), sec_equal);
	}
	
	@Test
	// it will override the file if the link name is the same and create a new one if it doesn't exist
	public void test2saveToFile() {
		String file = "/Users/AnnaZelisko/Desktop/group_0406/project/TestingFiles/test2saveToFile.csv";
		// you can check it there
		ArrayList<String> modelInfo = new ArrayList<String>();
		modelInfo.add("SES");
		modelInfo.add("IK");
		try {
			loder.outputOrdersLoaded(file);
		} catch (IOException e) {
			System.out.println("Test Not Passed");
		}
	}

}
