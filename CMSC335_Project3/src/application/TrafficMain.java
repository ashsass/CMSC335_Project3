/* Name: Ashlyn Sassaman
 * Project 3 for CMSC335
 * Due May 2024
 * Description: The main file of the project that will call the GUI and logic together to form a functional traffic control GUI. 
 */

package application;
	
import java.util.concurrent.TimeUnit;

//import javafx.animation.*;
//import javafx.animation.Timeline;
import javafx.application.Application;
//import javafx.scene.image.ImageView;
//import javafx.scene.image.Image;
import javafx.stage.Stage;
//import javafx.util.Duration;
//import javafx.scene.Group;
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
			primaryStage.setOnCloseRequest(e -> {
				try {
//					System.out.println("Close request sent");
					controller.executorTime.shutdown();
					controller.executorLight.shutdown();
					controller.executorCar.shutdown();
					if (!controller.executorTime.awaitTermination(5, TimeUnit.SECONDS)) {
//						System.out.println("normal shut down didn't work, in the conditional to await termination");
	                    controller.executorTime.shutdownNow();
	                }
					if (!controller.executorLight.awaitTermination(5, TimeUnit.SECONDS)) {
//						System.out.println("normal shut down didn't work, in the conditional to await termination");
	                    controller.executorLight.shutdownNow();
	                }
					if (!controller.executorCar.awaitTermination(5, TimeUnit.SECONDS)) {
//						System.out.println("normal shut down didn't work, in the conditional to await termination");
	                    controller.executorCar.shutdownNow();
	                }
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
//				finally {
//					System.out.println(controller.executorTime.isTerminated() ? "executor is terminated" : "executor did not terminate properly");
//					System.out.println(controller.executorLight.isTerminated() ? "executor is terminated" : "executor did not terminate properly");
//					System.out.println(controller.executorCar.isTerminated() ? "executor is terminated" : "executor did not terminate properly");
//				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
