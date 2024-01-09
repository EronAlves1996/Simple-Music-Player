package com.eronalves.simplemusicplayer;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class CentralizedLabel extends Label {

  {
    this.setAlignment(Pos.CENTER);
  }

  public CentralizedLabel (String label) {
    super(label);
  }

}
