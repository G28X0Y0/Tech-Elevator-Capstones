package com.techelevator.campground;

import java.text.SimpleDateFormat;

import com.techelevator.campground.view.Menu;


public class CampgroundCLI {
	
	private Menu menu = new Menu(System.in, System.out);
	
	private static final String VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String SEARCH_FOR_RESERVATION = "Search for Reservation";
	private static final String RETURN_TO_MAIN_MENU = "Return to Previous Screen";
	private static final String[] MENU_OPTIONS = { VIEW_CAMPGROUNDS, SEARCH_FOR_RESERVATION, RETURN_TO_MAIN_MENU };
	
	private static final String SEARCH_FOR_RESERVATION_SUB = "Search for Available Reservations";
	private static final String RETURN_TO_MAIN_MENU_SUB = "Return to Previous Screen";
	private static final String[] MENU_OPTIONS_SUB = { SEARCH_FOR_RESERVATION_SUB, RETURN_TO_MAIN_MENU_SUB };
	
	private static final String QUIT = "Q) Quit";
	private static final String[] MENU_QUIT = {QUIT};
	

	public static void main(String[] args) {
		
		CampgroundCLI application = new CampgroundCLI();
		application.run();
	}
	public void run() {
		menu.CampgroundCLI();
		while(true) { 
			menu.allParks();
			outerloop:
			while(true) { 
				String choice = (String)menu.getChoiceFromOptions(MENU_OPTIONS);
				if(choice.equals(VIEW_CAMPGROUNDS)) {
					menu.displayAllCampgrounds();
					String userChoice = (String)menu.getChoiceFromOptions(MENU_OPTIONS_SUB);
					if(userChoice.equals(SEARCH_FOR_RESERVATION_SUB)) {
						menu.getUserDatesAndCampground();
						if (menu.getIsZero()) {
							break;
						}
						menu.displayAvailableCampSites();
						menu.getUserSiteSelection();
						if (menu.getIsZero()) {
							break;
						}
						menu.makeReservation();
						break outerloop;
					} else if (userChoice.equals(RETURN_TO_MAIN_MENU_SUB)) {
						break;
					}
				} else if(choice.equals(SEARCH_FOR_RESERVATION)) {
					menu.searchForAvalablity();
					if (menu.getIsZero()) {
						break;
					}
					menu.getUserSiteSelection();
					if (menu.getIsZero()) {
						break;
					}
					menu.makeReservation();
					break outerloop;
				} else if(choice.equals(RETURN_TO_MAIN_MENU)) {
					break;
				}
			}	
		}
	}
}

