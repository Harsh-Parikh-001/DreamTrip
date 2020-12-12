package com.DreamTrip.UI;

import com.DreamTrip.Connectivity.ConnectionClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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
import javafx.stage.Screen;
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

public class TrainsUI implements Initializable
{
    static String train_id;
    static ResultSet rs;
    static String train_image;
    static String origin;
    static String dest;
    static String train_price;
    static String arrival_day;
    static String departure_time;
    static String seat_no;
    static int e;
    static String j;
    static float ty;
    static float finalPayment;
    static Vector <String> train_seats = new Vector<>();
    static Vector <String> train_seat_types = new Vector<>();

    @FXML
    StackPane sp;

    @FXML
    Label back_l;

    @FXML
    Label name_of_train;

    @FXML
    Label desc;

    @FXML
    Label Fac;

    @FXML
    HBox spc;

    @FXML
    Label payment;

    @FXML
    Label pay_bt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back_l.setPadding(new Insets(0,0,0,20));
        back_l.setOnMouseClicked(event -> {
            Parent root = null;
            Nav.cond = 3;
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
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        });
        try
        {
            rs = ConnectionClass.getTrainWhere(train_id);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            while(rs.next())
            {
                train_image= rs.getString("Train_img");
                origin = rs.getString("Origin");
                dest = rs.getString("Destination");
                arrival_day = rs.getString("Arrival_day");
                departure_time = rs.getString("Arrival_time");
                train_price = rs.getString("Fare");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try
        {
            rs = ConnectionClass.getTrainSeatsWhere(train_id);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try
        {
            train_seats.clear();
            train_seat_types.clear();
            while(rs.next())
            {
                train_seats.add(rs.getString("no_of_seats"));
                train_seat_types.add(rs.getString("seat_type"));
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        InputStream stream = null;
        try
        {
            stream = new FileInputStream(train_image);
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
                ConnectionClass.getTrain();
            }
            catch (ClassNotFoundException | SQLException e)
            {
                e.printStackTrace();
            }
            try
            {
                ConnectionClass.insertTrainOrder(LoginPage.customer_id, train_id, origin, dest,departure_time, arrival_day, seat_no,finalPayment);
                ConnectionClass.deleteTrainOrder(train_id, seat_no);
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
            Scene scene = new Scene(root,1300,600);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle("Bill for Trains");
            stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        });
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setImage(image);
        sp.getChildren().add(imageView);
        sp.setAlignment(imageView, Pos.CENTER);
        name_of_train.setText( "Train Number: "  +  train_id);
        name_of_train.setFont(Font.font(40));
        desc.setText("Source: "+ origin + "   Destination: "+ dest);
        desc.setFont(Font.font(20));
        Fac.setText("Arrival Day: " + arrival_day + "   Departure Time: " + departure_time);
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
                if (train_seat_types.get(e).equals("Sleeper"))
                {
                    ty = 1;
                }
                if (train_seat_types.get(e).equals("3 Tier")) {
                    ty = 1.15f;
                }
                if (train_seat_types.get(e).equals("2 Tier"))
                {
                    ty = 1.35f;
                }
                if (j != null )
                {
                    finalPayment = Float.parseFloat(train_price)*ty;
                    payment.setText("Rs "+ finalPayment);
                }
            }
        });
        int t = 0;
        for (; t<train_seats.size(); t++)
        {
            c.getItems().add(t+" . "+train_seats.get(t)+"- ("+train_seat_types.get(t)+" Class)");
        }
        l13.setPadding(new Insets(0,10,0,0));
        l13.setMinHeight(20);
        spc.getChildren().add(l13);
        c.setPadding(new Insets(0,0,0,10));
        c.setMinHeight(20);
        spc.getChildren().add(c);
    }
}
