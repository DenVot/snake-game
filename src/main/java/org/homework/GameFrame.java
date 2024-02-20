package org.homework;

import javax.swing.*;
import java.awt.*;

import static org.homework.Constants.*;

public class GameFrame extends JFrame {
  public GameFrame() throws HeadlessException {
    setBounds(200, 200, POLE_SIZE, POLE_SIZE);
    add(new GameField());
  }

  public void startGame() {
    setVisible(true);
  }
}