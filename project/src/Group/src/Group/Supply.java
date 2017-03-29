package Group;

import java.awt.List;
import java.util.logging.Logger;

public class Supply {
	private boolean supplyNotDamaged = true;
	Logger log = Logger.getLogger("my.logger");
	
	/**
     * Creates a new supply.
     * 
     * @param   color:String - the color of that facisa
     * @param   maker:String - the model of that facisa
     */
	public Supply(String color, String model, String position) throws Exception{
		log.info("Location: Supply, Event: A new supply has arrived to the Warehouse:"+ color+", "+ model+", &"+position);
	}

	/**
     * Checks the new supply for damage.
     */
	public void checkSupply(){
		
		for (int i = 0; i <= 40; i++){
			//system runs damage report, if something was wrong supplyNotDamaged = False
		}
		if (supplyNotDamaged){
			log.info("Location: Supply, Event: Supply not damaged, has been put away in the warehouse reserve room");
		}
		else{
			log.info("Location: Supply, Event: Supply damaged, its gonna be thrown out");
		}
		
	}

	//getters and setters	
	public void setSupplyNotDamaged(Boolean status) throws Exception{
		this.supplyNotDamaged = status;
	}

}
