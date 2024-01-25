package com.eronalves.simplemusicplayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

  private static Stage stage;

  @Override
  public void start (Stage stage) {
    App.stage = stage;
    InitialScreen initialScreen = getInitialScreen();
    initialScreen.render();
    stage.show();
    stage.setOnCloseRequest(e -> {
      try {
        this.stop();
        Platform.exit();
      } catch (Exception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    });
  }

  public static InitialScreen getInitialScreen () {
    return new InitialScreen(
        new SceneControls(App.stage::setScene, App.stage::setTitle),
        new DirectoryPickup(App.stage)
    );
  }

  public static void main (String[] args) {
    launch();
  }

}
