package Group;

public class Order {
	
	String colour;
	String model;
	private int SKUFront;
	private int SKUBack;
	
	public Order(String color, String maker){
		colour = color;
		model = maker;
		//add order to order queue aka this.add()
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
