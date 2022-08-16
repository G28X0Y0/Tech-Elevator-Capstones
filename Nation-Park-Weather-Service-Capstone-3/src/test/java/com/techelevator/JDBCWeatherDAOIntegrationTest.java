package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.JDBCParkDAO;
import com.techelevator.npgeek.JDBCWeatherDAO;
import com.techelevator.npgeek.ParkDAO;
import com.techelevator.npgeek.Weather;
import com.techelevator.npgeek.WeatherDAO;


public class JDBCWeatherDAOIntegrationTest extends DAOIntegrationTest {
	
	private WeatherDAO weatherDao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		weatherDao = new JDBCWeatherDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}
	
	@Test
	public void add_new_park_and_five_day_forecast_returns_list_size() {
		
		String newParkSql = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(newParkSql, "ONP", "Ohio National Park", "Ohio", 1500010, 100, 777.7, 10, "shitty", 2020, 10000, "Imma tell you something about me, Joe Rogan, that you might not know. I smoke rocks.", "Dave Chappelle", "This park boasts the most amazing flora and fauna", 10, 900);
		
		String fiveDaySql = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast) VALUES (?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?);";
		jdbcTemplate.update(fiveDaySql, "ONP", 1, 32, 42, "snow", "ONP", 2, 33, 45, "partly cloudy", "ONP", 3, 20, 40, "snow", "ONP", 4, 45, 65, "sunny", "ONP", 5, 35, 45, "thunderstorms");
		
		weatherDao.setTemp("F");
		
		List<Weather> fiveDayTest = weatherDao.get5DayForecast("ONP");
		
		Assert.assertEquals(5, fiveDayTest.size());
		
		// Test to verify if the forecast for the 4th day is sunny like we inserted it
		
		Assert.assertEquals("sunny", fiveDayTest.get(3).getForecast());
		
		
		
	}
	
	
}
