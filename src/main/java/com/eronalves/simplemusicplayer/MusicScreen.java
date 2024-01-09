package com.eronalves.simplemusicplayer;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;

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
    ListView<String> listView = new ListView<>();

    listView.setItems(
        FXCollections.observableArrayList(
            Arrays.stream(musicFiles)
                .map(File::getName)
                .collect(Collectors.toList())
        )
    );

    sceneControls.setScene
        .accept(new Scene(new FlowPane(Orientation.VERTICAL, listView)));
  }

}
