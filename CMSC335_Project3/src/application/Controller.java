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
	private Label timeDisplay;
	@FXML
	private Line road;
	
	/* Instance variables */
	private String timeText; //This string will be updates using the executor
	
	// Had to make this public to be accessed through TrafficMain - could maybe use a method and keep it private?
	public ScheduledExecutorService executorTime = Executors.newSingleThreadScheduledExecutor();
	public ScheduledExecutorService executorLight = Executors.newScheduledThreadPool(5);
	public ScheduledExecutorService executorCar = Executors.newScheduledThreadPool(5);
	
	
	private ArrayList<Car> carList = new ArrayList<>();
	private ArrayList<Light> lightList = new ArrayList<>();
	private ArrayList<TranslateTransition> translateList = new ArrayList<>();
	private final Double LIGHT_DIST = 100.0;
	private final Double FIRST_LIGHT_X = 176.0;
//	TranslateTransition translate = new TranslateTransition();
//	ExecutorService executor = Executors.newCachedThreadPool();

	// Start the initial GUI frame in the main JavaFX thread
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Start the GUI with one light and one car
		addLight();
		Platform.runLater(() -> {
			addCar();
		});
		
		// Executor updates the timeText String in a background thread
		// Platform updates the UI appropriately in JavaFX every second
		executorTime.scheduleAtFixedRate(() -> {
			timeText = printTime();
			Platform.runLater(() -> {
				startTimeline();
			});
		}, 0, 1, TimeUnit.SECONDS);
		
		executorLight.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				for(Light light: lightList) {
					light.setNewImage();
				}
			});
		}, 0, 3, TimeUnit.SECONDS);
		
		executorCar.scheduleAtFixedRate(() -> {
//			Platform.runLater(() -> {
//				addCar();
//				if(carList.size() > 0)
//					System.out.println(carList.get(0).translate.getNode().getTranslateX());
//					System.out.println(carList.get(0).translate.getByX());
//			});
			for(Car car: carList) {
//				System.out.println(car.translate.getNode().getTranslateX());
				updateCarPosition(car);
			}
//			
		}, 0, 100, TimeUnit.MILLISECONDS);
	}
	
	
	
	public void updateCarPosition(Car car) {
		for(Light light: lightList) {
//			System.out.println(car.translate.getNode().getTranslateX());
			if(car.translate.getNode().getTranslateX() > (light.getLight().getLayoutX()) - 20 &&
					light.isRed()) {
						car.translate.stop();
//		Platform.runLater(() -> {
//			if((car.getCar().getLayoutX() > light.getLight().getLayoutX() - 10 &&
//					car.getCar().getLayoutX() < light.getLight().getLayoutX()) &&
//					light.isRed()) {
//				System.out.println("in the updateCarPosition conditional");
				
//				car.getCar().setLayoutX(light.getLight().getLayoutX());
			}
//		});
			
		}
		
	}
	
	
	// Use Platform to appropriately populate the JavaFX UI
	// Probably need to figure something out with this so that the objects are created and held in the background? Only want 5 to be at the starting point at any time 
//	public void asyncAddCar() {
//		System.out.println("asyncAddCar called");
//		Future<?> future = executor.submit(() -> addCar());
//		Platform.runLater(() -> {
//			addCar();
//		});
//	}

	// Allow user to add a new car to the GUI
	// WANT TO ADD THIS TO CAR CLASS BUT PROBLEM IS NEED TO ACCESS OTHER  CARS MAYBE USE THE ARRAYLIST STILL TO KEEP TRACK OF OTHER CARS BUT MAKE IT STATIC SO THAT THERE IS ONE AND IT GETS UPDATED THROUGHOUT THE LIFETIME OF THE CLASS
	public void addCar() {
//		System.out.println("addCar called");
		Car car = new Car();
		translateList.add(car.translate);
		
		// Set animation parameters
//		carMovement(car);
		
		//Add to array list and determine place to put it
		if(carList.size() > 0) {
			// Finds the place of the last car put on the UI and sets the next car behind it
			car.setXPlacement(carList.get(carList.size() - 1).getCar().getLayoutX() - car.getCar().getFitWidth());
//			System.out.println(carList.get(carList.size() - 1).getCar().getLayoutX());
//			System.out.println(car.getCar().getFitWidth());
		} else {
			// If the list is empty place the new car at the beginning point
			System.out.println("no cars in the list yet");
			car.setXPlacement(0.0);
		}
//		System.out.println("Placement of car " + Car.id + " is x: " + car.getCar().getLayoutX() + " and y: " + car.getCar().getLayoutY());
		
		// Add new car to the ArrayList to keep track of the cars
		// May not end up needing to do this when I make this on it's own thread
		carList.add(car);
		
		// Add the car to the pane
		// Need to use getCar method to access the ImageView associate with the Car class
		pane.getChildren().add(car.getCar());
	}
	
	// Create the animation movement of each car
//	private void carMovement(Car car) {
////		System.out.println("carMovement called for car " + Car.id);
////		TranslateTransition translate = new TranslateTransition();
//		
//		// Add the translation to the ArrayList to keep track of them 
//		// May not end up needing to do this?
//		translateList.add(car.translate);
//		car.translate.setNode(car.getCar());
//		car.translate.setDuration(Duration.millis(9000));
//		// setByX is hard coded to go slightly beyond the parameters of the scene window
//		car.translate.setByX(910);
//	}
	
	
	
	
	
	
	
	
	// Allow user to add a new light to the GUI
	public void addLight() {
		Light light = new Light();
		// Check if this is the first light being added
		if(lightList.size() == 0) { 
			light.setPlacement(FIRST_LIGHT_X);
		}
		else { 
			// If not first light then find the x coord of the previous light and 
			// add 100 to space new light ahead
			double newX = lightList.get(lightList.size() - 1).getLight().getLayoutX();
			light.setPlacement(newX + LIGHT_DIST);
		}
		// Add to the array list
		lightList.add(light); 
//		System.out.println(light.getLight().getLayoutX());
		// Add to the pane
		pane.getChildren().add(light.getLight());
	}
	
	
	
	
	
	
	// Start the animation using the Start button
	// Should this only work so long as the program has just initiated?
	public void start() {
		for(TranslateTransition e: translateList)
			e.play();
//		carList.clear();
	}
	
	// Pause the animation using the Pause button
	public void pause() {
		for(TranslateTransition e: translateList)
			e.pause();
	}
	
	// Continue the animation after using the Pause button
	// So this needs to be used instead of play button after pause?
	public void cont() {
		System.out.println("need to add code");
	}
	
	// Stop the animation using the Stop button. 
	// Should restart the project?? and then play can be used after stop has been used
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
	
	// Update the time display
//	public void updateTime() {
//		timeDisplay.setText(printTime());
//	}
	
//	public String updateTime() {
//		return printTime();
//	}
}
	
	
