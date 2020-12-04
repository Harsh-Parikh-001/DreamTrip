package com.DreamTrip.UI;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.text.Element;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Nav implements Initializable {
	@FXML
	ScrollPane scrollPane;

	@FXML
	HBox hbox;

	@FXML
	Label flight_id;

	@FXML
	Label train_id;

	@FXML
	Label hotel_id;
	@FXML
	Label past_orders_lbl;


	Color color1 = Color.web("#A9A9A9");
	Color color = Color.web("#D3D3D3");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VBox vBox = new VBox();
		try {
			new OrderedHotels();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		past_orders_lbl.setOnMouseClicked(event ->
		{
			past_orders_lbl.setText("Past Orders >");
			scrollPane.setContent(Sprites.createLog());
			train_id.setText("Trains");
			hotel_id.setText("Hotels");
			flight_id.setText("Flights");
		});

		flight_id.setOnMouseClicked(event -> {
			flight_id.setText("Flights >");
			scrollPane.setContent(Sprites.createFlights());
			train_id.setText("Trains");
			hotel_id.setText("Hotels");
			past_orders_lbl.setText("Past Orders");
		});
		train_id.setOnMouseClicked(event -> {
			train_id.setText("Trains >");
			scrollPane.setContent(Sprites.createTrains());
			flight_id.setText("Flights");
			hotel_id.setText("Hotels");
			past_orders_lbl.setText("Past Orders");
		});
		hotel_id.setOnMouseClicked(event -> {
			hotel_id.setText("Hotels >");
			scrollPane.setContent(Sprites.createHotels());
			flight_id.setText("Flights");
			train_id.setText("Trains");
			past_orders_lbl.setText("Past Orders");
		});
		scrollPane.setContent(Sprites.createHotels());
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.addEventFilter(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				if (event.getDeltaX() != 0) {
					event.consume();
				}
			}
		});
	}
}