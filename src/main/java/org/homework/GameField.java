package org.homework;

import org.homework.gameObjects.Apple;
import org.homework.gameObjects.GameObject;
import org.homework.gameObjects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import static org.homework.Constants.*;

public class GameField extends JPanel implements KeyListener, ActionListener {
  private final Snake snake = new Snake(new Vector(0, 0), CELL_SIZE, CELL_SIZE);
  private final ArrayList<GameObject> gameObjects = new ArrayList<>();
  private final Timer timer;
  private Apple apple;
  private int ateApplesCount;
  private boolean isLoose = false;

  public GameField() {
    setFocusable(true);
    addKeyListener(this);

    gameObjects.add(snake);
    deployApple();
    timer = new Timer(200, this);
    timer.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (isLoose) {
      g.drawString("Вы проиграли", CELL_SIZE, CELL_SIZE);
      return;
    }

    for (GameObject gameObject : gameObjects) {
      gameObject.render(g, this);
    }

    g.drawString("Очков: " + ateApplesCount, CELL_SIZE, CELL_SIZE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    snake.processBlocks();

    if (GameObject.areIntersected(apple, snake)) {
      snake.grow();
      deployApple();
      ateApplesCount++;
    }

    var snakeHeadPosX = snake.getPos().x();
    var snakeHeadPosY = snake.getPos().y();

    if (snakeHeadPosX < 0 || snakeHeadPosX >= POLE_SIZE - CELL_SIZE ||
        snakeHeadPosY < 0 || snakeHeadPosY >= POLE_SIZE - CELL_SIZE ||
            snake.hasIntersectWithItself()) {
      loose();
    }

    repaint();
  }

  private void loose() {
    timer.stop();
    isLoose = true;
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case 37 -> snake.goLeft();
      case 38 -> snake.goUp();
      case 39 -> snake.goRight();
      case 40 -> snake.goDown();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  private void deployApple() {
    gameObjects.remove(apple);
    apple = new Apple(CELL_SIZE, CELL_SIZE, POLE_SIZE, POLE_SIZE);
    gameObjects.add(apple);
  }
}
