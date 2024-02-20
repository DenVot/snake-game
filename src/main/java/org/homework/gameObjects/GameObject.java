package org.homework.gameObjects;

import org.homework.Vector;

import java.awt.*;

import static org.homework.Constants.CELL_SIZE;

public interface GameObject {
  Vector getPos();
  void render(Graphics graphics, Object sender);

  static boolean areIntersected(GameObject a, GameObject b) {
    return Math.abs(a.getPos().x() - b.getPos().x()) < CELL_SIZE &&
            Math.abs(a.getPos().y() - b.getPos().y()) < CELL_SIZE;
  }
}
