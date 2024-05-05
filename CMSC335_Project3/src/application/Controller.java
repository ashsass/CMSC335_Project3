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
	
//	ArrayList<Car> carList = new ArrayList<>();
	Car car = new Car();
	TranslateTransition translate = new TranslateTransition();
	
	ArrayList<Light> lightList = new ArrayList<>();
	// might change this to map so i can access lights? probably need to be able to identify the lights when the cars are next to them
	
	final Double LIGHT_DIST = 100.0;
	final Double FIRST_LIGHT_X = 176.0;

	
	public void addCar() {
		pane.getChildren().add(car.getCar());
	}
	
	public void addLight() {
		//176 x 139 y
		Light light = new Light();
		if(lightList.size() == 0) { // Check if this is the first light being added
			light.setPlacement(FIRST_LIGHT_X);
		}
		else {
			double newX = lightList.get(lightList.size() - 1).getLight().getLayoutX();
			light.setPlacement(newX + LIGHT_DIST);
		}
		lightList.add(light); // Add to the array list
		pane.getChildren().add(light.getLight()); // add to the pane
	}
	//need to have it so that if there is a car in this location, need to wait for it to move out of place 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addLight();
		translate.setNode(car.getCar());
		translate.setDuration(Duration.millis(5000));
		translate.setByX(910);
		
		// Call updateTime to set the keyframe timeline animation to display time
		updateTime();
	 }
	
	public void start() {
		translate.play();
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
	
	
