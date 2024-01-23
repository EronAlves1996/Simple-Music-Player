package com.eronalves.simplemusicplayer;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class MusicScreen {

  private SceneControls sceneControls;
  private File directory;

  public MusicScreen (
      SceneControls sceneControls,
      File directory
  ) {
    this.directory = directory;
    this.sceneControls = sceneControls;
  }

  public void render () {
    File[] musicFiles = directory.listFiles(f -> f.getName().endsWith(".mp3"));
    ListView<File> listView = new ListView<>();

    listView.setItems(FXCollections.observableArrayList(musicFiles));
    ObjectProperty<Callback<ListView<File>, ListCell<File>>> cellFactory =
        listView.cellFactoryProperty();

    cellFactory.set(view -> new ListCell<>() {
      @Override
      protected void updateItem (
          File item,
          boolean empty
      ) {
        if (item != null) {
          super.updateItem(item, empty);
          setText(item.getName());
        }
      }
    });
    listView.setOrientation(Orientation.VERTICAL);
    var vbox = new VBox(listView);

    sceneControls.setScene.accept(new Scene(vbox, 640, 480));
  }

}
