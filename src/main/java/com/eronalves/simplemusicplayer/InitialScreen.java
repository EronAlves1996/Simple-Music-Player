package com.eronalves.simplemusicplayer;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class InitialScreen {

  public static void setup (Stage stage) {
    InitialScreen initialScreen = new InitialScreen();
    initialScreen.setupScreen(stage);
  }

  private void setupScreen (Stage stage) {
    var label = new Label(
        "Welcome to simple Music Player!!\nTo Start, please select a folder to scan musics!"
    );
    label.setAlignment(Pos.CENTER);
    DirectoryPickup directoryPickup =
        new DirectoryPickup("Choose folder", dir -> {});
    FlowPane rootElement = new FlowPane(
        Orientation.VERTICAL, 5.0, 5.0, label, directoryPickup.render(stage)
    );
    rootElement.setColumnHalignment(HPos.CENTER);
    rootElement.setAlignment(Pos.CENTER);
    var scene = new Scene(rootElement, 640, 480);
    stage.setScene(scene);
    stage.setTitle("Simple Music Player");
  }

}
