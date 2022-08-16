package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.List;

public interface SiteDAO {
	// Uses a subQuery to see available sites in Camp ground:
	public List<Site> getAllAvalableSites(long id, LocalDate startLocalDate, LocalDate endLocalDate); 

}
