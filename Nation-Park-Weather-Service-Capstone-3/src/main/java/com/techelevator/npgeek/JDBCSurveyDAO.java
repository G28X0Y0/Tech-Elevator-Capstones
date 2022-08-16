package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyDAO implements SurveyDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSurveyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveResponses(String parkcode, String emailaddress, String state, String activitylevel) {
		String sqlSurvey = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) " + 
				"VALUES(DEFAULT, ?, ?, ?, ?);";
		jdbcTemplate.update(sqlSurvey, parkcode, emailaddress, state, activitylevel);
	}
	
	@Override
	public List<Survey> displayResults() {
		List<Survey> surveyResults = new ArrayList<Survey>();
		String sqlSurvey = "SELECT park.parkname AS name, COUNT(survey_result.surveyid) AS favorite FROM survey_result JOIN park ON survey_result.parkcode = park.parkcode GROUP BY name ORDER BY favorite DESC, name ASC";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSurvey);
		
		while(result.next()) {
			surveyResults.add(mapRowToSurvey(result));
		}
		
		return surveyResults;
	}
	
	private Survey mapRowToSurvey(SqlRowSet result) {
		Survey survey = new Survey();
		
		survey.setName(result.getString("name"));
		survey.setFavorite(result.getString("favorite"));
		
		return survey;
	}
}
