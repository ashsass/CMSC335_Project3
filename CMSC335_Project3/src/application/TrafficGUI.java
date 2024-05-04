/* Name: Ashlyn Sassaman
 * Project 3 for CMSC335
 * Due May 2024
 * Description: The main GUI layout for the traffic display GUI. This will create the basic layout of the GUI and provide user interface capabilities.
 */

//package application;

/* Thoughts
 * Work through what all should be done in this file - want to keep as much of the display here and not do any logic
 * For now the constructor will call the buildig of the different layouts?
 */
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Group;
//import javafx.scene.control.*;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.*;
//import javafx.scene.shape.Line;
//
//public class TrafficGUI extends BorderPane {
	// Different portions need to make:
		// left border for display pane
		// bottom border for play, pause, etc buttons
		// center border to hold the main ui

//	Group group = new Group();
	
//	public TrafficGUI() {
//		this.setBorders();
//	}
//	
//	private final void setBorders() {
//		this.setLeft(createDisplayPanel());
//		this.setBottom(createFooter());
		// Will call add car else where down the road 
//		addCar(createCar());
//		this.setCenter(createCenter());
//	}
//
//	public VBox createDisplayPanel() {
//		String panelCSS = "-fx-border-color: black;\n" +
//                "-fx-border-insets: 15;\n" +
//                "-fx-border-width: 2;\n" +
//                "-fx-border-style: solid;\n" +
//                "-fx-padding: 15";
		
//		VBox displayPanel = new VBox();
//		displayPanel.setStyle(panelCSS);
//		displayPanel.getStyleClass().add("vbox");
//		Label titleLbl = new Label("Traffic Display");
//		titleLbl.getStyleClass().add("header-text");
//		// need time display
//		
//		// display speed of cars
//		Label car1Lbl = new Label("Car 1");
//		Label car2Lbl = new Label("Car 2");
//		Label car3Lbl = new Label("Car 3");
//		
//		//Buttons for adding car and lights
//		HBox vbox = new HBox();
//		Button addCarBtn = new Button("Add car");
//		Button addLightBtn = new Button("Add light");
//		vbox.getChildren().addAll(addCarBtn, addLightBtn);
//
//		displayPanel.getChildren().addAll(titleLbl, car1Lbl, car2Lbl, car3Lbl, vbox);
//		return displayPanel;
//	}
//	
//	public HBox createFooter() {
//		// Really like for the footer to be moved to the right so that the left border and fill that bottom space
//		HBox footer = new HBox(10);
////		footer.setPadding(new Insets(5, 40, 5, 40));
//		setMargin(footer, new Insets(5, 40, 5, 200));
//		Button[] btnArray = {new Button("Play"), new Button("Pause"), new Button("Continue"), new Button("Stop")};
//		for(Button e: btnArray) {
//			footer.getChildren().add(e);
//		}
//		return footer;
//	}
//	
//	public ImageView createCar() {
//		ImageView car = new ImageView(new Image(getClass().getResourceAsStream("/resources/car.png")));
//		car.setFitWidth(20);
//		car.setFitHeight(20);
//		return car;
//	}
//	
//	public ImageView createLight() {
//		ImageView light = new ImageView(new Image(getClass().getResourceAsStream("/resources/green-light.png")));
//		light.setFitWidth(30);
//		light.setFitHeight(30);
//		light.setX(500.0);
//		return light;
//	}
	
//	public void addCar(ImageView car) {
//		car.setTranslateY(200);
//		group.getChildren().add(car);
//	}
	
//	public Line createRoad() {
//		Line road = new Line(200.0, 200.0, Double.MAX_VALUE, Double.MAX_VALUE);
//		return road;
//	}
	
//	public VBox createCenter() {
//		VBox center = new VBox();
//		Line road = new Line(200.0, 0.0, 1000.0, 0.0);
//		center.getChildren().addAll(createLight(), createCar(), road);
//		center.setAlignment(Pos.CENTER_LEFT);
//		return center;
//	}
//
//}
