package com.DreamTrip.UI;

import com.DreamTrip.Connectivity.ConnectionClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

public class HotelUI implements Initializable {
	static String hotel;
	static ResultSet rs;
	static String hotel_image;
	static String hotel_name;
	static String hotel_desc;
	static String location1;
	static String hotel_price;
	static String hotel_review;
	static String no_of_review;
	static String faci;
	static Vector <String> rooms = new Vector<>();
	static Vector <String> room_type = new Vector<>();
	static String j;
	static String roomNo;
	static int e;
	static int noOfDays;
	static float finalPayment;
	static float ty;

	@FXML
	StackPane sp;

	@FXML
	Label back_l;

	@FXML
	Label name_of_hotel;

	@FXML
	Label desc;

	@FXML
	Label Fac;

	@FXML
	HBox spc;

	@FXML
	HBox spc1;

	@FXML
	Label payment;

	@FXML
	Label pay_bt;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		back_l.setPadding(new Insets(0,0,0,20));
		back_l.setOnMouseClicked(event -> {
			Parent root = null;
			Nav.cond = 1;
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
		try {
			rs = ConnectionClass.getHotelWhere(hotel);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		try
		{
			while(rs.next())
			{
				hotel_name = rs.getString("Hotel_name");
				hotel_image = rs.getString("Hotel_img");
				hotel_desc = rs.getString("Hotel_desc");
				location1 = rs.getString("Location");
				hotel_price = rs.getString("Fare_per_night");
				hotel_review = rs.getString("Rating");
				no_of_review = rs.getString("No_of_purchase");
				faci = rs.getString("Facilities");
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		try {
			rs = ConnectionClass.getHotelRoomsWhere(hotel);
		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		try {
			rooms.clear();
			room_type.clear();
			while(rs.next()) {
				rooms.add(rs.getString("room_no"));
				room_type.add(rs.getString("type"));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		InputStream stream = null;
		try {
			stream = new FileInputStream(hotel_image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		pay_bt.setDisable(false);
		pay_bt.setOnMouseClicked(event -> {

			Parent root = null;
			try
			{
				root = FXMLLoader.load(getClass().getResource("CheckOut.fxml"));
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
			try
			{
				ConnectionClass.insertHotelOrder(hotel, LoginPage.customer_id, noOfDays, roomNo, finalPayment);
				ConnectionClass.deleteHotelOrder(hotel, roomNo);
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
			Scene scene = new Scene(root,1300,600);
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(true);
			stage.setTitle("Bill for Hotel");
			stage.show();

		});
		Image image = new Image(stream);
		ImageView imageView = new ImageView();
		imageView.setFitHeight(300);
		imageView.setFitWidth(300);
		imageView.setImage(image);
		sp.getChildren().add(imageView);
		sp.setAlignment(imageView, Pos.CENTER);
		name_of_hotel.setText(hotel_name+" ("+location1+") " + hotel_review+" ☆");
		name_of_hotel.setFont(Font.font(40));
		desc.setText(hotel_desc +','+ location1);
		desc.setFont(Font.font(20));
		Fac.setText("Facilities Provided\n" + faci+"\n");
		Fac.setFont(Font.font(20));
		Label l13 = new Label();
		l13.setText("Enter the Number of Days: ");
		l13.setFont(Font.font(20));
		Label l12 = new Label();
		l12.setText("Choose the Room No: ");
		l12.setFont(Font.font(20));
		TextField tf = new TextField();
		tf.setOnMouseExited(event -> {
			String st = tf.getText();
			try
			{
				if (!st.isEmpty() && Integer.parseInt(st)<=15 && Integer.parseInt(st)>0)
				{
					int re = Integer.parseInt(st);
					noOfDays = re;
					finalPayment = Float.parseFloat(hotel_price)*re*ty;
					payment.setText("Rs "+ Float.parseFloat(hotel_price)*re*ty);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		});
		tf.setPrefWidth(300);
		tf.setMaxWidth(300);
		ChoiceBox c = new ChoiceBox();
		c.setPrefHeight(30);
		c.setPrefWidth(300);
		c.setMaxWidth(300);
		c.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
				j = (String) c.getItems().get((Integer) number2);
				roomNo = j.substring(4,8);
				e = Integer.parseInt(String.valueOf(j.charAt(0)));
				if (room_type.get(e).equals("Single")) {
					ty = 1;
				}
				if (room_type.get(e).equals("Double")) {
					ty = 1.5f;
				}
				String st = tf.getText();
				if (!st.isEmpty()  && Integer.parseInt(st)<=15 && Integer.parseInt(st)>0) {
					int re = Integer.parseInt(st);
					noOfDays = re;
					finalPayment = Float.parseFloat(hotel_price)*re*ty;
					payment.setText("Rs "+ Float.parseFloat(hotel_price)*re*ty);
				}
			}
		});
		int t = 0;
		for (; t<rooms.size(); t++) {
			c.getItems().add(t+" . "+rooms.get(t)+"- ("+room_type.get(t)+" Bed)");
		}
		l13.setPadding(new Insets(0,10,0,0));
		l13.setMinHeight(20);
		tf.setPadding(new Insets(0,0,0,10));
		tf.setMinHeight(30);
		spc.getChildren().add(l13);
		spc.getChildren().add(tf);
		c.setPadding(new Insets(0,0,0,10));
		c.setMinHeight(20);
		l12.setPadding(new Insets(0,10,0,0));
		l12.setMinHeight(20);
		spc1.getChildren().add(l12);
		spc1.getChildren().add(c);
	}
}
