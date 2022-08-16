package com.techelevator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.campground.model.jdbc.JDBCParkDAO;

public class JDBCCampgroundDAOIntegrationTest {
	
	private JdbcTemplate jdbcTemplate;
	private static SingleConnectionDataSource dataSource;
	private CampgroundDAO dao;
	private ParkDAO parkDao;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	@Before
	public void setup() {
		dao = new JDBCCampgroundDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		parkDao = new JDBCParkDAO(dataSource);
	}
	@Test
	public void get_all_Parks() {
		
		// Arrange
		List<Park> parks = parkDao.getAllAvailableParks();
		long parkId = parks.get(1).getParkId();
		
		List<Campground> campgrounds = dao.getAllCampgrounds(parkId);
		int originalSize = campgrounds.size();	
		long newCampgroundId = insertCampground(parkId, "Campy's Campground", "01", "02", new BigDecimal(35.00));
		// Act
		campgrounds = dao.getAllCampgrounds(parkId);
		
		// Assert
		Assert.assertEquals("Wrong number of Parks returned", originalSize + 1, campgrounds.size());
		
		boolean isInList = false;
		for (Campground campground : campgrounds) {
			if (campground.getCampgroundId() == newCampgroundId) {
				isInList = true;
			}
		}
		 
		Assert.assertTrue("New Campground is not in list", isInList);		
	}
	
	private int insertCampground(long parkId, String name, String openFrom, String openTo, BigDecimal dailyFee) {
		String projectSql = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (DEFAULT, ?, ?, ?, ?, ?) RETURNING campground_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(projectSql, parkId, name, openFrom, openTo, dailyFee);
		results.next();
		return results.getInt("campground_id");
	}

}
