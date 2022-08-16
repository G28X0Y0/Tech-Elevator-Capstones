package com.techelevator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.jdbc.JDBCParkDAO;

public class JDBCParkDAOIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private ParkDAO dao;
	private JdbcTemplate jdbcTemplate;
	
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
	
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@Before
	public void setup() {
		dao = new JDBCParkDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void get_all_Parks() {
		
		// Arrange
		List<Park> parks = dao.getAllAvailableParks();
		int originalSize = parks.size();	
		long rKellyPark = insertPark("R Kelly", "Ohio", LocalDate.of(2019, 10, 24), 40400404, 23894825, "The fox jumped over the moon");
		// Act
		parks = dao.getAllAvailableParks();
		
		// Assert
		Assert.assertEquals("Wrong number of Parks returned", originalSize + 1, parks.size());
		
		boolean isInList = false;
		for (Park park : parks) {
			if (park.getParkId() == rKellyPark) {
				isInList = true;
			}
		}
		 
		Assert.assertTrue("New Park is not in list", isInList);		
	}
	
	private int insertPark(String name, String location, LocalDate establishedDate, int area, int visitors, String discription) {
		String projectSql = "INSERT INTO park (park_id, name, location, establish_date, area, visitors, description) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING park_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(projectSql, name, location, establishedDate, area, visitors, discription);
		results.next();
		return results.getInt("park_id");
	}
}
