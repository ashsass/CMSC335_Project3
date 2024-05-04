package application;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Controller {
	
	@FXML
	private GridPane gridPane;
	
	public void addCar() {
		Car car = new Car();
		gridPane.add(car.getCar(), 0, 2);
	}
}
