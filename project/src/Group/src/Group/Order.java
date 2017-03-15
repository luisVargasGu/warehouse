package Group;

import java.util.ArrayList;

public class Order {
	static Integer n = 1;

	private Integer orderNum;
	private String colour;
	private String model;
	private Integer SKUFront;
	private Integer SKUBack;
	
	/**
	 * Initializes a new order.
	 * 
	 * @param color:String
	 *            - the color of that facisa
	 * @param maker:String
	 *            - the model of that facisa
	 */
	public Order(ArrayList<String> modelInfo, ArrayList<Integer> skuInfo) {
		orderNum = n;
		model = modelInfo.get(1);
		colour = modelInfo.get(0);
		SKUFront = skuInfo.get(0);
		SKUBack = skuInfo.get(1);
		n++;
	}
	//for testing 
	public void setOrderNum(int num) {
		this.orderNum = num;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}

	public String getColour() {
		return colour;
	}

	public String getModel() {
		return model;
	}

	public int getSKUFront() {
		return SKUFront;
	}

	public void setSKUFront(int SKUFront) {
		this.SKUFront = SKUFront;
	}

	public int getSKUBack() {
		return SKUBack;
	}

	public void setSKUBack(int SKUBack) {
		this.SKUBack = SKUBack;
	}

	public boolean containsFrontSKU(Integer sku) {
		if (this.getSKUFront() == sku) {
			return true;
		} else {
			return false;
		}
	}

	public boolean containsBackSKU(Integer sku) {
		if (this.getSKUBack() == sku) {
			return true;
		} else {
			return false;
		}
	}

}
