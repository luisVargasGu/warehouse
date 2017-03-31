package Group;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
	 * Constructor: creates our SKU file, from our provided file
	 * 
	 * @param filePath:
	 *            String the place where the Translation-table is located.
	 * @throws IOException
	 */
	public SKUReader(File fileWithSKUs) throws Exception {
		// local variables to track each line and the information on each line
		String line;
		String[] lineparts;
		// for each line we enter the information in to pairs of lists.
		BufferedReader br = new BufferedReader(new FileReader(fileWithSKUs));
		// removes the title
		br.readLine();

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
	}

	/**
	 * Gets the translationTableModel
	 * 
	 * @return Map<List<Integer>, List<String>> - information using models and
	 *         colour as keys
	 */
	public Map<List<Integer>, List<String>> getTranslationTableModel() {
		return translationTableModel;
	}

	/**
	 * Gets the translationTableSku
	 * 
	 * @return Map<List<String>, List<Integer>> - information using skufront and
	 *         skuback as keys
	 */
	public Map<List<String>, List<Integer>> getTranslationTableSku() {
		return translationTableSku;
	}

	/**
	 * The method that will give back the SKU of the fascia Model with the given
	 * specifications.
	 * 
	 * @param modelInfo:
	 *            List<String> - use this model/colour information
	 * @return List<Integer> - return our sku values for front and back
	 * @throws NullPointerException
	 */
	public List<Integer> getSKU(List<String> model) throws NullPointerException {
		return this.getTranslationTableSku().get(model);
	}

	/**
	 * The method that will get the model information for a certain SKU
	 * 
	 * @param skuInfo:ArrayList<Integer>
	 *            - use these sku values for front and back
	 * @return List<String> - return model/colour information for those skus
	 * @throws NullPointerException
	 */
	public List<String> getModelInfo(ArrayList<Integer> sku) throws NullPointerException {
		return this.getTranslationTableModel().get(sku);

	}
}
