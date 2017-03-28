package Group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class Order {
	static Integer n = 1;
	private Integer orderNum;
	private String colour;
	private String model;
	private Integer SKUFront;
	private Integer SKUBack;
	private Logger log = Logger.getLogger("my.logger");

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
		try {
			model = modelInfo.get(1);
			colour = modelInfo.get(0);
			SKUFront = skuInfo.get(0);
			SKUBack = skuInfo.get(1);
			n++;
		} catch (Exception cause) {
			log.warning("Location: Order, Input: Wrong input type, or size.");
			System.exit(0);
		}
	}

	/**
	 * Returns the id of this order.
	 *
	 * @return Integer - the id of this order
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	/**
	 * Returns the colour of this order.
	 * 
	 * @return String - the colour of this order
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * Returns the model of this order.
	 * 
	 * @return String - the model of this order
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Returns the SKU front number of this order.
	 * 
	 * @return Integer - the SKU front number of this order
	 */
	public int getSKUFront() {
		return SKUFront;
	}

	/**
	 * Sets the SKU front number of this order
	 * 
	 * @param SKUFront:
	 *            Integer - the number to set the SKU front
	 */
	public void setSKUFront(int SKUFront) {
		this.SKUFront = SKUFront;
	}

	/**
	 * Returns the SKU back number of this order.
	 * 
	 * @return Integer - the SKU back number of this order
	 */
	public int getSKUBack() {
		return SKUBack;
	}

	/**
	 * Sets the SKU back number of this order
	 * 
	 * @param skuBack:
	 *            Integer - the number to set the SKU back
	 */
	public void setSKUBack(int skuBack) {
		this.SKUBack = skuBack;

	}

	/**
	 * Returns a boolean representation telling the user if the SKU provided is
	 * the front one for this order
	 * 
	 * @param sku:
	 *            Integer - supposedly the SKU front number of this order
	 * @return Integer - the SKU front number of this order
	 */
	public boolean containsFrontSKU(int sku) {
		return this.getSKUFront() == sku;
	}

	/**
	 * Returns a boolean representation telling the user if the SKU provided is
	 * the back one for this order
	 * 
	 * @param sku:
	 *            Integer - supposedly the SKU back number of this order
	 * @return Integer - the SKU back number of this order
	 */
	public boolean containsBackSKU(int sku) {
		return this.getSKUBack() == sku;
	}

}
