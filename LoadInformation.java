/*
 * Salwa Abdalla 
 * ICS3U Culminating Assignment: 1/21/2019
 * Jeff Radulovic
 * 
 * The Load information class is used to read from the accompanying files
 * This includes loading in employee information, job listings and messages
 * that were sent between the employees.
 * 
 */

//importing the libraries necessary
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadInformation {
	
	//initializing and setting a "blank" value to an array list to hold all the information
	private ArrayList<Employees> Employees_List = new ArrayList<Employees>();
	private ArrayList<JobList> JobList_List = new ArrayList<JobList>();
	private ArrayList<Message> Message_List = new ArrayList<Message>();
	
	/**
	 * @param path
	 * @return An ArrayList of Employees
	 */
	public ArrayList<Employees> loadEmployees(String path) {
		
		//clear the employee list of past employees
		Employees_List.clear();
		File file = new File(path);
		Scanner scan; 

		try {
			scan = new Scanner(file); 
			scan.nextLine();
			
			//for each line, split the values by commas (as it's a csv file)
			while (scan.hasNextLine()) { 
				String line = scan.nextLine(); 
				String[] fields = line.split(",");
				
				
				//Employee Constructor ORDER: Employee Number, First Name, Last Name, User Name, 
				//								Password, Rank, Birthdate, Location
				//Fields from File ORDER: Employee Number, Last Name, First Name, User Name, 
				//								Password, Rank, Birthdate, Location
				
				//constructing the employee using the above order and adding it to the file
				Employees e = new Employees(fields[0], fields[2], fields[1], fields[3], fields[4], 
						fields[5], fields[6], fields[7]);
				Employees_List.add(e);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(path);
		}
		return Employees_List;
	}
	
	/**
	 * @param path
	 * @return An ArrayList of Jobs
	 */
	public ArrayList<JobList> loadJobLists(String path) {
		
		//clear the job list of past jobs
		JobList_List.clear();
		File file = new File(path);
		Scanner scan; 

		try {
			scan = new Scanner(file); 
			scan.nextLine();
			
			//for each line, split the values by commas (as it's a csv file)
			while (scan.hasNextLine()) { 
				String line = scan.nextLine(); 
				String[] fields = line.split(",");
				
				//JobList ORDER: Job Title	Location	Rank	Availability	Wage   Requirements
				//Fields ORDER: Job Title	Location	Rank	Availability	Wage   Requirements
				
				//constructing the  using the above order and adding it to the file
				JobList j = new JobList(fields[0], fields[1], fields[2], Boolean.valueOf(fields[3]), 
						Double.valueOf(fields[4]), fields[5]);
				JobList_List.add(j);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(path);
		}
		return JobList_List;
	}
	
	/**
	 * @param path
	 * @return An ArrayList of Messages
	 */
	public ArrayList<Message> loadMessages(String path) {

		//clear the personal messages of past messages
		Message_List.clear();
		File file = new File(path);
		Scanner scan; 

		try {
			scan = new Scanner(file); 
			scan.nextLine();

			//for each line, split the values by commas (as it's a csv file)
			while (scan.hasNextLine()) { 
				String line = scan.nextLine(); 
				String[] fields = line.split(",");
				
				//Message ORDER: String RecipientEmployeeNum, String SenderEmployeeNum, 
				//					int LevelOfEncryption, ArrayList<Long> Message, Encryption Key
				//Fields ORDER: Recipient Employee Num	Sender Employee Number	Level of Encryption	
				//				Message     Encryption Key
				
				//translating field variables into variables used for the constructor
				String[] list = fields[3].split(" ");
				long[] List = new long[list.length];
				for (int i = 0; i < list.length; i++) {
					List[i] = Long.valueOf(list[i]);
				}		
				
				String[] encryptionkey = fields[4].split(" ");		
				ArrayList<Long> EncryptionKey = new ArrayList<Long>();
				for (int i = 0; i < encryptionkey.length; i++) {
				    try {
				    	EncryptionKey.add(Long.valueOf(encryptionkey[i]));
				    }
				    catch(NumberFormatException ex) {
				    }

				}
				
				//constructing the  using the above order and adding it to the file
				Message m = new Message(fields[0], fields[1], Integer.valueOf(fields[2]), List, EncryptionKey);

				for(int i = 0; i < Employees_List.size(); i++) {
					if (fields[0].equals(Employees_List.get(i).getEmployeeNum()) || fields[1].equals(Employees_List.get(i).getEmployeeNum())) 
						Employees_List.get(i).addPersonalMessage(m);
				}
				
				Message_List.add(m);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return Message_List;
	}

}
