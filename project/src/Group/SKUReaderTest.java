package Group;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class SKUReaderTest {
	String basic_path = "C:/Users/ASUS/Desktop/CSC207Workspace/";

	@Test
	public void testSKUReaderTableSizeMapModel() throws Exception {
		SKUReader table = new SKUReader(new File("translation.csv"));
		int n = table.getTranslationTableModel().size();
		assertEquals(n, 48);
	}

	@Test
	public void testSKUReaderTableSizeMapSKU() throws Exception {
		SKUReader table = new SKUReader(new File("translation.csv"));
		int n = table.getTranslationTableSku().size();
		assertEquals(n, 48);
	}

	@Test
	public void testSKUReaderGetSku() throws Exception{
		SKUReader table = new SKUReader(new File("translation.csv"));
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
		SKUReader table = new SKUReader(new File("translation.csv"));
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
		SKUReader table = new SKUReader(new File("translation.csv"));
		ArrayList<String> model = new ArrayList<String>();
		ArrayList<Integer> realSku = new ArrayList<Integer>();
		model.add("White");
		model.add("SES");
		realSku.add((Integer) 5);
		realSku.add((Integer) 6);
		assertEquals(table.getSKU(model), realSku);
	}
	
	@Test
	public void testSKUReaderGetModelInfo() throws Exception{
		SKUReader table = new SKUReader(new File("translation.csv"));
		ArrayList<String> model = new ArrayList<>();
		ArrayList<Integer> realSku = new ArrayList<>();
		model.add("White");
		model.add("SES");
		realSku.add((Integer) 5);
		realSku.add((Integer) 6);
		assertEquals(table.getModelInfo(realSku), model);
	}
}