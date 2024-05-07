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
	
	/* Instance variables */
	private String timeText; //This string will be updated using the executor
	
	// Had to make this public to be accessed through TrafficMain - could maybe use a method and keep it private?
	public ScheduledExecutorService executorTime = Executors.newSingleThreadScheduledExecutor();
	public ScheduledExecutorService executorLight = Executors.newScheduledThreadPool(5);
	public ScheduledExecutorService executorCar = Executors.newScheduledThreadPool(5);
	
	
	private ArrayList<Car> carList = new ArrayList<>();
	private ArrayList<Light> lightList = new ArrayList<>();
	private ArrayList<TranslateTransition> translateList = new ArrayList<>();
	private final Double LIGHT_DIST = 100.0;
	private final Double FIRST_LIGHT_X = 176.0;

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
		}, 0, 100, TimeUnit.MILLISECONDS);
	}
	
	
	public void updateCarPosition(Car car) {
		for(Light light: lightList) {
			// If the car is right before a light then the animation pauses
			if(car.translate.getNode().getTranslateX() > (light.getLight().getLayoutX()) - 20 &&
					light.isRed()) 
						car.translate.pause();
			else if(car.translate.getNode().getTranslateX() > (light.getLight().getLayoutX()) - 20 &&
					!light.isRed()) 
				car.translate.play();			
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
		Car car = new Car();
		translateList.add(car.translate);
		
		for(Car c: carList) {
			if(c.translate.getNode().getTranslateX() == 0.0 || carList.size() > 0) 
				car.setXPlacement(carList.get(carList.size() - 1).getCar().getLayoutX() 
						- car.getCar().getFitWidth());
			else 
				car.setXPlacement(0.0);
		}
		
		carList.add(car);
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
}
	
	
