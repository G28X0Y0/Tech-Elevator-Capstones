package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCWeatherDAO implements WeatherDAO{
	
	
	private JdbcTemplate jdbcTemplate;
	private String temp;
	
	@Autowired
	public JDBCWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Weather> get5DayForecast(String code) {
		List<Weather> fiveDayForecast = new ArrayList<Weather>();
		String weatherSql = "SELECT parkcode, fivedayforecastvalue, low, high, forecast FROM weather WHERE parkcode = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(weatherSql, code);
		
		while(result.next()) {
			fiveDayForecast.add(mapRowToWeather(result));
		}
		
		return fiveDayForecast;
	}
	
	@Override
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
	
	
	private Weather mapRowToWeather(SqlRowSet result) {
		Weather weather = new Weather();
		weather.setForecastDay(result.getInt("fivedayforecastvalue"));
		double low = result.getInt("low");
		double high = result.getInt("high");
		String forecast = result.getString("forecast");
		weather.setForecast(forecast);
		weather = setAdvisory(weather, low, high, forecast);
		weather = convertTemp(weather, low, high, temp);
		weather.setTemp(temp);
		return weather;
	}
	
	private Weather setAdvisory(Weather weather, double low, double high, String forecast) {
		String advisory = "";
		if (forecast.equals("snow")) {
			advisory += "Pack your snowshoes. ";
		}
		if (forecast.equals("rain")) {
			advisory += "Pack your rain gear and wear waterproof shoes. ";
		}
		if (forecast.equals("thunderstorms")) {
			advisory += "Seek shelter and avoid hiking on exposed ridges. ";
		}
		if (forecast.equals("sunny")) {
			advisory += "Pack sunblock. ";
		}	
		if (low > 75 || high > 75) {
			advisory += "Bring an extra gallon of water. ";
		}
		if ((high - low) > 20) {
			advisory += "Wear breathable layers. ";
		}
		if (low < 20) {
			advisory += "Warning: You are in danger due to exposure to frigid temperatures. ";
		}
		weather.setAdvisory(advisory);
		return weather;
	}
		

	
	private Weather convertTemp(Weather weather, double low, double high, String temp) {
		if (temp.equals("F")) {
			weather.setLow(low);
			weather.setHigh(high);
		} else if (temp.equals("C")) {
			double newLow = Math.round(((low  - 32) * (5.0 / 9.0)) * 10.0) / 10.0;
			double newHigh = Math.round(((high - 32) * (5.0 / 9.0)) * 10.0) / 10.0;
			weather.setLow(newLow);
			weather.setHigh(newHigh);
		}
		return weather;
	}
	
	

}
