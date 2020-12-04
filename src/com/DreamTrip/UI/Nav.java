package com.DreamTrip.UI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Nav implements Initializable {
	@FXML
	ScrollPane scrollPane;

	@FXML
	HBox hbox;

	@FXML
	Label flight_id;

	@FXML
	Label user_name_lbl;

	@FXML
	Label train_id;

	@FXML
	Label hotel_id;
	@FXML
	Label past_orders_lbl;

	public static int cond = 1;

	Color color1 = Color.web("#A9A9A9");
	Color color = Color.web("#D3D3D3");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		switch (cond)
		{
			case 1:
			{
				scrollPane.setContent(Sprites.createHotels());
				hotel_id.setText("Hotels >");
				flight_id.setText("Flights");
				train_id.setText("Trains");
				past_orders_lbl.setText("Past Orders");
				break;
			}
			case 2:
			{
				scrollPane.setContent(Sprites.createFlights());
				flight_id.setText("Flights >");
				train_id.setText("Trains");
				hotel_id.setText("Hotels");
				past_orders_lbl.setText("Past Orders");
				break;
			}
			case 3:
			{
				scrollPane.setContent(Sprites.createTrains());
				train_id.setText("Trains >");
				flight_id.setText("Flights");
				hotel_id.setText("Hotels");
				past_orders_lbl.setText("Past Orders");
				break;
			}
			case 4:
			{
				past_orders_lbl.setText("Past Orders >");
				scrollPane.setContent(Sprites.createLog());
				train_id.setText("Trains");
				hotel_id.setText("Hotels");
				flight_id.setText("Flights");
				break;
			}
		}

		user_name_lbl.setText(" Welcome, " + LoginPage.customer + " ! ");
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