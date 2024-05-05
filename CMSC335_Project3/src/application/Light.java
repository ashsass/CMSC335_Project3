package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Light {
	private ImageView light;
	private final Double Y_COORD = 119.0;
	static int id = 0;
	
	public Light() {
		light = new ImageView(new Image(getClass().getResourceAsStream("/resources/green-light.png")));
		setSize();
		id++;
	}
	
	public ImageView getLight() {
		return light;
	}
	
	public void setPlacement(Double x) {
		light.setLayoutX(x);
		light.setLayoutY(Y_COORD);
	}
	
	private void setSize() {
		light.setFitWidth(24);
		light.setFitHeight(31);
	}
}
