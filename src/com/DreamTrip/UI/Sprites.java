package com.DreamTrip.UI;

import com.DreamTrip.Connectivity.ConnectionClass;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Sprites {

	static Color color1 = Color.web("#A9A9A9");
	static Color color = Color.web("#D3D3D3");

	static VBox createHotels() {
		VBox vBox = new VBox();
		try {
			new OrderedHotels();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		List<HBox> rectangleList = createClickableHotels();
		for (HBox rectangle : rectangleList) {
			Rectangle border = new Rectangle(1300, 1);
			border.setFill(Color.BLACK);
			vBox.getChildren().add(border);
			vBox.getChildren().add(rectangle);
		}
		return vBox;
	}

	static List<HBox> createClickableHotels() {
		List<HBox> rectangleList = new ArrayList<>();
		try
		{
			new Hotel();
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		for (int i = 0; i < Hotel.no; i++)
		{
			HBox rectangle = new HBox();
			rectangle.setMinHeight(150);
			rectangle.setMinWidth(1000);
			rectangle.setBackground(new Background(new BackgroundFill(color,
					CornerRadii.EMPTY, Insets.EMPTY)));
			Label hotel_name = new Label();
			Label hotel_review = new Label();
			Label hotel_price = new Label();
			hotel_review.setMinHeight(75);
			hotel_review.setText("Rated  ☆ " + Hotel.hotel_rating[i] + "  By  " + Hotel.no_of_purchases[i] + "  Customers");
			hotel_price.setText("Rs " + Hotel.cost_per_night[i]);
			hotel_price.setFont(Font.font("Tahoma", 15));
			hotel_name.setMinHeight(75);
			hotel_name.setFont(Font.font("Tahoma", 20));
			hotel_name.setText(Hotel.hotel_name[i] + " (" + Hotel.hotel_location[i] + ")");
			rectangle.setOnMouseEntered(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color1,
						CornerRadii.EMPTY, Insets.EMPTY)));
				hotel_name.setUnderline(true);
			});
			rectangle.setOnMouseExited(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color,
						CornerRadii.EMPTY, Insets.EMPTY)));
				hotel_name.setUnderline(false);
			});
			InputStream stream = null;
			try
			{
				stream = new FileInputStream(Hotel.hotel_image[i]);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
				System.out.println(e);
			}
			Image image = new Image(stream);
			ImageView imageView = new ImageView();
			imageView.setFitHeight(150);
			imageView.setFitWidth(150);
			imageView.setImage(image);
			Rectangle rectV1 = new Rectangle(5, 160);
			rectV1.setFill(color);
			Rectangle rectV2 = new Rectangle(5, 160);
			rectV2.setFill(color);
			VBox vBox1 = new VBox();
			Rectangle rectH1 = new Rectangle(150, 5);
			rectH1.setFill(color);
			Rectangle rectH2 = new Rectangle(150, 5);
			rectH2.setFill(color);
			vBox1.setMinHeight(140);
			vBox1.setMinWidth(140);
			vBox1.getChildren().add(rectH1);
			vBox1.getChildren().add(imageView);
			vBox1.getChildren().add(rectH2);
			VBox vBox = new VBox();
			vBox.setMinWidth(300);
			vBox.setMinHeight(150);
			vBox.setPadding(new Insets(0, 0, 0, 20));
			vBox.getChildren().add(hotel_name);
			vBox.getChildren().add(hotel_review);
			VBox pane = new VBox();
			pane.setMinHeight(150);
			pane.setMinWidth(300);
			pane.setMaxHeight(150);
			pane.setMaxWidth(300);
			pane.setPadding(new Insets(0, 20, 0, 0));
			pane.setAlignment(Pos.CENTER_RIGHT);
			pane.getChildren().add(hotel_price);
			HBox spacer = new HBox();
			spacer.setMinHeight(150);
			spacer.setMinWidth(200);
			rectangle.getChildren().add(rectV1);
			rectangle.getChildren().add(vBox1);
			rectangle.getChildren().add(rectV2);
			rectangle.getChildren().add(vBox);
			rectangle.getChildren().add(spacer);
			rectangle.getChildren().add(pane);
			int finalI = i;
			int finalI1 = i;
			rectangle.setOnMouseClicked(event -> {
				HotelUI.hotel = Hotel.hotel_id[finalI];
				Parent root = null;
				try
				{
					root = FXMLLoader.load(Sprites.class.getResource("Hotel_UI.fxml"));
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
				stage.setTitle(Hotel.hotel_name[finalI1]);
				stage.setMinWidth(1000);
				stage.setMinHeight(700);
				stage.show();
				Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
				stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
				stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
			});
			rectangleList.add(rectangle);
		}
		return rectangleList;
	}

	static VBox createFlights(){
		VBox vBox = new VBox();
		List<HBox> rectangleList = null;
		try {
			rectangleList = createClickableFlights();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		for (HBox rectangle : rectangleList) {
			Rectangle border = new Rectangle(1300, 1);
			border.setFill(Color.BLACK);
			vBox.getChildren().add(border);
			vBox.getChildren().add(rectangle);
		}
		return vBox;
	}
	static List<HBox> createClickableFlights() throws SQLException {
		List<HBox> rectangleList = new ArrayList<>();
		new Flights();
		for (int i = 0; i < 18; i++) {
			HBox rectangle = new HBox();
			rectangle.setMinHeight(150);
			rectangle.setMinWidth(1300);
			rectangle.setBackground(new Background(new BackgroundFill(color,
					CornerRadii.EMPTY, Insets.EMPTY)));
			Label airlines_name = new Label();
			airlines_name.setMinHeight(75);
			airlines_name.setFont(Font.font("Tahoma", 20));
			airlines_name.setText(Flights.flight_number[i] + " (" +Flights.airlines_name[i]+ ")");
			rectangle.setOnMouseEntered(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color1,
						CornerRadii.EMPTY, Insets.EMPTY)));
				airlines_name.setUnderline(true);
			});
			rectangle.setOnMouseExited(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color,
						CornerRadii.EMPTY, Insets.EMPTY)));
				airlines_name.setUnderline(false);
			});
			InputStream stream = null;
			try {
				stream = new FileInputStream(Flights.flight_image[i]);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println(e);
			}
			Image image = new Image(stream);
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setFitHeight(150);
			imageView.setFitWidth(150);
			Rectangle rectV1 = new Rectangle(5, 160);
			rectV1.setFill(color);
			Rectangle rectV2 = new Rectangle(5, 160);
			rectV2.setFill(color);
			VBox vBox1 = new VBox();
			Rectangle rectH1 = new Rectangle(150, 5);
			rectH1.setFill(color);
			Rectangle rectH2 = new Rectangle(150, 5);
			rectH2.setFill(color);
			vBox1.setMinHeight(140);
			vBox1.setMinWidth(140);
			vBox1.getChildren().add(rectH1);
			vBox1.getChildren().add(imageView);
			vBox1.getChildren().add(rectH2);
			VBox vBox = new VBox();
			vBox.setMinWidth(400);
			vBox.setMinHeight(150);
			vBox.setPadding(new Insets(0, 0, 0, 10));
			vBox.getChildren().add(airlines_name);
			Label s = new Label();
			s.setText("Origin: "+Flights.flight_origin[i] + "      Destination: " + Flights.flight_destination[i]);
			s.setMinWidth(400);
			s.setPrefHeight(30);
			Label s1 = new Label();
			s1.setText("Departure: "+Flights.departure_time[i] + "      Arrival: " + Flights.arrival_time[i]);
			s1.setMinWidth(400);
			s1.setPrefHeight(30);
			Label s2 = new Label();
			s2.setText("No of Available Seats: "+ Flights.no_of_seats[i]);
			s2.setPadding(new Insets(0,0,0,15));
			s2.setMinWidth(400);
			s2.setPrefHeight(30);
			vBox.getChildren().add(s);
			vBox.getChildren().add(s1);
			vBox.getChildren().add(s2);
			Label flight_price = new Label();
			flight_price.setFont(Font.font("Tahoma", 15));
			flight_price.setText("Rs " + Flights.flight_fare[i]);
			StackPane pane = new StackPane();
			pane.setMinHeight(150);
			pane.setMinWidth(300);
			pane.setMaxHeight(150);
			pane.setMaxWidth(300);
			VBox spacer = new VBox();
			spacer.setMinHeight(150);
			spacer.setMinWidth(200);
			pane.getChildren().add(flight_price);
			pane.setAlignment(flight_price,Pos.CENTER);
			rectangle.getChildren().add(rectV1);
			rectangle.getChildren().add(vBox1);
			rectangle.getChildren().add(rectV2);
			rectangle.getChildren().add(vBox);
			rectangle.getChildren().add(spacer);
			rectangle.getChildren().add(pane);
			int finalI = i;
			int finalI1 = i;
			rectangle.setOnMouseClicked(event -> {
				FlightsUI.flight_id = Flights.flight_number[finalI];
				Parent root = null;
				try
				{
					root = FXMLLoader.load(Sprites.class.getResource("FlightsUI.fxml"));
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
				stage.setTitle(Flights.airlines_name[finalI1] + "(" + Flights.flight_number[finalI1] + ")");
				stage.setMinWidth(1000);
				stage.setMinHeight(700);
				stage.show();
				Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
				stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
				stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
			});
			rectangleList.add(rectangle);
		}
		return rectangleList;
	}

	static VBox createTrains() {
		VBox vBox = new VBox();
		List<HBox> rectangleList = null;
		try {
			rectangleList = createClickableTrains();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		for (HBox rectangle : rectangleList) {
			Rectangle border = new Rectangle(1300, 1);
			border.setFill(Color.BLACK);
			vBox.getChildren().add(border);
			vBox.getChildren().add(rectangle);
		}
		return vBox;
	}

	static List<HBox> createClickableTrains() throws SQLException {
		List<HBox> rectangleList = new ArrayList<>();
		new Trains();
		for (int i = 0; i < 18; i++) {
			HBox rectangle = new HBox();
			rectangle.setMinHeight(150);
			rectangle.setMinWidth(1300);
			rectangle.setBackground(new Background(new BackgroundFill(color,
					CornerRadii.EMPTY, Insets.EMPTY)));
			Label hotel_name = new Label();
			hotel_name.setMinHeight(75);
			hotel_name.setFont(Font.font("Tahoma", 20));
			hotel_name.setText(Trains.train_number[i]);
			rectangle.setOnMouseEntered(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color1,
						CornerRadii.EMPTY, Insets.EMPTY)));
				hotel_name.setUnderline(true);
			});
			rectangle.setOnMouseExited(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color,
						CornerRadii.EMPTY, Insets.EMPTY)));
				hotel_name.setUnderline(false);
			});
			InputStream stream = null;
			try {
				stream = new FileInputStream(Trains.train_image[i]);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println(e);
			}
			Image image = new Image(stream);
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setFitHeight(150);
			imageView.setFitWidth(150);
			Rectangle rectV1 = new Rectangle(5, 160);
			rectV1.setFill(color);
			Rectangle rectV2 = new Rectangle(5, 160);
			rectV2.setFill(color);
			VBox vBox1 = new VBox();
			Rectangle rectH1 = new Rectangle(150, 5);
			rectH1.setFill(color);
			Rectangle rectH2 = new Rectangle(150, 5);
			rectH2.setFill(color);
			vBox1.setMinHeight(140);
			vBox1.setMinWidth(140);
			vBox1.getChildren().add(rectH1);
			vBox1.getChildren().add(imageView);
			vBox1.getChildren().add(rectH2);
			VBox vBox = new VBox();
			vBox.setMinWidth(300);
			vBox.setMinHeight(150);
			vBox.setPadding(new Insets(0, 0, 0, 10));
			vBox.getChildren().add(hotel_name);
			Label s = new Label();
			s.setText("Origin: "+Trains.origin[i] + "      Destination: " + Trains.destination[i]);
			s.setMinWidth(400);
			s.setPrefHeight(30);
			Label s1 = new Label();
			s1.setText("Departure: "+Trains.departure_time[i] + "      Arrival: " + Trains.arrival_time[i]);
			s1.setMinWidth(400);
			s1.setPrefHeight(30);
			Label s2 = new Label();
			s2.setText("No of Available Seats: "+ Trains.seats_remaining[i]);
			s2.setPadding(new Insets(0,0,0,15));
			s2.setMinWidth(400);
			s2.setPrefHeight(30);
			vBox.getChildren().add(s);
			vBox.getChildren().add(s1);
			vBox.getChildren().add(s2);
			Label train_price = new Label();
			train_price.setFont(Font.font("Tahoma", 15));
			train_price.setText("Rs " + Trains.cost_to_travel[i]);
			StackPane pane = new StackPane();
			pane.setMinHeight(150);
			pane.setMinWidth(300);
			pane.setMaxHeight(150);
			pane.setMaxWidth(300);
			VBox spacer = new VBox();
			spacer.setMinHeight(150);
			spacer.setMinWidth(200);
			pane.getChildren().add(train_price);
			rectangle.getChildren().add(rectV1);
			rectangle.getChildren().add(vBox1);
			rectangle.getChildren().add(rectV2);
			rectangle.getChildren().add(vBox);
			rectangle.getChildren().add(spacer);
			rectangle.getChildren().add(pane);
			int finalI = i;
			int finalI1 = i;
			rectangle.setOnMouseClicked(event -> {
				TrainsUI.train_id = Trains.train_number[finalI];
				Parent root = null;
				try
				{
					root = FXMLLoader.load(Sprites.class.getResource("TrainsUI.fxml"));
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
				stage.setTitle(Trains.train_number[finalI]);
				stage.setMinWidth(1000);
				stage.setMinHeight(700);
				stage.show();
				Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
				stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
				stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
			});
			rectangleList.add(rectangle);
		}
		return rectangleList;
	}
	static VBox createLog()
	{
		VBox vBox = new VBox();
		List<HBox> rectangleList = null;
		try
		{
			rectangleList = createClickableLog();
		}
		catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		for (HBox rectangle : rectangleList)
		{
			Rectangle border = new Rectangle(1300, 1);
			border.setFill(Color.BLACK);
			vBox.getChildren().add(border);
			vBox.getChildren().add(rectangle);
		}
		return vBox;
	}
	static List<HBox> createClickableLog() throws SQLException
	{
		List<HBox> rectangleList = new ArrayList<>();
		new OrderedHotels();
		for(int u = 0; u<OrderedHotels.no;u++)
		{
			HBox rectangle = new HBox();
			rectangle.setMinHeight(75);
			rectangle.setMinWidth(1300);
			rectangle.setBackground(new Background(new BackgroundFill(color,
					CornerRadii.EMPTY, Insets.EMPTY)));
			rectangle.setOnMouseEntered(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color1,
						CornerRadii.EMPTY, Insets.EMPTY)));
			});
			rectangle.setOnMouseExited(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color,
						CornerRadii.EMPTY, Insets.EMPTY)));
			});
			Label o_hotel_name = new Label();
			Label o_no_of_days = new Label();
			Label total_payment = new Label();
			Label rooms = new Label();
			o_hotel_name.setMinHeight(50);
			o_no_of_days.setMinHeight(50);
			total_payment.setMinHeight(50);
			rooms.setMinHeight(50);
			rectangle.setSpacing(198);
			try {
				String hotel_Name = ConnectionClass.getHotelNameById(OrderedHotels.hotel_id[u]);
				o_hotel_name.setText("Hotel Name: " + hotel_Name);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			o_no_of_days.setText("No. Of Days: " + OrderedHotels.no_of_days[u]);
			total_payment.setText("Billed Amount: "+ OrderedHotels.total_payment[u]);
			rooms.setText("Room No.: "+OrderedHotels.room_no[u]);
			rectangle.getChildren().add(o_hotel_name);
			rectangle.getChildren().add(o_no_of_days);
			rectangle.getChildren().add(rooms);
			rectangle.getChildren().add(total_payment);
			rectangleList.add(rectangle);
		}
		new OrderedFlights();
		for(int u = 0; u<OrderedFlights.airline_name.size();u++)
		{
			HBox rectangle = new HBox();
			rectangle.setMinHeight(75);
			rectangle.setMinWidth(1300);
			rectangle.setBackground(new Background(new BackgroundFill(color,
					CornerRadii.EMPTY, Insets.EMPTY)));
			rectangle.setOnMouseEntered(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color1,
						CornerRadii.EMPTY, Insets.EMPTY)));
			});
			rectangle.setOnMouseExited(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color,
						CornerRadii.EMPTY, Insets.EMPTY)));
			});
			Label airline_name = new Label();
			Label flight_id = new Label();
			Label origin = new Label();
			Label dest= new Label();
			Label arrival= new Label();
			Label departure = new Label();
			Label seat_no= new Label();
			Label total_payment= new Label();
			airline_name.setMinHeight(50);
			flight_id.setMinHeight(50);
			origin.setMinHeight(50);
			dest.setMinHeight(50);
			arrival.setMinHeight(50);
			departure.setMinHeight(50);
			seat_no.setMinHeight(50);
			total_payment.setMinHeight(50);
			rectangle.setSpacing(45);
			airline_name.setText("Airline: " + OrderedFlights.airline_name.get(u));
			flight_id.setText("Flight No.: " + OrderedFlights.flight_id.get(u));
			origin.setText("Source: " + OrderedFlights.origin.get(u));
			dest.setText("Destination: " + OrderedFlights.dest.get(u));
			arrival.setText("Arrival: " + OrderedFlights.arrival_time.get(u));
			departure.setText("Departure: " + OrderedFlights.departure_time.get(u));
			seat_no.setText("Seat No: " + OrderedFlights.seat_no.get(u));
			total_payment.setText("Billed Amount: " + OrderedFlights.final_price.get(u));
			rectangle.getChildren().add(airline_name);
			rectangle.getChildren().add(flight_id);
			rectangle.getChildren().add(origin);
			rectangle.getChildren().add(dest);
			rectangle.getChildren().add(arrival);
			rectangle.getChildren().add(departure);
			rectangle.getChildren().add(seat_no);
			rectangle.getChildren().add(total_payment);
			rectangleList.add(rectangle);
		}
		new OrderedTrains();
		for(int u = 0; u<OrderedTrains.train_id.size();u++)
		{
			HBox rectangle = new HBox();
			rectangle.setMinHeight(75);
			rectangle.setMinWidth(1300);
			rectangle.setBackground(new Background(new BackgroundFill(color,
					CornerRadii.EMPTY, Insets.EMPTY)));
			rectangle.setOnMouseEntered(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color1,
						CornerRadii.EMPTY, Insets.EMPTY)));
			});
			rectangle.setOnMouseExited(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color,
						CornerRadii.EMPTY, Insets.EMPTY)));
			});
			Label train_id = new Label();
			Label origin = new Label();
			Label dest= new Label();
			Label arrival_day= new Label();
			Label departure = new Label();
			Label seat_no= new Label();
			Label total_payment= new Label();
			train_id.setMinHeight(50);
			origin.setMinHeight(50);
			dest.setMinHeight(50);
			arrival_day.setMinHeight(50);
			departure.setMinHeight(50);
			seat_no.setMinHeight(50);
			total_payment.setMinHeight(50);
			rectangle.setSpacing(67);
			train_id.setText("Train No.: " + OrderedTrains.train_id.get(u));
			origin.setText("Source: " + OrderedTrains.origin.get(u));
			dest.setText("Destination: " + OrderedTrains.dest.get(u));
			arrival_day.setText("Arrival Day: " + OrderedTrains.arrival_day.get(u));
			departure.setText("Departure: " + OrderedTrains.departure_time.get(u));
			seat_no.setText("Seat No: " + OrderedTrains.seat_no.get(u));
			total_payment.setText("Billed Amount: " + OrderedTrains.final_price.get(u));
			rectangle.getChildren().add(train_id);
			rectangle.getChildren().add(origin);
			rectangle.getChildren().add(dest);
			rectangle.getChildren().add(arrival_day);
			rectangle.getChildren().add(departure);
			rectangle.getChildren().add(seat_no);
			rectangle.getChildren().add(total_payment);
			rectangleList.add(rectangle);
		}
		return rectangleList;
	}
}

