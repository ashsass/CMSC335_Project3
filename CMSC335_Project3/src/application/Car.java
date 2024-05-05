package application;

import javafx.scene.image.*;

public class Car {
	private ImageView car;
	static int id = 0;
	
	public Car() {
		car = new ImageView(new Image(getClass().getResourceAsStream("/resources/car.png")));
		id++;
		setSize();
		setPlacement();
	}
	
	public ImageView getCar() {
		return car;
	}
	
	private final void setPlacement() {
		car.setLayoutX(0);
		car.setLayoutY(180);
	}
	
	private final void setSize() {
		car.setFitWidth(20);
		car.setFitHeight(29);
	}
}
