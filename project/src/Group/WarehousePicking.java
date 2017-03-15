package Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WarehousePicking {

	/**
	 * Based on the Integer SKUs in List 'skus', return a List of locations,
	 * where each location is a String containing 5 pieces of information: the
	 * zone character (in the range ['A'..'B']), the aisle number (an integer in
	 * the range [0..1]), the rack number (an integer in the range ([0..2]), and
	 * the level on the rack (an integer in the range [0..3]), and the SKU
	 * number.
	 * 
	 * @param skus
	 *            the list of SKUs to retrieve.
	 * @return the List of locations.
	 */
	public WarehousePicking() {

	}

	public static List<String> optimize(List<Integer> skus) {
		ArrayList<String> result = new ArrayList<>();
		for (Integer sku : skus) {
			StringBuilder location = new StringBuilder();
			String[] zone = { "A", "B" };
			String[] aisle = { "0", "1" };
			String[] rack = { "0", "1", "2" };
			String[] level = { "0", "1", "2", "3" };
			Random rnd = new Random();
			location.append(zone[rnd.nextInt(2)]);
			location.append(aisle[rnd.nextInt(2)]);
			location.append(rack[rnd.nextInt(3)]);
			location.append(level[rnd.nextInt(4)]);
			location.append(sku.toString());
			result.add(location.toString());

		}
		return result;
	}
}
