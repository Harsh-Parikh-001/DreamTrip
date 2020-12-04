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

public class FlightsUI implements Initializable
{
    static String flight_id;
    static ResultSet rs;
    static String flight_image;
    static String airline_name;
    static String origin;
    static String dest;
    static String flight_price;
    static String arrival_time;
    static String departure_time;
    static String seat_no;
    static String class_travel;
    static int e;
    static String j;
    static float ty;
    static float finalPayment;
    static Vector <String> airline_seats = new Vector<>();
    static Vector <String> airline_seat_types = new Vector<>();

    @FXML
    StackPane sp;

    @FXML
    Label back_l;

    @FXML
    Label name_of_flight;

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
            Nav.cond = 2;
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
        try
        {
            rs = ConnectionClass.getFlightsWhere(flight_id);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            while(rs.next())
            {
                airline_name = rs.getString("Airlines");
                flight_image= rs.getString("Flight_img");
                origin = rs.getString("Origin");
                dest = rs.getString("Destination");
                arrival_time = rs.getString("Arrival_time");
                departure_time = rs.getString("Departure_time");
                flight_price = rs.getString("Fare");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try
        {
            rs = ConnectionClass.getFlightSeatsWhere(flight_id);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            while(rs.next()) {
                airline_seats.add(rs.getString("no_of_seats"));
                airline_seat_types.add(rs.getString("seat_type"));
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        InputStream stream = null;
        try {
            stream = new FileInputStream(flight_image);
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
                ConnectionClass.insertFlightOrder(LoginPage.customer_id, flight_id, airline_name, class_travel,origin, dest, arrival_time, departure_time, seat_no,finalPayment);
                ConnectionClass.deleteFlightOrder(flight_id, seat_no);
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
            Scene scene = new Scene(root,1300,600);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle("Bill for Flights");
            stage.show();

        });
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setImage(image);
        sp.getChildren().add(imageView);
        sp.setAlignment(imageView, Pos.CENTER);
        name_of_flight.setText(airline_name + "(Flight Number: "  + flight_id + ")");
        name_of_flight.setFont(Font.font(40));
        desc.setText("Source: "+ origin + "   Destination: "+ dest);
        desc.setFont(Font.font(20));
        Fac.setText("Arrival Time: " + arrival_time + "   Departure Time: " + departure_time);
        Fac.setFont(Font.font(20));
        Label l13 = new Label();
        l13.setText("Choose Seat No and Class: ");
        l13.setFont(Font.font(20));
        ChoiceBox c = new ChoiceBox();
        c.setPrefHeight(30);
        c.setPrefWidth(300);
        c.setMaxWidth(300);
        c.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2)
            {
                j = (String) c.getItems().get((Integer) number2);
                seat_no = j.substring(4,7);
                e = Integer.parseInt(String.valueOf(j.charAt(0)));
                if (airline_seat_types.get(e).equals("Economy"))
                {
                    ty = 1;
                }
                if (airline_seat_types.get(e).equals("Business")) {
                    ty = 1.25f;
                }
                if (airline_seat_types.get(e).equals("First"))
                {
                    ty = 1.5f;
                }
                if (j != null )
                {
                    finalPayment = Float.parseFloat(flight_price)*ty;
                    payment.setText("Rs "+ finalPayment);
                }
            }
        });
        int t = 0;
        for (; t<airline_seats.size(); t++)
        {
            c.getItems().add(t+" . "+airline_seats.get(t)+"- ("+airline_seat_types.get(t)+" Class)");
        }
        l13.setPadding(new Insets(0,10,0,0));
        l13.setMinHeight(20);
        spc.getChildren().add(l13);
        c.setPadding(new Insets(0,0,0,10));
        c.setMinHeight(20);
        spc.getChildren().add(c);
    }
}
