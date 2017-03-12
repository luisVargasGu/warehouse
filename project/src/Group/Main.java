package Group;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	 public static void main(String[] args){
		// Buffered Read is a java system used to read in files
		// try statement because sometimes file can't be found 
		String fileWithOrders = "/Users/AnnaZelisko/Documents/group_0406/project/16orders.txt";
		String fileSKUs = "/Users/AnnaZelisko/Documents/group_0406/project/translation.csv";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileWithOrders));
			//local variables to help us track and control each read in line
			String line;
			// this is to help figure and break down the read in line
			String[] lineParts;
			
			// line read in isnt empty
			while ((line = br.readLine()) != null) {
				// we split into the string array based on spaces
				lineParts = line.split(" ");
	
				
				// if its an order request, I used matches because if there is a space or miss type dont wanna risk == failing
				if (lineParts[0].matches("Order")){				
					new Order(lineParts[2], lineParts[1], fileSKUs);					
				}
				// if its an picker request
				else if (lineParts[0].matches("Picker")){
					new Worker();
					// we know that fields lineParts[1] has a name and lineParts[2], lineParts[3] has some status info 
				}	
				// if its an picker request
				else if (lineParts[0].matches("Sequencer")){
					
				}
				// if its an loader request
				else if (lineParts[0].matches("Loader")){
					
				}
				// if its an replenisher request
				else if (lineParts[0].matches("Replenisher")){
					
				}
				
			}
			br.close();	
		}
		// catch any exception
		catch (IOException e) {
			//track
			e.printStackTrace();
		}
	}

}
