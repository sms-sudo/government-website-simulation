/*
 * Salwa Abdalla 
 * ICS3U Culminating Assignment: 1/21/2019
 * Jeff Radulovic
 * 
 * The search class is a collection of methods that take in an array list and
 * some sort of string or integer and search through a specific aspect. For example
 * the employee number or perhaps the username or password
 * 
 */


import java.util.ArrayList;

public class Search {
		
	/**
	 * @param Employee_List
	 * @param EmployeeNum
	 * @return index of Employee according to filter
	 */
	public int searchIndexEmployees(ArrayList<Employees> Employee_List, String EmployeeNum) {
		for (int i = 0; i < Employee_List.size(); i++) {
			if (Employee_List.get(i).getEmployeeNum().equals(EmployeeNum))
				return i;
		}
		return -1;
		
	}
	
	/**
	 * @param Employee_List
	 * @param username
	 * @param password
	 * @return
	 */
	public int searchIndexEmployees(ArrayList<Employees> Employee_List, String username, String password) {
		for (int i = 0; i < Employee_List.size(); i++) {
			if (Employee_List.get(i).getUsername().equals(username) && Employee_List.get(i).getPassword().equals(password))
				return i;
		}
		return -1;
		
	}

	/**
	 * @param JobList_List
	 * @param JobTitle
	 * @return index of Job according to filter
	 */
	public int searchIndexJob(ArrayList<JobList> JobList_List, String JobTitle) {
		for (int i = 0; i < JobList_List.size(); i++) {
			if (JobList_List.get(i).getJobTitle().toLowerCase().equals(JobTitle.toLowerCase()))
				return i;
		}
		return -1;
	}
	
	/**
	 * @param Location
	 * @param JobList_List
	 * @return all of the Job that fit the location
	 */
	public ArrayList<JobList> searchIndexJob(String Location, ArrayList<JobList> JobList_List) {
		ArrayList<JobList> filtered = new ArrayList<JobList>();
		for (int i = 0; i < JobList_List.size(); i++) {
			if (JobList_List.get(i).getLocation().toLowerCase().equals(Location.toLowerCase()))
				filtered.add(JobList_List.get(i));
		}
		return filtered;
	}
	
	/**
	 * @param JobList_List
	 * @param Availability
	 * @return index of Job according to filter
	 */
	public int searchIndexJob(ArrayList<JobList> JobList_List, boolean Availability) {
		for (int i = 0; i < JobList_List.size(); i++) {
			if (JobList_List.get(i).isAvailable() == Availability)
				return i;
		}
		return -1;
		
	}
		
	/**
	 * @param JobList_List
	 * @param Wage
	 * @return index of Job according to filter
	 */
	public int searchIndexJob(ArrayList<JobList> JobList_List, double Wage) {
		for (int i = 0; i < JobList_List.size(); i++) {
			if (JobList_List.get(i).getWage() >= Wage)
				return i;
		}
		return -1;
		
	}
	
}
