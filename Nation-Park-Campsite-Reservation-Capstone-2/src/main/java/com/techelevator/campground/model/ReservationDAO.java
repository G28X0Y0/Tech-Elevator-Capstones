package com.techelevator.campground.model;

import java.time.LocalDate;

public interface ReservationDAO {

	public long createReservation(long id, String name, LocalDate startDate, LocalDate endDate);
}
