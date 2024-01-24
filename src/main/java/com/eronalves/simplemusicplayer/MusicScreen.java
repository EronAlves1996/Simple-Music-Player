package com.eronalves.simplemusicplayer;

import java.io.File;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Callback;

public class MusicScreen {

  private SceneControls sceneControls;
  private File directory;
  private File selectedMusic;
  private MediaPlayer mediaPlayer;
  private Timer poller;

  public MusicScreen (
      SceneControls sceneControls,
      File directory
  ) {
    this.directory = directory;
    this.sceneControls = sceneControls;
  }

  public void render () {
    File[] musicFiles = directory.listFiles(f -> f.getName().endsWith(".mp3"));

    var musicList = createMusicList(musicFiles);
    var musicTrack = new MusicTrack(0.0);

    musicList.getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (
                ov,
                o,
                n
            ) -> {
              selectedMusic = n;
            }
        );

    var musicButtons = createMusicControls( () -> {
      if (isEmpty(selectedMusic)) return;

      Media media = new Media(
          Paths.get(selectedMusic.getAbsolutePath()).toUri().toString()
      );

      if (
        isEmpty(mediaPlayer)
            || !mediaPlayer.getMedia().getSource().equals(media.getSource())
      ) {
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnPlaying( () -> {
          musicTrack
              .updateTotalDuration(mediaPlayer.getTotalDuration().toMillis());
          poller = new Timer();

          poller.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run () {
              musicTrack
                  .updateActualMark(mediaPlayer.getCurrentTime().toMillis());
            }
          }, 1000L, 1000L);
        });

        mediaPlayer.setOnPaused( () -> {
          if (poller == null) return;
          poller.purge();
        });

        mediaPlayer.setOnStopped( () -> {
          if (poller == null) return;
          musicTrack.reset();
          poller.purge();
        });

        mediaPlayer.setOnReady( () -> {
          musicTrack
              .updateTotalDuration(mediaPlayer.getTotalDuration().toMillis());
        });
      }

      mediaPlayer.play();
    }, () -> {
      if (isEmpty(mediaPlayer)) return;
      mediaPlayer.pause();
    }, () -> {
      if (isEmpty(mediaPlayer)) return;
      mediaPlayer.stop();
    });

    var vbox = new VBox(musicList, musicTrack.render(), musicButtons);
    vbox.setFillWidth(true);

    sceneControls.setScene.accept(new Scene(vbox, 640, 480));
  }

  private boolean isEmpty (Object o) {
    return o == null;
  }

  private ListView<File> createMusicList (File[] musicFiles) {
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
    return listView;
  }

  private HBox createMusicControls (
      Runnable onPlay,
      Runnable onPause,
      Runnable onStop
  ) {
    var playButton = new Button("PLAY");
    playButton.setOnAction( (e) -> onPlay.run());
    var pauseButton = new Button("PAUSE");
    pauseButton.setOnAction(e -> onPause.run());
    var stopButton = new Button("STOP");
    stopButton.setOnAction(e -> onStop.run());

    return new HBox(pauseButton, playButton, stopButton);
  }

}
