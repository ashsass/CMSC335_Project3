package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.util.*;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

public class Controller implements Initializable{
	
	@FXML
	private GridPane gridPane;
	@FXML 
	private Label timeDisplay;
	Car car = new Car();
	TranslateTransition translate = new TranslateTransition();
//	private Line road;
	
	public void addCar() {
		gridPane.add(car.getCar(), 0, 2);
	}
	//need to have it so that if there is a car in this location, need to wait for it to move out of place 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		translate.setNode(car.getCar());
		translate.setDuration(Duration.millis(5000));
		translate.setByX(910);
		updateTime();
//		timeDisplay.setText(printTime());
//		translate.play();
	 }
	
	public void start() {
		translate.play();
	}
	
	// Update the time display
    public void updateTime() {
        timeDisplay.setText(printTime());
    }
	
	public static String printTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm:ss a",
				Locale.getDefault());
        LocalDateTime ldt = LocalDateTime.now();
        return dtf.format(ldt);
	}
}
	
	
