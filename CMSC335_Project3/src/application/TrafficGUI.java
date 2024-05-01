/* Name: Ashlyn Sassaman
 * Project 3 for CMSC335
 * Due May 2024
 * Description: The main GUI layout for the traffic display GUI. This will create the basic layout of the GUI and provide user interface capabilities.
 */

package application;

/* Thoughts
 * Work through what all should be done in this file - want to keep as much of the display here and not do any logic
 * For now the constructor will call the building of the different layouts?
 */
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class TrafficGUI extends BorderPane {
	// Different portions need to make:
		// left border for display pane
		// bottom border for play, pause, etc buttons
		// center border to hold the main ui
	
//	TrafficGUI trafficGui;
	
	public TrafficGUI() {
//		TrafficGUI trafficGui = new TrafficGUI();
//		getChildren().add(new Button("hello"));
		this.setBorders();
	}
	
	private final void setBorders() {
		this.setLeft(createDisplayPanel());
	}

	public VBox createDisplayPanel() {
//		String panelCSS = "-fx-border-color: black;\n" +
//                "-fx-border-insets: 15;\n" +
//                "-fx-border-width: 2;\n" +
//                "-fx-border-style: solid;\n" +
//                "-fx-padding: 15";
		
		VBox displayPanel = new VBox();
//		displayPanel.setStyle(panelCSS);
		displayPanel.getStyleClass();
		Label titleLbl = new Label("Traffic Display");
		// need time display
		
		// display speed of cars
		Label car1Lbl = new Label("Car 1");
		Label car2Lbl = new Label("Car 2");
		Label car3Lbl = new Label("Car 3");
		
		//Buttons for adding car and lights
		HBox vbox = new HBox();
		Button addCarBtn = new Button("Add car");
		Button addLightBtn = new Button("Add light");
		vbox.getChildren().addAll(addCarBtn, addLightBtn);

		displayPanel.getChildren().addAll(titleLbl, car1Lbl, car2Lbl, car3Lbl, vbox);
		return displayPanel;
	}
	

}
