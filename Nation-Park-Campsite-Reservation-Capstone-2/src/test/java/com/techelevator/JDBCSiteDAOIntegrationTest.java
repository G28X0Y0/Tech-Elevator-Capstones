package com.techelevator;

import java.math.BigDecimal;
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

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;
import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.campground.model.jdbc.JDBCParkDAO;
import com.techelevator.campground.model.jdbc.JDBCSiteDAO;

public class JDBCSiteDAOIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private SiteDAO dao;
	private JdbcTemplate jdbcTemplate;
	private ParkDAO parkDao;
	private CampgroundDAO campgroundDao;
	
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
		dao = new JDBCSiteDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		parkDao = new JDBCParkDAO(dataSource);
		campgroundDao = new JDBCCampgroundDAO(dataSource);
	}
	
	@Test
	public void get_all_sites() {
		
		// Arrange
		List<Park> parks = parkDao.getAllAvailableParks();
		long parkId = parks.get(1).getParkId();
		
		List<Campground> campgrounds = campgroundDao.getAllCampgrounds(parkId);
		long campId = campgrounds.get(1).getCampgroundId();
		
		List<Site> sites = dao.getAllAvalableSites(campId, LocalDate.of(2019, 02, 24), LocalDate.of(2019, 02, 27));
		
		int originalSize = sites.size();	
		long newSiteId = insertSite(campId, 1000, 6, true, 35, true);
		// Act
		sites = dao.getAllAvalableSites(campId, LocalDate.of(2019, 02, 24), LocalDate.of(2019, 02, 27));
		
		//Assert:
		Assert.assertEquals(originalSize + 1, sites.size());
		
		boolean isInList = false;
		for (Site site : sites) {
			if (site.getSiteId() == newSiteId) {
				isInList = true;
			}
		}
		 
		Assert.assertTrue("New Site is not in list", isInList);		
	}
	private int insertSite(long campgroundId, int siteNumber, int maxOcc, boolean accessible, int maxRvLength, boolean utilities) {
		String projectSql = "INSERT INTO site (site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING site_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(projectSql, campgroundId, siteNumber, maxOcc, accessible, maxRvLength, utilities);
		results.next();
		return results.getInt("site_id");
	}
}
