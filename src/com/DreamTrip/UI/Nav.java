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
	Label cart_id;

	Color color1 = Color.web("#A9A9A9");
	Color color = Color.web("#D3D3D3");
	Stop[] stops = new Stop[]{new Stop(0, color), new Stop(1, Color.WHITE)};
	LinearGradient linear = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VBox vBox = new VBox();
		List<HBox> rectangleList = createClickableHotels();
		for (HBox rectangle : rectangleList) {
			Rectangle border = new Rectangle(1300, 1);
			border.setFill(Color.BLACK);
			vBox.getChildren().add(border);
			vBox.getChildren().add(rectangle);
		}
		flight_id.setOnMouseClicked(event -> {
			flight_id.setText("Flights >");
			scrollPane.setContent(createFlights());
			train_id.setText("Trains");
			hotel_id.setText("Hotels");
			cart_id.setText("Cart");
		});
		train_id.setOnMouseClicked(event -> {
			train_id.setText("Trains >");
			scrollPane.setContent(createTrains());
			flight_id.setText("Flights");
			hotel_id.setText("Hotels");
			cart_id.setText("Cart");
		});
		hotel_id.setOnMouseClicked(event -> {
			hotel_id.setText("Hotels >");
			scrollPane.setContent(createHotels());
			flight_id.setText("Flights");
			train_id.setText("Trains");
			cart_id.setText("Cart");
		});
		cart_id.setOnMouseClicked(event -> {
			hotel_id.setText("Hotels");
			scrollPane.setContent(createCart());
			flight_id.setText("Flights");
			train_id.setText("Trains");
			cart_id.setText("Cart >");
		});
		scrollPane.setContent(vBox);
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

	private VBox createHotels() {
		VBox vBox = new VBox();
		List<HBox> rectangleList = createClickableHotels();
		for (HBox rectangle : rectangleList) {
			Rectangle border = new Rectangle(1300, 1);
			border.setFill(Color.BLACK);
			vBox.getChildren().add(border);
			vBox.getChildren().add(rectangle);
		}
		return vBox;
	}
	private List<HBox> createClickableHotels() {
		List<HBox> rectangleList = new ArrayList<>();
		Hotel hotel = new Hotel();
		for (int i = 0; i < Hotel.no; i++) {
			HBox rectangle = new HBox();
			;
			rectangle.setMinHeight(150);
			rectangle.setMinWidth(1300);
			rectangle.setBackground(new Background(new BackgroundFill(color,
					CornerRadii.EMPTY, Insets.EMPTY)));
			Label hotel_name = new Label();
			Label hotel_review = new Label();
			hotel_review.setMinHeight(75);
			hotel_review.setText(Hotel.hotel_rating[i] + "☆" + Hotel.no_of_re[i]);
			hotel_name.setMinHeight(75);
			hotel_name.setFont(Font.font("Tahoma", 20));
			hotel_name.setText(Hotel.hotel_name[i] + " (" + Hotel.dest[i] + ")");
			rectangle.setOnMouseEntered(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color1,
						CornerRadii.EMPTY, Insets.EMPTY)));
				hotel_name.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
			});
			rectangle.setOnMouseExited(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color,
						CornerRadii.EMPTY, Insets.EMPTY)));
				hotel_name.setFont(Font.font("Tahoma", 20));
			});
			InputStream stream = null;
			try {
				stream = new FileInputStream(Hotel.hotel_image[i]);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println(e);
			}
			Image image = new Image(stream);
			ImageView imageView = new ImageView();
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
			vBox.setPadding(new Insets(0, 0, 0, 10));
			vBox.getChildren().add(hotel_name);
			vBox.getChildren().add(hotel_review);
			Button cart = new Button();
			cart.setText("Add to Cart");
			StackPane pane = new StackPane();
			pane.setMinHeight(150);
			pane.setMinWidth(300);
			pane.setMaxHeight(150);
			pane.setMaxWidth(300);
			pane.getChildren().add(cart);
			pane.setAlignment(cart, Pos.BOTTOM_RIGHT);
			rectangle.getChildren().add(rectV1);
			rectangle.getChildren().add(vBox1);
			rectangle.getChildren().add(rectV2);
			rectangle.getChildren().add(vBox);
			rectangle.getChildren().add(pane);
			rectangleList.add(rectangle);
		}
		return rectangleList;
	}

	private VBox createFlights() {
		VBox vBox = new VBox();
		List<HBox> rectangleList = createClickableFlights();
		for (HBox rectangle : rectangleList) {
			Rectangle border = new Rectangle(1300, 1);
			border.setFill(Color.BLACK);
			vBox.getChildren().add(border);
			vBox.getChildren().add(rectangle);
		}
		return vBox;
	}

	private List<HBox> createClickableFlights() {
		List<HBox> rectangleList = new ArrayList<>();
		Flights flights = new Flights();
		for (int i = 0; i < Flights.no; i++) {
			HBox rectangle = new HBox();
			;
			rectangle.setMinHeight(150);
			rectangle.setMinWidth(1300);
			rectangle.setBackground(new Background(new BackgroundFill(color,
					CornerRadii.EMPTY, Insets.EMPTY)));
			Label hotel_name = new Label();
			Label hotel_review = new Label();
			hotel_review.setMinHeight(75);
			hotel_review.setText(Flights.hotel_rating[i] + "☆" + Flights.no_of_re[i]);
			hotel_name.setMinHeight(75);
			hotel_name.setFont(Font.font("Tahoma", 20));
			hotel_name.setText(Flights.hotel_name[i] + " (" + Flights.dest[i] + ")");
			rectangle.setOnMouseEntered(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color1,
						CornerRadii.EMPTY, Insets.EMPTY)));
				hotel_name.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
			});
			rectangle.setOnMouseExited(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color,
						CornerRadii.EMPTY, Insets.EMPTY)));
				hotel_name.setFont(Font.font("Tahoma", 20));
			});
			InputStream stream = null;
			try {
				stream = new FileInputStream(Flights.hotel_image[i]);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println(e);
			}
			Image image = new Image(stream);
			ImageView imageView = new ImageView();
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
			vBox.setPadding(new Insets(0, 0, 0, 10));
			vBox.getChildren().add(hotel_name);
			vBox.getChildren().add(hotel_review);
			Button cart = new Button();
			cart.setText("Add to Cart");
			StackPane pane = new StackPane();
			pane.setMinHeight(150);
			pane.setMinWidth(300);
			pane.setMaxHeight(150);
			pane.setMaxWidth(300);
			pane.getChildren().add(cart);
			pane.setAlignment(cart, Pos.BOTTOM_RIGHT);
			rectangle.getChildren().add(rectV1);
			rectangle.getChildren().add(vBox1);
			rectangle.getChildren().add(rectV2);
			rectangle.getChildren().add(vBox);
			rectangle.getChildren().add(pane);
			rectangleList.add(rectangle);
		}
		return rectangleList;
	}

	private VBox createTrains() {
		VBox vBox = new VBox();
		List<HBox> rectangleList = createClickableTrains();
		for (HBox rectangle : rectangleList) {
			Rectangle border = new Rectangle(1300, 1);
			border.setFill(Color.BLACK);
			vBox.getChildren().add(border);
			vBox.getChildren().add(rectangle);
		}
		return vBox;
	}

	private List<HBox> createClickableTrains() {
		List<HBox> rectangleList = new ArrayList<>();
		Trains trains = new Trains();
		for (int i = 0; i < Flights.no; i++) {
			HBox rectangle = new HBox();
			;
			rectangle.setMinHeight(150);
			rectangle.setMinWidth(1300);
			rectangle.setBackground(new Background(new BackgroundFill(color,
					CornerRadii.EMPTY, Insets.EMPTY)));
			Label hotel_name = new Label();
			Label hotel_review = new Label();
			hotel_review.setMinHeight(75);
			hotel_review.setText(Trains.hotel_rating[i] + "☆" + Trains.no_of_re[i]);
			hotel_name.setMinHeight(75);
			hotel_name.setFont(Font.font("Tahoma", 20));
			hotel_name.setText(Trains.hotel_name[i] + " (" + Trains.dest[i] + ")");
			rectangle.setOnMouseEntered(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color1,
						CornerRadii.EMPTY, Insets.EMPTY)));
				hotel_name.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
			});
			rectangle.setOnMouseExited(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color,
						CornerRadii.EMPTY, Insets.EMPTY)));
				hotel_name.setFont(Font.font("Tahoma", 20));
			});
			InputStream stream = null;
			try {
				stream = new FileInputStream(Trains.hotel_image[i]);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println(e);
			}
			Image image = new Image(stream);
			ImageView imageView = new ImageView();
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
			vBox.setPadding(new Insets(0, 0, 0, 10));
			vBox.getChildren().add(hotel_name);
			vBox.getChildren().add(hotel_review);
			Button cart = new Button();
			cart.setText("Add to Cart");
			StackPane pane = new StackPane();
			pane.setMinHeight(150);
			pane.setMinWidth(300);
			pane.setMaxHeight(150);
			pane.setMaxWidth(300);
			pane.getChildren().add(cart);
			pane.setAlignment(cart, Pos.BOTTOM_RIGHT);
			rectangle.getChildren().add(rectV1);
			rectangle.getChildren().add(vBox1);
			rectangle.getChildren().add(rectV2);
			rectangle.getChildren().add(vBox);
			rectangle.getChildren().add(pane);
			rectangleList.add(rectangle);
		}
		return rectangleList;
	}

	private VBox createCart()
	{
		VBox vBox = new VBox();
		List<HBox> rectangleList = createClickableCart();
		for (HBox rectangle : rectangleList) {
			Rectangle border = new Rectangle(1300, 1);
			border.setFill(Color.BLACK);
			vBox.getChildren().add(border);
			vBox.getChildren().add(rectangle);
		}
		return vBox;
	}
	private List<HBox> createClickableCart() {
		List<HBox> rectangleList = new ArrayList<>();
		Cart cart = new Cart();
		for (int i = 0; i < Cart.no; i++)
		{
			HBox rectangle = new HBox();
			rectangle.setMinHeight(150);
			rectangle.setMinWidth(1300);
			rectangle.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
			Label item_name = new Label();
			Label item_details = new Label();
			item_details.setMinHeight(75);
			item_name.setMinHeight(75);
			item_name.setFont(Font.font("Tahoma", 20));
			item_details.setText(Cart.item_name[i] + " (" + Cart.item_details[i] + ")");
			rectangle.setOnMouseEntered(event ->
			{
				rectangle.setBackground(new Background(new BackgroundFill(color1,
						CornerRadii.EMPTY, Insets.EMPTY)));
				item_name.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
			});
			rectangle.setOnMouseExited(event -> {
				rectangle.setBackground(new Background(new BackgroundFill(color,  CornerRadii.EMPTY, Insets.EMPTY)));
				item_name.setFont(Font.font("Tahoma", 20));
			});
			InputStream stream = null;
			try {
				stream = new FileInputStream(Cart.item_image[i]);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println(e);
			}
			Image image = new Image(stream);
			ImageView imageView = new ImageView();
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
			vBox.setPadding(new Insets(0, 0, 0, 10));
			vBox.getChildren().add(item_name);
			Button delete = new Button();
			delete.setText("Remove from Cart");
			StackPane pane = new StackPane();
			pane.setMinHeight(150);
			pane.setMinWidth(300);
			pane.setMaxHeight(150);
			pane.setMaxWidth(300);
			pane.getChildren().add(delete);
			pane.setAlignment(delete, Pos.BOTTOM_RIGHT);
			rectangle.getChildren().add(rectV1);
			rectangle.getChildren().add(vBox1);
			rectangle.getChildren().add(rectV2);
			rectangle.getChildren().add(vBox);
			rectangle.getChildren().add(pane);
			rectangleList.add(rectangle);
		}
		return rectangleList;
	}
}