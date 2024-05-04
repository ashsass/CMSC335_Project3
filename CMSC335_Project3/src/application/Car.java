package application;

public class Car {
	ImageView car;
	
	public Car() {
		car = new ImageView(new Image(getClass().getResourceAsStream("/resources/car.png")));
	}
}
