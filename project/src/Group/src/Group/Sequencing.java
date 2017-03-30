package Group;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.logging.Logger;

public class Sequencing {
	// Amount of Fascia for sequencing.
	private int palletSize;
	// Attributes
	private String id;
	private ArrayList<Integer> skus;
	private PickingRequest pickingrequest;
	private ArrayList<Integer> frontFasciaPallet = new ArrayList<Integer>(palletSize);
	private ArrayList<Integer> backFasciaPallet = new ArrayList<Integer>(palletSize);
	Logger log = Logger.getLogger("my.logger");

	/**
	 * Create new sequencing.
	 */
	public Sequencing() {
	}

	// Getters and Setters
	/**
	 * Return the SKUS
	 * 
	 * @return ArrayList<Integer> - all the skus
	 */
	public ArrayList<Integer> getSkus() {
		return skus;
	}

	/**
	 * Return the pickingRequest for this sequencing process
	 * 
	 * @return PickingRequest
	 */
	public PickingRequest getPickingrequest() {
		return pickingrequest;
	}

	/**
	 * Sets the Pallet Size once from a file.
	 * 
	 * @param fileWithSpecs
	 *            - filepath.
	 */

	protected void setPalletSize(String fileWithSpecs) throws IOException {
		BufferedReader spec = new BufferedReader(new FileReader(fileWithSpecs));
		// Read the first line b4 the while so we skip the instructions.
		String line;
		String[] lineParts;
		while ((line = spec.readLine()) != null) {
			lineParts = line.split(" ");
			if (lineParts[0].equals("Palletsize:")) {
				this.palletSize = Integer.parseInt(lineParts[1]);
			}
		}
		spec.close();
	}

	/**
	 * Return the id.
	 * 
	 * @return String - the id of this sequencing process
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the id
	 * 
	 * @param id:String
	 *            -string id of sequencing process
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Return the front fascia pallet for this sequencing process
	 * 
	 * @return ArrayList<Integer> - all the front fasica
	 */
	public ArrayList<Integer> getFrontFasciaPallet() {
		return frontFasciaPallet;
	}

	/**
	 * return the front back pallet for this sequencing process
	 * 
	 * @return ArrayList<Integer> - all the back fasica
	 */
	public ArrayList<Integer> getBackFasciaPallet() {
		return backFasciaPallet;
	}

	// Methods
	/**
	 * Giving work to the sequencing process.
	 *
	 * @param pickingRequest:
	 *            PickingRequest - assigned picking request to the process
	 *
	 * @param skus:
	 *            ArrayList<Integer> - assigned skus to the process
	 */
	public void giveWork(PickingRequest pickingRequest, ArrayList<Integer> skus) throws Exception {
		this.skus = skus;
		this.pickingrequest = pickingRequest;
	}

	/**
	 * Checks if process has completed sequencing.
	 * 
	 * @return Boolean - true if process complete, false if not
	 */
	public boolean isSequenced() {
		return true;
	}

	/**
	 * The sequencing process.
	 */
	public void sequence() throws Exception {
		// try catch in case something fails

		log.info("Location: Sequencing, Event:" + this.getId() + " is sequencing" + " picking request "
				+ (this.getPickingrequest().getId()).toString());

		for (int j = 0; j < this.getPickingrequest().getOrders().size(); j++) {
			if (this.getFrontFasciaPallet().size() != palletSize && this.getBackFasciaPallet().size() != palletSize) {
				Order order = this.getPickingrequest().getOrders().get(j);
				for (int i = j; i < palletSize * 2; i++) {
					if (order.containsBackSKU(this.getSkus().get(i))) {

						this.getBackFasciaPallet().add(this.getPickingrequest().getOrders().indexOf(order),
								this.getSkus().get(i));
					} else if (order.containsFrontSKU(this.getSkus().get(i))) {

						this.getFrontFasciaPallet().add(this.getPickingrequest().getOrders().indexOf(order),
								this.getSkus().get(i));
					}

					else if (this.getFrontFasciaPallet().size() != palletSize) {
						log.info(
								"Location: Sequencing, Event: Front Fasica SKU pallet provided has a extra fasica. Go get new fasica.");
					} else if (this.getBackFasciaPallet().size() != palletSize) {
						log.info(
								"Location: Sequencing, Event: Back Fasica SKU pallet provided has a extra fasica. Go get new fasica.");
					}
				}

			}

		}
	}
}
