package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.JDBCParkDAO;
import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.ParkDAO;



public class JDBCParkDAOIntegrationTest extends DAOIntegrationTest {
	
	private ParkDAO parkDao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		parkDao = new JDBCParkDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}

	@Test
	public void get_all_parks_returns_correct_size() {
		List<Park> allParks = parkDao.getAllParks();
		int originalSize = allParks.size();
		
		String parkSql = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		jdbcTemplate.update(parkSql, "ONP", "Ohio National Park", "Ohio", 1500010, 100, 777.7, 10, "shitty", 
							2020, 10000, "Imma tell you something about me, Joe Rogan, that you might not know."
									+ " I smoke rocks.", "Dave Chappelle", "This park boasts the most amazing flora"
											+ " and fauna", 10, 900);
		List<Park> updatedParks = parkDao.getAllParks();
		int updatedSize = updatedParks.size();
		
		Assert.assertEquals(originalSize + 1, updatedSize);
		
		boolean isInList = false;
		for (Park park : parkDao.getAllParks()) {
			if (park.getCode().equals("ONP")) {
				isInList = true;
			}
		}
		Assert.assertTrue(isInList);
	}
	
	@Test
	public void get_park_by_code_returns_true_with_code_that_does_exist() {
		List<Park> allParks = parkDao.getAllParks();

		Park parkGNP = parkDao.getParkByCode("GNP");
		
		boolean isInList = false;
		for (Park park : allParks) {
			if (park.getCode().equals(parkGNP.getCode())) {
			isInList = true;
			}
		}
		Assert.assertTrue(isInList);
		
	}
 	
	@Test
	public void after_inserting_new_park_get_park_by_code_returns_new_park() {
		
		String parkSql = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		jdbcTemplate.update(parkSql, "ONP", "Ohio National Park", "Ohio", 1500010, 100, 777.7, 10, "shitty", 
							2020, 10000, "Imma tell you something about me, Joe Rogan, that you might not know."
									+ " I smoke rocks.", "Dave Chappelle", "This park boasts the most amazing flora"
											+ " and fauna", 10, 900);
		List<Park> allParks = parkDao.getAllParks();
		
		Assert.assertEquals(allParks.get(allParks.size() - 1).getCode(), parkDao.getParkByCode("ONP").getCode());
		
		
		
	}
	
	
}