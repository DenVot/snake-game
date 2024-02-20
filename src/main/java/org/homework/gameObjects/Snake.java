package org.homework.gameObjects;

import org.homework.Vector;

import java.awt.*;
import java.util.ArrayList;

public class Snake implements GameObject {
  private final int width;
  private final int height;
  private SnakeMovingDirection headMovingDirection = SnakeMovingDirection.NONE;
  private final ArrayList<SnakeBlock> snakeBlocks = new ArrayList<>();

  public Snake(Vector headPosition, int width, int height) {
    this.width = width;
    this.height = height;
    snakeBlocks.add(new HeadSnakeBlock(headPosition, width, height));
  }

  public enum SnakeMovingDirection {
    LEFT, UP, DOWN, RIGHT, NONE
  }

  public SnakeMovingDirection getDirection() {
    return headMovingDirection;
  }

  public void goLeft() {
    if (headMovingDirection == SnakeMovingDirection.RIGHT) {
      return;
    }

    headMovingDirection = SnakeMovingDirection.LEFT;
  }

  public void goRight() {
    if (headMovingDirection == SnakeMovingDirection.LEFT) {
      return;
    }

    headMovingDirection = SnakeMovingDirection.RIGHT;
  }

  public void goUp() {
    if (headMovingDirection == SnakeMovingDirection.DOWN) {
      return;
    }

    headMovingDirection = SnakeMovingDirection.UP;
  }

  public void goDown() {
    if (headMovingDirection == SnakeMovingDirection.UP) {
      return;
    }

    headMovingDirection = SnakeMovingDirection.DOWN;
  }

  @Override
  public Vector getPos() {
   return snakeBlocks.get(0).getPos();
  }

  @Override
  public void render(Graphics graphics, Object sender) {
    for (SnakeBlock snakeBlock : snakeBlocks) {
      snakeBlock.render(graphics, this);
    }
  }

  public void processBlocks() {
    if (headMovingDirection == SnakeMovingDirection.NONE) return;

    var headMovement = switch (headMovingDirection) {
      case LEFT -> new Vector(-width, 0);
      case UP -> new Vector(0, -height);
      case DOWN -> new Vector(0, height);
      case RIGHT -> new Vector(width, 0);
      default -> throw new IllegalStateException("Unexpected value: " + headMovingDirection);
    };

    var point  = snakeBlocks.get(0).getPos();
    snakeBlocks.get(0).move(headMovement);

    for (int i = 1; i < snakeBlocks.size(); i++) {
      var ctxBlock = snakeBlocks.get(i);
      var ctxPoint = ctxBlock.getPos();

      var movement = new Vector(point.x() - ctxPoint.x(), point.y() - ctxPoint.y());
      ctxBlock.move(movement);
      point = ctxPoint;
    }
  }

  public void grow() {
    var lastBlockPos = snakeBlocks.get(snakeBlocks.size() - 1).getPos();
    var growBlock = new SnakeBlock(new Vector(lastBlockPos.x() - width, lastBlockPos.y()), width, height);

    snakeBlocks.add(growBlock);
  }

  public boolean hasIntersectWithItself() {
    if (snakeBlocks.size() <= 2) return false;

    var head = snakeBlocks.get(0);

    for (int i = 2; i < snakeBlocks.size(); i++) {
      if (GameObject.areIntersected(head, snakeBlocks.get(i)) && !snakeBlocks.get(i).isNew()) {
        return true;
      }
    }

    return false;
  }
}
