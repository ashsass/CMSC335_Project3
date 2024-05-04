package application;

import javafx.scene.image.*;

public class Car {
	private ImageView car;
	
	public Car() {
		car = new ImageView(new Image(getClass().getResourceAsStream("/resources/car.png")));
		car.setFitWidth(30);
		car.setFitHeight(39);
		car.setLayoutX(0);
		car.setLayoutY(175);
	}
	
	public ImageView getCar() {
		return car;
	}
}
