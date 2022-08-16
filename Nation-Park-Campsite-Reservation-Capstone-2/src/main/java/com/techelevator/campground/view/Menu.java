package com.techelevator.campground.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

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

public class Menu {

	private PrintWriter out;
	private Scanner in;
	private ParkDAO parkDAO;
	private SiteDAO siteDAO;
	private CampgroundDAO campgroundDAO;
	private ReservationDAO reservationDAO;
	private boolean isParkMenu; 
	private boolean isSearchForCampground;
	private Park selectedPark;
	private Campground selectedCampground;
	private LocalDate startLocalDate;
	private LocalDate endLocalDate;
	private int campgroundChoice;
	private int userChoice;
	private String userName;
	private boolean isZero;
	private long days;
	private List<Site> sites;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}
	
	public void CampgroundCLI() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		parkDAO = new JDBCParkDAO(dataSource);
		siteDAO = new JDBCSiteDAO(dataSource);
		campgroundDAO = new JDBCCampgroundDAO(dataSource);
		reservationDAO = new JDBCReservationDAO(dataSource);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options);
			if (isParkMenu) {
				System.out.println("Q) Quit");
				isParkMenu = false;
				System.out.print("\nPlease choose an option >>> ");
			} else if (isSearchForCampground) {
				System.out.print("Which campground (enter 0 to cancel)? ");
			} else {
			System.out.print("\nPlease choose an option >>> ");
			} 
			choice = getChoiceFromUserInput(options);
			if (isZero) {
				break;
			}
		}
		return choice;
	}
	public void getUserDatesAndCampground() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		System.out.println();
		System.out.print("Which campground (enter 0 to cancel)? ");
		campgroundChoice = in.nextInt();
		in.nextLine();
		if (campgroundChoice == 0) {
			isZero = true;
		}
		else {
			boolean isValidDate = false;
			boolean breakLoop = true;
			while(breakLoop) {
				outerloop:
					while(isValidDate == false) {
						System.out.print("What is the arrival date? __/__/____ ");
						String startDate = in.nextLine();
						if (startDate.equals("")) {
							System.out.println("\n*** "+startDate+" is not a valid option ***\n");
							System.out.println();
							isValidDate = false;
							break outerloop;
						} try {
						startLocalDate = LocalDate.parse(startDate, formatter);
						} catch( DateTimeParseException e) {
							System.out.println("\n*** "+startDate+" is not in the MM/dd/yyyy date format ***\n");
							System.out.println();
							break outerloop;
						}
						
						while(true) {
							System.out.print("What is the departure date? __/__/____ ");
							String endDate = in.nextLine();
							if (endDate.equals("")) {
								System.out.println("\n*** "+endDate+" is not a valid option ***\n");
								System.out.println();
								isValidDate = false;
							} else {
								try {
									endLocalDate = LocalDate.parse(endDate, formatter);
									days = ChronoUnit.DAYS.between(startLocalDate,endLocalDate);
									if (startLocalDate.isAfter(endLocalDate)) {
										System.out.println();
										System.out.println("Arrival date is after Departure date. re-enter");
										System.out.println();
										isValidDate = false;
									} else {
									isValidDate = true;
									breakLoop = false;
									break;
									}
								} catch( DateTimeParseException e) {
									System.out.println("\n*** "+endDate+" is not in the MM/dd/yyyy date format ***\n");
									System.out.println();
									
								}
							
						}
					}
				}
			}
		}
	}
	
	public void getUserSiteSelection() {
		while (true) {
		System.out.println();
		System.out.print("Which site should be reserved (enter 0 to cancel)? ");	
		userChoice = in.nextInt();
		in.nextLine(); 
		if (userChoice == 0) {
			isZero = true;
		} 
		boolean isPresent = false;
		for (Site site : sites) {
			if (site.getSiteId() == userChoice) {	
				isPresent = true;
			}
		}
		if (!isPresent) {
			System.out.println("\n*** " + userChoice + " is not a valid option ***\n");
		} else {
			break;
		}
		}
		
		while (true) {
		System.out.print("What name should the reservation be made under? ");
		userName = in.nextLine();
		
		if (userName.equals("")) {
			System.out.println("Please enter a valid name.");
		} else {
			break;
		}
		}
	
	}
	public void makeReservation() {
		long confirmationId = reservationDAO.createReservation(userChoice, userName, startLocalDate, endLocalDate);
		System.out.println("The reservation has been made and the confirmation id is " + confirmationId);
	}
	
	public void getUserDates() {
		boolean isValidDate = false;
		boolean breakloop = true;
		while (breakloop) {
			outerloop:
				while(isValidDate == false) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					System.out.print("What is the arrival date? __/__/____ ");
					String startDate = in.nextLine();
					if (startDate.equals("")) {
						System.out.println("\n*** "+startDate+" is not a valid option ***\n");
						System.out.println();
						isValidDate = false;
						break outerloop;
					} try {
					startLocalDate = LocalDate.parse(startDate, formatter);
					} catch( DateTimeParseException e) {
						System.out.println("\n*** "+startDate+" is not in the MM/dd/yyyy date format ***\n");
						System.out.println();
						break outerloop;
					}
					
					while (true) {
					System.out.print("What is the departure date? __/__/____ ");
					String endDate = in.nextLine();
					if (endDate.equals("")) {
						System.out.println("\n*** "+endDate+" is not a valid option ***\n");
						System.out.println();
						isValidDate = false;
					} else {
						try {
							endLocalDate = LocalDate.parse(endDate, formatter);
							if (startLocalDate.isAfter(endLocalDate)) {
								System.out.println();
								System.out.println("Arrival date is after Departure date. re-enter");
								System.out.println();
								isValidDate = false;
							} else {
								isValidDate = true;
								breakloop = false;
								break;
							}
						} catch( DateTimeParseException e) {
								System.out.println("\n*** "+endDate+" is not in the MM/dd/yyyy date format  ***\n");
								System.out.println();
							
						}
					
					}
					}
				}
		}
	}
	public void displayAvailableCampSites() {
		List<Campground> campground = campgroundDAO.getAllCampgrounds(selectedPark.getParkId());
		selectedCampground = campground.get(campgroundChoice-1);	
		
		sites = siteDAO.getAllAvalableSites(selectedCampground.getCampgroundId(), startLocalDate, endLocalDate);
		System.out.printf("%-9s %-10s %-10s %-15s %-10s %-10s \n", "Site No.", "Max Occup.", "Accessible?", "Max RV Length", "Utility", "Cost");
		for (Site site : sites) {
			System.out.printf("%d %-6s %-10d %-10b %-15d %-10b $%4.2f \n", site.getSiteId(), "", site.getMaxOccupancy(), site.isAccessible(), site.getMaxRVLength(), site.hasUtilities(), selectedCampground.getDailyFee() * days);
		}
		
	}
	
	public void allParks() {
		isZero = false;
		isParkMenu = true;
		System.out.println("Select a Park for Further Details");
		List<Park> parks = parkDAO.getAllAvailableParks();
		selectedPark = (Park)getChoiceFromOptions(parks.toArray());
		displayParkInfo(selectedPark);
		
	}
	public void displayAllCampgrounds() {
		System.out.println();
		System.out.printf("%5s    %-30s %-10s %-10s %-10s \n", "", "Name", "Open", "Close", "Daily Fee");
		List<Campground> campgrounds = campgroundDAO.getAllCampgrounds(selectedPark.getParkId());
		
		int counter = 0;
		for (Campground campground : campgrounds) {
	
			String monthTo = stringNumberToMonth(campground.getOpenTo());
			String monthFrom = stringNumberToMonth(campground.getOpenFrom());
			
			counter++;
			System.out.printf("#%d %-5s %-30s %-10s %-10s $%4.2f \n", counter, "", campground.getName(), monthFrom, monthTo, campground.getDailyFee());
		}
		System.out.println();
	}
	
	public void searchForAvalablity() {
		System.out.println("Select a Campground to Search Avalablity");
		isSearchForCampground = true;
		System.out.println("isZero: " + isZero);
		
		List<Campground> campground = campgroundDAO.getAllCampgrounds(selectedPark.getParkId());
		selectedCampground = (Campground)getChoiceFromOptions(campground.toArray());
		if (isZero) {
			return;
		}
		
		getUserDates();
		sites = siteDAO.getAllAvalableSites(selectedCampground.getCampgroundId(), startLocalDate, endLocalDate);
		System.out.printf("%-9s %-10s %-10s %-15s %-10s %-10s \n", "Site No.", "Max Occup.", "Accessible?", "Max RV Length", "Utility", "Cost");
		
		for (Site site : sites) {
			System.out.printf("%d %-6s %-10d %-10b %-15d %-10b $%4.2f \n", site.getSiteId(), "", site.getMaxOccupancy(), site.isAccessible(), site.getMaxRVLength(), site.hasUtilities(), selectedCampground.getDailyFee());
		}
		
	}
	
	// The Single Getter 
	public boolean getIsZero() {
		return isZero;
	}
	
	
	
	//Private Methods:
	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			if (userInput.equals("Q") || userInput.equals("q")) {
				System.exit(0);
			}
			if (userInput.equals("0")) {
				isZero = true;
			}
			 else {
			int selectedOption = Integer.valueOf(userInput);
			
			if(selectedOption <= options.length && !isZero) {
				choice = options[selectedOption - 1];
			}
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null && !isZero) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}
	
	private String stringNumberToMonth(String month) {
		int result = Integer.parseInt(month);
		month = Month.of(result).name();
		month = month.substring(0, 1) + month.substring(1).toLowerCase();
		return month;
	}
	
	private void displayParkInfo(Park park) {
		System.out.println();
		System.out.println(park.getName());
		System.out.printf("Location: %-5s\n", park.getLocation());
		System.out.printf("Established: %-5s\n", park.getEstablishDate());
		System.out.printf("Area %d sq km\n", park.getArea());
		System.out.printf("Annual Visitors: %d\n", park.getVisitors());
	}
	
	private void displayMenuOptions(Object[] options) {
		out.println();
		for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options[i]);
		}
		out.flush();
	}

}
