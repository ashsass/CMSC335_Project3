/* Name: Ashlyn Sassaman
 * Project 3 for CMSC335
 * Due May 2024
 * Description: The main file of the project that will call the GUI and logic together to form a functional traffic control GUI. 
 */

package application;
	
import javafx.animation.*;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.*;
//import javafx.scene.layout.*;
//import java.io.*;


public class TrafficMain extends Application {
	private Controller controller;
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			TrafficGUI root = new TrafficGUI();
//			Group root = new Group();
//			ImageView car = new ImageView(new Image(getClass().getResourceAsStream("car.png")));
//			root.getChildren().add(car);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View.fxml"));
            Parent root = loader.load();
            controller = loader.getController();
			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Traffic Control");
			primaryStage.setResizable(false);
			primaryStage.show();
			controller.startTimeline();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
