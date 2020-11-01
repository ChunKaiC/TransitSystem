// These is a placeholder package and placeholder class
// Feel free to rename or remove these when you add in your own code (just make sure to add/commit/push any changes made,
//		and let your teammates know to pull the changes. Follow the workflow in the a2 instructions)

package transitapp;

public class Main {

    public static void main(String[] args) {



    }

}

/* Breakdown:

- Abstract class Location: Contains initialized variables. Defines locations on the transit map. No coordinates,
A way to link classes together. Used for stops and stations.
A user could choose a starting location -> shows all possible destinations from there.

- Stop class (extends location class) for bus.
Vars:
    atStop - bool
    onRoute (if the user is on the bus) - bool
    locationName - str

- Station class (extends location class) for subway.
Vars:
    atStation (bool)
    locationName - str, (there is no onRoute var because there is only one route)

LocationNodes class
Vars:
    next
    prev
    items - type Location
    durationTillNext - define under events.txt. Time till next Location (stop/station)
Methods - None currently

TransitRoutes - abstract class
Vars:
    nodes - a Linked list of location nodes object
    fares - int

- Card Holder class - Contains data of every card holder and can modified attributes
Vars:
    name - str
    email - str
    recent trips - Array list of trip objects (3 most recent trips)
    cards - a user can have multiple cards, meaning an array list of Card objects
    currTrip - keeps track of trip that has not passed the max duration mark
Methods:
    createCard
    addBalance
    averageCost - track average transit cost per month
    tapOn - at either stop or station, charge card
    tapOff - only for subway. Looks at how many stations were visited, under the trip object
    changeName

Trip class
Vars:
    MAX_COST - static variable for the max cost that can be accumulated on the trip, defined by admins
    MAX_DURATION - static variable, same as above but with duration
    currTripCost - accumulated cost of the trip
    currTime - start at 0, add the time till next destination

Card class
Vars:
    balance
    CARD_ISSUED - int, static, total number of cards issued to all users. Ensures all cards have unique id's.
    card_id - int, unique id
Methods:
    addBalance - adds int to balance
    deductFare - deduct fare of the trip from balance

AdminUser class - the people who run the transit system
Vars:
    totalRevenue - static, take into consideration expenses
    totalCost - static, the expenses. Compare fares
Methods:
    showDailyReport


-


* we will have the stops and stations stored in a text file *



 */