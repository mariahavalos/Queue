package lafoodpackage;

/*******************************************************************************
 * Party Class
 * 
 *The Party Class. This class is a supplemental class used to calculate and parse the
 *customer's party information for later use in calculating averages and printing to 
 *the console. It uses the string input from the Driver class and does not interact with
 *Objects or the Queue class itself. 
 * 
 * Preconditions: The strings given must be in the correct format, as designated by the .txt file. 
 * Incorrect whitespace allocation will throw this class entirely off.
 * 
 * Postconditions: Returns strings and integers that are properly parsed and accurate to the 
 * Driver class. 
 * 
 * @author Student Name
 * @date Date
 * @version 1.0
 * 
 ******************************************************************************/
public class Party{
	/*This class keeps track of the time the customers arrived, the party size, and
	 * the customer's name via the string input received from the Driver class. The
	 * methods run calculations and iterations to retrieve the strings in integer 
	 * format (in regards to time and party size), and properly format the names 
	 * while accounting for whitespace.
	 */
	
	public Party(String string) { 
		/*default constructor*/
	}
	
	public int getTime(String party){
		/*gets the time that customers arrived at the restuarant*/
		
		String string_to_int_time = "";
		
		for (int i = 2; i < party.length(); i++){
			if (party.charAt(i) == ' '){
				break;
			}
			else if (party.charAt(i) != ' '){
				string_to_int_time += party.charAt(i);
			}
		}
		
		int time = Integer.parseInt(string_to_int_time);
		return time;
	}
	
	
	public String getName(String party){
		/*gets the name of the customer*/
		
		String name = "";
		int track_whitespace = 0;
		for (int i = 0; i < party.length(); i++){
			if (party.charAt(i) == ' ' && track_whitespace < 3){
				track_whitespace += 1;
			}
			else if (track_whitespace >= 3){
				name += party.charAt(i);
			}
		}
		return name;
	}
	
	public int getSize(String party){
		/*gets the size of the customer's party*/ 
		
		int size = 0, track_whitespace = 0;
		String string_to_int_size = "";
		
		for (int i = 0; i < party.length(); i++){
			if (party.charAt(i) == ' '){
				track_whitespace += 1;
			}
			else if (party.charAt(i) != ' ' && track_whitespace == 2){
				string_to_int_size += party.charAt(i);
			}
		}
		size = Integer.parseInt(string_to_int_size);
		return size;
	}

}
