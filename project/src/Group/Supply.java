package Group;

import java.awt.List;

public class Supply {
	List newSupply; 
	
	/**
     * Controls a new supply.
     * 
     * @param   color:String - the color of that facisa
     * @param   maker:String - the model of that facisa
     */
	public Supply(String color, String model, String position){
		newSupply = new List();
		System.out.println("A new supply has arrived to the Warehouse:"+ color+", "+ model+", "+position);
		checkSupply();
	}
	
	
	/**
     * Checks the new supply for damage.
     */
	public void checkSupply(){
		boolean supplyNotDamaged = true;
		for (int i = 0; i <= 40; i++){
			//system runs damage report, if something was wrong supplyNotDamaged =False
		}
		if (supplyNotDamaged){
			System.out.println("Supply not damaged, has been put away in the warehouse reserve room");
		}
		
	}

}
