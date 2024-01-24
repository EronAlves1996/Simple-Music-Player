package com.eronalves.simplemusicplayer;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class MusicTrack {

  private ProgressBar actualMark;
  private Text visibleTrackSeconds;
  private Text totalMusicDuration;
  private double totalDuration;

  public MusicTrack (double totalDuration) {
    this.totalDuration = totalDuration;
    totalMusicDuration = new Text(translateDuration(totalDuration));
    actualMark = new ProgressBar(0.0);
    visibleTrackSeconds = new Text("00:00");
  }

  public HBox render () {
    var container =
        new HBox(visibleTrackSeconds, actualMark, totalMusicDuration);

    actualMark.setMaxWidth(Double.MAX_VALUE);
    container.setMaxWidth(Double.MAX_VALUE);
    HBox.setHgrow(actualMark, Priority.ALWAYS);

    return container;
  }

  public void updateActualMark (double duration) {
    this.actualMark.setProgress(duration / totalDuration);
    this.visibleTrackSeconds.setText(translateDuration(duration));
  }

  public void updateTotalDuration (double duration) {
    totalMusicDuration.setText(translateDuration(totalDuration));
    this.totalDuration = duration;
  }

  public void reset () {
    this.actualMark.setProgress(0.0);
    this.totalDuration = 0.0;
    this.totalMusicDuration.setText(translateDuration(0.0));
    this.visibleTrackSeconds.setText(translateDuration(0.0));
  }

  private static String translateNumber (int number) {
    if (number < 10) return "0" + number;
    return String.valueOf(number);
  }

  private static String translateDuration (double duration) {
    int totalSeconds = (int) duration / 1000;
    int minutes = totalSeconds / 60;
    int seconds = totalSeconds % 60;

    return translateNumber(minutes) + ":" + translateNumber(seconds);
  }

}
