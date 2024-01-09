package com.eronalves.simplemusicplayer;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

  private Stage stage;

  @Override
  public void start (Stage stage) {
    this.stage = stage;
    InitialScreen initialScreen = new InitialScreen(
        new SceneControls(this.stage::setScene, this.stage::setTitle),
        new DirectoryPickup(this.stage)
    );
    initialScreen.render();
    stage.show();
  }

  public static void main (String[] args) {
    launch();
  }

}
