package com.DreamTrip.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegisterPage implements Initializable
{
	@FXML
	Button navigation_bt;
	@FXML
	TextField email_tf;
	@FXML
	TextField contact_tf;
	@FXML
	TextField psswd_tf;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		email_tf.setOnMouseExited(event -> {
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
					"[a-zA-Z0-9_+&*-]+)*@" +
					"(?:[a-zA-Z0-9-]+\\.)+[a-z" +
					"A-Z]{2,7}$";
			Pattern pat = Pattern.compile(emailRegex);
			if(email_tf.getText() == null || !pat.matcher(email_tf.getText()).matches())
			{
				email_tf.setStyle("-fx-text-fill: red");
				navigation_bt.setDisable(true);
			}
			if(pat.matcher(email_tf.getText()).matches())
			{
				email_tf.setStyle("-fx-text-fill: black");
				navigation_bt.setDisable(false);
			}
		});
		contact_tf.setOnMouseExited(event -> {
			if(contact_tf.getText().length() > 10 || !isNumeric(contact_tf.getText()))
			{
				contact_tf.setStyle("-fx-text-fill: red");
				navigation_bt.setDisable(true);
			}
			else{
				contact_tf.setStyle("-fx-text-fill: black");
				navigation_bt.setDisable(false);
			}
		});
		psswd_tf.setOnMouseExited(event -> {
			if(psswd_tf.getText().length() < 8)
			{
				psswd_tf.setStyle("-fx-text-fill: red");
				navigation_bt.setDisable(true);
			}
			else{
				psswd_tf.setStyle("-fx-text-fill: black");
				navigation_bt.setDisable(false);
			}
		});
		navigation_bt.setOnMouseClicked(event -> {
				String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
						"[a-zA-Z0-9_+&*-]+)*@" +
						"(?:[a-zA-Z0-9-]+\\.)+[a-z" +
						"A-Z]{2,7}$";
				Pattern pat = Pattern.compile(emailRegex);
				if(email_tf.getText() == null || !pat.matcher(email_tf.getText()).matches())
				{
					email_tf.setStyle("-fx-text-fill: red");
					navigation_bt.setDisable(true);
					return;
				}
				if(pat.matcher(email_tf.getText()).matches())
				{
					email_tf.setStyle("-fx-text-fill: black");
					navigation_bt.setDisable(false);
				}
				if(contact_tf.getText().length() > 10 || !isNumeric(contact_tf.getText()))
				{
					contact_tf.setStyle("-fx-text-fill: red");
					navigation_bt.setDisable(true);
					return;
				}
				else{
					contact_tf.setStyle("-fx-text-fill: black");
					navigation_bt.setDisable(false);
				}
				if(psswd_tf.getText().length() < 8)
				{
					psswd_tf.setStyle("-fx-text-fill: red");
					navigation_bt.setDisable(true);
					return;
				}
				else{
					psswd_tf.setStyle("-fx-text-fill: black");
					navigation_bt.setDisable(false);
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
				Scene scene = new Scene(root,1300,700);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setTitle("DreamTrip");
				stage.show();
		});
	}
	public static boolean isNumeric(final String str)
	{
		if (str == null || str.length() == 0)
		{
			return false;
		}
		for (char c : str.toCharArray())
		{
			if (!Character.isDigit(c))
			{
				return false;
			}
		}
		return true;
	}
}
