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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {
	@FXML
	Button navigation_bt;

	@FXML
	TextField email_tf;

	@FXML
	PasswordField psswd;

	@FXML
	Checkbox check1_cb;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		navigation_bt.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				Parent root = null;
				try
				{
					root = FXMLLoader.load(getClass().getResource("Nav.fxml"));
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				Scene scene = new Scene(root,1300,700);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setTitle("Hotels");
				stage.show();
			}
		});
	}
}
