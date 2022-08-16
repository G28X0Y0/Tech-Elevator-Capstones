package com.techelevator.campground.model;

import java.time.LocalDate;

public class Reservation {
	
	private long reservationId;
	private long siteId; 
	private String name; 
	private LocalDate fromDate;
	private LocalDate ToDate;
	private LocalDate createDate;
	
	
	//Getters and Setters
	public long getReservationId() {
		return reservationId;
	}
	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}
	public long getSiteId() {
		return siteId;
	}
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return ToDate;
	}
	public void setToDate(LocalDate toDate) {
		ToDate = toDate;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public String toString() {
		return this.name;
	}

}
