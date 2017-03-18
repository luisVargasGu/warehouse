package Group;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SKUReaderTest {

	@Test
	public void testSKUReaderTableSizeMapModel() {
		SKUReader table = new SKUReader("/Users/donaldkajo/Desktop/group_0406/project/translation.csv");
		assertTrue(table.getTranslationTableModel().size() == 48);
	}

	@Test
	public void testSKUReaderTableSizeMapSKU() {
		SKUReader table = new SKUReader("/Users/donaldkajo/Desktop/group_0406/project/translation.csv");
		assertTrue(table.getTranslationTableSku().size() == 48);
	}

	@Test
	public void testSKUReaderGetSku() {
		SKUReader table = new SKUReader("/Users/donaldkajo/Desktop/group_0406/project/translation.csv");
		ArrayList<String> model = new ArrayList<>();
		ArrayList<Integer> realSku = new ArrayList<>();
		model.add("White");
		model.add("SES");
		realSku.add((Integer) 5);
		realSku.add((Integer) 6);
		assertEquals(table.getSKU(model), realSku);
	}

	@Test
	public void testSKUReaderGetSkuBadParameter() {
		SKUReader table = new SKUReader("/Users/donaldkajo/Desktop/group_0406/project/translation.csv");
		ArrayList<String> model = new ArrayList<>();
		ArrayList<Integer> realSku = new ArrayList<>();
		model.add("White");
		model.add("SES");
		realSku.add((Integer) 5);
		realSku.add((Integer) 6);
		assertEquals(table.getSKU(model), realSku);
	}

	
	@Test
	public void testSKUReaderGetModel(){
		SKUReader table = new SKUReader("/Users/donaldkajo/Desktop/group_0406/project/translation.csv");
		ArrayList<String> model = new ArrayList<>();
		ArrayList<Integer> realSku = new ArrayList<>();
		model.add("White");
		model.add("SES");
		realSku.add((Integer) 5);
		realSku.add((Integer) 6);
		assertEquals(table.getSKU(model), realSku);
	}
}