package com.eronalves.simplemusicplayer;

import java.io.File;
import java.util.function.Consumer;

import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class DirectoryPickup {

  final private Stage stage;
  final private DirectoryChooser chooser;

  public DirectoryPickup (Stage stage) {
    this.stage = stage;
    this.chooser = new DirectoryChooser();
  }

  public Button render (
      String label,
      Consumer<File> handler
  ) {
    Button button = new Button(label);
    button.setOnAction(e -> {
      File dir = chooser.showDialog(stage);
      handler.accept(dir);
    });
    return button;
  }

}
