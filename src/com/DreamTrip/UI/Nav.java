package com.DreamTrip.UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Nav implements Initializable {
    @FXML
    ScrollPane scrollPane;

    @FXML
    HBox hbox;

    Color color1 = Color.web("#A9A9A9");
    Color color = Color.web("#D3D3D3");
    Stop[] stops = new Stop[] { new Stop(0, color), new Stop(1, Color.WHITE)};
    LinearGradient linear = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox vBox = new VBox();
        List<HBox> rectangleList = createClickableRectangles();
        for(HBox rectangle:rectangleList) {
            Rectangle border = new Rectangle(1430,1);
            border.setFill(Color.BLACK);
            vBox.getChildren().add(border);
            vBox.getChildren().add(rectangle);
        }
        scrollPane.setContent(vBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    private List<HBox> createClickableRectangles() {
        List<HBox> rectangleList = new ArrayList<>();
        Hotel hotel = new Hotel();
        for(int i=0; i<Hotel.no;i++)
        {
            HBox rectangle = new HBox();;
            rectangle.setMinHeight(150);
            rectangle.setMinWidth(1430);
            rectangle.setBackground(new Background(new BackgroundFill(color,
                    CornerRadii.EMPTY, Insets.EMPTY)));
            rectangle.setOnMouseEntered(event -> rectangle.setBackground(new Background(new BackgroundFill(color1,
                    CornerRadii.EMPTY, Insets.EMPTY))));
            rectangle.setOnMouseExited(event -> rectangle.setBackground(new Background(new BackgroundFill(color,
                    CornerRadii.EMPTY, Insets.EMPTY))));
            Label label = new Label();
            label.setMinHeight(70);
            label.setMinWidth(70);
            label.setText(hotel.hotel_name[i]);
            rectangle.getChildren().add(label);
            rectangleList.add(rectangle);
        }
        return rectangleList;
    }
}
