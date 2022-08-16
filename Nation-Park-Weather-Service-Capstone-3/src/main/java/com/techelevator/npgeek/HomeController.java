package com.techelevator.npgeek;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller 
@SessionAttributes("temp")
public class HomeController {
	
	@Autowired
	ParkDAO parkDao;
	
	@Autowired
	WeatherDAO weatherDao;
	
	@Autowired
	SurveyDAO surveyDao;
	
	@RequestMapping(path="/", method=RequestMethod.GET) 
	public String displayHomePage(ModelMap map){
		
		map.addAttribute("parks", parkDao.getAllParks());
		
		return "homePage";
	}
	
	@RequestMapping(path="/parkDetail", method=RequestMethod.GET) 
	public String displayParkDetail(ModelMap map, @RequestParam String code, @RequestParam(required=false, value="temp") String temp) {
		
		map.addAttribute("park", parkDao.getParkByCode(code));
		
		if (map.get("temp") == null) {
			weatherDao.setTemp("F");
			map.addAttribute("temp", "F");
		} else if (temp != null) {
			map.addAttribute("temp", temp);
			String tempVal = (String)map.get("temp");
			weatherDao.setTemp(tempVal);
		} else {
			String tempVal = (String)map.get("temp");
			weatherDao.setTemp(tempVal);
		}
		
		map.addAttribute("forecast", weatherDao.get5DayForecast(code));
		
		return "parkDetail";
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.GET) 
	public String displaySurvey(ModelMap map) {
		
		if (!map.containsAttribute("survey")) {
			map.addAttribute("survey", new Survey());
		}
		return "survey";
	}
	@RequestMapping(path="/survey", method=RequestMethod.POST) 
	public String submitSurvey(@Valid @ModelAttribute("survey") Survey survey, BindingResult result) {
			if (result.hasErrors()) {
				return "survey";
			}
		surveyDao.saveResponses(survey.getCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
		return "redirect:surveyResults";
	}
	
	@RequestMapping(path="/surveyResults", method=RequestMethod.GET)
	public String displaySurveyResults(ModelMap map) {
		map.addAttribute("surveyResults", surveyDao.displayResults());
		
		return "surveyResults";
	}
}
