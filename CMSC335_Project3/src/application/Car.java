package application;

import javafx.util.*;
import javafx.animation.*;
import javafx.scene.image.*;

public class Car {
	private ImageView car;
	public TranslateTransition translate;
	
	public Car() {
		car = new ImageView(new Image(getClass().getResourceAsStream("/resources/car.png")));
		setTranslation();
		setYPlacement();
		setSize();
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
		translate.setToX(710);
	}
}
