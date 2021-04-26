/*
 * Salwa Abdalla 
 * ICS3U Culminating Assignment: 1/21/2019
 * Jeff Radulovic
 * 
 * The Write information class is used to write to the accompanying files
 * This includes creating new employee information, and messages
 * that were sent between the employees.
 * 
 */

//importing the necessary libraries
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class WriteInformation {
	
	//hard coding the paths that will be written into
	private String EmployeesPath = "./src/Files/Employees.csv";
	private String JobListingsPath = "./src/Files/JobListings.csv";
	private String MessagesPath = "./src/Files/Messages.csv";	
	
	/**
	 * @param NewEmployee
	 * @throws IOException
	 */
	public void createEmployee(Employees NewEmployee, ArrayList<Employees> Employees_List) throws IOException {
		
		//open the file and use random access to read/write
		File Employees_file = new File(EmployeesPath);
		Employees_List.add(NewEmployee);
		RandomAccessFile randomAccess = new RandomAccessFile(Employees_file, "rw");
		
		//move pointer to last character and write in new employee
		randomAccess.seek(randomAccess.length());	
		String fields = "\n" + NewEmployee.getEmployeeNum() + "," + NewEmployee.getLastName() + "," + NewEmployee.getFirstName() + "," + NewEmployee.getUsername() + "," + NewEmployee.getPassword() + "," + NewEmployee.getRank() + "," + NewEmployee.getBirthdate() + "," + NewEmployee.getLocation() + ",";
		randomAccess.write(fields.getBytes());
		randomAccess.close();
	}
	
	/**
	 * @param NewJobList
	 * @param JobList_List
	 * @throws IOException
	 */
	public void createJobListings(JobList NewJobList, ArrayList<JobList> JobList_List) throws IOException {
		
		//open the file and use random access to read/write
		File JobList_file = new File(JobListingsPath);
		JobList_List.add(NewJobList);
		RandomAccessFile randomAccess = new RandomAccessFile(JobList_file, "rw");
		
		//move pointer to last character and write in new job list
		randomAccess.seek(randomAccess.length());	
		String fields = "\n" + NewJobList.getJobTitle() + "," + NewJobList.getLocation() + "," + NewJobList.getRank() + "," + NewJobList.isAvailable() + "," + NewJobList.getWage() + ",";
		for (int i = 0; i < NewJobList.getRequirements().length; i ++) {
			fields += NewJobList.getRequirements()[i];
			if (i < NewJobList.getRequirements().length - 1)
				fields += " ";
		}
		fields += ",";
		randomAccess.write(fields.getBytes());
		randomAccess.close();
	}
	
	/**
	 * @param NewMessage
	 * @param Employees_List
	 * @param Message_List
	 * @throws IOException
	 */
	public void createMessage(Message NewMessage, ArrayList<Employees> Employees_List, ArrayList<Message> Message_List) throws IOException {
		
		//open the file and use random access to read/write
		File Messages_file = new File(MessagesPath);
		Message_List.add(NewMessage);
		RandomAccessFile randomAccess = new RandomAccessFile(Messages_file, "rw");
		
		//move pointer to last character and write in new message
		randomAccess.seek(randomAccess.length());	
		String fields = "\n" + NewMessage.getRecipientEmployeeNum() + "," + NewMessage.getSenderEmployeeNum() + "," + NewMessage.getLevelOfEncryption() + ",";
		for (int i = 0; i < NewMessage.getMessageEncrypted().size(); i ++) {
			fields += NewMessage.getMessageEncrypted().get(i);
			if (i < NewMessage.getMessageEncrypted().size() - 1)
				fields += " ";
			else
				fields += ", ";
		}
		
		for (int i = 0; i < NewMessage.getEncryption_key().size(); i++) {
			fields += NewMessage.getEncryption_key().get(i);
			if (i < NewMessage.getEncryption_key().size() - 1)
				fields += " ";
			else
				fields += ", ";
		}
		
		
		randomAccess.write(fields.getBytes());
		randomAccess.close();
		
		for(int i = 0; i < Employees_List.size(); i++) {
			if (NewMessage.getRecipientEmployeeNum().equals(Employees_List.get(i).getEmployeeNum()) || NewMessage.getSenderEmployeeNum().equals(Employees_List.get(i).getEmployeeNum())) 
				Employees_List.get(i).addPersonalMessage(NewMessage);
		}
	}
}
