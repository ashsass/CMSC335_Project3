package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public class Light {
	private ImageView light;
	private final Double Y_COORD = 119.0;
	private String[] lightImages = {"/resources/red-light.png", "/resources/yellow-light.png", "/resources/green-light.png"};
	/* lightImages index:
	 	* [0] = red light
	 	* [1] = yellow light
	 	* [2] = green light
	 */
	public int imageIndex = initializeLight();
	
	// Light's ImageView set using the initializeLight to randomly pull a light image from the array
	public Light() {
		light = new ImageView(
				new Image(
						getClass()
						.getResourceAsStream(lightImages[imageIndex])));
		setSize();
	}
	
	public ImageView getLight() {
		return light;
	}
	
	public void setPlacement(Double x) {
		light.setLayoutX(x);
		light.setLayoutY(Y_COORD);
	}
	
	private final void setSize() {
		light.setFitWidth(24);
		light.setFitHeight(31);
	}
	
	// Use this int to pull a random image from the light array
	// Instead of calling the method in the constructor I'm going to assign it to a variable
	// then I can use the global variable to move through the other images 
	public int initializeLight() {
		return new Random().nextInt(3); // 0-2 inclusive, 3 exclusive
	}
	
	public void setNewImage() {
		imageIndex = (imageIndex + 1) % lightImages.length; 
	    light.setImage(new Image(getClass().getResourceAsStream(lightImages[imageIndex])));
	}
	
	public boolean isRed() {
		return imageIndex == 0;
	}
}
