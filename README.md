# Traffic Simulator GUI
### Object Oriented and Concurrent Programming Project

This GUI was made as a final project for my object oriented and concurrent programming project in 2024. 
The purpose is to test our skills in concurrent programming and creating multiple threads within a graphical user interface.


![2024-08-30 17-51-29](https://github.com/user-attachments/assets/b8a0f30e-b1b4-4be0-9b17-ed3aa98745e0)

Stipulations in the project:
- Cars simulate driving through multiple intersections that can pass the intersection lights when they are green and yellow, but stop on a dime if the light turns red.
- Additionally, the cars can be paused using the buttons on the bottom of the GUI. 
- The left panel gives the user the ability to add multiple cars and lights.
- The left panel should display the time and position of three cars as they are added to the GUI. 

---

### How It's Made
**Tech Used** Java, JavaFX, FXML, Eclipse IDE
The project is broken into a few classes: Car, Light, Controller, TrafficMain. The idea is to have the cars and lights operating on separate threads in order to be operated without affecting each other. This allows for the traffic lights to continue moving, even as the cars set their own trajectory and can be controlled by the buttons on the graphical interface.

#### Car Class
A simple class that stored each Car object's image, size, and placement on the GUI.
Additionally, it set and started the translation of the car object to be moved "forward" in a straigh line as if it is a car moving through multiple intersections. 

#### Light Class
Another simple class that stores each Light object's possible states: green, yellow, or red. 
Each object shifts through the different states through an executor thread running in the Controller class.
When a Light is created, a random generator allows the starting state to differ from the other Light objects.

#### TrafficMain Class
This class holds the main method for the project and executes the JavaFX stage to begin the GUI. 

#### Controller Class
The Controller class is where the logic of the project is held. It utilizes ScheduledExecutorService to create threads to control the Car objects and Light objects separately. 
Additionally, the Platform class is used to ensure that the executors can properly work within JavaFX's specific concurrency environmeny. In other words, JavaFX requires that UI changes happen on the same thread as the application. Therefore, usage of Platform's runLater method was necessary to allow JavaFX to properly update the GUI's UI. 

---

### Future Improvements
- When adding new cars the window screen size should increase, or they should be "behind" the left sidebar.
- Cars should stop behind other cars, at this point some cars will overlap.
- Create a way to remove lights.
- Have a more robust fronend styling to the GUI.

---

### Attributes

Car icons created by [fjstudio – Flaticon](https://www.flaticon.com/free-icons/car )

Traffic light icons created by [AbtoCreative – Flaticon](https://www.flaticon.com/free-icons/traffic-light) 
