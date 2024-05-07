/* Name: Ashlyn Sassaman
 * Project 3 for CMSC335
 * Due May 2024
 * Description: The main file of the project that will call the GUI and logic together to form a functional traffic control GUI. 
 */

package application;
	
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.*;


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
					controller.executorTime.shutdown();
					controller.executorLight.shutdown();
					controller.executorCar.shutdown();
					
					if (!controller.executorTime.awaitTermination(5, TimeUnit.SECONDS))
	                    controller.executorTime.shutdownNow();
					if (!controller.executorLight.awaitTermination(5, TimeUnit.SECONDS))
	                    controller.executorLight.shutdownNow();
					if (!controller.executorCar.awaitTermination(5, TimeUnit.SECONDS)) 
	                    controller.executorCar.shutdownNow();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
