package com.techelevator.npgeek;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCParkDAO implements ParkDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<Park>();
		String parkSql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park;";	
		SqlRowSet result = jdbcTemplate.queryForRowSet(parkSql);
		
		while (result.next()) {
			allParks.add(mapRowToPark(result));
		}
		
		return allParks;
	}
		
	public Park getParkByCode(String code) {
		String parkSql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park WHERE parkcode = ?;";	
		SqlRowSet result = jdbcTemplate.queryForRowSet(parkSql, code);
		
		result.next();
		
		Park park = mapRowToPark(result);
		
		
		return park;
	}
	
	private Park mapRowToPark(SqlRowSet result) {
		Park park = new Park();
		park.setCode(result.getString("parkcode"));
		park.setImgCode(result.getString("parkcode").toLowerCase());
		park.setName(result.getString("parkname"));
		park.setState(result.getString("state"));
		park.setAcreage(result.getInt("acreage"));
		park.setElevation(result.getInt("elevationinfeet"));
		park.setMilesOfTrail(result.getDouble("milesoftrail"));
		park.setCampsites(result.getInt("numberofcampsites"));
		park.setClimate(result.getString("climate"));
		park.setYearFounded(result.getInt("yearfounded"));
		park.setVisitorCount(result.getInt("annualvisitorcount"));
		park.setQuote(result.getString("inspirationalquote"));
		park.setQuoteSource(result.getString("inspirationalquotesource"));
		park.setDescription(result.getString("parkdescription"));
		String entryFee = formatMoney(result.getInt("entryfee"));
		park.setFee(entryFee);
		park.setSpecies(result.getInt("numberofanimalspecies"));
		
		return park;
	}
	
	public String formatMoney(int number) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String money = formatter.format(number);
		return money;
	}
}
