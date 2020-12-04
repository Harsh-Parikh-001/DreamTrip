package com.DreamTrip.UI;

import com.DreamTrip.Connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegisterPage implements Initializable
{
	@FXML
	Button navigation_bt;
	@FXML
	TextField email_tf;
	@FXML
	TextField name_tf;
	@FXML
	TextArea address_ta;
	@FXML
	TextField contact_tf;
	@FXML
	TextField psswd_tf;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		email_tf.setOnMouseExited(event ->
		{
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
		psswd_tf.setOnMouseExited(event ->
		{
			if(psswd_tf.getText().length() < 8)
			{
				psswd_tf.setStyle("-fx-text-fill: red");
				navigation_bt.setDisable(true);
			}
			else
			{
				psswd_tf.setStyle("-fx-text-fill: black");
				navigation_bt.setDisable(false);
			}
		});
		navigation_bt.setOnMouseClicked(event -> {
			try {
				handle(event);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
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

	private void handle(MouseEvent event) throws SQLException {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		navigation_bt.setDisable(false);
		if (email_tf.getText() == null || !pat.matcher(email_tf.getText()).matches())
		{
			email_tf.setStyle("-fx-text-fill: red");
			if (pat.matcher(email_tf.getText()).matches())
			{
				email_tf.setStyle("-fx-text-fill: black");
				if (contact_tf.getText().length() > 10 || !isNumeric(contact_tf.getText()))
				{
					contact_tf.setStyle("-fx-text-fill: red");
				}
			}
			else
				{
				contact_tf.setStyle("-fx-text-fill: black");
				navigation_bt.setDisable(false);
			}
			if (psswd_tf.getText().length() < 8) {
				psswd_tf.setStyle("-fx-text-fill: red");
				navigation_bt.setDisable(true);
				return;
			} else {
				psswd_tf.setStyle("-fx-text-fill: black");
				navigation_bt.setDisable(false);
			}
		}



		Parent root = null;
		try
		{
			String name = name_tf.getText();
			String contact = contact_tf.getText();
			String email = email_tf.getText();
			String psswd = psswd_tf.getText();
			String address = address_ta.getText();
			String key = "Hello";
			try
			{
				ConnectionClass.insertCustomer(name, contact, email,address , psswd, key);
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
			root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1300, 700);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("DreamTrip");
		stage.show();
	}
}
