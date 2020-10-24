package com.DreamTrip.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable
{
	@FXML
	private Label progress;
	public static Label label;
	@FXML
	private ProgressBar progressBar;
	public static ProgressBar statProgressBar;
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		label = progress ;
		statProgressBar = progressBar;
	}
}
