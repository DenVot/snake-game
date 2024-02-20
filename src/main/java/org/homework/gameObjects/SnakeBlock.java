package org.homework.gameObjects;

import org.homework.Vector;

import java.awt.*;

public class SnakeBlock implements GameObject {
  private Vector pos;
  private final int width;
  private final int height;
  private boolean isNewBlock = true;

  public SnakeBlock(Vector pos, int width, int height) {
    this.pos = pos;
    this.width = width;
    this.height = height;
  }

  public Vector getPos() {
    return pos;
  }

  public void move(Vector delta) {
    pos = pos.plus(delta);
    isNewBlock = false;
  }

  @Override
  public void render(Graphics graphics, Object sender) {
    graphics.setColor(Color.GREEN);
    graphics.fillRect(pos.x(), pos.y(), width, height);
    graphics.setColor(Color.BLACK);
    graphics.drawRect(pos.x(), pos.y(), width, height);
  }

  public boolean isNew() {
    return isNewBlock;
  }
}
