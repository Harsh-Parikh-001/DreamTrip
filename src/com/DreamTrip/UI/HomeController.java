package com.DreamTrip.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
	@FXML
	Button login_bt;

	@FXML
	Button register_bt;

	static Stage stage;

	@Override
	public void initialize (URL location, ResourceBundle resources)
	{
		login_bt.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent event)
			{
				Parent root = null;
				try
				{
					root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				Scene scene = new Scene(root,800,700);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setMinWidth(800);
				stage.setMinHeight(700);
				stage.setScene(scene);
				stage.setTitle("Login");
				stage.show();
				Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
				stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
				stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
			}
		});

		register_bt.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				Parent root = null;
				try
				{
					root = FXMLLoader.load(getClass().getResource("RegisterPage.fxml"));
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				Scene scene = new Scene(root,800,700);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setMinWidth(800);
				stage.setMinHeight(700);
				stage.setTitle("Register");
				stage.show();
				Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
				stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
				stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
			}
		});
	}
}