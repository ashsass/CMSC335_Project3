/* Name: Ashlyn Sassaman
 * Project 3 for CMSC335
 * Due May 2024
 * Description: The main file of the project that will call the GUI and logic together to form a functional traffic control GUI. 
 */

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;


public class TrafficMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			TrafficGUI root = new TrafficGUI();
			Scene scene = new Scene(root,600,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Traffic Control");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
