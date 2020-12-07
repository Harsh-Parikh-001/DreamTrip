package com.DreamTrip.UI;

import com.DreamTrip.Connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CheckOut implements Initializable {
	@FXML
	Label label_lb;
	@FXML
	Label back_btn;
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		back_btn.setPadding(new Insets(0,0,0,20));
		back_btn.setOnMouseClicked(event -> {
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
			Scene scene = new Scene(root,1300,700);
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("DreamTrip Navigator");
			stage.show();
		});
		label_lb.setText("Mr/Mrs. "+LoginPage.customer+", the Order has been added to Your Orders.");
	}
}
