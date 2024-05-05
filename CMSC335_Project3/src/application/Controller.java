package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.util.*;

import java.util.*;

import javafx.animation.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;

public class Controller implements Initializable{
	
	@FXML
	private Pane pane;
	@FXML 
	private Label timeDisplay;
	@FXML
	private Line road;
	
	ArrayList<Car> carList = new ArrayList<>();
//	Car car = new Car();
	ArrayList<Light> lightList = new ArrayList<>();
	// might change this to map so i can access lights? probably need to be able to identify the lights when the cars are next to them
	
	TranslateTransition translate = new TranslateTransition();
	
	final Double LIGHT_DIST = 100.0;
	final Double FIRST_LIGHT_X = 176.0;

	//need to have it so that if there is a car in this location, need to wait for it to move out of place 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Start the GUI with one light and one car
		addLight();
		addCar();
		
		// Set the animation to the car that is added 
		// WILL NEED TO HAVE THIS REPEAT SO THAT IT CAN BE DONE MULTIPLE TIMES?
//		translate.setNode(car.getCar());
//		translate.setDuration(Duration.millis(5000));
//		translate.setByX(910);
		
		// Call updateTime to set the keyframe timeline animation to display time
		updateTime();
	 }
	

	// Allow user to add a new car to the GUI
	public void addCar() {
		Car car = new Car();
		translate.setNode(car.getCar());
		translate.setDuration(Duration.millis(5000));
		translate.setByX(910);
		pane.getChildren().add(car.getCar());
	}
	
	// Allow user to add a new light to the GUI
	public void addLight() {
		Light light = new Light();
		if(lightList.size() == 0) { // Check if this is the first light being added
			light.setPlacement(FIRST_LIGHT_X);
		}
		else { // If not find the x coord of the previous light and add 100 to space new light ahead
			double newX = lightList.get(lightList.size() - 1).getLight().getLayoutX();
			light.setPlacement(newX + LIGHT_DIST);
		}
		lightList.add(light); // Add to the array list
		pane.getChildren().add(light.getLight()); // add to the pane
	}
	
	// Start the animation using the Start button
	public void start() {
		translate.play();
	}
	
	public void stop() {
		translate.stop();
	}
	
	// Update the time display
    public void updateTime() {
        timeDisplay.setText(printTime());
    }
	
	public static String printTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm:ss a",
				Locale.getDefault());
        LocalDateTime ldt = LocalDateTime.now();
        return dtf.format(ldt);
	}
}
	
	
