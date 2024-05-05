package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Light {
	private ImageView light;
	private final Double Y_COORD = 139.0;
	static int id = 0;
	
	public Light() {
		light = new ImageView(new Image(getClass().getResourceAsStream("/resources/green-light.png")));
		light.setFitWidth(34);
		light.setFitHeight(41);
//		light.setLayoutX(0);
//		light.setLayoutY(175);
		id++;
	}
	
	public ImageView getLight() {
		return light;
	}
	
	public void setPlacement(Double x) {
		light.setLayoutX(x);
		light.setLayoutY(Y_COORD);
	}
}
