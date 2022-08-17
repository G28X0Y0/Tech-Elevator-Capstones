# Module 2 Capstone - National Park Campsite Reservation

Congratulations! You did such a great job on your previous application we want you to build our new campsite
reservation application. We are tasking you to build a command line driven application that our National Park
Service can use to book campsite reservations.

## The requirements for your application are listed below:

1. As a user of the system, I need the ability to view a list of the available parks in the system, sorted
alphabetically by name.
  
  a. A park includes an id, name, location, established date, area, annual visitor count, and
  description.

2. As a user of the system, I need the ability to select a park that my customer is visiting and see a list of
all campgrounds for that available park.
  
  a. A campground includes an id, name, open month, closing month, and a daily fee.

3. As a user of the system, I need the ability to select a campground and search for date availability so
that I can make a reservation.
  
  a. A reservation search only requires the desired campground, a start date, and an end date.
  
  b. A campsite is unavailable if any part of their preferred date range overlaps with an existing
  reservation.
  
  c. If no campsites are available, indicate to the user that there are no available sites and ask them
  if they would like to enter in an alternate date range.
  
  d. The TOP 5 available campsites should be displayed along with the cost for the total stay.
  
  e. BONUS: If a date range is entered that occurs during the park off-season, then the user should
  not see any campsites available for reservation.
  
  f. BONUS: Provide an advanced search functionality allowing users to indicate any requirements
  they have for maximum occupancy, requires wheelchair accessible site, an rv and its length if
  required, and if a utility hookup is necessary.

4. As a user of the system, once I find a campsite that is open during the time window I am looking for, I
need the ability to book a reservation at a selected campsite.
  
  a. A reservation requires a name to reserve under, a start date, and an end date.
  
  b. A confirmation id is presented to the user once the reservation has been submitted.

5. BONUS: As a user of the system, I want the ability to select a park and search for campsite availability
across the entire park so that I can make a reservation.
  
  a. Up to 5 campsites for each campground (if applicable) should be displayed if they have
  availability along with the cost of the total stay.
  
  b. The same rules apply as the campground search.

6. BONUS: As a user of the system, I would like the ability to see a list of all upcoming reservations within
the next 30 days for a selected national park.
