package org.homework.gameObjects;

import org.homework.Vector;

import java.awt.*;
import java.util.Random;
import static org.homework.Constants.*;

public class Apple implements GameObject {
  private static final Random random = new Random();
  private final Vector pos;
  private final int width;
  private final int height;

  public Apple(int width, int height, int gamePoleWidth, int gamePoleHeight) {
    this.width = width;
    this.height = height;

    pos = new Vector(Math.min(POLE_SIZE - CELL_SIZE, Math.max(0, random.nextInt(0, gamePoleWidth / width) * width)),
            Math.min(POLE_SIZE - CELL_SIZE, Math.max(0, random.nextInt(0, gamePoleHeight / height) * height)));
  }

  @Override
  public Vector getPos() {
    return pos;
  }

  @Override
  public void render(Graphics graphics, Object sender) {
    graphics.setColor(Color.RED);
    graphics.fillRect(pos.x(), pos.y(), width, height);
    graphics.setColor(new Color(139, 69, 19));
    graphics.fillRect(pos.x() + 2 * CELL_SIZE / 5, pos.y() - CELL_SIZE / 3, CELL_SIZE / 5, CELL_SIZE / 2);
    graphics.setColor(Color.BLACK);
  }
}
