package lafoodpackage;

/*******************************************************************************
 * Driver Main Class
 * 
 * The main class. The purposes of this program is to maintain a seating arrangement
 * for guests coming to a restaurant with no reservations. The program keeps track of
 * the guests by placing their information in an unbounded array based queue, and
 * uses various different methods to break down the information outside of the queue
 * in order to neatly print the information to the console. The program calculates
 * the average time waited, each customer's wait time, the seating arrangements, guests
 * who were not seated, and the party information using the Party class. The queue is
 * implemented from a generic unbounded queue array based class and accounts for underflow
 * exceptions.
 * 
 * Preconditions: The document must be in the proper format (letter, space, number, space, number, space, name). 
 * The document must also be in .txt format. 
 * 
 * Postconditions: The customers will be seated properly, as instructed by the .txt document, and the 
 * average wait time, customers not seated, and console based output to maintain the customer seating
 * throughout the program will printed. 
 * 
 * @author Mariah Avalos
 * @date 10/15/2017
 * @version 1.0
 * 
 ******************************************************************************/

import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Driver {


	public static void main(String[] args) throws IOException{
		/*the main function- used only for intializing the reading of the file
		 * as well as closing the file out when finished. Responsible for the
		 * user input as well.
		 */
		
		System.out.println("*** Welcome to the La Food Restaurant Simulator ***");
		System.out.println("Enter data file name:");
		Scanner input = new Scanner(System.in);
		String file_name = input.nextLine();
		System.out.println("\n");
		analyze_file (file_name);
		
		input.close();
	}
	
	public static void analyze_file(String file_name) throws FileNotFoundException{
		/* The analyze_file method - the meat of this program. 
		 * parses the input file, calls the methods that keep track of the time waited,
		 * the customers seated, the party information, and the queue manipulations. It
		 * also checks for empty files and file not found errors.
		 * Once finished, the method prints out a list of unseated guests. 
		 */
		
		Queue queue = new QueueArray();
		int check_if_empty_file = 0, current_time, current_wait_time = 0, 
				current_total_wait_time = 0, arrival_time = 0, track_guests = 0;
		float avg_wait_time;
		File input = new File(file_name);
		Scanner file = new Scanner(input);
		
		while (file.hasNextLine()){
			String line = file.nextLine();
			if (line.length() > 0){
				check_if_empty_file += 1;
				current_time = parse_array(line, queue);
				if (current_time > 0){
					track_guests += 1;
					arrival_time = remove_if_available_table(queue);
					current_wait_time = calculate_current_waittime (current_time, arrival_time);
					current_total_wait_time += calculate_current_waittime (current_time, arrival_time);
					System.out.println("Your wait time was " + current_wait_time + " minutes." + "\n");
				}
			}
		}
	
		if (check_if_empty_file < 1) {
			System.out.println("That's an empty file!");
		}
		
		avg_wait_time = (float)current_total_wait_time / (float)track_guests;
		
		System.out.println("**Simulation Terminated**");
		System.out.println("The average wait time was: " + (Math.round(avg_wait_time * 100)/100.0) + " minutes." + "\n");
		
		System.out.println("Guests not seated: ");
		while (!queue.isEmpty()){
			String customer = (String)queue.dequeue();
			Party party = new Party(customer);
			System.out.println(party.getName(customer));
		}
		
		file.close();
		
	}


	public static int parse_array(String sentence, Queue queue){
		/*method checks for the first character of the input line, and:
		 * If it is an A then add a party to the queue for further 
		 * evaluation and print out wait information. 
		 * If it is a T, recordthe current time. 
		 * If it is a Q, quit the program. If the current time is 0, 
		 * which is by default, return the value so that the calling method will know
		 * a table is not ready. Return a value > 0 and it will signify that the table 
		 * is ready. This value can later be subtracted from the value recorded in
		 * the Party class to find the difference in wait time. 
		 */
		
		if (sentence.charAt(0) == 'A'){
			Object array = sentence;
			String name = ""; 
			int party_size = 0, time = 0; 
			queue.enqueue(array);
			
			Party party = new Party((String)array);
			name = party.getName(sentence);
			party_size = party.getSize((String)array);
			System.out.println("Please wait at the bar "+ name + " party of " + party_size + "!");
			
			return -1;
		}
		
		else if (sentence.charAt(0) == 'T'){
			String time = "";
			int current_time = 0, track_whitespace = 0;
			
			for (int i = 0; i < sentence.length(); i++){
				if (sentence.charAt(i) == ' '){
					track_whitespace += 1;
				}
				else if (sentence.charAt(i) != ' ' && track_whitespace == 1){
					time += sentence.charAt(i);
				}
			}
			
			current_time = Integer.parseInt(time);
			return current_time;
		}
		
		return 0;
		
	}
	
	
	public static int remove_if_available_table(Queue queue){
		/*method that dequeues from the queue if a customer is seated and
		 * prints out their information to the console. It returns the time 
		 * the customer waited to be seated. 
		 */
		
		Object array = queue.dequeue();
		Party party = new Party((String)array);
		String name = party.getName((String)array);
		int party_size = party.getSize((String)array);
		int get_time = party.getTime ((String)array);
		
		System.out.println("\n" + "Table for "+ name + " party of " + party_size + "!");
		
		return get_time;
	}
	
	public static int calculate_current_waittime(int current_time, int arrival_time){
		/*small method, calculates the current wait time of each customer and returns it to the
		 * analyze file method for further calculations
		 */
		
		return (current_time - arrival_time );
	}

}