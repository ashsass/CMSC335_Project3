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
	
	@FXML
	private Pane pane;
	@FXML 
	private Label timeDisplay;
	@FXML
	private Line road;
	
	private String timeText;
	
	ArrayList<Car> carList = new ArrayList<>();
//	Car car = new Car();
	ArrayList<Light> lightList = new ArrayList<>();
	// might change this to map so i can access lights? probably need to be able to identify the lights when the cars are next to them
	
//	ExecutorService executor = Executors.newCachedThreadPool();
	ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	
	
	
	
	/* Time Methods */
	public void startTimeline() {
        Timeline timeline = new Timeline();
        KeyFrame keyframe = new KeyFrame(Duration.seconds(1), event -> {
            timeDisplay.setText(timeText);
        });
        timeline.getKeyFrames().add(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
	
//	Future<String> timeFuture = executor.submit(new Callable<String>() {
//		public String call() throws Exception {
//			System.out.println(String.format("starting expensive task thread %s", 
//		        Thread.currentThread().getName()));
//		    String returnedValue = printTime();
//
//		    return returnedValue;
//		  } 
//		});
	
	
//	executor.scheduleAtFixedRate(() -> {
//		timeText = printTime();
//		Platform.runLater(() -> {
//			startTimeline();
//		});
//	}, 0, 1, 1000);
	
		// Update the time display
//	public void updateTime() {
//		timeDisplay.setText(printTime());
//	}
	
//	public String updateTime() {
//		return printTime();
//	}
	
	public static String printTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm:ss a",
				Locale.getDefault());
      LocalDateTime ldt = LocalDateTime.now();
      return dtf.format(ldt);
	}
	
	
	/* Time Methods ends */
	
	
	
	
	
	ArrayList<TranslateTransition> translateList = new ArrayList<>();
//	TranslateTransition translate = new TranslateTransition();
	
	final Double LIGHT_DIST = 100.0;
	final Double FIRST_LIGHT_X = 176.0;

	//need to have it so that if there is a car in this location, need to wait for it to move out of place 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Start the GUI with one light and one car
		addLight();
		addCar();
		executor.scheduleAtFixedRate(() -> {
			timeText = printTime();
			Platform.runLater(() -> {
				startTimeline();
			});
		}, 0, 1, TimeUnit.SECONDS);
//		try {
//			timeText = timeFuture.get();
//		}
//		catch(Exception ex) {
//			ex.printStackTrace();
//		}
		
		
		// Set the animation to the car that is added 
		// WILL NEED TO HAVE THIS REPEAT SO THAT IT CAN BE DONE MULTIPLE TIMES?
//		translate.setNode(car.getCar());
//		translate.setDuration(Duration.millis(5000));
//		translate.setByX(910);
		
		// Call updateTime to set the keyframe timeline animation to display time
//		Platform.runLater(() -> {
//			startTimeline();
//		});
	 }
	
	public void asyncAddCar() {
//		System.out.println("asyncAddCar called");
//		Future<?> future = executor.submit(() -> addCar());
		Platform.runLater(() -> {
			addCar();
		});
	}

	// Allow user to add a new car to the GUI
	public void addCar() {
//		System.out.println("addCar called");
		Car car = new Car();
		
		// Set animation parameters
		carMovement(car);
		
		//Add to array list and determine place to put it
		if(carList.size() > 0) {
//			System.out.println("car list is not empty");
			car.setXPlacement(carList.get(carList.size() - 1).getCar().getLayoutX() - car.getCar().getFitWidth());
		} else
			car.setXPlacement(0.0);
//		System.out.println("Placement of car " + Car.id + " is x: " + car.getCar().getLayoutX() + " and y: " + car.getCar().getLayoutY());
		
		carList.add(car);
		pane.getChildren().add(car.getCar());
	}
	
	private void carMovement(Car car) {
//		System.out.println("carMovement called for car " + Car.id);
		TranslateTransition translate = new TranslateTransition();
		translateList.add(translate);
		translate.setNode(car.getCar());
		translate.setDuration(Duration.millis(9000));
		translate.setByX(910);
	}
	
	// Allow user to add a new light to the GUI
	public void addLight() {
		Light light = new Light();
		if(lightList.size() == 0) { // Check if this is the first light being added
			light.setPlacement(FIRST_LIGHT_X);
		}
		else { 
			// If not first light then find the x coord	 of the previous light and 
			// add 100 to space new light ahead
			double newX = lightList.get(lightList.size() - 1).getLight().getLayoutX();
			light.setPlacement(newX + LIGHT_DIST);
		}
		lightList.add(light); // Add to the array list
		pane.getChildren().add(light.getLight()); // add to the pane
	}
	
	// Start the animation using the Start button
	public void start() {
		for(TranslateTransition e: translateList)
			e.play();
		carList.clear();
	}
	
	// Pause the animation using the Pause button
	public void pause() {
		for(TranslateTransition e: translateList)
			e.pause();
	}
	
	// Stop the animation using the Stop button. Should restart the project??
	public void stop() {
		for(TranslateTransition e: translateList)
			e.stop();
	}
}
	
	
