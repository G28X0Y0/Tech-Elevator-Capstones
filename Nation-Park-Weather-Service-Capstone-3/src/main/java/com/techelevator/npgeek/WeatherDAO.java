package com.techelevator.npgeek;

import java.util.List;

public interface WeatherDAO {
	
	public List<Weather> get5DayForecast(String code);
	
	public void setTemp(String temp);
	
}
