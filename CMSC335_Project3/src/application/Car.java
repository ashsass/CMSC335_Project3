/* 
 * Name: Ashlyn Sassaman
 * Project 3 for CMSC335
 * Due May 2024
 * Description: The Car file that holds the class to represent each Car object that is created on the GUI. This holds the ImageView to represent the car, sets its position on the GUI and its translation parameters. 
 */

package application;

import javafx.util.*;
import javafx.animation.*;
import javafx.scene.image.*;

public class Car {
	private ImageView car;
	public TranslateTransition translate;
	private static int id = 0;
	
	public Car() {
		car = new ImageView(new Image(getClass().getResourceAsStream("/resources/car.png")));
		setTranslation();
		setYPlacement();
		setSize();
		id++;
	}
	
	public ImageView getCar() {
		return car;
	}
	
	// Should always be at the same y coordinate
	private final void setYPlacement() {
		car.setLayoutY(180);
	}
	
	public void setXPlacement(Double x) {
		car.setLayoutX(x);
	}
	
	private final void setSize() {
		car.setFitWidth(20);
		car.setFitHeight(29);
	}
	
	private final void setTranslation() {
		translate = new TranslateTransition(Duration.seconds(12), car);
		translate.setCycleCount(1);
		translate.setToX(1000);
	}
	
	public String getID() {
		return "" + id;
	}
}
