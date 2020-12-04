package com.DreamTrip.UI;

import com.DreamTrip.Connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	static ResultSet rs;
	static String customer;
	static String customer_id;

	String email;
	String Password;

	boolean checkCustomer() throws SQLException {
		email = email_tf.getText();
		Password = psswd.getText();
		try
		{
			rs = ConnectionClass.getCustomer();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		while (rs.next()) {
			String Remail = rs.getString("Email");
			String Rpass = rs.getString("pass_word");
			if (Remail.equals(email)) {
				if (Rpass.equals(Password)) {
					customer = rs.getString("Cust_name");
					customer_id = rs.getString("customer_id");
					return true;
				}
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Invalid");
				alert.setContentText("Invalid Password");
				alert.show();
				return false;
			}
		}
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Invalid");
		alert.setContentText("Invalid Email");
		alert.show();
		return false;
	}

	static boolean c = false;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		navigation_bt.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try {
					c = checkCustomer();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				if(!c) {
					return;
				}
				Parent root = null;
				try
				{
					root = FXMLLoader.load(getClass().getResource("Nav.fxml"));
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				try
				{
					ConnectionClass.getFlights();
				}
				catch (ClassNotFoundException | SQLException e)
				{
					e.printStackTrace();
				}
				Scene scene = new Scene(root,1300,600);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setMinWidth(1300);
				stage.setMinHeight(700);
				stage.setTitle("DreamTrip Navigator");
				stage.show();
				Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
				stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
				stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
			}
		});
	}
}
