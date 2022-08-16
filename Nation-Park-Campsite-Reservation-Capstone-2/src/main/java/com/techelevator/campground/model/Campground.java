package com.techelevator.campground.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

public class Campground {
	private long campgroundId;
	private long parkId;
	private String name;
	private String openFrom;
	private String openTo;
	private double dailyFee;
	
	//Getters and Setters:
	public long getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(long campgroundId) {
		this.campgroundId = campgroundId;
	}
	public long getParkId() {
		return parkId;
	}
	public void setParkId(long parkId) {
		this.parkId = parkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpenFrom() {
		return openFrom;
	}
	public void setOpenFrom(String openFrom) {
		this.openFrom = openFrom;
	}
	public String getOpenTo() {
		return openTo;
	}
	public void setOpenTo(String openTo) {
		this.openTo = openTo;
	}
	public double getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(double dailyFee) {
		this.dailyFee = dailyFee;
	}
	public String toString() {
		return this.name;
	}
	
	
	
	
}
