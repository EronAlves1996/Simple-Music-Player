package com.eronalves.simplemusicplayer;

import java.io.File;
import java.util.function.Consumer;

import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class DirectoryPickup {

  final private DirectoryChooser chooser;
  final private String label;
  final private Consumer<File> handler;

  public DirectoryPickup (
      String label,
      Consumer<File> handler
  ) {
    this.chooser = new DirectoryChooser();
    this.label = label;
    this.handler = handler;
  }

  public Button render (Stage stage) {
    Button button = new Button(label);
    button.setOnAction(e -> {
      File dir = chooser.showDialog(stage);
      handler.accept(dir);
    });
    return button;
  }

}
