package Group;

import java.io.IOException;
import java.util.ArrayList;

public class Sequencing {

	// Attributes
	private String id;
	private ArrayList<Integer> skus;
	private PickingRequest pickingrequest;
	private ArrayList<Integer> frontFasciaPallet;
	private ArrayList<Integer> backFasciaPallet;

	// Constructor
	public Sequencing() {

	}

	// Getters and Setters
	public ArrayList<Integer> getSkus() {
		return skus;
	}

	public void setSkus(ArrayList<Integer> skus) {
		this.skus = skus;
	}

	public PickingRequest getPickingrequest() {
		return pickingrequest;
	}

	public void setPickingrequest(PickingRequest pickingrequest) {
		this.pickingrequest = pickingrequest;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Integer> getFrontFasciaPallet() {
		return frontFasciaPallet;
	}

	public ArrayList<Integer> getBackFasciaPallet() {
		return backFasciaPallet;
	}
	// Methods

	public void giveWork(PickingRequest pickingRequest, ArrayList<Integer> skus) {
		this.skus = skus;
		this.pickingrequest = pickingRequest;
	}

	public boolean isSequenced() {
		if ((this.getFrontFasciaPallet().size() == 4) && (this.getBackFasciaPallet().size() == 4)) {
			return true;
		} else {
			return false;
		}
	}

	public void sequence() {
		try {
			for (int i = 0; i <= 8; i++) {
				for (int j = 0; j < this.getPickingrequest().getOrders().size(); j++) {
					if (this.getPickingrequest().getOrders().get(j).containsBackSKU(this.getSkus().get(i))) {
						this.getBackFasciaPallet().add(j, this.getSkus().get(i));
					} else if (this.getPickingrequest().getOrders().get(j).containsFrontSKU(this.getSkus().get(i))) {
						this.getFrontFasciaPallet().add(j, this.getSkus().get(i));
					} else {
						throw new IOException();
					}
				}
			}
		} catch (IOException e) {
			System.out.println("The picking request could not be sequenced, due to missing or incorect fascia");
		}

	}
}
