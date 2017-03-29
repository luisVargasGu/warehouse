package Group;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SKUReaderTest {
	String basic_path = "/Users/AnnaZelisko/Desktop";

	@Test
	public void testSKUReaderTableSizeMapModel() throws Exception {
		SKUReader table = new SKUReader(basic_path + "/group_0406/project/translation.csv");
		assertTrue(table.getTranslationTableModel().size() == 48);
	}

	@Test
	public void testSKUReaderTableSizeMapSKU() throws Exception {
		SKUReader table = new SKUReader(basic_path + "/group_0406/project/translation.csv");
		assertTrue(table.getTranslationTableSku().size() == 48);
	}

	@Test
	public void testSKUReaderGetSku() throws Exception{
		SKUReader table = new SKUReader(basic_path + "/group_0406/project/translation.csv");
		ArrayList<String> model = new ArrayList<>();
		ArrayList<Integer> realSku = new ArrayList<>();
		model.add("White");
		model.add("SES");
		realSku.add((Integer) 5);
		realSku.add((Integer) 6);
		assertEquals(table.getSKU(model), realSku);
	}

	@Test
	public void testSKUReaderGetSkuBadParameter() throws Exception{
		SKUReader table = new SKUReader(basic_path + "/group_0406/project/translation.csv");
		ArrayList<String> model = new ArrayList<>();
		ArrayList<Integer> realSku = new ArrayList<>();
		model.add("White");
		model.add("SES");
		realSku.add((Integer) 5);
		realSku.add((Integer) 6);
		assertEquals(table.getSKU(model), realSku);
	}

	@Test
	public void testSKUReaderGetModel() throws Exception{
		SKUReader table = new SKUReader(basic_path + "/group_0406/project/translation.csv");
		ArrayList<String> model = new ArrayList<>();
		ArrayList<Integer> realSku = new ArrayList<>();
		model.add("White");
		model.add("SES");
		realSku.add((Integer) 5);
		realSku.add((Integer) 6);
		assertEquals(table.getSKU(model), realSku);
	}
}