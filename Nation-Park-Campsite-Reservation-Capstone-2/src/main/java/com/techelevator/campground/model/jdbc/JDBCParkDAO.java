package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;

public class JDBCParkDAO implements ParkDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public List<Park> getAllAvailableParks() {
		List<Park> parks = new ArrayList<Park>();
		
		String selectsql = "SELECT park_id, name, location, establish_date, area, visitors, description FROM park ORDER BY name";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectsql);
		
		while(results.next()) {
			Park d = mapRowToPark(results);
			parks.add(d);
		}
		
		return parks;
	}

	private Park mapRowToPark(SqlRowSet results) {
		Park park = new Park(); 
		park.setParkId(results.getLong("park_id"));
		park.setName(results.getString("name"));
		park.setLocation(results.getString("location"));
		park.setEstablishDate(results.getDate("establish_date").toLocalDate());
		park.setArea(results.getInt("area"));
		park.setVisitors(results.getInt("visitors"));
		park.setDescription(results.getString("description"));
		
		return park;
	}
}
