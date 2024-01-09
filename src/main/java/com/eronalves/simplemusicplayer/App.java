package com.eronalves.simplemusicplayer;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

  @Override
  public void start (Stage stage) {
    var label = new Label(
        "Welcome to simple Music Player!!\nTo Start, please select a folder to scan musics!"
    );
    label.setAlignment(Pos.CENTER);
    var button = new Button("Choose folder");
    button.setOnMouseClicked(e -> System.out.println("Clicked"));
    FlowPane rootElement =
        new FlowPane(Orientation.VERTICAL, 5.0, 5.0, label, button);
    rootElement.setColumnHalignment(HPos.CENTER);
    rootElement.setAlignment(Pos.CENTER);
    var scene = new Scene(rootElement, 640, 480);
    stage.setScene(scene);
    stage.setTitle("Simple music player");
    stage.show();
  }

  public static void main (String[] args) {
    launch();
  }

}
