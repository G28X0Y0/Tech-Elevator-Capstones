package com.techelevator.campground.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;

public class JDBCSiteDAO implements SiteDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Site> getAllAvalableSites(long id, LocalDate startLocalDate, LocalDate endLocalDate) {
		List<Site> site = new ArrayList<Site>();
		String selectSql = "Select distinct * from site\n" + 
				"join campground on site.campground_id = campground.campground_id\n" + 
				"where site.campground_id = ?\n" + 
				"and site_id not in\n" + 
				"(select site.site_id from site\n" + 
				"join reservation on reservation.site_id = site.site_id\n" + 
				"where (? > reservation.from_date AND ? < reservation.to_date) OR (? > reservation.from_date AND ? < reservation.to_date)) order by site_number DESC\n" + 
				"LIMIT 5";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSql, id, startLocalDate, endLocalDate, startLocalDate, endLocalDate);
		
		while(results.next()) {
			Site s = mapRowToSite(results);
			site.add(s);
		}
		return site;
	}
		
	private Site mapRowToSite(SqlRowSet results) {
		Site site = new Site(); 
		site.setSiteId(results.getLong("site_id"));
		site.setCampgroundId(results.getLong("campground_id"));
		site.setSiteNumber(results.getInt("site_number"));
		site.setMaxOccupancy(results.getInt("max_occupancy"));
		site.setAccessible(results.getBoolean("accessible"));
		site.setMaxRVLength(results.getInt("max_rv_length"));
		site.setHasUtilities(results.getBoolean("utilities"));
		return site;
	}

}
