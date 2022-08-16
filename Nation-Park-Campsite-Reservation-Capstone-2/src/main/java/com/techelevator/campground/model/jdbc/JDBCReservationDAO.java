package com.techelevator.campground.model.jdbc;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public long createReservation(long id, String name, LocalDate startDate, LocalDate endDate) {
		Reservation reservation = new Reservation();
		String insertSql = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date, create_date) VALUES (DEFAULT, ?, ?, ?, ?, now()) RETURNING reservation_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(insertSql, id, name, startDate, endDate);
		
		results.next();
		reservation.setReservationId(results.getLong("reservation_id"));
		
		return reservation.getReservationId();
	}


}
