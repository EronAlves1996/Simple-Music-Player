package com.eronalves.simplemusicplayer;

import java.util.function.Consumer;

import javafx.scene.Scene;

public class SceneControls {

  final public Consumer<Scene> setScene;
  final public Consumer<String> setScreenTitle;

  public SceneControls (
      Consumer<Scene> setScene,
      Consumer<String> setScreenTitle
  ) {
    super();
    this.setScene = setScene;
    this.setScreenTitle = setScreenTitle;
  }

}
