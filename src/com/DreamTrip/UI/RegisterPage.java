package com.DreamTrip.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterPage implements Initializable {
	@FXML
	Button navigation_bt;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		navigation_bt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("NavHotel.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(root,1300,650);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setTitle("Hotels");
				stage.show();
			}
		});
	}
}
