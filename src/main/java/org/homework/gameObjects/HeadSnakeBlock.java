package org.homework.gameObjects;

import org.homework.Vector;

import java.awt.*;

import static org.homework.Constants.CELL_SIZE;

public class HeadSnakeBlock extends SnakeBlock {
  public HeadSnakeBlock(Vector pos, int width, int height) {
    super(pos, width, height);
  }

  @Override
  public void render(Graphics graphics, Object sender) {
    super.render(graphics, sender);

    Snake snake = (Snake) sender;
    Snake.SnakeMovingDirection dir = snake.getDirection();

    if (dir == Snake.SnakeMovingDirection.LEFT || dir == Snake.SnakeMovingDirection.RIGHT) {
      graphics.setColor(Color.BLACK);
      graphics.fillRect(getPos().x() + 4 * CELL_SIZE / 9, getPos().y() + CELL_SIZE / 9, CELL_SIZE / 3, CELL_SIZE / 3);
      graphics.fillRect(getPos().x() + 4 * CELL_SIZE / 9, getPos().y() + (2 * CELL_SIZE) / 9 + CELL_SIZE / 3, CELL_SIZE / 3, CELL_SIZE / 3);
    } else {
      graphics.setColor(Color.BLACK);
      graphics.fillRect(getPos().x()  + CELL_SIZE / 9, getPos().y() + 4 * CELL_SIZE / 9, CELL_SIZE / 3, CELL_SIZE / 3);
      graphics.fillRect(getPos().x() + (2 * CELL_SIZE) / 9 + CELL_SIZE / 3, getPos().y() + 4 * CELL_SIZE / 9, CELL_SIZE / 3, CELL_SIZE / 3);
    }
  }
}
