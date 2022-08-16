package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Park;

public class JDBCCampgroundDAO implements CampgroundDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campground> getAllCampgrounds(long id) {
		List<Campground> campgrounds = new ArrayList<Campground>();
		String selectSql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee FROM campground WHERE park_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSql, id);
		
		while(results.next()) {
			Campground c = mapRowToCampground(results);
			campgrounds.add(c);
		}
		return campgrounds;
	}

	@Override
	public List<Campground> getAvailableCampgrounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Private Method:
	private Campground mapRowToCampground(SqlRowSet results) {
		Campground campground = new Campground(); 
		campground.setCampgroundId(results.getLong("campground_id"));
		campground.setParkId(results.getLong("park_id"));
		campground.setName(results.getString("name"));
		campground.setOpenFrom(results.getString("open_from_mm"));
		campground.setOpenTo(results.getString("open_to_mm"));
		campground.setDailyFee(results.getDouble("daily_fee"));
		return campground;
	}

}
