package com.DreamTrip.UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckOut implements Initializable {
	@FXML
	Label label_lb;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label_lb.setText("Mr/Mrs. "+LoginPage.customer+", the hotel has been added in your orders.");
	}
}
