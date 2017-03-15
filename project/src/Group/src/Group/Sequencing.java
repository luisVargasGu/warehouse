package Group;

import java.io.IOException;
import java.util.ArrayList;

public class Sequencing {

	// Attributes
	private String id;
	private ArrayList<Integer> skus;
	private PickingRequest pickingrequest;
	ArrayList<Integer> frontFasciaPallet;
	ArrayList<Integer> backFasciaPallet;

	// Constructor
	/**
	 * Create new sequencing.
	 */
	public Sequencing() {
		frontFasciaPallet = new ArrayList<Integer>(4);
		backFasciaPallet = new ArrayList<Integer>(4);
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
	 * Sets the SKUS
	 * 
	 * @param skus:ArrayList<Integer>
	 *            all the skus to be set as
	 */
	public void setSkus(ArrayList<Integer> skus) {
		this.skus = skus;
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
	 * Sets the pickingRequest for this sequencing process
	 * 
	 * @param pickingrequest:PickingRequest
	 *            - this is the pickingRequest for this sequencing process
	 */
	public void setPickingrequest(PickingRequest pickingrequest) {
		this.pickingrequest = pickingrequest;
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
	public void giveWork(PickingRequest pickingRequest, ArrayList<Integer> skus) {
		frontFasciaPallet = new ArrayList<Integer>(4);
		backFasciaPallet = new ArrayList<Integer>(4);
		this.skus = skus;
		this.pickingrequest = pickingRequest;
	}

	/**
	 * Checks if process has completed sequencing.
	 * 
	 * @return Boolean - true if process complete, false if not
	 */
	public boolean isSequenced() {
		// if ((this.getFrontFasciaPallet().size() == 4) &&
		// (this.getBackFasciaPallet().size() == 4)) {
		return true;
		// } else {
		// return false;
		// }
	}

	/**
	 * The sequencing process.
	 */
	public void sequence() {
		// try catch in case something fails
		try {
			System.out.println(this.getId() + " is sequencing" + " picking request "
					+ (this.getPickingrequest().getId()).toString());

			for (int j = 0; j < this.getPickingrequest().getOrders().size(); j++) {
				if (this.getFrontFasciaPallet().size() != 4 && this.getBackFasciaPallet().size() != 4) {
					Order order = this.getPickingrequest().getOrders().get(j);
					for (int i = j; i < 8; i++) {
						if (order.containsBackSKU(this.getSkus().get(i))) {

							this.getBackFasciaPallet().add(this.getPickingrequest().getOrders().indexOf(order),
									this.getSkus().get(i));
						} else if (order.containsFrontSKU(this.getSkus().get(i))) {

							this.getFrontFasciaPallet().add(this.getPickingrequest().getOrders().indexOf(order),
									this.getSkus().get(i));
						}
					}

				}

			}
			if (this.isSequenced() == false) {
				throw new IOException();
			}

		} catch (IOException e) {
			System.out.println("The picking request could not be sequenced, due to missing or incorect fascia");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Not enoguh indexs.");
		}

	}
}
