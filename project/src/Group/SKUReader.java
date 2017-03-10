package Group;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Class that stores the translation table for fascia and can convert between
 * SKU's and model information.
 */
public class SKUReader {
	/**
	 * The HashMaps responsible for keeping track of the the information in
	 * translation table.
	 */
	private Map<List<String>, List<Integer>> translationTableSku = new HashMap<List<String>, List<Integer>>();
	private Map<List<Integer>, List<String>> translationTableModel = new HashMap<List<Integer>, List<String>>();

	/**
	 * @param filePath
	 *            the place where the Translation-table is located.
	 * @throws IOException
	 */
	public SKUReader(String filePath) {
		// local variables to track each line and the information on each line
		String line;
		String[] lineparts;
		// for each line we enter the information in to pairs of lists.
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			while (((line = br.readLine()) != null)) {
				lineparts = line.split(",");
				// local variables for the model info and the front/back Sku
				ArrayList<String> modelInfo = new ArrayList<String>();
				ArrayList<Integer> skuInfo = new ArrayList<Integer>();

				// setting up the keys and values for the map
				modelInfo.add(lineparts[0]);
				modelInfo.add(lineparts[1]);
				skuInfo.add(Integer.parseInt(lineparts[2]));
				skuInfo.add(Integer.parseInt(lineparts[3]));

				// putting the key/value pairs into the maps
				this.getTranslationTableSku().put(modelInfo, skuInfo);
				this.getTranslationTableModel().put(skuInfo, modelInfo);

			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the translationTableModel
	 */
	public Map<List<Integer>, List<String>> getTranslationTableModel() {
		return translationTableModel;
	}

	/**
	 * Gets the translationTableSku
	 */
	public Map<List<String>, List<Integer>> getTranslationTableSku() {
		return translationTableSku;
	}

	/**
	 * The method that will give back the SKU of the fascia Model with the given
	 * specifications.
	 * 
	 * @param modelInfo
	 * @return
	 * @throws ClassCastException
	 */
	public List<Integer> getSKU(ArrayList<String> model) throws ClassCastException {
		try {
			if (this.getTranslationTableSku().containsKey(model)) {
				return this.getTranslationTableSku().get(model);
			} else {
				throw new ClassCastException();
			}
		} catch (ClassCastException e) {
			System.out.println("That key does not exist");
			return null;
		}

	}

	/**
	 * The method that will get the model information for a certain SKU
	 * 
	 * @param skuInfo
	 * @return
	 * @throws ClassCastException
	 */
	public List<String> getModelInfo(ArrayList<Integer> sku) throws ClassCastException {
		try {
			if (this.getTranslationTableModel().containsKey(sku)) {
				return this.getTranslationTableModel().get(sku);
			} else {
				throw new ClassCastException();
			}
		} catch (ClassCastException e) {
			System.out.println("that key does not exist");
			return null;
		}
	}
}
