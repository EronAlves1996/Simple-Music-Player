package com.eronalves.simplemusicplayer;

import java.util.function.Consumer;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;

public class InitialScreen {

  private Consumer<Scene> setScene;
  private Consumer<String> setStageTitle;
  private DirectoryPickup pickup;

  public InitialScreen (
      SceneControls controls,
      DirectoryPickup pickup
  ) {
    this.setScene = controls.setScene;
    this.setStageTitle = controls.setScreenTitle;
    this.pickup = pickup;
  }

  public void render () {
    var label = new CentralizedLabel(
        "Welcome to simple Music Player!!\nTo Start, please select a folder to scan musics!"
    );
    FlowPane rootElement = new FlowPane(
        Orientation.VERTICAL, 5.0, 5.0, label,
        pickup.render("Choose a directory", dir -> {
          new MusicScreen(new SceneControls(setScene, setStageTitle), dir)
              .render();
        })
    );
    rootElement.setColumnHalignment(HPos.CENTER);
    rootElement.setAlignment(Pos.CENTER);
    var scene = new Scene(rootElement, 640, 480);
    setScene.accept(scene);
    setStageTitle.accept("Simple Music Player");
  }

}
