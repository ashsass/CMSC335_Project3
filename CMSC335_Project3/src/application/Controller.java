/* Name: Ashlyn Sassaman
 * Project 3 for CMSC335
 * Due May 2024
 * Description: The Controller file handles the logic and event handlers for the GUI. 
 */


package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.util.*;
import java.util.*;
import java.util.concurrent.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;

public class Controller implements Initializable{
	/* FXML Variables */
	@FXML
	private Pane pane;
	@FXML 
	private Label timeDisplay, car1, car2, car3;
	@FXML
	private VBox vBox;
	
	/* Instance variables */
	private String timeText; //This string will be updated using the executor
	public ScheduledExecutorService executorTime = Executors.newSingleThreadScheduledExecutor();
	public ScheduledExecutorService executorLight = Executors.newScheduledThreadPool(5);
	public ScheduledExecutorService executorCar = Executors.newScheduledThreadPool(5);
	
	private ArrayList<Car> carList = new ArrayList<>();
	private ArrayList<Light> lightList = new ArrayList<>();
	private ArrayList<TranslateTransition> translateList = new ArrayList<>();
	public ArrayList<Label> carLocationList = new ArrayList<>();
	
	private final Double LIGHT_DIST = 100.0;
	private final Double FIRST_LIGHT_X = 176.0;
	private int vboxChildIndex = 2;

	// Start the initial GUI frame in the main JavaFX thread
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Start the GUI with one light
		addLight();
		
		// Executor updates the timeText String in a background thread
		// Platform updates the UI appropriately in JavaFX every second
		executorTime.scheduleAtFixedRate(() -> {
			timeText = printTime();
			Platform.runLater(() -> {
				startTimeline();
			});
		}, 0, 1, TimeUnit.SECONDS);
		
		// Executor changes the ImageView of each Light ever 3 seconds
		executorLight.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				for(Light light: lightList) {
					light.setNewImage();
				}
			});
		}, 0, 3, TimeUnit.SECONDS);
		
		// Executor starts the GUI with one car
		executorCar.schedule(() -> {
			Platform.runLater(() -> {
				addCar();
			});
		}, 0, TimeUnit.SECONDS);
		
		// Executor checks the position of all the cars to stop them if they are at a red light
		executorCar.scheduleAtFixedRate(() -> {
			for(Car car: carList) 
				updateCarPosition(car);
			Platform.runLater(() -> {
				if(carLocationList.size() > 0) {
					for(Label label: carLocationList) {
						// Uses the index of label to grab the same car in the carList
						label.setText("Car #" + (carLocationList.indexOf(label) + 1) + " " + displayCarPosition(carList.get(carLocationList.indexOf(label))));
					}
				}
			});
		}, 0, 100, TimeUnit.MILLISECONDS);
	}
	
	
	public void updateCarPosition(Car car) {
		for(Light light: lightList) {
			double lightPos = light.getLight().getLayoutX();
			double carPos = car.translate.getNode().getTranslateX();
			// If the car is right before a light then the animation pauses
			if((carPos > (lightPos - 20) && carPos < lightPos) && light.isRed()) 
				car.translate.pause();
			else if((carPos > (lightPos - 20) && carPos < lightPos) && !light.isRed()) 
				car.translate.play();			
		}	
	}
	
	// Allow user to add a new car to the GUI
	public void addCar() {
		Car car = new Car();
		translateList.add(car.translate);
		
		for(Car c: carList) {
			if(c.translate.getNode().getTranslateX() == 0.0) 
				// If there is a car in the starting position, place the next car behind it
				car.setXPlacement(carList.get(carList.size() - 1).getCar().getLayoutX() 
						- (car.getCar().getFitWidth() + 3));
			if(carList.size() == 0)
				// If it is the first car, place it at the start
				car.setXPlacement(0.0);
		}
		carList.add(car);
		addCarLabel(car);
		pane.getChildren().add(car.getCar());
	}
	
	// Allow user to add a new light to the GUI
	public void addLight() {
		Light light = new Light();
		// Check if this is the first light being added
		if(lightList.size() == 0)
			light.setPlacement(FIRST_LIGHT_X);
		else { 
			// If not first light then find the x coord of the previous light and 
			// add 100 to space new light ahead
			double newX = lightList.get(lightList.size() - 1).getLight().getLayoutX();
			light.setPlacement(newX + LIGHT_DIST);
		}
		// Add to the array list
		lightList.add(light); 
		// Add to the pane
		pane.getChildren().add(light.getLight());
	}
	
	// Start the animation using the Start button
	public void start() {
		for(TranslateTransition e: translateList)
			e.play();
	}
	
	// Pause the animation using the Pause button
	public void pause() {
		for(TranslateTransition e: translateList)
			e.pause();
	}
	
	// Continue the animation after using the Pause button
	public void cont() {
		start();
	}
	
	// Stop the animation using the Stop button. 
	public void stop() {
		for(TranslateTransition e: translateList)
			e.stop();
	}
	
	public void startTimeline() {
        Timeline timeline = new Timeline();
        KeyFrame keyframe = new KeyFrame(Duration.seconds(1), event -> {
            timeDisplay.setText(timeText);
        });
        timeline.getKeyFrames().add(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
	
	// Update the timeText string to then display the time on a label 
	public static String printTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm:ss a",
				Locale.getDefault());
      LocalDateTime ldt = LocalDateTime.now();
      return dtf.format(ldt);
	}
	
	// Format the X coordinate of a car as a string
	public static String getXLocation(Car car) {
		return String.format("%.2f", car.translate.getNode().getTranslateX());
	}
	
	// Properly display the car number and it's x coordinate
	public String displayCarPosition(Car car) {
//		return String.format("Car #%s: %s", car.getID(), getXLocation(car));
		return String.format(" %s", getXLocation(car));
	}
	
	// Create a new label for each car added
	public void addCarLabel(Car car) {
		Label label = new Label();
		carLocationList.add(label);
		vBox.getChildren().add(vboxChildIndex, label);
		vboxChildIndex++;
	}
}
	
	
