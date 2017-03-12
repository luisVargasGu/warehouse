package Group;

public class Order extends QueueOfOrders{
	
	String colour;
	String model;
	private int SKUFront;
	private int SKUBack;
	
	/**
     * Initializes a new order.
     * 
     * @param   color:String - the color of that facisa
     * @param   maker:String - the model of that facisa
     */
	public Order(String color, String maker, String filePath){
		super(filePath);
		colour = color;
		model = maker;
		enqueue(this);
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

}
