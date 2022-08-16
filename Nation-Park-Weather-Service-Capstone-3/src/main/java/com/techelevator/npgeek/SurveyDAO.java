package com.techelevator.npgeek;

import java.util.List;

public interface SurveyDAO {
	
	public void saveResponses(String parkcode, String emailaddress, String state, String activitylevel);
	public List<Survey> displayResults();
}
