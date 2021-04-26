/*
 * Salwa Abdalla 
 * ICS3U Culminating Assignment: 1/21/2019
 * Jeff Radulovic
 * 
 * The JobList is a class to hold the jobs available in my country
 * and it holds the getters and setter for the characteristics
 * 
 */

public class JobList {

	private String JobTitle;
	private String Location;
	private String Rank;
	private boolean Availability;
	private double Wage;
	private String[] Requirements;

	/**
	 * @return constructor for creating an instance of Job Occupation
	 */
	public JobList(String JobTitles, String Location, String Rank, boolean Availability, double Wage, String Requirements) {
		this.JobTitle = JobTitles;
		this.Location = Location;
		this.Rank = Rank;
		this.Availability = Availability; 
		this.Wage = Wage;
		this.Requirements = Requirements.split(" ");
		
	}
	
	/**
	 * @param Rank
	 * @return true or false depending on access of job due to rank
	 */
	public boolean filterJobList(String Rank){
		if (Rank == this.Rank)
			return true;
		else
			return false;
		
	}
	
	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return JobTitle;
	}
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return Location;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return Rank;
	}

	/**
	 * @return the availability
	 */
	public boolean isAvailable() {
		return Availability;
	}
	
	/**
	 * @return the wage
	 */
	public double getWage() {
		return Wage;
	}
	
	/**
	 * @return the requirements
	 */
	public String[] getRequirements() {
		return Requirements;
	}
	
	/**
	 * @param rank
	 * Sets the rank
	 */
	public void setRank(String rank) {
		Rank = rank;
	}
	
	/**
	 * @param availability
	 * Sets the availability
	 */
	public void setAvailability(boolean availability) {
		Availability = availability;
	}
	
	/**
	 * @return a specific format for the Occupation to be printed
	 */
	public String toString() {
		String result = "Job Title : " + JobTitle;
		result += " \n	Location: " + Location;
		result += " \n	Minimum Rank: " + Rank;
		result += " \n	Availability: " + Availability;
		result += " \n	Wage: $" + Wage;
		result += " \n	Requirements: ";
		for (int i = 0; i < Requirements.length; i++)
			result += Requirements[i] + " ";
		return result;
	}

}
