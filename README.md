# UBC Fountain Map

## *stay hydrated!*

This application will pinpoint water fountains around UBC Vancouver campus.
These features will appear as a pin on a map, which users would be able to click on to gather more
specific information. They will be able to select favourite pins to add to a collection. 
Users will also be able to create new pins on the map to mark any place on campus
of their choice.

Each pin would these key information:
- a tag describing the pin
- UBC building code/general location in case of user-pins
- directions from the entrance of the building (not for all pins) 


Should the user choose to add a pin, they **do not** have to enter every information listed above.

This map is not an exhaustive list of all fountains around campus. Please add more as you discover them!

## User Stories

- as a user, I want to be able to click on a pin and view its information
- as a user, I want to be able to select a pin and add to favourites
- as a user, I want to be able to add a pin to the map with a tag,
location, and directions
- as a user, I want to be able to mark a pin as "unavailable" or "broken"
- as a user, I want to be able to make edits on a pin's information 
- as a user, I want to be able to search pins by buildings or tags
- as a user, I want to be able to view all pins in my favourites and all pins present 
in the program
- I want to be able to save my pins and favourites to file 
- I want to be able to load my pins and favourites from file

## Instructions for grader
- You can generate the first required action by clicking anywhere on the screen. Click "Mark a Water Fountain"
or "Mark a Pin" then click "Ok" on the next screen to generate a pin. If you click on the added pin again, you can see
the information you entered previously.
- You can generate the second required action by clicking on a pin, checking off the "delete pin" radiobox
in the popup screen, then clicking "ok".
- My visual components are displayed in the screen at all times. Water fountains added by the user has a
water bottle icon, and others are shown as a red pin. The pins are centered around the point the user clicked 
on. 
- You can save the state of my application by clicking on "main menu" then selecting "save"
- You can load the state of my application by clicking on "main menu" then selecting "load"
- Because of the overwritten Equals method, pins with matching information in all fields are considered
duplicates and causes an error

## Citations 

userPin icons: https://icons8.com/icon/13800/location

waterFountain icons: https://icons8.com/icon/W1eLHFFCMpBA/bottle-of-water

ubc campus map taken from: https://planning.ubc.ca/about-us/campus-maps

event logging and printing code from https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

## Phase 4: Task 2


Mon Nov 27 18:23:50 PST 2023
new water fountain created


Mon Nov 27 18:23:50 PST 2023
fountain status edited


Mon Nov 27 18:23:50 PST 2023
fountain direction edited


Mon Nov 27 18:24:04 PST 2023
found pin(s) with matching location within list of all pins

## Phase 4: task 3

![UML_Design_Diagram.jpg](..%2FUML_Design_Diagram.jpg)

One of the biggest things I would refactor is the Pins interface and the two classes extending it.
The two classes that extend Pin are functionally the same. So I would change Pin class to be a concrete class that
with implementation, and give it a point field so that MapGUI does not have to keep track of the clicked points
in a separate list. This change would lessen the amount of fields stored in Json files, and MapGUI class would become
more cohesive as it can focus more on displaying the GUI rather than keeping track of the state of the application. 

Another class I wish I could overhaul are the Map, MapGUI, and BackgroundPanel classes. MapGUI does not adhere to single
responsibility clause at the moment and its tasks can be split into multiple classes. I would split it into 
SearchManager, MenuManager, JsonManager, PinsManager(in charge of displaying appropriate pins), and MapGUI (which would
handle the task of displaying the different managers on one JFrame). This would improve readability and maintainability
of the code. Right now, MapGUI is managing so many different fields by itself that it is overwhelming to rework or debug
as it is not immediately clear where the code is going wrong. 