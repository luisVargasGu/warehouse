package Group;

import java.awt.List;

public class Supply {
	private List newSupply;
	private boolean supplyNotDamaged = true;
	
	/**
     * Controls a new supply.
     * 
     * @param   color:String - the color of that facisa
     * @param   maker:String - the model of that facisa
     */
	public Supply(String color, String model, String position){
		setNewSupply(new List());
		System.out.println("A new supply has arrived to the Warehouse:"+ color+", "+ model+", "+position);
	}
	

	/**
     * Checks the new supply for damage.
     */
	public void checkSupply(){
		
		for (int i = 0; i <= 40; i++){
			//system runs damage report, if something was wrong supplyNotDamaged = False
		}
		if (supplyNotDamaged){
			System.out.println("Supply not damaged, has been put away in the warehouse reserve room");
		}
		else{
			System.out.println("Supply damaged, its gonna be thrown out");
		}
		
	}

	//getters and setters
	public List getNewSupply() {
		return newSupply;
	}

	public void setNewSupply(List newSupply) {
		this.newSupply = newSupply;
	}
	
	public void setSupplyNotDamaged(Boolean status) {
		this.supplyNotDamaged = status;
	}

}
