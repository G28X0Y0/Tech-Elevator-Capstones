package com.techelevator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;
import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.campground.model.jdbc.JDBCParkDAO;
import com.techelevator.campground.model.jdbc.JDBCReservationDAO;
import com.techelevator.campground.model.jdbc.JDBCSiteDAO;

public class JDBCReservationDAOIntegrationTest {
	private JdbcTemplate jdbcTemplate;
	private static SingleConnectionDataSource dataSource;
	private SiteDAO siteDao;
	private ReservationDAO dao;
	private CampgroundDAO campgroundDao;
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
		siteDao = new JDBCSiteDAO(dataSource);
		campgroundDao = new JDBCCampgroundDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		parkDao = new JDBCParkDAO(dataSource);
		dao = new JDBCReservationDAO(dataSource);
	}
	
	@Test
	public void returns_a_valid_confirmation_id() {
		List<Park> parks = parkDao.getAllAvailableParks();
		long parkId = parks.get(1).getParkId();
		
		List<Campground> campgrounds = campgroundDao.getAllCampgrounds(parkId);
		long campgroundId = campgrounds.get(1).getCampgroundId();	
		
		List<Site> sites = siteDao.getAllAvalableSites(campgroundId, LocalDate.of(3030, 02, 24), LocalDate.of(3030, 02, 27));
		long siteId = sites.get(0).getSiteId();
		long reservationId = dao.createReservation(siteId, "Quinn", LocalDate.of(3030, 02, 24), LocalDate.of(3030, 02, 27));
		
		Assert.assertNotSame(0, reservationId);
		Assert.assertNotNull(reservationId);
		
	}
	
}
