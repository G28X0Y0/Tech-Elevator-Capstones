package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.npgeek.JDBCSurveyDAO;
import com.techelevator.npgeek.JDBCWeatherDAO;
import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.SurveyDAO;
import com.techelevator.npgeek.WeatherDAO;

public class JDBCSurveyDAOIntegrationTest extends DAOIntegrationTest {

	private SurveyDAO surveyDao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		surveyDao = new JDBCSurveyDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}
	
	@Test
	public void save_responses_increases_survey_result_size_by_one() {
		
		String sizeSql = "SELECT COUNT(surveyid) AS survey_count FROM survey_result;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sizeSql);
		result.next();
		int originalSize = result.getInt("survey_count");
		
		surveyDao.saveResponses("GNP", "headington5@aol.com", "Ohio", "Extremely Active");
		
		SqlRowSet newResult = jdbcTemplate.queryForRowSet(sizeSql);
		newResult.next();
		
		int updatedSize = newResult.getInt("survey_count");
		
		Assert.assertEquals(originalSize + 1, updatedSize);
	}
	
	@Test
	public void new_survey_submission_increases_GNP_park_favorite_votes_by_one() {
		
		List<Survey> originalSurveys = surveyDao.displayResults();
		String gnpOrignalFavoriteVotes = originalSurveys.get(3).getFavorite();
		int original = Integer.parseInt(gnpOrignalFavoriteVotes);
		
		surveyDao.saveResponses("GNP", "headington5@aol.com", "Ohio", "Extremely Active");
		
		List<Survey> updatedSurveys = surveyDao.displayResults();
		String gnpUpdatedFavoriteVotes = updatedSurveys.get(3).getFavorite();
		int updated = Integer.parseInt(gnpUpdatedFavoriteVotes);
		
		Assert.assertEquals(original + 1, updated);
		
		
	
		
	
	}
	
}
