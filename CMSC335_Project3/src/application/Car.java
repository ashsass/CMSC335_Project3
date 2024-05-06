package application;

import javafx.scene.image.*;

public class Car {
	private ImageView car;
	static int id = 0;
	
	public Car() {
		car = new ImageView(new Image(getClass().getResourceAsStream("/resources/car.png")));
		id++; // Increment id so it coordinates with the number of car objects in the program
		setYPlacement();
		setSize();
//		System.out.println("car constructor called, car id is " + id);
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
	} // may want to make this private to keep it immutable?
	
	private final void setSize() {
		car.setFitWidth(20);
		car.setFitHeight(29);
	}
}
