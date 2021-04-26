/*
 * Salwa Abdalla 
 * ICS3U Culminating Assignment: 1/21/2019
 * Jeff Radulovic
 * 
 * The employees class is a class to create a constructor and a getter
 * and setter for each of the characteristics.
 * 
 */

import java.util.ArrayList;

public class Employees {
	
	private String EmployeeNum;
	private String FirstName;
	private String LastName;
	private String Username;
	private String Password;
	private String rank;
	private String Birthdate;
	private String Location;
	private String Occupation;
	private ArrayList<Message> PersonalMessages;
	
	/**
	 * @return constructor for creating an instance of Employees
	 */
	public Employees(String EmployeeNum, String FirstName, String LastName, String Username, String Password, String rank, String Birthdate, String Location) {
		this.EmployeeNum = EmployeeNum;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Username = Username;
		this.Password = Password;
		this.rank = rank;
		this.Birthdate = Birthdate;
		this.Location = Location;
		this.PersonalMessages = new ArrayList<Message>();
	}
	
	/**
	 * @return getter for the employeeNum
	 */
	public String getEmployeeNum() {
		return EmployeeNum;
	}

	/**
	 * @return getter for the username
	 */
	public String getUsername() {
		return Username;
	}

	/**
	 * @return getter for the password
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * @return the birthdate
	 */
	public String getBirthdate() {
		return Birthdate;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return Location;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * @return getter for the rank
	 */
	public String getRank() {
		return rank;
	}
	
	/**
	 * @return getter for the occupation
	 */
	public String getOccupation() {
		return Occupation;
	}
	
	/**
	 * @return getter for the Messages
	 */
	public ArrayList<Message> getPersonalMessages() {
		return PersonalMessages;
	}
	
	/**
	 * @param Message
	 * Add a message to person's message list
	 */
	public void addPersonalMessage(Message PersonalMessage) {
		PersonalMessages.add(PersonalMessage);
	}

	/**
	 * @param employeeNum
	 * A setter for the Employee Number
	 */
	public void setEmployeeNum(String employeeNum) {
		EmployeeNum = employeeNum;
	}

	/**
	 * @param username
	 * A setter for the UserName
	 */
	public void setUsername(String username) {
		Username = username;
	}

	/**
	 * @param password
	 * A setter for the Password
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * @param rank
	 * A setter for the Rank
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	/**
	 * @param Occupation
	 * A setter for the Occupation
	 */
	public void setOccupation(String Occupation) {
		this.Occupation = Occupation;
	}

	/**
	 * @return specific format of the Employees to be printed
	 */
	public String toString() {
		String result = "Employee Num : " + EmployeeNum;
		result += "\nEmployee Name: " + FirstName + " " + LastName;
		result += "\nLogin Information: " + Username + " " + Password;
		result += "\nRank: " + rank;
		result += "\nBirthdate: " + Birthdate;
		result += "\nLocation: " + Location;
		result += "\nPersonal Messages: " + PersonalMessages;
		return result;
	}

}
