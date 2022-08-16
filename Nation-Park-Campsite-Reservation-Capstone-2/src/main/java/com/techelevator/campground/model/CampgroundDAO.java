package com.techelevator.campground.model;

import java.util.List;

public interface CampgroundDAO {
	
	public List<Campground> getAllCampgrounds(long id);
	//Uses a subQuery of selected Park:
	public List<Campground> getAvailableCampgrounds(); 


}
