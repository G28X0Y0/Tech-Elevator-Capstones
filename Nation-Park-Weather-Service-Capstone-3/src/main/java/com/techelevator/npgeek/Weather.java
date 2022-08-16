package com.techelevator.npgeek;

import java.util.List;

public class Weather {
	
	private String code;
	private int forecastDay;
	private double low;
	private double high;
	private String forecast;
	private String advisory;
	private String temp;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getForecastDay() {
		return forecastDay;
	}
	public void setForecastDay(int forecastDay) {
		this.forecastDay = forecastDay;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public String getAdvisory() {
		return advisory;
	}
	public void setAdvisory(String advisory) {
		this.advisory = advisory;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
	
}
